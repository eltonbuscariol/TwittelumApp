package br.com.caelum.twittelumapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class TweetActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet)

        val btnPublicar = findViewById<Button>(R.id.btnPublicar)

        btnPublicar.setOnClickListener {
            publicaTweet()
        }
    }

    private fun publicaTweet() {

        val conteudo = findViewById<EditText>(R.id.tweet_mensagem)

        Toast.makeText(this, conteudo.text, Toast.LENGTH_SHORT).show()
    }
}
