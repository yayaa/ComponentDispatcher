package com.yayandroid.componentdispatcher.sample.feature1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.yayandroid.componentdispatcher.sample.base.getComponent

class Feature1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_1)

        findViewById<TextView>(R.id.sampleTextView).apply {
            text = "Component: \n ${getComponent<Feature1Component>()}"
        }
    }
}
