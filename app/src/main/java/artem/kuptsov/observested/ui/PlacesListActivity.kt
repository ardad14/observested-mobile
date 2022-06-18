package artem.kuptsov.observested.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import artem.kuptsov.observested.R
import artem.kuptsov.observested.api.objects.GetPlacesForUser
import artem.kuptsov.observested.data.Place
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import artem.kuptsov.observested.ui.MainActivity.Companion.getAuthToken

class PlacesListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places_list)

        // use arrayadapter and define an array
        val arrayAdapter: ArrayAdapter<*>
        val places = arrayOf(getPlaces())

        val mListView = findViewById<ListView>(R.id.places_list)
        arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1, places
        )
        mListView.adapter = arrayAdapter
    }

    private fun getPlaces(): ArrayList<Place> {
        val places = ArrayList<Place>()
        val apiService = GetPlacesForUser.retrofitService;
        val authToken = getAuthToken()

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
                    println(response.body())
                    response.body()?.let { places.addAll(it) }
                }
            })
        }

        return places
    }
}