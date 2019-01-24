package br.com.caelum.twittelumapp.modelo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Tweet(
    val mensagem:String,
    @PrimaryKey(autoGenerate = true) var id : Int = 0){

    override fun toString(): String {
        return mensagem
    }
}