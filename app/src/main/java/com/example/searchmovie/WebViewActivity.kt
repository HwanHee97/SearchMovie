package com.example.searchmovie

import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.searchmovie.databinding.ActivityWebViewBinding
import com.example.searchmovie.utils.Constants

class WebViewActivity: AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding
    private lateinit var url:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setContentView(binding.root)
        getIntents()
        setWebView()
    }

    private fun setBinding(){
        binding = ActivityWebViewBinding.inflate(layoutInflater)
    }

    //url 받기
    private fun getIntents(){
        url=intent.getStringExtra("view_url") as String
        Log.d(Constants.TAG,"WebViewActivity - getIntents() called / url: $url ")
    }
    //웹뷰에 url연결해서 띄우기
    private fun setWebView(){
        binding.webview.apply {
            webViewClient = WebViewClient()//하이퍼링크 클릭시 새창 띄우기 방지
            settings.javaScriptEnabled = true//자바스크립트 허용
            settings.loadsImagesAutomatically = true
        }
        binding.webview.loadUrl(url)
    }

}