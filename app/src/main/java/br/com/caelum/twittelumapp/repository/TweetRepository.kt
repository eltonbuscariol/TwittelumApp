package br.com.caelum.twittelumapp.repository

import br.com.caelum.twittelumapp.database.TweetDao
import br.com.caelum.twittelumapp.modelo.Tweet

class TweetRepository(private val fonteDeDados : TweetDao) {

    fun lista() = fonteDeDados.lista()

    fun salva(tweet: Tweet) = fonteDeDados.salva(tweet)

    fun delete(tweet: Tweet) = fonteDeDados.deleta(tweet)
}