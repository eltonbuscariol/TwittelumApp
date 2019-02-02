package br.com.caelum.twittelumapp.gps

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.*

class GPS(context : Context) : LocationCallback(){

    private val client : FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    var lastLocation : Location? = null

    override fun onLocationResult(result: LocationResult?) {
        super.onLocationResult(result)
        lastLocation = result?.lastLocation
    }

    @SuppressLint("MissingPermission")
    fun fazBusca() {
        val request = LocationRequest()

        request.apply {
            interval = 1000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            smallestDisplacement = 10.0F
        }

        client.requestLocationUpdates(request, this, null)
    }

    fun cancela(){
        client.removeLocationUpdates(this)
    }

    fun coordenadas() : Pair<Double, Double> {
        val latitude = lastLocation?.latitude
        val longitude = lastLocation?.longitude
        return Pair(latitude ?: 0.0, longitude ?: 0.0)
    }
}