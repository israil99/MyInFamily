package com.neobis.israil.infamily.ui.main_sections.become_parent

import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.app.DialogFragment
import android.util.Log
import com.neobis.israil.infamily.BaseActivity
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.model.DocumentStatus
import com.neobis.israil.infamily.model.Section
import com.neobis.israil.infamily.utill.Const
import kotlinx.android.synthetic.main.activity_become_parent.*

class BecomeParentActivity : BaseActivity(),BecomeParentAdapter.Listener ,BecomeParentContract.View{

    private var list: MutableList<Section> = ArrayList()


    private lateinit var adapter :BecomeParentAdapter
    private lateinit var presenter:BecomeParentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_become_parent)
        checkForClickFromNotification()
        init()
    }

    private fun checkForClickFromNotification() {
        if(intent.getStringExtra(Const.NOTIFCATION_INTENT) == "PerformClick")
            presenter.checkApplicaitonStatus()
    }

    private fun init(){
        initPresenter()
        initRecyclerView()
    }
    private fun initPresenter(){
        presenter = BecomeParentPresenter(this,this)
        presenter.loadSections(3)
    }

    private fun initRecyclerView() {
        adapter = BecomeParentAdapter(list, this,this)
        recyclerView2.adapter = adapter
    }

    override fun onSuccess(result: MutableList<Section>) {
        this.list.addAll(result)
        adapter?.setPostList(this.list)
        recyclerView2.adapter = adapter
    }

    override fun onError(message: String?) {
        Log.d("Error",message)
    }

    override fun onItemSelectedAt(positon: Int) {
        presenter.startActivity(positon)

    }
    override fun onAlreadyApplicationSend() {
        presenter.checkApplicaitonStatus()

    }
    override fun onFailureConnnectedWithServer() {


    }

    override fun onSuccessApplicationStatusChecked(documentStatus: DocumentStatus) {
        showDialogStatus(presenter.determineStatus(documentStatus))
    }



    private fun showDialogStatus(serverStatus: String) {
        val ft = supportFragmentManager.beginTransaction()
        val dialog: DialogFragment = AdoptDialogFragment(serverStatus,this)
        dialog.show(ft,Const.TAG_FOR_SHOW_DIALOG_FRAGMENT)

    }


}
