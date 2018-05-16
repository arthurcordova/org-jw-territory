package org.jw.territory.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jw.territory.R

class TerritoryAdapter(val list: MutableList<String>,
                       val onClick: (Int) -> Unit) : RecyclerView.Adapter<TerritoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_list_territory, parent, false)
            return ViewHolder(view)


    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageBitmap = list[position]
//        holder?.imageView?.setImageBitmap(imageBitmap)
//        holder?.imageClose?.setOnClickListener { onClick(position) }
    }


    fun filter(filtered: List<String>) {
        list.clear()
        list.addAll(filtered)
        notifyDataSetChanged()
    }

    fun restart() {
        list.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

//        var imageView: ImageView = view.imagePreview
//        var imageClose: ImageView = view.imageClose

    }

}