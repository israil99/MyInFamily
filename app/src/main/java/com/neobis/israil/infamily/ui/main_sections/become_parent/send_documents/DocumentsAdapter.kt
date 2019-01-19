package com.neobis.israil.infamily.ui.main_sections.become_parent.send_documents

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.StartApplication

import kotlinx.android.synthetic.main.item_document.view.*


class DocumentsAdapter(context:Context,val listener:Listener):
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val documentList = context.resources.getStringArray(R.array.documents_list)
    private val documnetDescriptoins = context.resources.getStringArray(R.array.documents_description_list)
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_document, p0, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return documentList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ViewHolder)?.bind(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(positon: Int) {
            if(StartApplication.sharedPreference.getString(positon.toString(),null) != null)
                itemView.imgCheck.setImageResource(R.drawable.ic_check_green)
            else
                itemView.imgCheck.setImageResource(R.drawable.ic_check_no)
            itemView.tvDocumentName.text = documentList.get(positon)
            itemView.tvDocumentDescription.text = documnetDescriptoins.get(positon)
            itemView.tag = positon
            itemView.setOnClickListener {
                val index = it.tag as Int
                listener.onItemSelectedAt(index)
            }
        }

    }


    interface Listener {
        fun onItemSelectedAt(positon: Int)
    }

}