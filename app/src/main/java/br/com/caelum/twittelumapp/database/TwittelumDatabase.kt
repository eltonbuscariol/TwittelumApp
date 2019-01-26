package br.com.caelum.twittelumapp.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import br.com.caelum.twittelumapp.application.TwittelumApplication
import br.com.caelum.twittelumapp.modelo.Tweet

@Database(entities = [Tweet::class], version = 2)
abstract class TwittelumDatabase : RoomDatabase() {

    abstract fun tweetDao() : TweetDao

    companion object {

        private var database:TwittelumDatabase? = null

        private val DATABASE = "TwittelumDB"

        fun getInstance() : TwittelumDatabase{

            return database ?: criaBanco().also { database = it }
        }

        private fun criaBanco() : TwittelumDatabase{
            return Room.databaseBuilder(TwittelumApplication.getInstance(), TwittelumDatabase::class.java, DATABASE)
                .allowMainThreadQueries()
                .addMigrations(Migration1Para2)
                .build()
        }
    }

}