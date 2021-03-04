package com.gyy.guoLinKt.bean

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TelService {


//    https://api.binstd.com/shouji/query?appkey=c1f9251fc22da079&shouji=13859110503

    /**
    查询归属地
     */
    @GET("shouji/query")
    fun queryPlace(@Query("appkey") appkey: String, @Query("shouji") shouji: String): Call<Tel>
}