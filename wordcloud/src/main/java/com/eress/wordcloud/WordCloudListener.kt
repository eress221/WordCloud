package com.eress.wordcloud

import android.webkit.WebView

interface WordCloudListener {

    fun onPageFinished(view: WebView)
    fun onTouch()
    fun onWordClick(word: String)
}