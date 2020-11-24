package com.gyy.guoLinKt.bean

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

//@ActivityRetainedScoped
//class MyViewModel @Inject constructor(val repository: MyRepository) : ViewModel() {
//}

@ActivityRetainedScoped
class MyViewModel @ViewModelInject constructor(val repository: Repository) : ViewModel() {
}
