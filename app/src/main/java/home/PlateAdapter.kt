package home

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.item_plate.view.*
import lib.Plate
import sample.R

class PlateAdapter(private val plates: List<Plate>) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plate, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = plates.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bindView(plates[position])
}

class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(plate: Plate) {
        val layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, plate.height)
        layoutParams.gravity = Gravity.CENTER_VERTICAL
        itemView.item_plate_color.layoutParams = layoutParams
        itemView.item_plate_color.setBackgroundColor(Color.parseColor(plate.color))
        itemView.item_plate_text.text = plate.weight.toString().replace(".0", "")
    }
}