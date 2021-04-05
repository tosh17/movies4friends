package ru.thstdio.feature_login.impl.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.thstdio.core_auth.firebase.AppAuth
import ru.thstdio.core_auth.firebase.FireBaseAuthResult
import ru.thstdio.feature_login.impl.framework.navigation.FeatureNavigator
import ru.thstdio.feature_login.impl.usecase.ResetPassword
import ru.thstdio.feature_login.impl.usecase.SingInWithEmail
import ru.thstdio.feature_login.impl.usecase.SingInWithGoogle
import ru.thstdio.feature_login.impl.usecase.SingUpWithEmail

class AuthViewModel(
    private val singInWithGoogle: SingInWithGoogle,
    private val singInWithEmail: SingInWithEmail,
    private val singUpWithEmail: SingUpWithEmail,
    private val resetPassword: ResetPassword
) : ViewModel() {


    private val _state: MutableStateFlow<AuthScreenState> = MutableStateFlow(AuthScreenState.SingIn)
    val state: StateFlow<AuthScreenState> = _state.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.e("AuthViewModel", exception.toString())
    }

    fun onClickSingInWithGoogle(token: String?) {
        viewModelScope.launch(exceptionHandler) {
            if (token == null) {
                _state.emit(AuthScreenState.SameThingWrong)
            } else {
                val state = when (singInWithGoogle(token)) {
                    is FireBaseAuthResult.Successful -> AuthScreenState.SameThingWrong
                    FireBaseAuthResult.NetworkError -> AuthScreenState.NetWorkError
                    else -> AuthScreenState.SameThingWrong
                }
                _state.tryEmit(state)
            }
        }

    }

    fun onClickSingIn(email: String, password: String) {

    }

    fun onClickSingUp(email: String, password: String) {

    }

    fun onClickResetPassword(email: String) {}


}