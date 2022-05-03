package com.adrian.core.domain.contants

import androidx.annotation.StringDef

object Preferences {
    @Retention(AnnotationRetention.SOURCE)
    @StringDef(
        USER_PREFS,
        USER_EMAIL,
        USER_PASS,
        USER_CREDENTIALS
    )

    annotation class Preferences

    const val USER_PREFS = "user_prefs"
    const val USER_EMAIL = "user_email"
    const val USER_PASS = "user_pass"
    const val USER_CREDENTIALS = "user_credentials"
}