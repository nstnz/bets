package dev.nst.core.presentation.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import dev.nst.core.R

abstract class BaseDialog(
    context: Context,
) : Dialog(context, true, null) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        requestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    override fun show() {
        super.show()
        window?.setLayout(context.resources.getDimensionPixelSize(R.dimen.dialog_width), ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}