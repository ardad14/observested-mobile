package artem.kuptsov.observested.ui.fragment

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import artem.kuptsov.observested.R
import artem.kuptsov.observested.api.objects.GetPlacesForUser
import artem.kuptsov.observested.data.Place
import artem.kuptsov.observested.ui.MainActivity
import artem.kuptsov.observested.ui.adapter.PlacesListAdapter

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapsFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        val apiService = GetPlacesForUser.retrofitService;
        val authToken = MainActivity.getAuthToken()

        if (authToken != null) {
            apiService.getPlacesForUser(authToken).enqueue(object : Callback<ArrayList<Place>> {
                override fun onFailure(call: Call<ArrayList<Place>>, t: Throwable) {
                    println(t.message)
                    println(t.stackTrace)
                }

                override fun onResponse(
                    call: Call<ArrayList<Place>>,
                    response: Response<ArrayList<Place>>
                ) {
                    val places = response.body()!!

                    for (place in places) {
                        val marker = googleMap.addMarker(
                            MarkerOptions()
                                .position(LatLng(place.latitude, place.longitude))
                                .title(place.name)
                                .snippet(place.address)
                        )
                        if (marker != null) {
                            marker.tag = place
                        };
                    }

                    val homeLatLng = LatLng(places[0].latitude, places[0].longitude)
                    val zoomLevel = 13f
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(homeLatLng, zoomLevel))
                    //googleMap.setInfoWindowAdapter(CustomInfoWindowForGoogleMap(context!!))
                }
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}