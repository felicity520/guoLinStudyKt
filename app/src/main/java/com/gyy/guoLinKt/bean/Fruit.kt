package com.gyy.guoLinKt.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

//class Fruit(val name: String, val fruitImage: Int) : Serializable {
//}

@Parcelize
//class Fruit(val name: String, val fruitImage: Int) : Parcelable {
//}


class Fruit(val name: String, val fruitImage: Int) : Parcelable {}