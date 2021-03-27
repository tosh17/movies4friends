package ru.thstdio.core_auth.firebase

sealed class FireBaseAuthResult {
    data class Successful(val token: String) : FireBaseAuthResult()
    object NetworkError : FireBaseAuthResult()
    object UserAlreadyExist : FireBaseAuthResult()
    object UserNotValid : FireBaseAuthResult()
    object UserPasswordWrong : FireBaseAuthResult()
    object Etc : FireBaseAuthResult()
}
