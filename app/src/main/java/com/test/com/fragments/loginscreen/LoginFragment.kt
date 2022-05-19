package com.test.com.fragments.loginscreen

import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.test.com.R
import com.test.com.base.BaseFragment
import com.test.com.databinding.FragmentLoginBinding
import com.test.com.models.Login
import com.test.com.util.NetworkResult
import com.test.com.util.Preferences
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {
    private val binding: FragmentLoginBinding by viewBinding(FragmentLoginBinding::bind)
    val loginViewModel by viewModels<LoginViewModel>()

    @Inject
    lateinit var sharedPreferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!sharedPreferences.readBearerToken().isNullOrEmpty()){
            findNavController().navigate(R.id.action_loginFragment_to_productFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            if (binding.editLogin.text.isNullOrEmpty()) {
                binding.editLoginLayout.hintTextColor =
                    ContextCompat.getColorStateList(requireContext(), R.color.red)
            } else if (binding.editPassword.text.isNullOrEmpty()) {
                binding.editPasswordLayout.hintTextColor =
                    ContextCompat.getColorStateList(requireContext(), R.color.red)
            } else {
                val login =
                    Login(binding.editLogin.text.toString(), binding.editPassword.text.toString())
                lifecycleScope.launch {
                    loginViewModel.login(login)
                }
            }
        }
        loginViewModel.login.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                    val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
                    alertDialog.setTitle("Xatolik")
                    alertDialog.setMessage(it.message.toString())
                    alertDialog.show()
                }
                is NetworkResult.Loading -> {

                }
                is NetworkResult.Success -> {
                    it.data?.let { it1 -> sharedPreferences.saveBearerToken(it1.token) }
                }
            }
        }
    }
}