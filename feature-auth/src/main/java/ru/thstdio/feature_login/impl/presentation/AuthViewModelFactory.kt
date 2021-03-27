package ru.thstdio.feature_login.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.thstdio.feature_login.impl.framework.navigation.FeatureNavigator
import ru.thstdio.feature_login.impl.usecase.ResetPassword
import ru.thstdio.feature_login.impl.usecase.SingInWithEmail
import ru.thstdio.feature_login.impl.usecase.SingInWithGoogle
import ru.thstdio.feature_login.impl.usecase.SingUpWithEmail
import javax.inject.Inject

class AuthViewModelFactory @Inject constructor(
    private val singInWithGoogle: SingInWithGoogle,
    private val singInWithEmail: SingInWithEmail,
    private val singUpWithEmail: SingUpWithEmail,
    private val resetPassword: ResetPassword
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            AuthViewModel::class.java -> AuthViewModel(
                singInWithGoogle,
                singInWithEmail,
                singUpWithEmail,
                resetPassword
            )
            else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
        } as T

    }

}