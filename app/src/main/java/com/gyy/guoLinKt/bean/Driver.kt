package com.gyy.guoLinKt.bean

import android.app.Activity
import android.app.Application
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

//class Driver @Inject constructor(@ApplicationContext val context: Context) {
//}

class Driver @Inject constructor(val application: Application) {
}

//class Driver @Inject constructor(val activity: Activity) {
//}
