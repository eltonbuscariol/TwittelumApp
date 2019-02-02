package br.com.caelum.twittelumapp.activity

import android.Manifest
import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import br.com.caelum.twittelumapp.R
import br.com.caelum.twittelumapp.extensions.decodificarParaBase64
import br.com.caelum.twittelumapp.gps.GPS
import br.com.caelum.twittelumapp.modelo.Tweet
import br.com.caelum.twittelumapp.viewmodel.Injetor
import br.com.caelum.twittelumapp.viewmodel.TweetViewModel
import br.com.caelum.twittelumapp.viewmodel.UsuarioViewModel
import kotlinx.android.synthetic.main.activity_tweet.*
import java.io.File

class TweetActivity : AppCompatActivity() {

    private var localFoto : String? = null
    private val REQUEST_PHOTO : Int = 0
    private val usuarioViewModel by lazy {
        ViewModelProviders.of(this, Injetor).get(UsuarioViewModel::class.java)
    }
    // Outra maneira de deixa a instância do objeto para depois
    private lateinit var viewModel: TweetViewModel
//    private val viewModel : TweetViewModel by lazy {
//        ViewModelProviders.of(this, Injetor).get(TweetViewModel::class.java)
//    }


    private lateinit var gps: GPS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        gps = GPS(this)

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            gps.fazBusca()
        } else{
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 123)
        }

        viewModel = ViewModelProviders.of(this, Injetor).get(TweetViewModel::class.java)

        cameraButton.setOnClickListener {
            tirarFoto()
        }

        saveButton.setOnClickListener {
            publicaTweet()
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        gps.cancela()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == REQUEST_PHOTO){
            if (resultCode == Activity.RESULT_OK){
                carregaFoto()
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 123) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                gps.fazBusca()
            }
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.tweet_menu, menu)
//        return true
//    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when(item?.itemId) {
//        R.id.salvar_tweet_menu -> {
//            publicaTweet()
//            finish()
//            true
//        }
//        R.id.photo_menu ->{
//            tirarFoto()
//            true
//        }
        android.R.id.home -> {
            finish()
            true
        }
        else -> false
    }

    private fun tirarFoto() {

        val vaiPraCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val caminhoFoto = defineLocalDaFoto()

        vaiPraCamera.putExtra(MediaStore.EXTRA_OUTPUT, caminhoFoto)

        startActivityForResult(vaiPraCamera, REQUEST_PHOTO)
    }

    private fun defineLocalDaFoto(): Uri? {
        localFoto = "${getExternalFilesDir("fotos")}/${System.currentTimeMillis()}.jpg"

        val arquivo = File(localFoto)

        return FileProvider.getUriForFile(this, "MeuProvider", arquivo)

    }

    private fun carregaFoto() {
        val bitmap = BitmapFactory.decodeFile(localFoto)
        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 300, 300, true)
        tweet_foto_form.setImageBitmap(scaledBitmap)
        val fotoNaBase64 = scaledBitmap.decodificarParaBase64()
        tweet_foto_form.tag = fotoNaBase64
        tweet_foto_form.scaleType = ImageView.ScaleType.FIT_XY
        card_foto_form.visibility = View.VISIBLE
    }

    private fun publicaTweet() {

        val (latitude, longitude) = gps.coordenadas()
        val dono = usuarioViewModel.usuarioDaSessao().value



        val tweet = Tweet(tweet_mensagem.text.toString(), tweet_foto_form.tag as String?, dono!!, latitude, longitude )

        viewModel.salva(tweet)

        // Qualquer das duas maneiras
        //Toast.makeText(this, tweet.toString(), Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "$tweet", Toast.LENGTH_SHORT).show()
        //Snackbar.make(this, "$tweet", Snackbar.LENGTH_SHORT).show()
    }

}
