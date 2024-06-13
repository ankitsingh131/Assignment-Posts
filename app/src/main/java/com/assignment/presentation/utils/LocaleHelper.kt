package com.assignment.presentation.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import com.assignment.presentation.constant.Constants.LANGUAGE_ARABIC
import com.assignment.presentation.constant.Constants.LANGUAGE_ENGLISH
import com.assignment.presentation.constant.Constants.PREF_FILE_NAME
import com.assignment.presentation.constant.Constants.SELECTED_LANGUAGE
import java.util.Locale

/**
 * Author: Ankit Singh
 * Package: com.assignment.presentation.utils
 * Project: EITC du Assignment
 **/
object LocaleHelper {

    private var sharedPreference: SharedPreferences? = null


    /**
     * This method sets the application locale based on device display language.
     * If the device display language is arabic/french and the app is opening for the first time,
     * then this method will update the resources with the current device language
     */
    fun setLocale(context: Context?): Context? {
        val nonNullContext = context ?: return null
        val deviceLanguage = getCurrentLanguage(
            nonNullContext
        ) ?: Locale.getDefault().language
        persistLanguagePreference(
            context,
            deviceLanguage
        )
        return if (arrayOf(
                LANGUAGE_ARABIC,
                LANGUAGE_ENGLISH
            ).contains(deviceLanguage)
        ) {
            updateResources(
                nonNullContext,
                deviceLanguage
            )
        } else {
            updateResources(
                nonNullContext,
                LANGUAGE_ENGLISH
            )
        }

    }


    /**
     * This method is used to set the new locale.
     * Say if you want to set the locale from UI, use this method
     * @param context : Activity Context
     * @param language: Language code of the selected language
     */
    fun setNewLocale(context: Context, language: String): Context? {
        return updateResources(context, language)
    }

    /**
     * This method will return the current language which is already set.
     * If not set, it will return english by default
     * @param context: Activity Context
     */
    fun getCurrentLanguage(context: Context?): String? {

        val currentLanguage: String?
        val nonNullContext = context?.let { it } ?: return null

        if (sharedPreference == null)
            sharedPreference =
                nonNullContext.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)

        val nonNullSharedPref = sharedPreference ?: return null

        currentLanguage = nonNullSharedPref.getString(SELECTED_LANGUAGE, LANGUAGE_ENGLISH)

        return currentLanguage
    }

    /**
     * Return true if current language is arabic
     */
    fun isArabic(context: Context): Boolean = getCurrentLanguage(context) == LANGUAGE_ARABIC

    /**
     * This method will saveClick the user selected language on shared preference
     * @param context : Activity Context
     * @param language: Language code
     */
    fun persistLanguagePreference(context: Context, language: String) {
        if (sharedPreference == null)
            sharedPreference = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
        val nonNullSharedPref = sharedPreference ?: return
        nonNullSharedPref.edit().putString(SELECTED_LANGUAGE, language).apply()

    }

    /**
     * This method will update the resources based on language code passed in.
     */
    fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)
        persistLanguagePreference(
            context,
            language
        )
        return context.createConfigurationContext(configuration)
    }

    fun notifyLanguageChange(activity: Activity) {
        activity.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        activity.finish()
        activity.startActivity(activity.intent)
    }
}