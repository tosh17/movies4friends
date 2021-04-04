package ru.thstdio.core_data.navigation

import android.os.Bundle
import androidx.annotation.IdRes

interface AppRouter {
    fun openAuth()
    fun startMainScreen()
    fun openScreen(@IdRes screenId:Int, argument: Bundle)
}