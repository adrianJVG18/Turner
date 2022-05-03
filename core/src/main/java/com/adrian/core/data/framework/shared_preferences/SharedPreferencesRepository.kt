package com.adrian.core.data.framework.shared_preferences

import android.content.Context
import android.content.SharedPreferences
import com.adrian.core.domain.contants.Constant
import com.adrian.core.domain.contants.Preferences.Preferences
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject

class SharedPreferencesRepository(private val context: Context) {

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences("Turner", Context.MODE_PRIVATE)
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


    fun put(@Preferences key: String, value: String): Completable = prefSubject
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

    fun get(@Preferences key: String): Observable<String> = prefSubject
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