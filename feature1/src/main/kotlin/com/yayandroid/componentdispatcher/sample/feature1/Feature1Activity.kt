package com.yayandroid.componentdispatcher.sample.feature1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.yayandroid.componentdispatcher.sample.base.SampleCoreLogger
import com.yayandroid.componentdispatcher.sample.base.componentDispatcher
import javax.inject.Inject

class Feature1Activity : AppCompatActivity() {

    @Inject lateinit var sampleCoreLogger: SampleCoreLogger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_1)

        val feature1Component = componentDispatcher.get<Feature1Component>()
        feature1Component.inject(this)
        sampleCoreLogger.logSelf()

        findViewById<TextView>(R.id.sampleTextView).apply {
            text = "Component: \n ${sampleCoreLogger.string(feature1Component)}"
        }
    }
}
