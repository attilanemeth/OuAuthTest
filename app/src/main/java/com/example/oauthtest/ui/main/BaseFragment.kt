package com.example.expensestracker

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    private val TAG = "FragmentLifecycle"

    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
        Log.d(TAG, javaClass.simpleName + " - onInflate")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, javaClass.simpleName + " - onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, javaClass.simpleName + " - onViewCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, javaClass.simpleName + " - onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, javaClass.simpleName + " - onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, javaClass.simpleName + " - onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, javaClass.simpleName + " - onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, javaClass.simpleName + " - onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, javaClass.simpleName + " - onDetach")
    }
}