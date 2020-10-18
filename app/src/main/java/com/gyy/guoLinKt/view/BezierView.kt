package com.gyy.guoLinKt.view

import android.animation.*
import android.content.Context
import android.graphics.*
import android.graphics.PathMeasure.TANGENT_MATRIX_FLAG
import android.util.AttributeSet
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnRepeat
import androidx.core.animation.doOnResume
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.doOnPreDraw
import com.gyy.guoLinKt.R
import java.math.MathContext
import java.util.*
import java.util.concurrent.locks.Lock
import kotlin.math.atan2
import kotlin.random.Random

/**
 * 称之为小船游啊游
 */

class BezierView(context: Context, attrs: AttributeSet) : View(context, attrs) {


    private var mPaint: Paint? = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL_AND_STROKE
        strokeJoin = Paint.Join.ROUND
    }
    private var mPath: Path? = Path()

    //一个波浪长，相当于两个二阶贝塞尔曲线的长度
    private var mItemWaveLength = 0f

    //波浪在Y轴方向的位置
    var originY = 400


    @Volatile
    var heightList = object : LinkedList<Float>() {
        @Synchronized
        override fun addFirst(e: Float?) {
            super.addFirst(e)
            postInvalidate()
        }

        @Synchronized
        override fun get(index: Int): Float {
            return super.get(index)
        }
    }


    lateinit var boat: Bitmap

    var waveNum = 3


    //波浪幅度
    private val range = 100
    var dx = 0f
        set(value) {
            field = value
            postInvalidate()
        }
        get() = field


    init {
        for (index in 0 until 20) {
            heightList.add(Random.nextInt(500).toFloat())
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mItemWaveLength = w.toFloat() / 2
        boat = resources.getDrawable(R.drawable.ic_boat).toBitmap()
        startAnim()
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var waveIndex = 0
        //每次初始化Path
        mPath!!.reset()
        val halfWaveLen = mItemWaveLength / 2 //半个波长，即一个贝塞尔曲线长度
        mPath!!.moveTo(-mItemWaveLength + dx, originY.toFloat()) //波浪的开始位置
        //每一次for循环添加一个波浪的长度到path中，根据view的宽度来计算一共可以添加多少个波浪长度
        var i = -mItemWaveLength


        while (i <= width + mItemWaveLength) {
            mPath!!.rQuadTo(
                halfWaveLen / 2.toFloat(),
                //使用随机的波浪高度
                -heightList.get(waveIndex),
                halfWaveLen.toFloat(),
                0f
            )
            mPath!!.rQuadTo(
                halfWaveLen / 2.toFloat(),
                heightList.get(waveIndex),
                halfWaveLen.toFloat(),
                0f
            )
            i += mItemWaveLength
            waveIndex++

        }

        mPath!!.lineTo(width.toFloat(), height.toFloat())
        mPath!!.lineTo(0f, height.toFloat())

        mPath!!.close() //封闭path路径

        canvas.drawPath(mPath!!, mPaint!!)


        //微积分的概念，来获取切线角度
        var x = width.toFloat() / 2
        var region = Region()
        var region2 = Region()
        var clip = Region((x - 0.1).toInt(), 0, x.toInt(), height)
        var clip2 = Region((x - 10).toInt(), 0, (x - 9).toInt(), height)
        region.setPath(mPath!!, clip)
        region2.setPath(mPath!!, clip2)

        var rect = region.getBounds()
        var rect2 = region2.getBounds()

        val fl =
            -atan2(-rect.top.toFloat() + rect2.top.toFloat(), 9.5f) * 180 / Math.PI.toFloat()

        canvas.save()

        canvas.rotate(
            fl, rect.right.toFloat(),
            rect.top.toFloat()
        )
        canvas.drawBitmap(
            boat,
            rect.right.toFloat() - boat.width / 2,
            rect.top.toFloat() - boat.height / 4 * 3,
            mPaint
        )
        canvas.restore()
    }

    fun startAnim() {
        //根据一个动画不断得到0~mItemWaveLength的值dx，通过dx的增加不断去改变波浪开始的位置，dx的变化范围刚好是一个波浪的长度，
        //所以可以形成一个完整的波浪动画，假如dx最大小于mItemWaveLength的话， 就会不会画出一个完整的波浪形状


        var anim: ObjectAnimator =
            ObjectAnimator.ofFloat(BezierView@ this, "dx", 0f, mItemWaveLength.toFloat())

        anim.duration = 2000
        anim.repeatCount = ValueAnimator.INFINITE

        anim.doOnRepeat {
            Log.e("gyy", "startAnim: --------------------")
            heightList.addFirst(Random.nextInt(500).toFloat())
            heightList.removeAt(10)
        }

        anim.interpolator = LinearInterpolator()
        anim.start()
    }


}