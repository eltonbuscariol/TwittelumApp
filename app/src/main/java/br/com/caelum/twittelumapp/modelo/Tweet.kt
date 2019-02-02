package br.com.caelum.twittelumapp.modelo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


data class Tweet(
    val mensagem:String,
    val foto:String?,
    val dono : Usuario,
    val latitude : Double,
    val longitude : Double,
    val id : Long = 0){

    override fun toString(): String {
        return mensagem
    }
}

//@Entity
//data class Tweet(
//    val mensagem: String,
//    val foto: String?,
//    val dono: Usuario? = null,
//    @PrimaryKey(autoGenerate = true) var id: Int = 0
//) {
//
//    override fun toString(): String {
//        return mensagem
//    }
//}