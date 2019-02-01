package br.com.caelum.twittelumapp.viewmodel

import android.arch.lifecycle.ViewModel
import br.com.caelum.twittelumapp.modelo.Tweet
import br.com.caelum.twittelumapp.repository.TweetRepository

class TweetViewModel(private val repository: TweetRepository) : ViewModel() {

    fun  tweets() = repository.lista

    fun erro() = repository.erro

    fun buscaTweets() = repository.busca()

    fun salva(tweet: Tweet) = repository.salva(tweet)

    fun deleta(tweet: Tweet) = repository.delete(tweet)
}