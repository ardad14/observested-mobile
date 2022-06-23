package artem.kuptsov.observested.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import artem.kuptsov.observested.R
import artem.kuptsov.observested.api.objects.GetPlaceById
import artem.kuptsov.observested.api.objects.GetPlaceGeneralAnalytics
import artem.kuptsov.observested.data.Place
import artem.kuptsov.observested.ui.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment(val placeId: Int) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        getAnalytics(placeId)
        return view
    }

    private fun getAnalytics(placeId: Int) {
        val authToken = MainActivity.getAuthToken()

        val apiService = GetPlaceGeneralAnalytics.retrofitService

        if (authToken != null) {
            apiService.getPlaceGeneralAnalytics(authToken, placeId).enqueue(object : Callback<Place> {
                override fun onFailure(call: Call<Place>, t: Throwable) {
                    println(t.message)
                    println(t.stackTrace)
                }

                override fun onResponse(
                    call: Call<Place>,
                    response: Response<Place>
                ) {
                    println(response.code())
                    println(response.body())

                    if (response.code() == HttpURLConnection.HTTP_OK) {

                    }
                }
            })
        }
    }
}