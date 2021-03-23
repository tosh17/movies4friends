package ru.thstdio.feature_login.impl.framework.firebase

import android.util.Log
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private const val TAG_LOG = "FireBase_Auth"

class AppAuth {
    private val auth: FirebaseAuth by lazy { Firebase.auth }

     fun loginWithGoogleToken(
        token: String,
        callback: (FireBaseAuthResult) -> Unit
    ) {
        val credential = GoogleAuthProvider.getCredential(token, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                val result = if (task.isSuccessful) {
                    FireBaseAuthResult.Successful(auth.currentUser.providerId)
                } else {
                    Log.w(TAG_LOG, "signInWithCredential:failure", task.exception)
                    when (task.exception) {
                        is FirebaseNetworkException -> FireBaseAuthResult.NetworkError
                        else -> FireBaseAuthResult.Etc
                    }
                }
                callback(result)
            }
    }

     fun signInWithEmailAndPassword(
        email: String,
        password: String,
        callback: (FireBaseAuthResult) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                val result = if (task.isSuccessful) {
                    FireBaseAuthResult.Successful(auth.currentUser.providerId)
                } else {
                    Log.w(TAG_LOG, "createUserWithEmail:failure", task.exception)
                    when (task.exception) {
                        is FirebaseNetworkException -> FireBaseAuthResult.NetworkError
                        is FirebaseAuthInvalidCredentialsException -> FireBaseAuthResult.UserNotValid
                        is FirebaseAuthUserCollisionException -> FireBaseAuthResult.UserAlreadyExist
                        else -> FireBaseAuthResult.Etc
                    }
                }
                callback(result)
            }
    }

     fun loginWithEmailAndPassword(
        email: String,
        password: String,
        callback: (FireBaseAuthResult) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    FireBaseAuthResult.Successful(auth.currentUser.providerId)
                } else {
                    Log.w(TAG_LOG, "signInWithEmail:failure", task.exception)
                    when (task.exception) {
                        is FirebaseNetworkException -> FireBaseAuthResult.NetworkError
                        is FirebaseAuthInvalidCredentialsException -> FireBaseAuthResult.UserPasswordWrong
                        is FirebaseAuthUserCollisionException -> FireBaseAuthResult.UserAlreadyExist
                        else -> FireBaseAuthResult.Etc
                    }
                }
            }
    }

}