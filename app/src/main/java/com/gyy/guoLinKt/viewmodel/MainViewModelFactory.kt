package com.gyy.guoLinKt.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/***
 * 这里构造参数必须加private val,否则会报错，不理解？？？
 */
class MainViewModelFactory(private val counterPara: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(counterPara) as T
    }
}