package com.neobis.israil.infamily.ui.main_sections.become_parent

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.model.Section
import com.neobis.israil.infamily.ui.main_sections.become_parent.information.InformationActivity
import com.neobis.israil.infamily.ui.main_sections.become_parent.quiz.QuizActivity
import com.neobis.israil.infamily.ui.main_sections.become_parent.signup.AuthActivity
import kotlinx.android.synthetic.main.card_section.view.*

class BecomeParentAdapter(var list: MutableList<Section>, private var listener: Listener, private var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
            itemView.tv_title.text = list[position].title
            Glide.with(itemView.context)
                    .load(list.get(position).image)
                    .into(itemView.iwCategory)
            itemView.tag = position
            itemView.setOnClickListener {
                val index = it.tag as Int

                listener.onItemSelectedAt(index)

            }

        }
    }


    interface Listener {
        fun onItemSelectedAt(position: Int)
    }
    fun setPostList(otherList: MutableList<Section>) {
        list = otherList
        notifyDataSetChanged()
    }
}

