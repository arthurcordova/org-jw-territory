package org.jw.territory.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_list_territory.view.*
import org.jw.territory.R
import org.jw.territory.data.Territory

class TerritoryAdapter(val list: MutableList<Territory>,
                       val onClick: (Int) -> Unit) : RecyclerView.Adapter<TerritoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_list_territory, parent, false)
            return ViewHolder(view)


    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val territory = list[position]
        holder?.labelName.text = territory.name
//        val imageBitmap = list[position]

//        holder?.imageView?.setImageBitmap(imageBitmap)
//        holder?.imageClose?.setOnClickListener { onClick(position) }
    }


    fun filter(filtered: List<Territory>) {
        list.clear()
        list.addAll(filtered)
        notifyDataSetChanged()
    }

    fun restart() {
        list.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var labelName: TextView = view.labelName
//        var imageClose: ImageView = view.imageClose

    }

}