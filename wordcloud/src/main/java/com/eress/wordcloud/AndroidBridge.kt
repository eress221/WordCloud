package com.eress.wordcloud

import android.webkit.JavascriptInterface

class AndroidBridge(private val view: WordCloudView) {

    @JavascriptInterface
    fun onWordClick(word: String) {
        view.getListener()?.onWordClick(word)
    }
}