package com.gyy.guoLinKt.bean;

public class MyJNIClass {

    public int age = 18;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static String getDes(String text) {
        if (text == null) {
            text = "";
        }
        return "传入的字符串长度是 :" + text.length() + "  内容是 : " + text;
    }

}
