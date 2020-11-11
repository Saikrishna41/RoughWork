package com.tpinfo.roughwork.data.Repo

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.tpinfo.roughwork.R
import com.tpinfo.roughwork.data.Monster
import com.tpinfo.roughwork.utils.FileHelper
import com.tpinfo.roughwork.utils.TAG

class MonsterRepo(val app: Application) {

    var listType = Types.newParameterizedType(
        List::class.java,
        Monster::class.java
    )

    val monsterDatas = MutableLiveData<List<Monster>>()

    init {

        getText()
        if(isNetworkAvailable()) {

            Log.i(TAG, "Network available")
        }
    }

    fun getText() {

        val text = FileHelper.getTextFromRaw(app, R.raw.monster_data)

        val moshi = Moshi.Builder().build()

        val adapter: JsonAdapter<List<Monster>> = moshi.adapter(listType)

        val monsterData = adapter.fromJson(text)

        monsterDatas.postValue(monsterData ?: emptyList())


    }

    fun isNetworkAvailable(): Boolean {
        val cm = app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)

            if (capabilities != null) {

                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {

                    return true
                }
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                }
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                }
            }
        } else {

            return cm.activeNetworkInfo?.isConnectedOrConnecting ?: false


        }

        return false
    }


}