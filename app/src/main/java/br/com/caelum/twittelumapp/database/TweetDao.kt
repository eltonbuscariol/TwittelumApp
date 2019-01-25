package br.com.caelum.twittelumapp.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import br.com.caelum.twittelumapp.modelo.Tweet

@Dao
interface TweetDao {

    @Insert
    fun salva(tweet: Tweet)

    @Query("select * from Tweet")
    fun lista() : LiveData<List<Tweet>>

    @Delete()
    fun deleta(tweet: Tweet)

}