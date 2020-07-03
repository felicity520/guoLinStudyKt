#include <jni.h>
#include <string.h>
#include <stdlib.h>

/***
 * 将传入的String再拼接一个String返回
 * @param env
 * @param this
 * @param jstring1 输入的java层的String（Unicode编码）
 * @return 将C代码的String(UTF编码)返回到java层（Unicode编码），自动转换编码
 * 注意：Get和Release是一一对应的
 */

JNIEXPORT jstring JNICALL
Java_com_gyy_guoLinKt_activity_MainActivity_getStrFromC(
        JNIEnv *env,
        jobject this, jstring jstring1) {

    const char *chars = (*env)->GetStringUTFChars(env, jstring1, NULL);
    //注意：Get之后一定要判空，因为Jni报错了之后还是会继续往下走
    if (chars == NULL)
        return NULL;

    jint jstrLength = (*env)->GetStringUTFLength(env, jstring1);

    char *buff = (char *) malloc(jstrLength * sizeof(char));

    strcpy(buff, chars);
    strcat(buff, "This is a string from C.");

    (*env)->ReleaseStringUTFChars(env, jstring1, chars);

    return (*env)->NewStringUTF(env, buff);
}

