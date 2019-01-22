package br.com.caelum.twittelumapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_lista_tweets.*

class ListaTweetsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tweets)

        val tweets = listOf("Oi", "Tchau")

        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tweets)

        listaTweets.adapter = arrayAdapter
    }
}