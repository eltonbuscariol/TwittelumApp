package br.com.caelum.twittelumapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.caelum.twittelumapp.database.TweetDao
import br.com.caelum.twittelumapp.database.TwittelumDatabase
import br.com.caelum.twittelumapp.repository.TweetRepository
import br.com.caelum.twittelumapp.repository.UsuarioRepository
import br.com.caelum.twittelumapp.webclient.InicializadorDoRetrofit
import br.com.caelum.twittelumapp.webclient.TweetWebClient
import br.com.caelum.twittelumapp.webclient.UsuarioWebClient

object Injetor : ViewModelProvider.Factory {

//    private val database: TwittelumDatabase = TwittelumDatabase.getInstance()
//    private val fonteDeDados: TweetDao = database.tweetDao()
//    private val tweetRepository: TweetRepository = TweetRepository(fonteDeDados)
    private val tweetRepository: TweetRepository = TweetRepository(TweetWebClient(InicializadorDoRetrofit.retrofit))
    private val usuarioRepository = UsuarioRepository(UsuarioWebClient(InicializadorDoRetrofit.retrofit))

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        TweetViewModel::class.java -> TweetViewModel(tweetRepository) as T
        UsuarioViewModel::class.java -> UsuarioViewModel(usuarioRepository) as T
        else -> {
            null as T
        }
    }
}