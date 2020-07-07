#include <jni.h>
#include <string.h>
#include <stdlib.h>
#include <android/log.h>

/***
 * 不懂的地方：https://www.cnblogs.com/bxynlbyx/p/11729604.html
 * 参考文章：https://mp.weixin.qq.com/s/nG6cxGVk1tCI9jNjghdw0g
 * 将传入的String再拼接一个String返回
 * @param env
 * @param this
 * @param jstring1 输入的java层的String（Unicode编码）
 * @return 将C代码的String(UTF编码)返回到java层（Unicode编码），自动转换编码
 * 注意：Get和Release是一一对应的
 */

#define TAG "gyytest" // 这个是自定义的LOG的标识
#define logd(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) // 定义LOGD类型


JNIEXPORT jstring JNICALL
Java_com_gyy_guoLinKt_activity_MainActivity_getStrFromC(
        JNIEnv *env,
        jobject this, jstring jstring1) {

    const char *chars = (*env)->GetStringUTFChars(env, jstring1, NULL);
    //注意：Get之后一定要判空，因为Jni报错了之后还是会继续往下走，这样会导致很难找到报错点
    if (chars == NULL)
        return NULL;

    int jstrLength = (*env)->GetStringUTFLength(env, jstring1);
    logd("jstrLength = %d", jstrLength);

    char *buff = (char *) malloc(jstrLength * sizeof(jchar));
    //置空，NULL相当于填入0
    memset(buff, 0, jstrLength);
//    memset(buff, NULL, jstrLength);

    //C中char占一个字节，jchar占两个字节
    logd("jstrLength * sizeof(jchar) = %d", jstrLength * sizeof(jchar));

    strcpy(buff, chars);
    strcat(buff, "This is a string from C.");
    //打印字符串用 %s 单个字符用 %c ,这里是字符串，所有要用%s
    logd("getStrFromC buff = %s\n", buff);

    //GetStringUTFLength: 获取UTF-8编码字符串的长度,就是获取C/C++默认编码字符串的长度.
    (*env)->ReleaseStringUTFChars(env, jstring1, chars);

    //动态申请的内存 必须释放
    free(buff);

    return (*env)->NewStringUTF(env, buff);
}

void correctUtfBytes(char *bytes) {
    char three = 0;
    while (*bytes != '\0') {
        unsigned char utf8 = *(bytes++);
        three = 0;
        logd("utf8 = %c", utf8);
        // Switch on the high four bits.
        switch (utf8 >> 4) {
            case 0x00:
            case 0x01:
            case 0x02:
            case 0x03:
            case 0x04:
            case 0x05:
            case 0x06:
            case 0x07:
                // Bit pattern 0xxx. No need for any extra bytes.
                break;
            case 0x08:
            case 0x09:
            case 0x0a:
            case 0x0b:
            case 0x0f:
                /*
                 * Bit pattern 10xx or 1111, which are illegal start bytes.
                 * Note: 1111 is valid for normal UTF-8, but not the
                 * modified UTF-8 used here.
                 */
                *(bytes - 1) = '?';
                break;
            case 0x0e:
                // Bit pattern 1110, so there are two additional bytes.
                utf8 = *(bytes++);
                if ((utf8 & 0xc0) != 0x80) {
                    --bytes;
                    *(bytes - 1) = '?';
                    break;
                }
                three = 1;
                // Fall through to take care of the final byte.
            case 0x0c:
            case 0x0d:
                // Bit pattern 110x, so there is one additional byte.
                utf8 = *(bytes++);
                if ((utf8 & 0xc0) != 0x80) {
                    --bytes;
                    if (three)--bytes;
                    *(bytes - 1) = '?';
                }
                break;
        }
    }
}

/***
 * int数组求和的方法一（推荐）
 * @param env
 * @param this
 * @param jstring2
 * @return
 */
JNIEXPORT jstring JNICALL
Java_com_gyy_guoLinKt_activity_MainActivity_getStrFromCReg(
        JNIEnv *env,
        jobject this, jstring jstring2) {

    //GetStringLength: 获取Unicode字符串(jstring)的长度
    int strLength = (*env)->GetStringLength(env, jstring2);
    logd("strLength = %d", strLength);

    //C 语言中char占1个字节
//    logd("sizeof(char) = %d", sizeof(char));

    //UTF-8编码的字符串是以\0结尾,而Unicode的不是
//    char *buff = (char *) malloc(strLength * sizeof(char));
//    memset(buff, NULL, strLength);

//int a = 10;
//char b[a];                    //编译不报错
//char b[a] = "jfkd";    //报错，使用变量定义长度时，不可在定义时同时进行初始化赋值，需要在之后进行赋值

//总结：要先定义再赋值
    char buff[strLength];
//    buff[strLength] = "";
    (*env)->GetStringUTFRegion(env, jstring2, 0, strLength, buff);
    for (int i = 0; i < strLength; ++i) {
        logd("getStrFromCReg buff = %c\n", buff[i]);
    }

//    correctUtfBytes(buff);
    return (*env)->NewStringUTF(env, buff);

}

