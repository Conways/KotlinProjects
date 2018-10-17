package com.conways.kotlinprojects.activity

import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.conways.kotlinprojects.R
import com.conways.kotlinprojects.fragment.FruitFragment
import com.conways.kotlinprojects.fragment.MeatFragment
import com.conways.kotlinprojects.fragment.VegetablesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private var INDEX_FRUIT = 0
        private var INDEX_MEAT = 1
        private var INDEX_VEGETABLES = 2

        private var INDEX_FRUIT_TAG = "fruit"
        private var INDEX_MEAT_TAG = "meat"
        private var INDEX_VEGETABLES_TAG = "vegetables"
        private var CURRENT_INDEX = "current_index"
    }

    var fruitFragment: FruitFragment? = null
    var meatFragment: MeatFragment? = null
    var vegetablesFragment: VegetablesFragment? = null

    var index: Int? = INDEX_FRUIT


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        if (null != savedInstanceState) {
            index = savedInstanceState.getInt(CURRENT_INDEX)
            fruitFragment = supportFragmentManager.findFragmentByTag(INDEX_FRUIT_TAG) as FruitFragment?
            meatFragment = supportFragmentManager.findFragmentByTag(INDEX_MEAT_TAG) as MeatFragment?
            vegetablesFragment = supportFragmentManager.findFragmentByTag(INDEX_VEGETABLES_TAG) as VegetablesFragment?
        }
        setIndex(this.index!!)
    }

    fun init() {
        activity_main_index_fruit.setOnClickListener(this)
        activity_main_index_meat.setOnClickListener(this)
        activity_main_index_vegetables.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.activity_main_index_fruit -> {
                if (this.index != INDEX_FRUIT) {
                    setIndex(INDEX_FRUIT)
                }
            }

            R.id.activity_main_index_meat -> {
                if (this.index != INDEX_MEAT) {
                    setIndex(INDEX_MEAT)
                }
            }
            R.id.activity_main_index_vegetables -> {
                if (this.index != INDEX_VEGETABLES) {
                    setIndex(INDEX_VEGETABLES)
                }
            }
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
                    fragmentTransaction.add(R.id.activity_main_fragment_holder, fruitFragment!!, INDEX_FRUIT_TAG)
                } else {
                    fragmentTransaction.show(fruitFragment!!)
                }
                activity_main_index_fruit?.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.drawable.ic_fruit_checked), null, null)
                activity_main_index_fruit?.setTextColor(resources.getColor(R.color.colorMain))
            }

            INDEX_MEAT -> {
                if (null == meatFragment) {
                    meatFragment = MeatFragment();
                    fragmentTransaction.add(R.id.activity_main_fragment_holder, meatFragment!!, INDEX_MEAT_TAG)
                } else {
                    fragmentTransaction.show(meatFragment!!)
                }
                activity_main_index_meat?.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.drawable.ic_meat_checked), null, null)
                activity_main_index_meat?.setTextColor(resources.getColor(R.color.colorMain))
            }

            INDEX_VEGETABLES -> {
                if (null == vegetablesFragment) {
                    vegetablesFragment = VegetablesFragment();
                    fragmentTransaction.add(R.id.activity_main_fragment_holder, vegetablesFragment!!, INDEX_VEGETABLES_TAG)
                } else {
                    fragmentTransaction.show(vegetablesFragment!!)
                }
                activity_main_index_vegetables?.setCompoundDrawablesWithIntrinsicBounds(null, getDrawable(R.drawable.ic_vegetables_checked), null, null)
                activity_main_index_vegetables?.setTextColor(resources.getColor(R.color.colorMain))

            }
        }
        fragmentTransaction.commit()
        this.index = index;

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

        activity_main_index_fruit?.setTextColor(resources.getColor(R.color.colorGrayDark))
        activity_main_index_meat?.setTextColor(resources.getColor(R.color.colorGrayDark))
        activity_main_index_vegetables?.setTextColor(resources.getColor(R.color.colorGrayDark))
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        if (outState != null) {
            outState.putInt(CURRENT_INDEX, this.index!!)
        }
    }
}
