package ru.rubik.dotastats.shared

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
import ru.rubik.dotastats.R

class WinLoseStatView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.winLoseStatViewStyle,
    defStyleRes: Int = R.style.WinLoseStatViewStyle,
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private val STROKE_WITH = context.toDp(3f)
    private val WITH = context.toDp(300f)
    private val HEIGHT = context.toDp(50f)

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

    private val strokePaint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeWidth = STROKE_WITH
            color = strokeColor
        }
    }

    private val fillPositivePaint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            strokeWidth = STROKE_WITH
            color = positiveColor
        }
    }

    private val fillNegativePaint: Paint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            strokeWidth = STROKE_WITH
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
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        setMeasuredDimension(
            resolveSize(WITH.toInt(), widthMeasureSpec) + paddingLeft + paddingRight,
            resolveSize(HEIGHT.toInt(), heightMeasureSpec) + paddingTop + paddingBottom,
        )
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        canvas.translate(paddingLeft.toFloat(), paddingTop.toFloat())

        canvas.drawRect(0f, 0f, WITH, HEIGHT, strokePaint)
        canvas.translate(STROKE_WITH, STROKE_WITH)
        canvas.drawRect(0f, 0f, WITH / 100 * positivePercent, HEIGHT - 2 * STROKE_WITH, fillPositivePaint)
        canvas.drawRect(WITH / 100 * positivePercent, 0f, WITH - 2 * STROKE_WITH, HEIGHT - 2 * STROKE_WITH, fillNegativePaint)
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