package ru.thstdio.feature_login.impl.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.thstdio.core_auth.result_contract.GoogleAuthResultContract
import ru.thstdio.feature_login.BuildConfig
import ru.thstdio.feature_login.R
import ru.thstdio.feature_login.databinding.LoginFragmentBinding
import ru.thstdio.feature_login.impl.framework.di.AuthFeatureComponentHolder
import ru.thstdio.feature_login.impl.presentation.dialogs.sing_up.SingUpDialog
import javax.inject.Inject

private const val GOOGLE_SIGN_IN: Int = 10234

class AuthFragment : Fragment(R.layout.login_fragment) {

    @Inject
    lateinit var viewModelFactory: AuthViewModelFactory
    private val viewModel: AuthViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(AuthViewModel::class.java)
    }
    private val googleCallBack = registerForActivityResult(
        GoogleAuthResultContract(BuildConfig.GOOGLE_REQUEST_ID_TOKEN)
    )
    { token -> onGoogleCallBack(token) }

    private val binding: LoginFragmentBinding by viewBinding(LoginFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AuthFeatureComponentHolder.getComponent().inject(this)
        binding.buttonGoogle.setOnClickListener {
            googleCallBack.launch(GOOGLE_SIGN_IN)
        }
        binding.buttonLogin.setOnClickListener {
            val login = binding.loginFormLoginEdit.text.toString()
            val password = binding.loginFormPasswordEdit.text.toString()
            viewModel.onClickSingIn(login, password)
        }
        binding.forgotPassword.setOnClickListener { }
        binding.singUp.setOnClickListener { SingUpDialog().show(parentFragmentManager,"123")}
    }

    private fun onGoogleCallBack(token: String?) {
        viewModel.onClickSingInWithGoogle(token)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        AuthFeatureComponentHolder.reset()
    }
}