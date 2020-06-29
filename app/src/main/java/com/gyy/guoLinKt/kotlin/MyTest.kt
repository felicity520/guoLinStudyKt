package com.gyy.guoLinKt.kotlin

import com.gyy.guoLinKt.kotlin.Util.doAction1
import kotlin.math.max

fun main() {
//    KT中的集合
    studyAggregate()
    Singleton.singletonTest()//调用单例的函数，类似于java的static
    val cellphone1 = Cellphone("tom", 123.11)
    val cellphone2 = Cellphone("tom", 123.11)
    println(cellphone1)
    println(cellphone2)
    println("cellphone1 equals cellphone2:" + (cellphone2 == cellphone1))
    //显式声明可变变量a为Int类型
    var a: Int = 10
    //声明不可变变量b为Int类型,使用类型推导得出
    val b: Int = 10
    a = a * 10
    println("最大值为 = " + getLargerNum(10, 13))
    println("最大值为 = " + getLargerNum1(10, 14))
    println("最大值为 = " + getLargerNum2(10, 15))
    println("最大值为 = " + getLargerNum3(10, 16))
    println("最大值为 = " + getLargerNum4(10, 17))
    println("成绩为 = " + getScore("Lily"))
    println("成绩为 = " + getScore1("My"))
    checkNumber(1.5)
    println("成绩为 = " + getScore3("Tom"))
    studyFor()
    //类的数据类型也是val
    val student = Student("123", 5, "Tom", 12).doHomework()
    val student1 = Student("Tom", 15)
    doStudy(student1)
    val student2 = Student()

    studyHanShuAPI()
    studyJavaApi()

//    学习判空
    println("doStudy传入null的参数")
    doStudy(null)

    println("变局变量传入null")
    doStudy3()

    println("变局变量传入null的let")
    doStudy4()

//    传参数时默认从第一个开始，KT引入键值对传参,任意传参组合，无需注意参数顺序
    getParameterDefault(str = "1111111")
    getParameterDefault(str = "1111111", num = 2222)

    studyWith()
    studyWithPlus()
    studyRun()
    studyApply()

    Util.doAction3()

    Util.doAction1();
    doAction1()
//    HelperKt.doSomething()  //在java中这样调用顶层方法
    doSomething()  //在KT中直接调用顶层方法

    fun getResultMsg(result: Result) = when (result) {
        is Success -> result.msg
        is Unknow -> result.msg
        is Failure -> "Error is ${result.error.message}"
    }

    studyFive()

    val xx1: Int = num1AndNum2(100, 80, ::plus)
    val xx2 = num1AndNum2(100, 80, ::minus)

    val xx3: Int = num1AndNum2(100, 280) { n1, n2 ->
        n1 + n2
    }

    println("xx1 is $xx1,xx2 is $xx2,xx3 is $xx3")


    val stringBuilder = StringBuilder().build {
        append("aaa")
    }
    println("xx1 is ${stringBuilder.toString()}")


    println("main start")
    val str = ""
    printString(str) { s ->
        println("lambda start")
        if (s.isEmpty()) return
        println(s)
        println("lambda end")
    }
}

inline fun runRunnable(crossinline block: () -> Unit) {
    val runnable = Runnable {
        block()
    }
}

inline fun printString(str: String, block: (String) -> Unit) {
    println("printString is begin")
    block(str)
    println("printString is end")
}


fun plus(num1: Int, num2: Int): Int {
    return num1 + num2
}

fun minus(num1: Int, num2: Int): Int {
    return num1 - num2
}

//函数类型参数
fun num1AndNum2(num1: Int, num2: Int, example: (Int, Int) -> Int): Int {
    return example(num1, num2)
}

fun studyFive() {
    val count = "123abcd".letterCount()
    val moneyValue = Money(5) + Money(7)
    val moneyValue1 = Money(5) + Money(6)
    println("count is $count,money is $moneyValue,moneyValue1 is $moneyValue1")

    val xxx = "hello".contains("he")
    val xxy = "he0" in "hello"
    val str = "hello".repeat(2)
    val str1 = "hello" * 1
    println("xxx is $xxx,xxy is $xxy,str is $str,str1 is $str1")


}


fun studyApply() {
    val list = listOf("Apple", "Banana", "Orange")
    val builder = StringBuilder()
    val result = builder.apply {
        append("开始吃水果了Apply\n\r")
        for (fruit in list) {
            builder.append("$fruit\n\r")
        }
        append("水果吃完了Apply\n\r")
        toString()
    }
    println(result.toString())

}

