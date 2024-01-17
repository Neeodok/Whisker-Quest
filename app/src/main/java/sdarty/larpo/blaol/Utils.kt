package sdarty.larpo.blaol

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


const val prefSoundOnKey = "sound_key"


fun animClickView(view: View, context: Context){

    (AnimatorInflater.loadAnimator(
        context,
        R.animator.animpulsebtn
    ) as AnimatorSet).apply {
        setTarget(view)
        start()
    }

}


fun pulseBtnAnimation(view: View) {
    val animScaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 0.95f, 1.1f)
    animScaleX.apply {
        duration = 700
        interpolator = AccelerateDecelerateInterpolator()
        repeatCount = ValueAnimator.INFINITE
        repeatMode = ValueAnimator.REVERSE
    }
    val animScaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.95f, 1.1f)
    animScaleY.apply {
        duration = 700
        interpolator = AccelerateDecelerateInterpolator()
        repeatCount = ValueAnimator.INFINITE
        repeatMode = ValueAnimator.REVERSE
    }
    animScaleX.start()
    animScaleY.start()
}

fun flipKartunka(context: Context, visibleView: View, inVisibleView: View) {
    try {
        val scale = context.resources.displayMetrics.density
        val cameraDist = 8000 * scale
        visibleView.cameraDistance = cameraDist
        inVisibleView.cameraDistance = cameraDist

        val flipOutAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.flip_out) as AnimatorSet
        flipOutAnimatorSet.setTarget(inVisibleView)

        val flipInAnimationSet = AnimatorInflater.loadAnimator(context, R.animator.flip_in) as AnimatorSet
        flipInAnimationSet.setTarget(visibleView)

        flipOutAnimatorSet.start()
        flipInAnimationSet.start()

    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun setAllClickable(frontCrystals: List<View>) {
    frontCrystals.forEach {
        it.isClickable = true
    }
}

fun setAllNotClickable(frontCrystals: List<View>) {
    frontCrystals.forEach {
        it.isClickable = false
    }
}
fun setFrontsCryst(frontCrystals: List<View>) {
    frontCrystals.forEach {
        it.setBackgroundResource(R.drawable.box_question)
    }
}




fun setAmountLast(indyx: MutableList<Int>, currentIndyx: Int, tvCrystalLast: TextView) {
    val lastToFind = indyx.count { it == currentIndyx }
    tvCrystalLast.text = lastToFind.toString()
}

fun gameOverchik(
    tvTostik: TextView,
    currentIndyx: Int,
    lastToFind: Int,
    indyx: MutableList<Int>,
    startGame: () -> Unit
) {
    tvTostik.text = "Game over!"
    CoroutineScope(Dispatchers.Main).launch {
        delay(2500)
        startGame()
    }
}
