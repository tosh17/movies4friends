package ru.thstdio.core_auth.firebase

import android.util.Log
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.lang.Exception

private const val TAG_LOG = "FireBase_Auth"

class AppAuth {
    private val auth: FirebaseAuth =  Firebase.auth

    fun signInWithGoogleToken(
        token: String,
        callback: (FireBaseAuthResult) -> Unit
    ) {
        val credential = GoogleAuthProvider.getCredential(token, null)
        try {
            auth.signInWithCredential(credential)
                .addOnCompleteListener { task ->
                    val result = if (task.isSuccessful) {
                        FireBaseAuthResult.Successful(auth.currentUser.uid)
                    } else {
                        Log.w(TAG_LOG, "signInWithCredential:failure", task.exception)
                        when (task.exception) {
                            is FirebaseNetworkException -> FireBaseAuthResult.NetworkError
                            else -> FireBaseAuthResult.Etc
                        }
                    }
                    callback(result)
                }
        }catch (e:Exception){
            Log.e(TAG_LOG, "signInWithCredential:failure", e)
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
                    FireBaseAuthResult.Successful(auth.currentUser.uid)
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

    fun singUpWithEmailAndPassword(
        email: String,
        password: String,
        callback: (FireBaseAuthResult) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                val result = if (task.isSuccessful) {
                    FireBaseAuthResult.Successful(auth.currentUser.uid)
                } else {
                    Log.w(TAG_LOG, "signInWithEmail:failure", task.exception)
                    when (task.exception) {
                        is FirebaseNetworkException -> FireBaseAuthResult.NetworkError
                        is FirebaseAuthInvalidCredentialsException -> FireBaseAuthResult.UserPasswordWrong
                        is FirebaseAuthUserCollisionException -> FireBaseAuthResult.UserAlreadyExist
                        else -> FireBaseAuthResult.Etc
                    }
                }
                callback(result)
            }
    }

    fun resetPassword(
        email: String,
        callback: (FireBaseAuthResult) -> Unit
    ) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                val result = if (task.isSuccessful) {
                    FireBaseAuthResult.Successful("")
                } else {
                    Log.w(TAG_LOG, "sendPasswordResetEmail:failure", task.exception)
                    when (task.exception) {
                        is FirebaseNetworkException -> FireBaseAuthResult.NetworkError
                        else -> FireBaseAuthResult.Etc
                    }
                }
                callback(result)
            }
    }

}