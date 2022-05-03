package com.adrian.core.data.framework.shared_preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.adrian.core.domain.contants.Constant
import com.adrian.core.domain.contants.Preferences
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

class EncryptedSharedPreferencesRepository(private val context: Context) {

    private var masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    private val prefs: SharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            context, "turner-e-preferences", masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    private val prefSubject by lazy {
        BehaviorSubject.createDefault(prefs)
    }

    private val prefChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, _ ->
            prefSubject.onNext(sharedPreferences)
        }

    init {
        prefs.registerOnSharedPreferenceChangeListener(prefChangeListener)
    }

    fun put(@Preferences.Preferences key: String, value: String): Completable = prefSubject
        .firstOrError()
        .editSharedPreferences {
            putString(key, value).apply()
        }


    fun clean() {
        prefSubject.firstOrError()
            .clearSharedPreferences {
                clear().apply()
            }
    }

    fun get(@Preferences.Preferences key: String): Observable<String> = prefSubject
        .map { pref ->
            pref.getString(key, Constant.EMPTY_STRING) ?: "Pref not found"
        }


    private fun Single<SharedPreferences>.editSharedPreferences(
        batch: SharedPreferences.Editor.() -> Unit
    ): Completable = flatMapCompletable {
        Completable.fromAction {
            it.edit().also(batch).apply()
        }
    }

    private fun Single<SharedPreferences>.clearSharedPreferences(
        batch: SharedPreferences.Editor.() -> Unit
    ): Completable = flatMapCompletable {
        Completable.fromAction {
            it.edit().also(batch).apply()
        }
    }
}