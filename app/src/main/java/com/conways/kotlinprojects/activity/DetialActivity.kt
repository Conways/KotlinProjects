package com.conways.kotlinprojects.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.conways.kotlinprojects.R
import com.conways.kotlinprojects.adapter.VpAdapter
import com.conways.kotlinprojects.common.PagerSnapHelper
import kotlinx.android.synthetic.main.activity_detial.*

class DetialActivity : BaseActivity() ,PagerSnapHelper.OnPageSelectedLisenter{
    override fun pageSelected(position: Int) {
        Log.e("findTargetSnapPosition",""+position)
        activity_detial_position.setText(position.toString())
    }

    var adapter: VpAdapter = VpAdapter(this)
    var pagerSnapHelper: PagerSnapHelper = PagerSnapHelper()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detial)
        var mLayoutManager = LinearLayoutManager(this);
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL;
        activity_detial_vp.layoutManager = mLayoutManager;
        pagerSnapHelper.onPageSelectedLisenter=this
        pagerSnapHelper.attachToRecyclerView(activity_detial_vp);
        activity_detial_vp.adapter = adapter
        activity_detial_vp.scrollToPosition(Int.MAX_VALUE / 2)
        activity_detial_refresh.setOnClickListener { adapter.refresh() }
        activity_detial_vp.addOnScrollListener(MyLister())
        activity_detial_position.setText((Int.MAX_VALUE/2).toString())
    }


    class MyLister : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            Log.e("MyLister onScrolled" , dx.toString() + "           " + dy.toString())
            var layoutManager:LinearLayoutManager= recyclerView.layoutManager as LinearLayoutManager
            Log.e("onScrolledchildCount" , layoutManager.childCount.toString())
            Log.e("onScrolleditemCount" , layoutManager.itemCount.toString())
            Log.e("onScrolledFirstVisible" , layoutManager.findFirstVisibleItemPosition().toString())
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            Log.e("MyLister newState" , newState.toString() )
        }
    }
}
