package br.com.caelum.twittelumapp.fragment

import android.arch.lifecycle.ViewModelProviders
import br.com.caelum.twittelumapp.viewmodel.Injetor
import br.com.caelum.twittelumapp.viewmodel.TweetViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapaFragment : SupportMapFragment(), OnMapReadyCallback {


    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders.of(this, Injetor).get(TweetViewModel::class.java)
    }

    override fun onResume() {
        super.onResume()
        getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        val lista = viewModel.tweets().value

        lista?.forEach { tweet ->
            val markerOptions = MarkerOptions()
            markerOptions.apply {
                position(LatLng(tweet.latitude, tweet.longitude))
                title(tweet.dono.nome)
            }

            googleMap?.addMarker(markerOptions)
        }
    }
}