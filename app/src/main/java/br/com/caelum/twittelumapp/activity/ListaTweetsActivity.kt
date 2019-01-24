package br.com.caelum.twittelumapp.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import br.com.caelum.twittelumapp.R
import br.com.caelum.twittelumapp.database.TweetDao
import br.com.caelum.twittelumapp.database.TwittelumDatabase
import br.com.caelum.twittelumapp.modelo.Tweet
import kotlinx.android.synthetic.main.activity_lista_tweets.*

class ListaTweetsActivity : AppCompatActivity() {

    var tweetDao : TweetDao? = null
    var arrayAdapter : ArrayAdapter<Tweet>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tweets)

        tweetDao = TwittelumDatabase.getInstance().tweetDao()

        arrayAdapter = ArrayAdapter<Tweet>(this, android.R.layout.simple_list_item_1, tweetDao!!.lista())

        listaTweets.adapter = arrayAdapter

        fabAddTweet.setOnClickListener {
            val intent = Intent(this, TweetActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()

        arrayAdapter = ArrayAdapter<Tweet>(this, android.R.layout.simple_list_item_1, tweetDao!!.lista())

        listaTweets.adapter = arrayAdapter


    }

}