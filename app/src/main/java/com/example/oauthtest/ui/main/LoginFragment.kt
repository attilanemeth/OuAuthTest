package com.example.oauthtest.ui.main

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.expensestracker.BaseFragment
import com.example.oauthtest.databinding.FragmentMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private var _binding: FragmentMainBinding? = null

    private val viewModel: LoginViewModel by viewModel()
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMainBinding.inflate(inflater).apply {
            _binding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.test()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}