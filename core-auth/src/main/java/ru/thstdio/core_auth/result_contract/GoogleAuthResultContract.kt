package ru.thstdio.core_auth.result_contract

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class GoogleAuthResultContract(private val requestIdToken: String) :
    ActivityResultContract<Int, String?>() {

    override fun createIntent(context: Context, input: Int?): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(requestIdToken)
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        return googleSignInClient.signInIntent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
        return try {
            // Google Sign In was successful, authenticate with Firebase
            val account = task.getResult(ApiException::class.java)!!
            Log.d("GoogleAuthContract", "firebaseAuthWithGoogle:" + account.id)
            account.idToken!!
        } catch (e: ApiException) {
            // Google Sign In failed, update UI appropriately
            Log.w("GoogleAuthContract", "Google sign in failed", e)
            null
        }
    }
}