fun studyRun() {
    val list = listOf("Apple", "Banana", "Orange")
    val builder = StringBuilder()
    val result = builder.run {
        append("开始吃水果了run\n\r")
        for (fruit in list) {
            builder.append("$fruit\n\r")
        }
        append("水果吃完了run\n\r")
        toString()
    }
    println(result)
}

fun studyWithPlus() {
    val list = listOf("Apple", "Banana", "Orange")
    val builder = StringBuilder()
    val result = with(builder) {
        append("开始吃水果了Plus\n\r")
        for (fruit in list) {
            builder.append("$fruit\n\r")
        }
        append("水果吃完了Plus\n\r")
        toString()
    }
    println(result)
}

fun studyWith() {
    val list = listOf("Apple", "Banana", "Orange")
    val builder = StringBuilder()
    builder.append("开始吃水果了\n\r")
    for (fruit in list) {
        builder.append("$fruit\n\r")
    }
    builder.append("水果吃完了\n\r")
    println(builder.toString())
}

//学习函数参数的默认值
fun getParameterDefault(num: Int = 122, str: String) {
    //    字符串内嵌表达式
    val name = "Tommm"
    val age = 12
    println("$name age is $age")

    println("Num is $num,Str is $str")
}

//演示多态的实现
fun doStudy(study: Study?) {
//    进行了两次非空判断
    study?.doHomework()
    study?.readBook()
}

//进化：只进行了一次判空操作
fun doStudy1(study: Study?) {
//    其实就是调用study的let函数（属于对象的一个函数），所以不需要写study.let而是直接写let，
//    stu只是参数，->后面的是业务逻辑，所以调用doHomework的前面要加stu.doHomework()
//    let{}里面的就是lambda表达式
    study?.let { stu ->
        stu.doHomework()
        stu.readBook()
    }
}

fun doStudy2(study: Study?) {
    study?.let {
        it.doHomework()
        it.readBook()
    }
}

val studyPublic: Study? = null
fun doStudy3() {
    if (studyPublic != null) {
        studyPublic.readBook()
        studyPublic.doHomework()
    }
}

val studyPublic1: Study? = null
fun doStudy4() {
    studyPublic1?.let {
        it.doHomework()
        it.readBook()
    }
}


//KT语法糖：函数只有一行时，可以忽略{}直接用= 可以省去显示声明，会根据max推导得出返回类型
fun getLargerNum(num1: Int, num2: Int) = max(num1, num2)

//第一步
fun getLargerNum1(num1: Int, num2: Int): Int {
    var value = 0
    if (num1 > num2) {
        value = num1
    } else {
        value = num2
    }
    return value
}

//第二步：KT中的if可以有返回值，返回值就是条件中最后一行代码的返回值,value只被赋值一次，所以数据类型为val
fun getLargerNum2(num1: Int, num2: Int): Int {
    val value = if (num1 > num2) {
        num1
    } else {
        num2
    }
    return value
}

//第三步：value看上去有点多余，可以去除
fun getLargerNum3(num1: Int, num2: Int): Int {
    return if (num1 > num2) {
        num1
    } else {
        num2
    }
}

//第四步：函数中只有一句话时，可以去掉返回值，KT语法糖
fun getLargerNum4(num1: Int, num2: Int) = if (num1 > num2) num1 else num2

//when语句之前
fun getScore(name: String) = if (name == "Tom") {
    0
} else if (name == "Jim") {
    88
} else if (name == "Jack") {
    90
} else if (name == "Lily") {
    66
} else {
    12
}

//when语法 匹配值 -> {执行逻辑} else相当于default，必须要有
fun getScore1(name: String) = when (name) {
    "Tom" -> 0
    "Jim" -> 88
    "Jack" -> 90
    "Lily" -> 12
    else -> 0
}

//when语句可以进行类型匹配。Int和Long是Number的子类。is是instanceof的关键词
fun checkNumber(num: Number) = when (num) {
    is Int -> println("这是Int类型的参数")
    is Long -> println("这是Long类型的参数")
    else -> println("不知道是什么类型的参数")
}

//when:不带参数的用法，扩展性很强。注意：不带参数连()也去掉
fun getScore2(name: String) = when {
    name == "Tom" -> 10
    name == "Jim" -> 88
    name == "Jack" -> 90
    name == "Lily" -> 12
    else -> 0
}

