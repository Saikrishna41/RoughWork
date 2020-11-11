package com.tpinfo.roughwork.utils

import android.content.Context

class FileHelper {

    companion object {

        fun getTextFromRaw(context: Context, resId : Int) : String {

           return context.resources.openRawResource(resId).use {

                it.bufferedReader().use {

                    it.readText()
                }
            }
        }
    }
}