JNIEXPORT jint JNICALL
Java_com_gyy_guoLinKt_activity_MainActivity_sumArray(
        JNIEnv *env,
        jobject this, jintArray jintArray1) {

    jint result = 0;

    jint arrLength = (*env)->GetArrayLength(env, jintArray1);
    jint *jint1 = malloc(arrLength * sizeof(jint));
    logd("arrLength = %d,arrLength * sizeof(jint) = %d", arrLength, arrLength * sizeof(jint));
    //使用GetIntArrayRegion不会对数组进行修改
    (*env)->GetIntArrayRegion(env, jintArray1, 0, arrLength, jint1);

    for (int i = 0; i < arrLength; ++i) {
        result += jint1[i];
    }
    logd("result = %d", result);
    free(jint1);

    return result;
}

/***
 * int数组求和的方法二（不推荐）
 * @param env
 * @param this
 * @param jintArray1
 * @return
 */
JNIEXPORT jint JNICALL
Java_com_gyy_guoLinKt_activity_MainActivity_sumArrayTwo(
        JNIEnv *env,
        jobject this, jintArray jintArray1) {

    jint result = 0;

    jint arrLength = (*env)->GetArrayLength(env, jintArray1);
    logd("sumArrayTwo arrLength = %d", arrLength);
    jint *jint1 = (*env)->GetIntArrayElements(env, jintArray1, NULL);
// 注意：使用GetIntArrayElements可对数组进行动态修改，这里将第一位改成2。
    jint1[0] = 2;
    for (int i = 0; i < arrLength; ++i) {
        result += jint1[i];
    }
    logd("sumArrayTwo result = %d", result);
    (*env)->ReleaseIntArrayElements(env, jintArray1, jint1, 0);

    return result;
}

/***
 * 创建一维数组
 * @param env
 * @param this
 * @param jint1
 * @return
 */
JNIEXPORT jintArray JNICALL
Java_com_gyy_guoLinKt_activity_MainActivity_create1DArray(
        JNIEnv *env,
        jobject this, jint jint1) {

    //1.创建一个整型数组
    jintArray jintArray1 = (*env)->NewIntArray(env, jint1);
    if (jintArray1 == NULL)
        return NULL;

    //2.获取数组指针
    jint *arr = (*env)->GetIntArrayElements(env, jintArray1, NULL);
    //3.赋值
    int i = 0;
    for (; i < jint1; i++) {
        arr[i] = i;
    }
    //4.释放资源
    (*env)->ReleaseIntArrayElements(env, jintArray1, arr, 0);

    return jintArray1;

}

/***
 * 二维数组是作为一个jobjectArray
 * @param env
 * @param this
 * @param jint1
 * @return
 */
JNIEXPORT jobjectArray JNICALL
Java_com_gyy_guoLinKt_activity_MainActivity_create2DArray(
        JNIEnv *env,
        jobject this, jint jint1) {

    //创建一个int[]对象
    jclass jclass1 = (*env)->FindClass(env, "[I");
    if (jclass1 == NULL)
        return NULL;

    jobjectArray jobjectArray1;
    //创建一个jobjectArray
    jobjectArray1 = (*env)->NewObjectArray(env, jint1, jclass1, NULL);

    for (int i = 0; i < jint1; ++i) {
        //1.创建一个整型数组
        jintArray jintArray1 = (*env)->NewIntArray(env, jint1);
        if (jintArray1 == NULL)
            return NULL;

        //2.获取数组指针
        jint *arr = (*env)->GetIntArrayElements(env, jintArray1, NULL);
        //3.赋值
        int j = 0;
        for (; j < jint1; j++) {
            arr[j] = j;
        }
        arr[0] = 8;
        //4.释放资源
        (*env)->ReleaseIntArrayElements(env, jintArray1, arr, 0);

//        5.给jobjectArray1赋值
        (*env)->SetObjectArrayElement(env, jobjectArray1, i, jintArray1);

        //及时移除引用
//        (*env)->DeleteLocalRef(env, jintArray1);
    }

    return jobjectArray1;

}

/***
 * 二维数组是作为一个jobjectArray
 * @param env
 * @param this
 * @param jint1
 * @return
 */
