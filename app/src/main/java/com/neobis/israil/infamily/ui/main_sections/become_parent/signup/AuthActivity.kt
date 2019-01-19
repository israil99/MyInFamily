package com.neobis.israil.infamily.ui.main_sections.become_parent.signup

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.Editable
import android.text.TextWatcher
import com.neobis.israil.infamily.BaseActivity
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.ui.main_sections.become_parent.send_documents.DocumentsActivity
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : BaseActivity(),AuthContract.View{


    private lateinit var presenter :AuthContract.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        init()
    }

    private fun init() {
        initPresenter()
        initButtonListeners()
        initInputLayoutTextWatcherListeners()
    }

    private fun initPresenter() {
        presenter = AuthPresenter(this)
    }

    private fun initButtonListeners() {
        btn_registration.setOnClickListener {
            val name = etLogin.text.toString()
            val mail = etMail.text.toString()
            val phone = etPhone.text.toString()
            presenter.checkInputFields(name,mail,phone)
        }
    }

    private fun initInputLayoutTextWatcherListeners() {
        etLogin.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                input_login.error = null
                input_login.isErrorEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        etMail.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                input_mail.error = null
                input_mail.isErrorEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        etPhone.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                input_phone.error = null
                input_phone.isErrorEnabled = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

    override fun onSuccessCheckFields() {
        presenter.saveUserData(etLogin.text.toString(),etMail.text.toString(),etPhone.text.toString())
    }

    override fun onIncorrectName() {
        input_login.error = getString(R.string.error_login)
        input_login.isErrorEnabled = true
    }

    override fun onIncorrectEmail() {
        input_mail.error = getString(R.string.error_mail)
        input_mail.isErrorEnabled = true
    }

    override fun onIncorrectPhone() {
        input_phone.error = getString(R.string.error_phone)
        input_phone.isErrorEnabled = true
    }
    override fun onSuccessUserDataSaved() {
        presenter.sendFirebaseToken()
    }

    override fun onSuccessTokenSending() {
        Snackbar.make(rootView,getString(R.string.success_registration),Snackbar.LENGTH_LONG).show()
        finishRegistration()
    }
    private fun finishRegistration(){
        startActivity(Intent(this, DocumentsActivity::class.java))
        finish()
    }
    override fun onFailureTokenSending() {
        Snackbar.make(rootView,getString(R.string.no_internet),Snackbar.LENGTH_LONG).setAction(R.string.ok,
                {finishRegistration()}).show()
    }







}