package com.example.oauthtest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.oauthtest.R
import com.example.oauthtest.databinding.FragmentAppInitBinding
import com.example.oauthtest.models.Navigation
import com.example.oauthtest.ui.main.viemodels.AppInitViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class AppInitFragment : Fragment() {

    companion object {
        fun newInstance() = AppInitFragment()
    }

    private val viewModel: AppInitViewModel by viewModel()
    private val jobs = arrayListOf<Job>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentAppInitBinding.inflate(inflater).root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        jobs += viewModel.navigation.onEach {
            when (it) {
                Navigation.LoginFragment -> {
                    val fragment = LoginFragment.newInstance()
                    val transition = parentFragmentManager.beginTransaction()
                    transition.replace(R.id.container, fragment, "LoginFragment")
                    transition.commit()
                }
                Navigation.UserFragment -> {
                    val fragment = UserFragment.newInstance()
                    val transition = parentFragmentManager.beginTransaction()
                    transition.replace(R.id.container, fragment, "UserFragment")
                    transition.commit()
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        viewModel.init()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        jobs.forEach {
            it.cancel()
        }
    }
}