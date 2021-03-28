package ru.thstdio.feature_login.impl.presentation.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment

abstract class TransparentDialog(@LayoutRes contentLayoutId: Int) :
    DialogFragment(contentLayoutId) {
    override fun onResume() {
        super.onResume()
        dialog?.window?.let { window ->
            val params: ViewGroup.LayoutParams = window.attributes
            params.width = ConstraintLayout.LayoutParams.MATCH_PARENT
            window.run {
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                attributes = params as WindowManager.LayoutParams

            }
            dialog?.setCancelable(false)
        }

    }
}