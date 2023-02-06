package ru.rubik.dotastats.design

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import kotlin.math.min

class WinLoseStatView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.winLoseStatViewStyle,
    defStyleRes: Int = R.style.WinLoseStatViewStyle,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val WITH = context.getWith()
    private val HEIGHT = context.toDp(20f)

    private var positivePercent = DEFAULT_POSITIVE_VALUE
    private var strokeColor = Color.BLACK
    private var positiveColor = Color.GREEN
    private var negativeColor = Color.RED


    private val animator = ObjectAnimator().apply {
        duration = ANIMATION_DURATION
        interpolator = AccelerateDecelerateInterpolator()
        addUpdateListener {
            positivePercent = (it.getAnimatedValue(POSITIVE_PVH_TITLE) as Float).toInt()
            invalidate()
        }
    }

    private val fillPositivePaint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            color = positiveColor
        }
    }

    private val fillNegativePaint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            color = negativeColor
        }
    }

    init {
        attrs?.let {  initAttrs(context, it, defStyleAttr, defStyleRes) }
    }

    private fun startAnimation() {
        animator.cancel()
        val positivePercentPVH = PropertyValuesHolder.ofFloat(POSITIVE_PVH_TITLE, 0f, positivePercent.toFloat())
        animator.setValues(positivePercentPVH)
        animator.start()
    }

    private fun initAttrs(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int,
        defStyleRes: Int
    ) {
        context.theme
            .obtainStyledAttributes(attrs, R.styleable.WinLoseStatView, defStyleAttr, defStyleRes)
            .apply {
                try {
                    positivePercent = getInt(R.styleable.WinLoseStatView_positivePercent, DEFAULT_POSITIVE_VALUE)
                    strokeColor = getColor(R.styleable.WinLoseStatView_strokeColor, strokeColor)
                    positiveColor = getColor(R.styleable.WinLoseStatView_positiveColor, positiveColor)
                    negativeColor = getColor(R.styleable.WinLoseStatView_negativeColor, negativeColor)
                    startAnimation()
                } finally {
                    recycle()
                }
            }
    }

    fun setPositivePercent(value: Int) {
        positivePercent = value.coerceIn(MIN_DISPLAYABLE_VALUE.. MAX_DISPLAYABLE_VALUE)
        startAnimation()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        val desiredWidth = WITH.toInt() + paddingLeft + paddingRight
        val desiredHeight = HEIGHT.toInt() + paddingTop + paddingBottom

        setMeasuredDimension(measureDimension(desiredWidth, widthMeasureSpec),
            measureDimension(desiredHeight, heightMeasureSpec));
    }

    private fun measureDimension(desiredSize: Int, measureSpec: Int): Int {
        var result: Int
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = desiredSize
            if (specMode == MeasureSpec.AT_MOST) {
                result = min(result, specSize)
            }
        }
        return result
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        canvas.translate(paddingLeft.toFloat(), paddingTop.toFloat())

        canvas.drawRect(0f, 0f, WITH / 100 * positivePercent, HEIGHT, fillPositivePaint)
        canvas.drawRect(WITH / 100 * positivePercent, 0f, WITH, HEIGHT, fillNegativePaint)
    }

    override fun onSaveInstanceState(): Parcelable {
        val state = super.onSaveInstanceState()

        return SavedState(
            positivePercent, state
        )
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        state as SavedState
        super.onRestoreInstanceState(state.superState)
        setPositivePercent(state.positivePercent)
    }

    @Parcelize
    class SavedState(
        val positivePercent: Int,
        @IgnoredOnParcel val state: Parcelable? = null
    ) : BaseSavedState(state)

    companion object {
        const val MAX_DISPLAYABLE_VALUE = 100
        const val MIN_DISPLAYABLE_VALUE = 0
        const val DEFAULT_POSITIVE_VALUE = 50
        const val POSITIVE_PVH_TITLE = "positivePercent"
        const val ANIMATION_DURATION = 650L
    }
}

fun Context.toDp(value: Float): Float {
    return resources.displayMetrics.density * value
}

fun Context.getWith(): Float {
    return resources.displayMetrics.widthPixels.toFloat()
}