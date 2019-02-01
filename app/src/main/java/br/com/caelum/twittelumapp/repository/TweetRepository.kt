package br.com.caelum.twittelumapp.repository

import android.arch.lifecycle.MutableLiveData
import br.com.caelum.twittelumapp.modelo.Tweet
import br.com.caelum.twittelumapp.webclient.TweetWebClient

//class TweetRepository(private val fonteDeDados : TweetDao) {
class TweetRepository(private val client: TweetWebClient) {

    val lista: MutableLiveData<List<Tweet>> = MutableLiveData()
    val erro: MutableLiveData<Throwable> = MutableLiveData()


    fun busca() {
        client.buscaTweets(sucessoLista(), falha())
    }

    private fun falha() = { t: Throwable ->
        erro.postValue(t)
    }

    fun salva(tweet: Tweet) = client.salva(tweet, falha())

    private fun sucessoLista() = { list: List<Tweet> ->
        lista.postValue(list)
    }

    //    fun delete(tweet: Tweet) = fonteDeDados.deleta(tweet)
    fun delete(tweet: Tweet) {

    }
}