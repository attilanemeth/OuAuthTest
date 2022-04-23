package com.example.oauthtest.ui.main

import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.expensestracker.BaseFragment
import com.example.oauthtest.databinding.FragmentMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private var _binding: FragmentMainBinding? = null

    private val viewModel: LoginViewModel by viewModel()
    private val loadingScreenHelper: LoadingScreenHelper by inject()
    private val binding get() = _binding!!
    private val jobs = arrayListOf<Job>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMainBinding.inflate(inflater).apply {
            _binding = this
            lifecycleOwner = this@LoginFragment.viewLifecycleOwner
            viewModel = this@LoginFragment.viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.test()
        jobs += viewModel.loadingScreen.onEach {
            if (it) {
                loadingScreenHelper.showLoadingScreen(requireContext())
            } else {
                loadingScreenHelper.dismissDialog()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}