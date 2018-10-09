package com.conways.kotlinprojects

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private var INDEX_FRUIT = 0
        private var INDEX_MEAT = 1
        private var INDEX_VEGETABLES = 2
    }

    var fruitFragment: FruitFragment? = null
    var meatFragment: MeatFragment? = null
    var vegetablesFragment: VegetablesFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        setIndex(INDEX_FRUIT)
    }

    fun init() {
        activity_main_index_fruit.setOnClickListener(this)
        activity_main_index_meat.setOnClickListener(this)
        activity_main_index_vegetables.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.activity_main_index_fruit -> setIndex(INDEX_FRUIT)
            R.id.activity_main_index_meat -> setIndex(INDEX_MEAT)
            R.id.activity_main_index_vegetables -> setIndex(INDEX_VEGETABLES)
            else -> {

            }

        }
    }

    fun setIndex(index: Int) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        resetAll(fragmentTransaction)
        when (index) {
            INDEX_FRUIT -> {
                if (null == fruitFragment) {
                    fruitFragment = FruitFragment();
                    fragmentTransaction.add(R.id.activity_main_fragment_holder, fruitFragment!!, "fruit")
                } else {
                    fragmentTransaction.show(fruitFragment!!)
                }
                activity_main_index_fruit?.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.drawable.ic_fruit_checked), null, null)
            }

            INDEX_MEAT -> {
                if (null == meatFragment) {
                    meatFragment = MeatFragment();
                    fragmentTransaction.add(R.id.activity_main_fragment_holder, meatFragment!!, "meat")
                } else {
                    fragmentTransaction.show(meatFragment!!)
                }
                activity_main_index_meat?.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.drawable.ic_meat_checked), null, null)
            }

            INDEX_VEGETABLES -> {
                if (null == vegetablesFragment) {
                    vegetablesFragment = VegetablesFragment();
                    fragmentTransaction.add(R.id.activity_main_fragment_holder, vegetablesFragment!!, "vegetables")
                } else {
                    fragmentTransaction.show(vegetablesFragment!!)
                }
                activity_main_index_vegetables?.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.drawable.ic_vegetables_checked), null, null)
            }
        }
        fragmentTransaction.commit()

    }

    fun resetAll(fragmentTransaction: FragmentTransaction) {
        if (null != fruitFragment) {

            fragmentTransaction.hide(fruitFragment!!)
        }
        if (null != meatFragment) {

            fragmentTransaction.hide(meatFragment!!)
        }
        if (null != vegetablesFragment) {

            fragmentTransaction.hide(vegetablesFragment!!)
        }

        activity_main_index_fruit?.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.drawable.ic_fruit_normal), null, null)
        activity_main_index_meat?.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.drawable.ic_meat_normal), null, null)
        activity_main_index_vegetables?.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.drawable.ic_vegetables_normal), null, null)
    }
}
