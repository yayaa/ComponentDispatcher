package com.yayandroid.componentdispatcher.sample.base.di

import com.yayandroid.componentdispatcher.sample.base.SampleCoreLogger
import dagger.Module
import dagger.Provides

@Module
class SampleCoreModule {

    @CoreScope
    @Provides fun providesSampleCoreLogger(): SampleCoreLogger = SampleCoreLogger()

}