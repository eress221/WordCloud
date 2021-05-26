package com.eress.wordcloud

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class WordCloudView(
    context: Context,
    attrs: AttributeSet
) : WebView(context, attrs), View.OnTouchListener {

    private var mListener: WordCloudListener? = null

    init {
        init()
    }

    @SuppressLint("SetJavaScriptEnabled", "ClickableViewAccessibility")
    private fun init() {
        this.settings.loadWithOverviewMode = true
        this.settings.useWideViewPort = true
        this.settings.javaScriptEnabled = true
        this.isVerticalScrollBarEnabled = false
        this.isHorizontalScrollBarEnabled = false
        this.webChromeClient = WebChromeClient()
        this.setOnTouchListener(this)
        this.setOnLongClickListener {
            return@setOnLongClickListener true
        }
        this.performLongClick()
        this.addJavascriptInterface(AndroidBridge(this), "AndroidBridge")
        this.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                mListener?.onPageFinished(view)
            }
        }
        this.loadUrl("file:///android_asset/wordcloud.html")
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            mListener?.onTouch()
        }
        return (event?.action == MotionEvent.ACTION_MOVE)
    }

    fun setListener(listener: WordCloudListener) {
        this.mListener = listener
    }

    fun getListener(): WordCloudListener? {
        return mListener
    }

    fun setDataSet(wordList: List<Word>) {
        try {
            val jsonArr = JSONArray()
            val colorArr = JSONArray()
            for (word in wordList) {
                val jsonObj = JSONObject()
                jsonObj.put("text", word.getWord()?.replace("[|?*<:\\\">+\\\\[\\\\]/',.]", ""))
                jsonObj.put("size", word.getSize())
                jsonObj.put("color", word.getColor())
                jsonArr.put(jsonObj)
                if (!word.getColor().isNullOrBlank()) {
                    colorArr.put(word.getColor())
                }
            }
            this.evaluateJavascript("javascript:load($jsonArr, $colorArr)", null)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }
}