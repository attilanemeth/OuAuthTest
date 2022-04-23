package com.example.oauthtest.ui.main


import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.example.oauthtest.R


class LoadingScreenHelper(private val context: Context ) {
    private var dialog: AlertDialog? = null

    fun showLoadingScreen(contextTest: Context) {
        val builder = AlertDialog.Builder(contextTest)
        val inflater = LayoutInflater.from(contextTest)
        builder.setView(inflater.inflate(R.layout.loading_screen, null))
        builder.setCancelable(false)
        dialog = builder.create()
        dialog!!.show()
    }

    fun dismissDialog() {
        dialog?.dismiss()
    }

}