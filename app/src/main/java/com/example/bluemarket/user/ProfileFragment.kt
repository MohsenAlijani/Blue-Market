package com.example.bluemarket.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.bluemarket.R
import com.example.bluemarket.utils.MyFragment
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileFragment : MyFragment() {

    val profileViewModel: ProfileViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loginView = view.findViewById<ConstraintLayout>(R.id.constraint_profile_login)
        val signUpView = view.findViewById<ConstraintLayout>(R.id.constraint_profile_signUp)
        val signUpSuccessView = view.findViewById<ConstraintLayout>(R.id.constraint_profile_successful_signup)
        val loginSuccessView = view.findViewById<ConstraintLayout>(R.id.constraint_profile_welcome)

        val txtLoginError = view.findViewById<TextView>(R.id.txt_login_error)
        val txtSignUpHere = view.findViewById<TextView>(R.id.txt_profile_signUp)

        val edtUser = view.findViewById<TextInputEditText>(R.id.edt_login_username)
        val edtPass = view.findViewById<TextInputEditText>(R.id.edt_login_password)
        val btnLogin = view.findViewById<Button>(R.id.btn_login)
        val btnSignUp = view.findViewById<Button>(R.id.btn_signup)
        val btnLoginSuccessfulSignUp = view.findViewById<Button>(R.id.btn_successful_signup)

        btnLogin.setOnClickListener {
            if (edtUser.text.isNullOrBlank() || edtPass.text.isNullOrBlank()) {
                txtLoginError.visibility = View.VISIBLE
            } else {
                txtLoginError.visibility = View.INVISIBLE
                loginView.visibility = View.GONE
                loginSuccessView.visibility = View.VISIBLE
            }
        }

        btnLoginSuccessfulSignUp.setOnClickListener {
            if (edtUser.text.isNullOrBlank() || edtPass.text.isNullOrBlank()) {
                txtLoginError.visibility = View.VISIBLE
            } else {
                txtLoginError.visibility = View.INVISIBLE
                loginView.visibility = View.GONE
                loginSuccessView.visibility = View.VISIBLE
            }
        }

//        profileViewModel.loginLiveData.observe(viewLifecycleOwner) {
//            if (it) {
//                loginView.visibility = View.GONE
//                loginSuccessView.visibility = View.VISIBLE
//            } else {
//                txtLoginError.visibility = View.VISIBLE
//            }
//        }

        txtSignUpHere.setOnClickListener {
            loginView.visibility = View.GONE
            signUpView.visibility = View.VISIBLE
        }

        btnSignUp.setOnClickListener {
            signUpView.visibility = View.GONE
            signUpSuccessView.visibility = View.VISIBLE
        }


    }
}