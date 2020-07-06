package com.gyy.guoLinKt.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.gyy.guoLinKt.R
import com.gyy.guoLinKt.kotlin.Study
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        studyJni()


    }

    /***
     * Jni学习示例
     */
    private fun studyJni() {
        // Example of a call to a native method
        sample_text.append("${getStrFromC("This is a string from java.")}\n")
//        sample_text.append("${getStrFromCReg("This is a string from java.")}\n")

        val intArray: IntArray = intArrayOf(1, 3, 5)
        val result = sumArray(intArray)
        val resultTwo = sumArrayTwo(intArray)
        Log.d(TAG, "result: " + result + "resultTwo:" + resultTwo)
        val intArr = create1DArray(3)
        for (element in intArr) {
            Log.d(TAG, "element=$element")
        }
        val int2Arr = create2DArray(3)
        //这样打印也OK
        for (element in int2Arr) {
            for (element1 in element) {
                Log.d(TAG, "element1 = $element1")
            }
        }
        //这样也OK
        for (i in 0..2) {
            for (i1 in 0..2) {
                Log.d(
                    TAG,
                    "element[" + i + "][" + i1 + "]" + " = " + int2Arr.get(i).get(i1)
                )
            }
        }

        val int2ArrTwo = create2DArrayTwo(3)
        for (i in 0..2) {
            for (i1 in 0..2) {
                Log.d(
                    TAG,
                    "init2DArray[" + i + "][" + i1 + "]" + " = " + int2ArrTwo.get(i).get(i1)
                )
            }
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    external fun getStrFromC(strFromJava: String): String

    external fun getStrFromCReg(strFromJava: String): String

    //kotlin中的整型数组
    external fun sumArray(intArr: IntArray): Int

    external fun sumArrayTwo(intArr: IntArray): Int

    //    一维数组的表示：IntArray
    external fun create1DArray(size: Int): IntArray

    //二维数组的表示： Array<IntArray>
    external fun create2DArray(size: Int): Array<IntArray>

    external fun create2DArrayTwo(size: Int): Array<IntArray>

    companion object {
        const val TAG = "MainActivity"

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}
