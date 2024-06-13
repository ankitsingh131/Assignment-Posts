package com.assignment.presentation.utils

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.annotation.ArrayRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

/**
 * Author: Ankit Singh
 * Package: com.assignment.presentation.utils
 * Project: EITC du Assignment
 **/
class ResourcesResolver(private val context: Context) {

    fun getString(@StringRes int: Int): String {
        return try {
            context.getString(int)
        } catch (e: Exception) {
            ""
        }
    }

    fun getString(@StringRes int: Int, vararg params: String): String? {
        return try {
            context.getString(int, *params)
        } catch (e: Exception) {
            ""
        }
    }

    fun getStringArray(@ArrayRes int: Int): Array<String> {
        return try {
            context.resources.getStringArray(int)
        } catch (e: Exception) {
            arrayOf()
        }
    }

    fun getTypeface(@FontRes font: Int): Typeface {
        return ResourcesCompat.getFont(context, font) ?: Typeface.DEFAULT
    }

    fun getDrawable(@DrawableRes int: Int): Drawable? {
        return try {
            ContextCompat.getDrawable(context, int)
        } catch (e: Exception) {
            null
        }
    }

    fun getAssets() = context.assets
}