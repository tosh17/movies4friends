package ru.thstdio.feature_login.impl.usecase

import kotlinx.coroutines.suspendCancellableCoroutine
import ru.thstdio.core_auth.firebase.AppAuth
import ru.thstdio.core_auth.firebase.FireBaseAuthResult
import javax.inject.Inject
import kotlin.coroutines.resume

class SingUpWithEmail @Inject constructor(private val auth: AppAuth) {
    suspend operator fun invoke(email: String, password: String): FireBaseAuthResult {
        return suspendCancellableCoroutine { continuation ->
            auth.singUpWithEmailAndPassword(email,password) { result ->
                continuation.resume(result)
            }
        }
    }
}