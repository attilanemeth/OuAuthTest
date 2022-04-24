package com.example.oauthtest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.expensestracker.BaseFragment
import com.example.oauthtest.R
import com.example.oauthtest.databinding.FragmentUserBinding
import com.example.oauthtest.ui.main.viemodels.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserFragment : BaseFragment() {

    companion object {
        fun newInstance() = UserFragment()
    }

    private val viewModel: UserViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       return FragmentUserBinding.inflate(inflater).apply {
           this.lifecycleOwner = this@UserFragment.viewLifecycleOwner
           this.viewModel = this@UserFragment.viewModel
       }.root
    }

}