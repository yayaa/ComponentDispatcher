package com.yayandroid.componentdispatcher.sample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.yayandroid.componentdispatcher.sample.base.SampleCoreComponent
import com.yayandroid.componentdispatcher.sample.base.getComponent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.sampleTextView).apply {
            text = "Component: \n ${getComponent<SampleCoreComponent>()}"
        }

        findViewById<Button>(R.id.feature1Button).apply {
            setOnClickListener { featureClickListener("sample:///feature1") }
        }

        findViewById<Button>(R.id.feature2Button).apply {
            setOnClickListener { featureClickListener("sample:///feature2") }
        }
    }

    private val featureClickListener: (deeplink: String) -> Unit = { deeplink ->
        startActivity(Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(deeplink)
        })
    }

}
