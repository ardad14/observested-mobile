package artem.kuptsov.observested.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import artem.kuptsov.observested.R
import artem.kuptsov.observested.api.objects.GetPlaceById
import artem.kuptsov.observested.data.Place
import artem.kuptsov.observested.data.user.UserLoginResponse
import artem.kuptsov.observested.ui.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * A simple [Fragment] subclass.
 * Use the [PlaceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlaceFragment(private val placeId: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_place, container, false)
        getPlace(view, placeId)

        val editIcon = view.findViewById<ImageView>(R.id.edit)

        val editPlaceFragment = EditPlaceBottomDialogFragment()
        val args = Bundle()
        args.putInt("placeId", placeId)
        editPlaceFragment.arguments = args

        editIcon.setOnClickListener {
            editPlaceFragment.show(requireActivity().getSupportFragmentManager(), "EditPlace")
        }

        return view
    }

    private fun getPlace(view: View, placeId: Int) {
        val name: TextView = view.findViewById<TextView>(R.id.nameText)
        val address: TextView = view.findViewById<TextView>(R.id.addressText)
        val wtStart: TextView = view.findViewById<TextView>(R.id.wtStartText)
        val wtEnd: TextView = view.findViewById<TextView>(R.id.wtEndText)
        val authToken = MainActivity.getAuthToken()

        val apiService = GetPlaceById.retrofitService

        if (authToken != null) {
            apiService.getPlaceById(authToken, placeId).enqueue(object : Callback<Place> {
                override fun onFailure(call: Call<Place>, t: Throwable) {
                    println(t.message)
                    println(t.stackTrace)
                }

                override fun onResponse(
                    call: Call<Place>,
                    response: Response<Place>
                ) {
                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        name.setText(response.body()?.name)
                        address.setText(response.body()?.address)
                        wtStart.setText(response.body()?.working_hours_start)
                        wtEnd.setText(response.body()?.working_hours_end)
                    }
                }
            })
        }
    }
}