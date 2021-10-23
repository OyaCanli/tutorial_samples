package com.example.recyclerviewitemanimationsample

import android.animation.Animator
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView

const val LIKE_ITEM = 6728
const val DISLIKE_ITEM = 1672

class ProductItemAnimator : DefaultItemAnimator() {

    override fun canReuseUpdatedViewHolder(
        viewHolder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ): Boolean = true

    override fun canReuseUpdatedViewHolder(viewHolder: RecyclerView.ViewHolder) = true

    override fun recordPreLayoutInformation(
        state: RecyclerView.State,
        viewHolder: RecyclerView.ViewHolder,
        changeFlags: Int,
        payloads: MutableList<Any>
    ): ItemHolderInfo {

        if (changeFlags == FLAG_CHANGED) {
            return when (payloads[0] as? Int) {
                LIKE_ITEM -> ProductItemHolderInfo(true)
                DISLIKE_ITEM -> ProductItemHolderInfo(false)
                else -> super.recordPreLayoutInformation(state, viewHolder, changeFlags, payloads)
            }
        }
        return super.recordPreLayoutInformation(state, viewHolder, changeFlags, payloads)
    }

    override fun animateChange(
        oldHolder: RecyclerView.ViewHolder,
        newHolder: RecyclerView.ViewHolder,
        preInfo: ItemHolderInfo,
        postInfo: ItemHolderInfo
    ): Boolean {

        val holder = newHolder as ProductAdapter.ProductViewHolder

        if (preInfo is ProductItemHolderInfo) {
            if (preInfo.likeItem) {
                // Animate like
                holder.binding.likeAnim.apply {
                    addAnimatorListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(animation: Animator?) {}
                        override fun onAnimationRepeat(animation: Animator?) {}
                        override fun onAnimationCancel(animation: Animator?) {}

                        override fun onAnimationEnd(animation: Animator?) {
                            dispatchAnimationFinished(holder)
                        }
                    })
                    playAnimation()
                }
            } else {
                // Dislike (You could possibly have a reverse animation here)
                holder.binding.likeAnim.progress = 0f
            }
            return true
        }

        return super.animateChange(oldHolder, newHolder, preInfo, postInfo)
    }

    class ProductItemHolderInfo(val likeItem: Boolean) : ItemHolderInfo()
}
