package com.conways.kotlinprojects.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.conways.kotlinprojects.R
import com.conways.kotlinprojects.adapter.FrutAdapter
import kotlinx.android.synthetic.main.fragment_fruit.*


class FruitFragment : Fragment() {

    var frutAdapter:FrutAdapter?=null;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fruit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_fruit_fruits.layoutManager= LinearLayoutManager(activity)
        frutAdapter=FrutAdapter(activity)
        fragment_fruit_fruits.adapter=frutAdapter;
    }



}
