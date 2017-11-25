package com.yayandroid.componentdispatcher.sample.feature2

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.yayandroid.componentdispatcher.ComponentDispatcher

class Feature2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feature_2)

        findViewById<TextView>(R.id.sampleTextView).apply {
            text = "Component: \n ${ComponentDispatcher.get<Feature2Component>()}"
        }
    }
}
