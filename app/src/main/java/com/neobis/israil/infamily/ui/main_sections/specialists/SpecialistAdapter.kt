package com.neobis.israil.infamily.ui.main_sections.specialists

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.model.Specialistest
import kotlinx.android.synthetic.main.item_specialist_list.view.*

class SpecialistAdapter (private var list: List<Specialistest>, var listener: Listener, var imageList:ArrayList<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): RecyclerView.ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_specialist_list,parent,false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position:Int){
        (holder as? ViewHolder)?.bind(position)
    }
    override fun getItemCount():Int = list.size

    inner class ViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!){
        fun bind(position:Int){

            val cardView = itemView.findViewById<CardView>(R.id.rootView1)
            val layoutParams = cardView.getLayoutParams() as ViewGroup.MarginLayoutParams
            layoutParams.bottomMargin = 8
            cardView.requestLayout()
            itemView.tv_title.text = list.get(position).field
            Glide.with(itemView.context).load(imageList.get(position)).into(itemView.iwImage2)

            itemView.tag = position
            itemView.setOnClickListener{v->
                val index = v.tag as Int
                listener.onItemSelectedAt(index)
            }
        }}
    interface Listener{
        fun onItemSelectedAt(position:Int)
    }
}