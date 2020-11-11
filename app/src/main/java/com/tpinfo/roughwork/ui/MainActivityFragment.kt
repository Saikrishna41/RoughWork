package com.tpinfo.roughwork.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tpinfo.roughwork.R
import com.tpinfo.roughwork.utils.TAG
import com.tpinfo.roughwork.viewmodel.SharedViewModel


class MainActivityFragment : Fragment() {

    private lateinit var viewModel : SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view =  inflater.inflate(R.layout.fragment_main_activity, container, false)

        viewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        viewModel.mRepo.monsterDatas.observe(viewLifecycleOwner, Observer {

            for (monster in it) {

                Log.i(TAG, "Monster  name ${monster.monsterName}")
            }
        })

        return view
    }


}