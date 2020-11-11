package com.tpinfo.roughwork.data.Repo

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.tpinfo.roughwork.R
import com.tpinfo.roughwork.data.Monster
import com.tpinfo.roughwork.utils.FileHelper

class MonsterRepo(val app: Application) {

    var listType = Types.newParameterizedType(
        List::class.java,
        Monster::class.java
    )

    val monsterDatas = MutableLiveData<List<Monster>>()

    init {

    }

    fun getText() {

        val text = FileHelper.getTextFromRaw(app, R.raw.monster_data)

        val moshi = Moshi.Builder().build()

        val adapter: JsonAdapter<List<Monster>> = moshi.adapter(listType)

        val monsterData = adapter.fromJson(text)

        monsterDatas.postValue(monsterData ?: emptyList())


    }
}