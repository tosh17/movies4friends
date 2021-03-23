package ru.thstdio.feature_login.impl.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import ru.thstdio.feature_login.R
import ru.thstdio.feature_login.databinding.LoginFragmentBinding
import ru.thstdio.feature_login.impl.framework.result_contract.GoogleAuthResultContract

private const val GOOGLE_SIGN_IN: Int = 10234
const val requestIdToken =
    "695547972648-jf80uru7g60recjjt8mvjdtlkqb19p2e.apps.googleusercontent.com"

class AuthFragment : Fragment(R.layout.login_fragment) {
    private val myActionCall = registerForActivityResult(
        GoogleAuthResultContract(requestIdToken), ::loginWithGoogleToken
    )


    private val binding: LoginFragmentBinding by viewBinding(LoginFragmentBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonGoogle.setOnClickListener {
            myActionCall.launch(GOOGLE_SIGN_IN)
        }
        binding.buttonLogin.setOnClickListener {
            signInWithEmailAndPassword("tosh17@list.ru", "123456") }
    }

    private fun loginWithGoogleToken(token: String?) {
        val auth = Firebase.auth
        if (token == null) return
        val credential = GoogleAuthProvider.getCredential(token, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    Log.d("TAG", "signInWithCredential:success")
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    //updateUI(null)
                }
            }
    }

    private fun signInWithEmailAndPassword(email: String, password: String) {
        val auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")
                    val user = auth.currentUser

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                }
            }
    }

    private fun loginWithEmailAndPassword(email: String, password: String) {
        val auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success")
                    val user = auth.currentUser

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                }
            }

    }
}