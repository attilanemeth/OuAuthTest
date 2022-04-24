package com.example.oauthtest.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.example.expensestracker.BaseFragment
import com.example.oauthtest.R
import com.example.oauthtest.databinding.FragmentMainBinding
import com.example.oauthtest.models.Navigation
import com.example.oauthtest.ui.main.viemodels.LoginViewModel
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

        jobs += viewModel.error.onEach {
            createGeneralErrorPopup(requireContext(),it ?: "Error").show()

        }.launchIn(viewLifecycleOwner.lifecycleScope)

        jobs += viewModel.navigation.onEach {
            when(it){
                Navigation.UserFragment -> {
                    val fragment = UserFragment.newInstance()
                    val transition = parentFragmentManager.beginTransaction()
                    transition.setCustomAnimations(
                        R.anim.fragment_default_enter,
                        R.anim.fragment_default_exit,
                        R.anim.fragment_default_enter,
                        R.anim.fragment_default_exit
                    )
                    transition.replace(R.id.container, fragment, "UserFragment")
                    transition.commit()
                }
                else  -> {
                    Log.d("fsf","fsfs")
                }
            }
        }.launchIn(lifecycleScope)
    }


    private fun createGeneralErrorPopup(context: Context,msg:String) = AlertDialog.Builder(context).apply {
        setTitle("Error")
        setMessage(msg)
        setNeutralButton("Ok") { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        jobs.forEach {
            it.cancel()
        }
        _binding = null
    }
}