package com.example.oauthtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.example.expensestracker.BaseFragment
import com.example.oauthtest.ui.main.AppInitFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, AppInitFragment.newInstance())
                .commitNow()
        }
    }

    override fun onBackPressed() {
        val lastVisibleFragment = supportFragmentManager.fragments.lastOrNull { it.isVisible }
        if (lastVisibleFragment == null) {
            super.onBackPressed()
        } else {
            if (lastVisibleFragment.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                (lastVisibleFragment as? BaseFragment)?.onBackPressed()
            }
        }
    }
}