package com.adrian.turner.utils.dialogs

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class BaseDialog : DialogFragment() {

    // TODO add styles of the warning, error and information dialogs
    fun warning(context: Context, dialogData: DialogData) {
        return buildDialog(context, dialogData).show()
    }

    fun error(context: Context, dialogData: DialogData) {
        return buildDialog(context, dialogData).show()
    }

    fun information(context: Context, dialogData: DialogData) {
        return buildDialog(context, dialogData).show()
    }

    private fun buildDialog(context: Context, dialogData: DialogData): android.app.Dialog =
        if (dialogData.secondaryAction == null) {
            with(dialogData) {
                AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(body)
                    .setPositiveButton(primaryAction.label) { dialog, which ->
                        primaryAction.action.invoke()
                    }
                    .create()
            }
        } else {
            with(dialogData) {
                AlertDialog.Builder(context)
                    .setTitle(title)
                    .setMessage(body)
                    .setPositiveButton(primaryAction.label) { dialog, which -> primaryAction.action.invoke() }
                    .setNegativeButton(secondaryAction?.label) { dialog, which -> secondaryAction?.action?.invoke() }
                    .create()
            }
        }
}