package br.com.caelum.twittelumapp.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import br.com.caelum.twittelumapp.R
import br.com.caelum.twittelumapp.adapter.TweetAdapter
import br.com.caelum.twittelumapp.modelo.Tweet
import br.com.caelum.twittelumapp.viewmodel.Injetor
import br.com.caelum.twittelumapp.viewmodel.TweetViewModel
import kotlinx.android.synthetic.main.fragment_lista_tweets.view.*

class ListaTweetsFragment : Fragment() {

    private val viewModel : TweetViewModel by lazy {
        ViewModelProviders.of(activity!!, Injetor).get(TweetViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lista_tweets, container, false)

        viewModel.lista().observe(this, Observer { tweets ->

            tweets?.let {
                val arrayAdapter = TweetAdapter(it)

                view.listaTweets.adapter = arrayAdapter
            }
        })

        view.listaTweets.onItemLongClickListener  = AdapterView.OnItemLongClickListener { adapter, view, position, id ->

            val tweet = view.listaTweets.getItemAtPosition(position) as Tweet

            perguntaSePodeDeletar(tweet)

            true
        }

        return view
    }

    private fun perguntaSePodeDeletar(tweet: Tweet) {
        AlertDialog.Builder(context!!)
            .setTitle("ATENÇÃO")
            .setIcon(R.drawable.ic_warning)
            .setMessage("Deseja realmente excluir?")
            .setPositiveButton("SIM"){_, _ -> viewModel.deleta(tweet)}
            .setNegativeButton("Não", null)
            .show()
    }
}