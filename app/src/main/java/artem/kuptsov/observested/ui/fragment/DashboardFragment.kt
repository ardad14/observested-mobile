package artem.kuptsov.observested.ui.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import artem.kuptsov.observested.R
import artem.kuptsov.observested.api.objects.GetPlaceGeneralAnalytics
import artem.kuptsov.observested.data.Customer
import artem.kuptsov.observested.data.Place
import artem.kuptsov.observested.ui.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

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
                        val customers: ArrayList<Customer> = response.body()?.customers!!
                        var attendsMonth = 0
                        var moneyMonth = 0
                        var attendsAllTime = 0
                        var moneyAllTime = 0
                        val attendsMonthCount = view?.findViewById<TextView>(R.id.attendMonthCount)
                        val moneyMonthCount = view?.findViewById<TextView>(R.id.moneyMonthCount)
                        val attendsAllTimeCount = view?.findViewById<TextView>(R.id.attendAllCount)
                        val moneyAllTimeCount = view?.findViewById<TextView>(R.id.moneyAllCount)

                        for (customer in customers) {
                            attendsAllTime += 1
                            moneyAllTime += customer.pivot!!.spend_money

                            val localDate: LocalDate =
                                Date(customer.pivot?.created_at?.getTime()!!).toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                            val month = localDate.monthValue
                            if (month == LocalDate.now().monthValue) {
                                attendsMonth += 1
                                moneyMonth += customer.pivot!!.spend_money
                            }
                        }

                        attendsMonthCount?.setText(attendsMonth.toString())
                        val progressBarAttendsMonth = view?.findViewById<ProgressBar>(R.id.attendMonthPlaceBar)
                        progressBarAttendsMonth?.max = (attendsMonth + 2 * attendsMonth) * 1000 //Multiply for 1000 make smooth animation
                        ObjectAnimator.ofInt(progressBarAttendsMonth, "progress", attendsMonth * 1000) //Multiply for 1000 make smooth animation
                            .setDuration(1000)
                            .start()

                        moneyMonthCount?.setText(moneyMonth.toString())
                        val progressBarMoneyMonth = view?.findViewById<ProgressBar>(R.id.moneyMonthPlaceBar)
                        progressBarMoneyMonth?.max = (moneyMonth + 2 * moneyMonth) * 1000 //Multiply for 1000 make smooth animation
                        ObjectAnimator.ofInt(progressBarMoneyMonth, "progress", moneyMonth * 1000) //Multiply for 1000 make smooth animation
                            .setDuration(1000)
                            .start()

                        attendsAllTimeCount?.setText(attendsAllTime.toString())
                        val progressBarAttendsAllTime = view?.findViewById<ProgressBar>(R.id.attendAllPlaceBar)
                        progressBarAttendsAllTime?.max = (attendsMonth + 2 * attendsMonth) * 1000 //Multiply for 1000 make smooth animation
                        ObjectAnimator.ofInt(progressBarAttendsAllTime, "progress", attendsMonth * 1000) //Multiply for 1000 make smooth animation
                            .setDuration(1000)
                            .start()

                        moneyAllTimeCount?.setText(moneyAllTime.toString())
                        val progressBarMoneyAllTime = view?.findViewById<ProgressBar>(R.id.moneyAllPlaceBar)
                        progressBarMoneyAllTime?.max = (moneyMonth + 2 * moneyMonth) * 1000 //Multiply for 1000 make smooth animation
                        ObjectAnimator.ofInt(progressBarMoneyAllTime, "progress", moneyMonth * 1000) //Multiply for 1000 make smooth animation
                            .setDuration(1000)
                            .start()
                    }
                }
            })
        }
    }
}