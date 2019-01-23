package br.com.caelum.twittelumapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import br.com.caelum.twittelumapp.modelo.Tweet

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

        val conteudo = findViewById<EditText>(R.id.tweet_mensagem)
        val tweet = Tweet(conteudo.text.toString())

        // Qualquer das duas maneiras
        //Toast.makeText(this, tweet.toString(), Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "$tweet", Toast.LENGTH_SHORT).show()
    }
}
