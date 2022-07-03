package artem.kuptsov.observested.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import artem.kuptsov.observested.R
import artem.kuptsov.observested.api.objects.GetPlaceProductAnalytics
import artem.kuptsov.observested.api.objects.GetPlacesForUser
import artem.kuptsov.observested.data.Place
import artem.kuptsov.observested.data.Product
import artem.kuptsov.observested.ui.MainActivity
import artem.kuptsov.observested.ui.adapter.ProductsListAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [InfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductFragment(val placeId: Int) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_product, container, false)
        getProducts(view, placeId)
        return view
    }

    private fun getProducts(view: View, placeId: Int) {
        val apiService = GetPlaceProductAnalytics.retrofitService;
        val authToken = MainActivity.getAuthToken()

        if (authToken != null) {
            apiService.getPlaceProductAnalytics(authToken, placeId).enqueue(object : Callback<ArrayList<Product>> {
                override fun onFailure(call: Call<ArrayList<Product>>, t: Throwable) {
                    println(t.message)
                    println(t.stackTrace)
                }

                override fun onResponse(
                    call: Call<ArrayList<Product>>,
                    response: Response<ArrayList<Product>>
                ) {
                    val listView = view.findViewById<ListView>(R.id.products_list)
                    val adapter = response.body()?.let { ProductsListAdapter(view.context, it) }
                    listView.adapter = adapter
                }
            })
        }
    }

}