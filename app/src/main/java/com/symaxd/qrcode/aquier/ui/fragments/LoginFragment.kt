package com.symaxd.qrcode.aquier.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.symaxd.qrcode.aquier.R
import com.symaxd.qrcode.aquier.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        requireActivity().title = "Sign In"
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
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