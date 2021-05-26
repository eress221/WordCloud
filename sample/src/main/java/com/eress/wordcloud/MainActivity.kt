package com.eress.wordcloud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import com.eress.wordcloud.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val test = mutableListOf<Word>()
        for (i in 1..100) {
            test.add(Word(Random().nextInt(100).toString(), 100-i, i))
        }
        binding.wcView.setListener(object : WordCloudListener{
            override fun onPageFinished(view: WebView) {
                binding.wcView.setDataSet(test)
            }

            override fun onTouch() {

            }

            override fun onWordClick(word: String) {
                Toast.makeText(applicationContext, "word: $word", Toast.LENGTH_SHORT).show()
            }
        })
    }
}