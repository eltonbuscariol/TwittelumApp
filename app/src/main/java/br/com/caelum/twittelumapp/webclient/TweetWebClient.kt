package br.com.caelum.twittelumapp.webclient

import br.com.caelum.twittelumapp.modelo.Tweet
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

class TweetWebClient(retrofit: Retrofit) {

    private val service = retrofit.create(TweetService::class.java)

    fun buscaTweets(
        sucesso : (tweets : List<Tweet>) -> Unit,
        falha : (t : Throwable) -> Unit
    ) {
        service.buscaTweets().enqueue(object : Callback<List<Tweet>>{
            override fun onFailure(call: Call<List<Tweet>>, t: Throwable) {
                falha(t)
            }

            override fun onResponse(call: Call<List<Tweet>>, response: Response<List<Tweet>>) {
                response.body()?.let(sucesso)
            }

        })
    }

    fun salva(
        tweet: Tweet,
        falha: (t: Throwable) -> Unit
    ){
        service.salva(tweet).enqueue(object : Callback<Tweet>{
            override fun onFailure(call: Call<Tweet>, t: Throwable) {
                falha(t)
            }

            override fun onResponse(call: Call<Tweet>, response: Response<Tweet>) {

            }

        })
    }


    private interface TweetService{

        @GET("tweet")
        fun buscaTweets() : Call<List<Tweet>>

        @POST("tweet")
        fun salva(@Body tweet: Tweet) : Call<Tweet>
    }
}