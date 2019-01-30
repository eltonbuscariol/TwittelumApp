package br.com.caelum.twittelumapp.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.caelum.twittelumapp.database.TweetDao
import br.com.caelum.twittelumapp.database.TwittelumDatabase
import br.com.caelum.twittelumapp.repository.TweetRepository

object Injetor : ViewModelProvider.Factory {

    private val database: TwittelumDatabase = TwittelumDatabase.getInstance()
    private val fonteDeDados: TweetDao = database.tweetDao()
    private val repository: TweetRepository = TweetRepository(fonteDeDados)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        TweetViewModel::class.java -> {
            TweetViewModel(repository) as T
        }
        else -> {
            null as T
        }
    }
}