package artem.kuptsov.observested.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import artem.kuptsov.observested.R
import artem.kuptsov.observested.ui.fragment.DashboardFragment
import artem.kuptsov.observested.ui.fragment.InfoFragment
import artem.kuptsov.observested.ui.fragment.PlaceFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val placeId = intent.getIntExtra("placeId", 0)

        setContentView(R.layout.activity_bottom_navigation)
        loadFragment(PlaceFragment(placeId))

        val bottomNav: BottomNavigationView = findViewById(R.id.navigation)
        bottomNav.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.dashboard -> {
                    loadFragment(DashboardFragment(placeId))
                    return@setOnNavigationItemReselectedListener
                }
                R.id.place -> {
                    loadFragment(PlaceFragment(placeId))
                    return@setOnNavigationItemReselectedListener
                }
                R.id.info -> {
                    loadFragment(InfoFragment())
                    return@setOnNavigationItemReselectedListener
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_layout, fragment)
            commit()
        }
    }
}