//when:不带参数的用法，扩展性很强。需求：所有以Tom开头的名字，成绩都为1
fun getScore3(name: String) = when {
    name.startsWith("Tom") -> 1
    name == "Jim" -> 88
    name == "Jack" -> 90
    name == "Lily" -> 12
    else -> 0
}

fun studyFor() {
    //闭区间
    for (i in 0..10) {
        println("test1 i = " + i)
    }
    //左闭右开
    for (i in 0 until 10 step 3) {
        println("test2 i = " + i)
    }
    //降序的闭区间
    for (i in 10 downTo 0 step 2) {
        println("test3 i = " + i)
    }
}

fun studyAggregate() {
    //    创建不可变的List集合
    val list = listOf("Apple", "Banana", "Orange")
    println(list)
//    创建可变的List集合
    val list1 = mutableListOf("Apple", "Banana", "Orange")
    list1.add("Pear")
    println(list1)
//    创建不可变的Set集合
    val set = setOf("Apple", "Banana")
    println(set)
//    创建可变的Set集合
    val set1 = mutableSetOf("Apple", "Banana")
    set1.add("Orange")
    println(set1)
//    创建不可变的Map集合
    val map = mapOf("Apple" to 1, "Banana" to 2, "gyy" to 3)
    for ((fruit, number) in map)
        println("fruit = " + fruit + ", number = " + number)
//    创建不可变的Map集合
    val map1 = mutableSetOf("Apple" to 1, "Banana" to 2, "gyy" to 4)
    map1.add("App" to 3)
    for ((fruit, number) in map1)
        println("guolin fruit = " + fruit + ", number = " + number)
}

//学习函数式API
fun studyHanShuAPI() {
    //原始方法获取：list集合中最长的水果
    val list1 = listOf("Apple", "Banana", "Orangea")
    var maxLengeth1 = ""
    for (fruit in list1) {
        if (fruit.length > maxLengeth1.length) {
            //可以直接将fruit赋值给maxLengeth1
            maxLengeth1 = fruit
        }
    }
    println("maxLengeth1 is:" + maxLengeth1)

//    lambda表达式推导演化过程
    val list = listOf("Apple", "Bananaaaaa", "Orangex")
//    第一步：
//    val lambda = { fruit: String -> fruit.length }
//    val maxLength = list.maxBy(lambda)
//    第二步：可以直接放到maxBy()的括号中
//    val maxLength = list.maxBy({ fruit: String -> fruit.length })
//    第三步：当lambda参数是函数最后一个参数时，可将其移动到外面
//    val maxLength = list.maxBy() { fruit: String -> fruit.length }
//    第四步：当lambda参数是唯一一个参数时，可将()省略
//    val maxLength = list.maxBy { fruit: String -> fruit.length }
//    第五步：类型推导，可以省去参数类型
//    val maxLength = list.maxBy { fruit -> fruit.length }
//    第六步：当lambda表达式的参数列表只有一个参数时，参数名也可以省略，直接用it代替
    val maxLength = list.maxBy { it.length }
    println("maxLength is:" + maxLength)

//    学习map函数：按照lambda的规则映射成新的集合
    val newlist = list.map { it.toUpperCase() }
    for (fruit in newlist) {
        println("fruit name is:" + fruit)
    }

//    学习filter:过滤
    val fliterlist = list.filter { it.length <= 5 }
    for (fruit in fliterlist) {
        println("fliterlist name is:" + fruit)
    }

//学习filter和map
    val fliterlist1 = list.filter { it.length <= 5 }.map { it.toUpperCase() }
    for (fruit in fliterlist1) {
        println("fliterlist1 name is:" + fruit)
    }

//    学习any（至少有一个满足满足）和all（所有的都要满足）
    val anylist = list.any { it.length <= 5 }
    val alllist = list.all { it.length >= 5 }
    println("anylist = " + anylist + " alllist = " + alllist)
}

//java函数式API:单抽象方法接口，比如按钮监听
fun studyJavaApi() {
    Thread {
        println("Thread is running")
    }.start()
}

//初代：获取字符串长度的方法
fun getStringLength(text: String?): Int {
    if (text != null) {
        return text.length
    }
    return 0
}

//演化：学习 ?.  和   ?:
fun getStringLength1(text: String?) = text?.length ?: 0

















