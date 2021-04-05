package ru.thstdio.feature_login.impl.framework.di

import dagger.Component
import ru.thstdio.feature_login.api.AuthFeatureApi
import ru.thstdio.feature_login.impl.presentation.AuthFragment
import ru.thstdio.feature_login.impl.presentation.dialogs.sing_up.SingUpDialog
import ru.thstdio.module_injector.di.PerFeature

@Component(
    modules = [AuthModule::class, AuthFeatureModule::class],
    dependencies = [AuthFeatureDependencies::class]
)
@PerFeature
abstract class AuthFeatureComponent : AuthFeatureApi {
    abstract fun inject(fragment: AuthFragment)
    abstract fun inject(dialog: SingUpDialog)

    companion object {
        fun initAndGet(dependencies: AuthFeatureDependencies): AuthFeatureComponent {
            return DaggerAuthFeatureComponent.builder()
                .authFeatureDependencies(dependencies)
                .build()
        }
    }
}