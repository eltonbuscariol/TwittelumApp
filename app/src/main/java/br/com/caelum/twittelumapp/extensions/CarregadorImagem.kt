package br.com.caelum.twittelumapp.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

object CarregadorImagem {
    fun decodifica(fotoBase64: String) : Bitmap {
        val bytes = Base64.decode(fotoBase64, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }
}
