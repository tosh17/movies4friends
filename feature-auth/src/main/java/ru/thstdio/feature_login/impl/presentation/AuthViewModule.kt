package ru.thstdio.feature_login.impl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.thstdio.feature_login.impl.framework.firebase.AppAuth
import ru.thstdio.feature_login.impl.framework.firebase.FireBaseAuthResult

class AuthViewModule : ViewModel() {
    private val auth: AppAuth by lazy { AppAuth() }

    private val _state: MutableStateFlow<AuthScreenState> = MutableStateFlow(AuthScreenState.SingIn)
    val state: StateFlow<AuthScreenState> = _state.asStateFlow()

    fun singInWithGoogle(token: String?) {
        viewModelScope.launch {
            if (token == null) {
                _state.emit(AuthScreenState.SameThingWrong)
            } else {
                auth.loginWithGoogleToken(token) { result ->
                   val  state =  when(result){
                        is FireBaseAuthResult.Successful -> AuthScreenState.SameThingWrong
                        FireBaseAuthResult.NetworkError -> AuthScreenState.NetWorkError
                        else -> AuthScreenState.SameThingWrong
                    }
                    _state.tryEmit(state)
                }
            }

        }

    }

    fun singIn(email: String, password: String) {

    }

    fun singUp(email: String, password: String) {

    }

    fun resetPassword(email: String) {}


}