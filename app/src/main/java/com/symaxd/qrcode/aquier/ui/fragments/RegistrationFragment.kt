package com.symaxd.qrcode.aquier.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.symaxd.qrcode.aquier.R
import com.symaxd.qrcode.aquier.viewmodel.RegistrationViewModel

class RegistrationFragment : Fragment() {

    companion object {
        fun newInstance() = RegistrationFragment()
    }

    private lateinit var viewModel: RegistrationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        viewModel = ViewModelProvider(this).get(RegistrationViewModel::class.java)

        requireActivity().title = "Create Profile"
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.login_menu, menu)
        menu.findItem(R.id.sign_up_menu_item).title = "Log In"
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sign_up_menu_item -> {
                this.activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, LoginFragment.newInstance())
                    ?.commitNow()
            }
        }
        return super.onOptionsItemSelected(item)
    }


}