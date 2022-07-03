package artem.kuptsov.observested.ui.fragment

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import artem.kuptsov.observested.R
import artem.kuptsov.observested.api.objects.EditPlace
import artem.kuptsov.observested.api.objects.GetPlaceById
import artem.kuptsov.observested.api.objects.Login
import artem.kuptsov.observested.data.Place
import artem.kuptsov.observested.data.UpdatePlaceForm
import artem.kuptsov.observested.data.UpdatePlaceResponse
import artem.kuptsov.observested.data.user.UserLoginResponse
import artem.kuptsov.observested.ui.MainActivity
import artem.kuptsov.observested.ui.MainActivity.Companion.getAuthToken
import artem.kuptsov.observested.ui.PlacesListActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import java.text.SimpleDateFormat
import java.util.*

private const val PLACE_ID = "placeId"
/**
 * A simple [Fragment] subclass.
 * Use the [EditPlaceBottomDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditPlaceBottomDialogFragment : BottomSheetDialogFragment() {
    private var placeId: Int? = null
    private var calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            placeId = it.getInt(PLACE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_edit_place_bottom_dialog, container, false)
        getPlace(view, placeId!!)

        val name = view.findViewById<TextView>(R.id.editNameText)
        val address  = view.findViewById<TextView>(R.id.editAddressText)
        val whStart = view.findViewById<TextView>(R.id.editWhStartText)
        val whEnd = view.findViewById<TextView>(R.id.editWhEndText)
        val editSubmitButton = view.findViewById<Button>(R.id.submit)

        val timeSetListener1 = TimePickerDialog.OnTimeSetListener { view, minute, second ->
            calendar.set(Calendar.MINUTE, minute)
            calendar.set(Calendar.SECOND, second)
            updateDateInView(whStart)
        }

        val timeSetListener2 = TimePickerDialog.OnTimeSetListener { view, minute, second ->
            calendar.set(Calendar.MINUTE, minute)
            calendar.set(Calendar.SECOND, second)
            updateDateInView(whEnd)
        }

        whStart.setOnClickListener { view ->
            TimePickerDialog(
                view.context,
                timeSetListener1,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                true
            ).show()
        }

        whEnd.setOnClickListener { view ->
            TimePickerDialog(
                view.context,
                timeSetListener2,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                true
            ).show()
        }

        editSubmitButton.setOnClickListener {
            val apiService = EditPlace.retrofitService

            val placeData = UpdatePlaceForm(
                name.text.toString(),
                address.text.toString(),
                whStart.text.toString(),
                whEnd.text.toString(),
            )

            apiService.updatePlace(getAuthToken()!!, placeId!!, placeData).enqueue(object : Callback<UpdatePlaceResponse> {
                override fun onFailure(call: Call<UpdatePlaceResponse>, t: Throwable) {
                    println(t.message)
                    println(t.stackTrace)
                }

                override fun onResponse(
                    call: Call<UpdatePlaceResponse>,
                    response: Response<UpdatePlaceResponse>
                ) {

                    println(response.code())
                    println(response.body())

                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        val intent = Intent(view.context, PlacesListActivity::class.java)
                        startActivity(intent)
                    }
                }
            })
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(placeId: Int) =
            EditPlaceBottomDialogFragment().apply {
                arguments = Bundle().apply {
                    putInt(PLACE_ID, placeId)
                }
            }
    }

    private fun getPlace(view: View, placeId: Int) {
        val name: TextView = view.findViewById(R.id.editNameText)
        val address: TextView = view.findViewById(R.id.editAddressText)
        val whStart: TextView = view.findViewById(R.id.editWhStartText)
        val whEnd: TextView = view.findViewById(R.id.editWhEndText)
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
                        name.text = response.body()?.name
                        address.text = response.body()?.address
                        whStart.text = response.body()?.working_hours_start
                        whEnd.text = response.body()?.working_hours_end
                    }
                }
            })
        }
    }

    private fun updateDateInView(timePicker: TextView) {
        val myFormat = "HH:mm" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        timePicker.text = sdf.format(calendar.time)
    }
}