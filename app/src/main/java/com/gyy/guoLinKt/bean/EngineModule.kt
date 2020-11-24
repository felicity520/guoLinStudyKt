package com.gyy.guoLinKt.bean

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
abstract class EngineModule {
    @Binds
//    abstract fun bindEngine(gasEngine: GasEngine): Engine
    abstract fun bindEngine(electricEngine: ElectricEngine): Engine
}