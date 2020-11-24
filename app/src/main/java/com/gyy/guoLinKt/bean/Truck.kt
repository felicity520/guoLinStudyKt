package com.gyy.guoLinKt.bean

import javax.inject.Inject


class Truck @Inject constructor(val driver: Driver) {

    @Inject
    lateinit var engine: Engine

    fun deliver() {
        engine.start()
        println("Truck is delivering cargo. Driven by $driver")
        engine.shutdown()
    }

}