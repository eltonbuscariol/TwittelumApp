package br.com.caelum.twittelumapp.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.com.caelum.twittelumapp.R
import br.com.caelum.twittelumapp.database.TwittelumDatabase
import br.com.caelum.twittelumapp.modelo.Tweet
import kotlinx.android.synthetic.main.activity_tweet.*

class TweetActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tweet_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when(item?.itemId) {
        R.id.salvar_tweet_menu -> {
            publicaTweet()
            finish()
            true
        }
        android.R.id.home -> {
            finish()
            true
        }
        else -> false
    }

    private fun publicaTweet() {

        val tweet = Tweet(tweet_mensagem.text.toString())

        val tweetDao = TwittelumDatabase.getInstance().tweetDao()
        tweetDao.salva(tweet)

        // Qualquer das duas maneiras
        //Toast.makeText(this, tweet.toString(), Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "$tweet", Toast.LENGTH_SHORT).show()
    }
}
