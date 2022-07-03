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
import artem.kuptsov.observested.data.Product
import artem.kuptsov.observested.ui.BottomNavigationActivity

class ProductsListAdapter(
    private val context: Context,
    private val dataSource: ArrayList<Product>
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
        val rowView = inflater.inflate(R.layout.activity_products_list_item, parent, false)

        //Get fields for list item
        val nameTextView = rowView.findViewById(R.id.name) as TextView
        val availableAmountTextView = rowView.findViewById(R.id.availableAmountText) as TextView
        val priceTextView = rowView.findViewById(R.id.priceText) as TextView
        val soldTextView = rowView.findViewById(R.id.soldText) as TextView
        val purchaseTextView = rowView.findViewById(R.id.purchaseText) as TextView
        val layout = rowView.findViewById(R.id.item_layout) as ConstraintLayout
        //Set up values for list item
        val product = getItem(position) as Product

        nameTextView.text = product.name

        //Example clicker for list item layout
        layout.setOnClickListener {
            //Toast.makeText(context, "this is toast message", Toast.LENGTH_SHORT).show()
            /*val intent = Intent(context, BottomNavigationActivity::class.java)
            intent.putExtra("placeId", place.id)
            context.startActivity(intent)*/
        }

        return rowView
    }

}