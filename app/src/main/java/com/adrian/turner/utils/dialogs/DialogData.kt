package com.adrian.turner.utils.dialogs

data class DialogData(
    val title: String,
    val body: String,
    val primaryAction: DialogAction
) {
    var secondaryAction: DialogAction? = null

    constructor(
        title: String,
        body: String,
        primaryAction: DialogAction,
        secondaryAction: DialogAction
    ): this(title, body, primaryAction) {
        this.secondaryAction = secondaryAction
    }
}

data class DialogAction(val label: String, val action: () -> Unit)