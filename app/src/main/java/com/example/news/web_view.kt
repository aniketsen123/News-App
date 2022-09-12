package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class web_view : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val progress=findViewById<ProgressBar>(R.id.progressBar5)
        val url = intent.getStringExtra("URL")
        if (url != null) {
            val webview = findViewById<WebView>(R.id.webVIew)
            webview.settings.javaScriptEnabled = true
            // if mobile view then show it
            webview.settings.userAgentString="Mozilla/5.0 (iPhone; CPU iPhone OS 13_5_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.1.1 Mobile/15E148 Safari/604.1"
            webview.webViewClient = object : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progress.visibility= View.GONE
                    webview.visibility=View.VISIBLE
                }


            }
            webview.loadUrl(url)
        }
    }
}