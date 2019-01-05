package com.neobis.israil.infamily.ui.main_sections

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.model.MainSection
import com.neobis.israil.infamily.ui.main_sections.about_children.AboutChildrenActivity
import com.neobis.israil.infamily.ui.main_sections.already_parent.AlreadyParentActivity
import com.neobis.israil.infamily.ui.main_sections.become_parent.BecomeParentActivity
import kotlinx.android.synthetic.main.card_category.view.*


class MainAdapter(var list: List<MainSection>, private var listener: Listener, private var context:Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_category, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder)
            holder.bind(position)

        holder.itemView.setOnClickListener{
          val intent:Intent
            if (position==0){
                intent = Intent(context,BecomeParentActivity::class.java)
                context.startActivity(intent)
            }else if (position==1){
                intent = Intent(context,AboutChildrenActivity::class.java)
                context.startActivity(intent)
            }else if(position==2){
                intent = Intent(context,AlreadyParentActivity::class.java)
                context.startActivity(intent)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {




        fun bind(position: Int) {
            val carView = itemView.findViewById<CardView>(R.id.card_category)
            carView.setBackgroundResource(R.drawable.main_rectangle)
            itemView.tv_title.text = list[position].title
            itemView.image_category.setBackgroundResource(list[position].imageId)
            itemView.tag = position
/*            itemView.setOnClickListener {
                val index = it.tag as Int

                listener.onItemSelectedAt(index)

            }*/

        }
    }


interface Listener {
    fun onItemSelectedAt(index: Int) {
    }

}
}
