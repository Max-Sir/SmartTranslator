package com.symaxd.qrcode.aquier.ui.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.symaxd.qrcode.aquier.ui.fragments.LoginFragment
import com.symaxd.qrcode.aquier.R
import com.symaxd.qrcode.aquier.ui.fragments.RegistrationFragment

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment.newInstance())
                .commitNow()
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.login_menu,menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.sign_up_menu_item ->{
//                val fragment = RegistrationFragment.newInstance()
//                supportFragmentManager.beginTransaction()
//                    .setReorderingAllowed(true)
//                    .replace(R.id.container,fragment)
//                    .commitNow()
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

    override fun onNavigateUp(): Boolean {
        return super.onNavigateUp()
    }
}