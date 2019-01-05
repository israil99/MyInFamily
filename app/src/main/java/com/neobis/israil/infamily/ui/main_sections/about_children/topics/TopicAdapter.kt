package com.neobis.israil.infamily.ui.main_sections.about_children.topics

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.model.Category
import com.neobis.israil.infamily.model.Section
import kotlinx.android.synthetic.main.topic_item.view.*

class TopicAdapter(private var topicList:List<Category>, val listener:Listener):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.topic_item,viewGroup,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return topicList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolder)?.bind(position)
    }

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(position: Int){
            itemView.tvTitle.text = topicList.get(position).title
            Glide.with(itemView.context)
                    .load(topicList.get(position).image)
                    .into(itemView.imageView)
            itemView.tag = position
            itemView.setOnClickListener {
                val index = it.tag as Int
                listener.onItemSeletedAt(topicList.get(index))
            }

        }
    }


    interface Listener{
        fun onItemSeletedAt(position: Category)
    }
    fun setPostList(otherList: MutableList<Category>) {
        topicList = otherList
        notifyDataSetChanged()
    }
}