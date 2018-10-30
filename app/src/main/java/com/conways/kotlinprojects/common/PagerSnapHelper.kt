package com.conways.kotlinprojects.common

import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.PagerSnapHelper
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View

class PagerSnapHelper : PagerSnapHelper() {

     var mVerticalHelper: OrientationHelper? = null
     var mHorizontalHelper: OrientationHelper? = null
     var onPageSelectedLisenter:OnPageSelectedLisenter?=null


    init {

    }

    fun calculateDistanceToFinalSnap(layoutManager: RecyclerView.LayoutManager, targetView: RecyclerView): IntArray {
        var out = intArrayOf()
        if (layoutManager.canScrollHorizontally()) {
            out[0] = this.distanceToCenter(layoutManager, targetView, this.getHorizontalHelper(layoutManager))
        } else {
            out[0] = 0;
        }

        if (layoutManager.canScrollVertically()) {
            out[1] = this.distanceToCenter(layoutManager, targetView, this.getVerticalHelper(layoutManager))
        } else {
            out[1] = 0
        }
        return out
    }

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager): View? {
        return if (layoutManager.canScrollVertically()) {
            this.findCenterView(layoutManager, this.getVerticalHelper(layoutManager))
        } else {
            if (layoutManager.canScrollHorizontally()) this.findCenterView(layoutManager, this.getHorizontalHelper(layoutManager)) else null
        }
    }

    override fun findTargetSnapPosition(layoutManager: RecyclerView.LayoutManager, velocityX: Int, velocityY: Int): Int {
        val itemCount = layoutManager.itemCount
        if (itemCount == 0) {
            return -1
        } else {
            var mStartMostChildView: View? = null
            if (layoutManager.canScrollVertically()) {
                mStartMostChildView = this.findStartView(layoutManager, this.getVerticalHelper(layoutManager))
            } else if (layoutManager.canScrollHorizontally()) {
                mStartMostChildView = this.findStartView(layoutManager, this.getHorizontalHelper(layoutManager))
            }

            if (mStartMostChildView == null) {
                return -1
            } else {
                val centerPosition = layoutManager.getPosition(mStartMostChildView)
                if (centerPosition == -1) {
                    return -1
                } else {
                    val forwardDirection: Boolean
                    if (layoutManager.canScrollHorizontally()) {
                        forwardDirection = velocityX > 0
                    } else {
                        forwardDirection = velocityY > 0
                    }

                    var reverseLayout = false
                    if (layoutManager is RecyclerView.SmoothScroller.ScrollVectorProvider) {
                        val vectorProvider = layoutManager as RecyclerView.SmoothScroller.ScrollVectorProvider
                        val vectorForEnd = vectorProvider.computeScrollVectorForPosition(itemCount - 1)
                        if (vectorForEnd != null) {
                            reverseLayout = vectorForEnd.x < 0.0f || vectorForEnd.y < 0.0f
                        }
                    }
                    var a = if (reverseLayout) if (forwardDirection) centerPosition - 1 else centerPosition else if (forwardDirection) centerPosition + 1 else centerPosition
                    onPageSelectedLisenter!!.pageSelected(a)
                    return a
                }
            }
        }
    }


    fun getVerticalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        if (this.mVerticalHelper == null || this.mVerticalHelper!!.getLayoutManager() !== layoutManager) {
            this.mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager)
        }

        return this.mVerticalHelper!!
    }

    fun getHorizontalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        if (this.mHorizontalHelper == null || this.mHorizontalHelper!!.getLayoutManager() !== layoutManager) {
            this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager)
        }

        return this.mHorizontalHelper!!
    }

    private fun distanceToCenter(layoutManager: RecyclerView.LayoutManager, targetView: View, helper: OrientationHelper): Int {
        val childCenter = helper.getDecoratedStart(targetView) + helper.getDecoratedMeasurement(targetView) / 2
        val containerCenter: Int
        if (layoutManager.clipToPadding) {
            containerCenter = helper.startAfterPadding + helper.totalSpace / 2
        } else {
            containerCenter = helper.end / 2
        }

        return childCenter - containerCenter
    }

    private fun findCenterView(layoutManager: RecyclerView.LayoutManager, helper: OrientationHelper): View? {
        val childCount = layoutManager.childCount
        if (childCount == 0) {
            return null
        } else {
            var closestChild: View? = null
            val center: Int
            if (layoutManager.clipToPadding) {
                center = helper.startAfterPadding + helper.totalSpace / 2
            } else {
                center = helper.end / 2
            }

            var absClosest = 2147483647

            for (i in 0 until childCount) {
                val child = layoutManager.getChildAt(i)
                val childCenter = helper.getDecoratedStart(child) + helper.getDecoratedMeasurement(child) / 2
                val absDistance = Math.abs(childCenter - center)
                if (absDistance < absClosest) {
                    absClosest = absDistance
                    closestChild = child
                }
            }

            return closestChild
        }
    }

    private fun findStartView(layoutManager: RecyclerView.LayoutManager, helper: OrientationHelper): View? {
        val childCount = layoutManager.childCount
        if (childCount == 0) {
            return null
        } else {
            var closestChild: View? = null
            var startest = 2147483647

            for (i in 0 until childCount) {
                val child = layoutManager.getChildAt(i)
                val childStart = helper.getDecoratedStart(child)
                if (childStart < startest) {
                    startest = childStart
                    closestChild = child
                }
            }

            return closestChild
        }
    }

    open interface OnPageSelectedLisenter{
        fun pageSelected(position:Int)
    }


}