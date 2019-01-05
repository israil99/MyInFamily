package com.neobis.israil.infamily.ui.main_sections.about_children

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.model.Section
import kotlinx.android.synthetic.main.card_section.view.*

class AboutChildrenAdapter(var list: MutableList<Section>, private var listener: Listener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_section, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder)
            holder.bind(position)

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {




        fun bind(position: Int) {
            val cardView = itemView.findViewById<CardView>(R.id.card_section)
            itemView.tv_title.text = list[position].title
            Glide.with(itemView.context)
                    .load(list.get(position).image)
                    .into(itemView.iwSection)
            itemView.tag = position
            itemView.setOnClickListener {
                val index = it.tag as Int

                listener.onItemSelectedAt(index)

            }

        }
    }


    interface Listener {
        fun onItemSelectedAt(index: Int) {

        }

    }
    fun setPostList(otherList: MutableList<Section>) {
        list = otherList
        notifyDataSetChanged()
    }
}

