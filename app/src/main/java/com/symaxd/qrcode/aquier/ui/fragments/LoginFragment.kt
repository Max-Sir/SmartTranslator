package com.symaxd.qrcode.aquier.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.symaxd.qrcode.aquier.BuildConfig
import com.symaxd.qrcode.aquier.R
import com.symaxd.qrcode.aquier.databinding.FragmentLoginBinding
import com.symaxd.qrcode.aquier.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    @Inject
    lateinit var viewModel: LoginViewModel

    private lateinit var binding: FragmentLoginBinding

    @Suppress("DEPRECATION")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_login, container, false)

        binding.login.setOnClickListener {
            viewModel.loginUser(binding.userLoginInput.text.toString())
        }

        viewModel.userNoFoundEvent.observe(viewLifecycleOwner) {
            if (BuildConfig.ALLOW_TEST_FEATURES && it) {
                Toast.makeText(this.activity, "Not found", Toast.LENGTH_LONG).show()
                viewModel.endUserNoFoundEvent()
            }
        }
        viewModel.user.observe(viewLifecycleOwner) {
            if (BuildConfig.ALLOW_TEST_FEATURES)
                Toast.makeText(this.requireContext(), it.toString(), Toast.LENGTH_LONG).show()
        }

        requireActivity().title = "Sign In"
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.login_menu, menu)
        menu.findItem(R.id.sign_up_menu_item).title = "Sign Up"
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sign_up_menu_item -> {
                this.requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RegistrationFragment.newInstance())
                    .commitNow()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}