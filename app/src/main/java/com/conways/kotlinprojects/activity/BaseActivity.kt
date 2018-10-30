package com.conways.kotlinprojects.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import com.conways.kotlinprojects.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    open fun getAppColor(id: Int): Int {
        return ActivityCompat.getColor(this, id)
    }

    open fun getAppDrawable(id: Int): Drawable? {
        return ActivityCompat.getDrawable(this, id)

    }


}