package com.neobis.israil.infamily.ui.main_sections.become_parent

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.ui.main_sections.become_parent.send_documents.DocumentsActivity

import kotlin.properties.Delegates

@SuppressLint("ValidFragment")
class AdoptDialogFragment(serverStatus:String,context:Context): DialogFragment(){
    private var status = serverStatus
    private var tvStatus:TextView by Delegates.notNull()
    private var btnOk: Button by Delegates.notNull()

    private lateinit var rootView:View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.dialog_server_answer,null)
        init()

        return rootView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }
    private fun init(){
        tvStatus = rootView.findViewById(R.id.tvStatusText)
        btnOk = rootView.findViewById(R.id.btnOK)
        tvStatus.text= status
        if(!isApplicationChecked())
            btnOk.setOnClickListener {
                dismiss()
            }



    }

    private fun isApplicationChecked(): Boolean {
        if(tvStatus.text == getString(R.string.status4)){
            btnOk.setOnClickListener {
                startActivity(Intent(context, DocumentsActivity::class.java))
                btnOk.text = "Переотправить"
                dismiss()
            }
            return true
        }
        return false
    }

}
