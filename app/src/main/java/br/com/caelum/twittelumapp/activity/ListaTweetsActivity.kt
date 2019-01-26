package br.com.caelum.twittelumapp.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import android.widget.ArrayAdapter
import br.com.caelum.twittelumapp.R
import br.com.caelum.twittelumapp.database.TweetDao
import br.com.caelum.twittelumapp.database.TwittelumDatabase
import br.com.caelum.twittelumapp.modelo.Tweet
import br.com.caelum.twittelumapp.viewmodel.Injetor
import br.com.caelum.twittelumapp.viewmodel.TweetViewModel
import kotlinx.android.synthetic.main.activity_lista_tweets.*

class ListaTweetsActivity : AppCompatActivity() {

    private val viewModel :TweetViewModel by lazy {
        ViewModelProviders.of(this, Injetor).get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tweets)

       viewModel.lista().observe(this, Observer { tweets ->
           val arrayAdapter = ArrayAdapter<Tweet>(this, android.R.layout.simple_list_item_1, tweets)

           listaTweets.adapter = arrayAdapter
       })

        listaTweets.onItemLongClickListener  = AdapterView.OnItemLongClickListener { adapter, view, position, id ->

            val tweet = listaTweets.getItemAtPosition(position) as Tweet

             perguntaSePodeDeletar(tweet)

            true
        }

        fabAddTweet.setOnClickListener {
            val intent = Intent(this, TweetActivity::class.java)
            startActivity(intent)
        }

    }

    private fun perguntaSePodeDeletar(tweet: Tweet) {
        AlertDialog.Builder(this)
            .setTitle("ATENÇÃO")
            .setIcon(R.drawable.ic_warning)
            .setMessage("Deseja realmente excluir?")
            .setPositiveButton("SIM"){_, _ -> viewModel.deleta(tweet)}
            .setNegativeButton("Não", null)
            .show()
    }


}