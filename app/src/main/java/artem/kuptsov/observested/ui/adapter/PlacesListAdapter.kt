package artem.kuptsov.observested.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import artem.kuptsov.observested.R
import artem.kuptsov.observested.data.Place
import artem.kuptsov.observested.ui.BottomNavigationActivity

class PlacesListAdapter(
    private val context: Context,
    private val dataSource: ArrayList<Place>
) : BaseAdapter()  {

    @SuppressLint("ServiceCast")
    private val inflater: LayoutInflater
            = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = inflater.inflate(R.layout.activity_places_list_item, parent, false)

        //Get fields for list item
        val nameTextView = rowView.findViewById(R.id.name) as TextView
        val addressTextView = rowView.findViewById(R.id.address) as TextView
        val workingStartTextView = rowView.findViewById(R.id.working_hours_start) as TextView
        val workingEndTextView = rowView.findViewById(R.id.working_hours_end) as TextView
        val layout = rowView.findViewById(R.id.item_layout) as ConstraintLayout
        //Set up values for list item
        val place = getItem(position) as Place

        nameTextView.text = place.name
        addressTextView.text = place.address
        workingStartTextView.text = place.working_hours_start
        workingEndTextView.text = place.working_hours_end

        //Example clicker for list item layout
        layout.setOnClickListener {
            //Toast.makeText(context, "this is toast message", Toast.LENGTH_SHORT).show()
            val intent = Intent(context, BottomNavigationActivity::class.java)
            intent.putExtra("placeId", place.id)
            context.startActivity(intent)
        }

        return rowView
    }

}