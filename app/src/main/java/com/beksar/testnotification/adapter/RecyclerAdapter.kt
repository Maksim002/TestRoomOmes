package com.example.testtask.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beksar.testnotification.R
import com.beksar.testnotification.adapter.listener.RecyclerViewListener
import com.beksar.testnotification.adapter.model.RecyclerModel
import com.timelysoft.tsjdomcom.common.GenericRecyclerAdapter
import com.timelysoft.tsjdomcom.common.ViewHolder
import kotlinx.android.synthetic.main.item_recycler_view.view.*

class RecyclerAdapter(var listener: RecyclerViewListener, var item: ArrayList<RecyclerModel> = arrayListOf()): GenericRecyclerAdapter<RecyclerModel>(item) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return super.onCreateViewHolder(parent, R.layout.item_recycler_view)
    }

    override fun bind(item: RecyclerModel, holder: ViewHolder) {
        holder.itemView.text_key.text = item.key.toString()
        holder.itemView.text_country.text = item.country.toString()
        holder.itemView.text_region.text = item.region.toString()
        holder.itemView.layout_recycler.setOnClickListener {
            listener.setOnClickListener(item)
        }
    }

    fun addItem(list: ArrayList<RecyclerModel>) {
        item.addAll(list)
        notifyDataSetChanged()
    }

    fun listUpdate(list: ArrayList<RecyclerModel>) {
        item.clear()
        notifyDataSetChanged()
        item = list
        notifyDataSetChanged()
    }
}