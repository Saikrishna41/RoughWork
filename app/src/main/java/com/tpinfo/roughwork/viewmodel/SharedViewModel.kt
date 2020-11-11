package com.tpinfo.roughwork.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.tpinfo.roughwork.data.Repo.MonsterRepo

class SharedViewModel(app: Application) : AndroidViewModel(app){

    val mRepo = MonsterRepo(app)

    val monsterDatas = mRepo.monsterDatas
}