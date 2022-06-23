package artem.kuptsov.observested.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import artem.kuptsov.observested.R
import artem.kuptsov.observested.api.objects.GetPlacesForUser
import artem.kuptsov.observested.data.Place
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import artem.kuptsov.observested.ui.MainActivity.Companion.getAuthToken
import artem.kuptsov.observested.ui.adapter.PlacesListAdapter

class PlacesListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_places_list)

        val apiService = GetPlacesForUser.retrofitService;
        val authToken = getAuthToken()
        val context = this

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
                    val listView = findViewById<ListView>(R.id.places_list)
                    val adapter = response.body()?.let { PlacesListAdapter(context, it) }
                    listView.adapter = adapter
                }
            })
        }
    }
}