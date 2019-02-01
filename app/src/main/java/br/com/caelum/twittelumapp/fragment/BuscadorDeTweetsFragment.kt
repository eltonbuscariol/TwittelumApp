package br.com.caelum.twittelumapp.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.*
import br.com.caelum.twittelumapp.R
import br.com.caelum.twittelumapp.adapter.TweetAdapter
import br.com.caelum.twittelumapp.modelo.Tweet
import br.com.caelum.twittelumapp.viewmodel.Injetor
import br.com.caelum.twittelumapp.viewmodel.TweetViewModel
import kotlinx.android.synthetic.main.fragment_lista_tweets.*

class BuscadorDeTweetsFragment : Fragment() {

    private val viewModel :TweetViewModel by lazy {
        ViewModelProviders.of(activity!!, Injetor).get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_lista_tweets, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.buscador_menu, menu)

        val botaoBusca = menu?.findItem(R.id.barra_busca)
        val searchView = botaoBusca?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(texto: String?): Boolean {
                filtraTweetsPelo(texto)
                return false
            }

        })
    }

    private fun filtraTweetsPelo(texto: String?) {
        val filtrados = ArrayList<Tweet>()

        if (!texto.isNullOrEmpty()) {
            viewModel.tweets().observe(this, Observer {tweets->
                tweets?.let {
                    filtrados.addAll(tweets.filter { tweet -> tweet.mensagem.contains(texto!!, true) })
                    listaTweets.adapter = TweetAdapter(filtrados)
                }

            })
        }
        else
        {
            filtrados.clear()
            listaTweets.adapter = null
        }
    }
}