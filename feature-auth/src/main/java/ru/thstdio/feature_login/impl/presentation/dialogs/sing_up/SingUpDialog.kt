package ru.thstdio.feature_login.impl.presentation.dialogs.sing_up

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.coroutines.launch
import ru.thstdio.core_auth.firebase.FireBaseAuthResult
import ru.thstdio.feature_login.R
import ru.thstdio.feature_login.databinding.SingUpDialogBinding
import ru.thstdio.feature_login.impl.framework.di.AuthFeatureComponentHolder
import ru.thstdio.feature_login.impl.presentation.dialogs.TransparentDialog
import ru.thstdio.feature_login.impl.usecase.SingUpWithEmail
import java.lang.Exception
import javax.inject.Inject

class SingUpDialog : TransparentDialog(R.layout.sing_up_dialog) {
    private val binding: SingUpDialogBinding by viewBinding(SingUpDialogBinding::bind)

    @Inject
    lateinit var singUp: SingUpWithEmail
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AuthFeatureComponentHolder.getComponent().inject(this)
        binding.btnCancel.setOnClickListener { dismiss() }
        binding.formPasswordEdit.doOnTextChanged { text, _, _, _ ->
            binding.formPassword.error = ""
        }
        binding.formPasswordConfirmEdit.doOnTextChanged { text, _, _, _ ->
            binding.formPassword.error = ""
        }
        binding.btnSingUp.setOnClickListener { onClickSingUp() }
    }

    private fun onClickSingUp() {
        lifecycleScope.launch {
            val login = binding.formLoginEdit.text.toString()
            val password = binding.formPasswordEdit.text.toString()
            val passwordConfirm = binding.formPasswordConfirmEdit.text.toString()
            if (password != passwordConfirm || password.isEmpty()) {
                binding.formPassword.error = "Пароли должны совпадать"
            } else {
                Toast.makeText(
                    requireContext(),
                    singUp(login, password)::class.java.canonicalName, Toast.LENGTH_LONG
                ).show()
            }

        }
    }
}