JNIEXPORT jobjectArray JNICALL
Java_com_gyy_guoLinKt_activity_MainActivity_create2DArrayTwo(
        JNIEnv *env,
        jobject this, jint size) {

    //创建一个size*size大小的二维数组

    //jobjectArray是用来装对象数组的   Java数组就是一个对象 int[]
    jclass classIntArray = (*env)->FindClass(env, "[I");
    if (classIntArray == NULL) {
        return NULL;
    }
    //创建一个数组对象,元素为classIntArray
    jobjectArray result = (*env)->NewObjectArray(env, size, classIntArray, NULL);
    if (result == NULL) {
        return NULL;
    }
    for (int i = 0; i < size; ++i) {
        jint buff[100];
        //创建第二维的数组 是第一维数组的一个元素
        jintArray intArr = (*env)->NewIntArray(env, size);
        if (intArr == NULL) {
            return NULL;
        }
        for (int j = 0; j < size; ++j) {
            //这里随便设置一个值
            buff[j] = 666;
        }
        //给一个jintArray设置数据
        (*env)->SetIntArrayRegion(env, intArr, 0, size, buff);
        //给一个jobjectArray设置数据 第i索引,数据位intArr
        (*env)->SetObjectArrayElement(env, result, i, intArr);
        //及时移除引用
        //注意：在JNI中,只要是jobject的子类就属于引用变量,会占用引用表的空间，要及时移除引用
        (*env)->DeleteLocalRef(env, intArr);
    }

    return result;
}

/***
 * native调用java的静态方法
 * @param env
 * @param this
 */
JNIEXPORT void JNICALL
Java_com_gyy_guoLinKt_activity_MainActivity_callJavaStaticMethod(
        JNIEnv *env,
        jobject this) {

    //1. 从classpath路径下搜索MyJNIClass这个类,并返回该类的Class对象
    jclass jclass1 = (*env)->FindClass(env, "com/gyy/guoLinKt/bean/MyJNIClass");
    if (jclass1 == NULL)
        return;

    //2. 从clazz类中查找getDes方法 得到这个静态方法的方法id  (形参参数类型列表)返回值
    jmethodID jmethodId = (*env)->GetStaticMethodID(env, jclass1, "getDes",
                                                    "(Ljava/lang/String;)Ljava/lang/String;");
    if (jmethodId == NULL)
        return;

    //3. 构建入参,调用static方法,获取返回值
    jstring jstring1 = (*env)->NewStringUTF(env, "你好gyy");
    if (jstring1 == NULL)
        return;

    //4.调用静态方法拿到返回值
    jstring jstring2 = (jstring) (*env)->CallStaticObjectMethod(env, jclass1, jmethodId, jstring1);
    if (jstring2 == NULL)
        return;

    //注意：不可直接打印jstring，需要转换成char*类型
    char *result = (char *) ((*env)->GetStringUTFChars(env, jstring2, 0));
    //D/gyytest: result = 传入的字符串长度是 :5  内容是 : 你好gyy
    logd("result = %s", result);

    //5. 移除局部引用(凡是 jobject{} 对象的子类，都需要移除局部引用，否则JVM引用表会溢出)
    //需要我们手动申请和释放内存(new/find->delete,malloc->free)
    (*env)->DeleteLocalRef(env, jclass1);
    (*env)->DeleteLocalRef(env, jstring1);
    (*env)->DeleteLocalRef(env, jstring2);
}

/***
 * native调用Java实例方法
 * @param env
 * @param this
 */
JNIEXPORT void JNICALL
Java_com_gyy_guoLinKt_activity_MainActivity_createAndCallJavaInstanceMethod(
        JNIEnv *env,
        jobject this) {

    //1. 从classpath路径下搜索MyJNIClass这个类,并返回该类的Class对象
    jclass jclass1 = (*env)->FindClass(env, "com/gyy/guoLinKt/bean/MyJNIClass");
    if (jclass1 == NULL)
        return;

    //2.获取构造方法的id。返回值是void 无形参
    jmethodID jmethodId = (*env)->GetMethodID(env, jclass1, "<init>", "()V");

    //3.利用NewObject()函数构建一个Java对象
    jobject jobject1 = (*env)->NewObject(env, jclass1, jmethodId);

    //4.获取setAge和getAge方法: ()V V表示void，形参一定要写()，不可以省略
    jmethodID setAgeId = (*env)->GetMethodID(env, jclass1, "setAge", "(I)V");
    jmethodID getAgeId = (*env)->GetMethodID(env, jclass1, "getAge", "()I");

    //5.设置年龄，返回是void类型的。调用实例方法使用CallXXMethod函数,XX表示返回数据类型
    (*env)->CallVoidMethod(env, jobject1, setAgeId, 12);

    //6.拿到返回的年龄
    jint jint1 = (*env)->CallIntMethod(env, jobject1, getAgeId);
    logd("jint1 = %d", jint1);

    //7.移除局部引用
    (*env)->DeleteLocalRef(env, jclass1);
    (*env)->DeleteLocalRef(env, jobject1);
}







