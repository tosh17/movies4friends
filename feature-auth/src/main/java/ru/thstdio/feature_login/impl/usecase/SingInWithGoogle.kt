package ru.thstdio.feature_login.impl.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import ru.thstdio.core_auth.firebase.AppAuth
import ru.thstdio.core_auth.firebase.FireBaseAuthResult
import javax.inject.Inject
import kotlin.coroutines.resume

class SingInWithGoogle @Inject constructor(private val auth: AppAuth) {
    @ExperimentalCoroutinesApi
   suspend operator fun invoke(token: String): FireBaseAuthResult {
         return suspendCancellableCoroutine { continuation ->
             auth.signInWithGoogleToken(token) { result ->
                continuation.resume(result)
            }
        }
    }
}