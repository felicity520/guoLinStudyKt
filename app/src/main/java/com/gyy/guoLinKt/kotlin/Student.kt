package com.gyy.guoLinKt.kotlin

//显式的主构造函数：没有函数体，但是又一个init结构体.  :表示继承
//Person()空括号表示调用父类中的无参构造函数，每个类默认都会有一个无参的构造函数
//Person()若是没有无参的构造函数，则Person()会报错
//这里理解成子类传参数到Person，所以Person中的参数，需要在Student中先创建，然后再传到Person
class Student(val sno: String, val grade: Int, name: String, age: Int) : Person(name, age),Study {
    init {
        println("sno:" + sno + "  grade:" + grade + "   name:" + name + "   age:" + age)
    }

    //次构造函数必须直接或间接调用主构造函数
    constructor(sno: String, grade: Int) : this(sno, grade, "", 0)
    constructor() : this("", 0)

    //如果函数体有默认实现，则类实现接口时，可以选择性的实现，这里可以不用实现doHomework()
    override fun readBook() {
        println(sno + "gyy id readBook")
    }

//    override fun readBook() {
//        println(sno + "id readBook")
//    }
//
//    override fun doHomework() {
////        这句话必须删除，否则会报错
////        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        println(sno + "id doHomework")
//    }
}