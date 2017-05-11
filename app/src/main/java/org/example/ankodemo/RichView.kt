package org.example.ankodemo

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity.CENTER
import android.view.ViewManager
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView
import org.jetbrains.anko.sdk25.coroutines.onClick

class RichView : LinearLayout {
    private lateinit var image: ImageView
    private lateinit var text: TextView

    private fun init() = AnkoContext.createDelegate(this).apply {
        gravity = CENTER
        padding = dip(24)

        image = imageView(imageResource = R.drawable.kotlin) {
            onClick { startAnimation() }

            padding = dip(8)
            layoutParams = LinearLayout.LayoutParams(dip(48), dip(48))
        }

        text = textView("Anko Rich view") {
            textSize = 19f
        }

        startAnimation()
    }

    private fun startAnimation() {
        if (image.animation != null) return

        image.startAnimation(ScaleAnimation(1f, 1.3f, 1f, 1.3f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            repeatCount = 1
            repeatMode = Animation.REVERSE
            duration = 1000
        })
    }

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }
}

@Suppress("NOTHING_TO_INLINE")
inline fun ViewManager.myRichView(theme: Int = 0) = myRichView({}, theme)
inline fun ViewManager.myRichView(init: RichView.() -> Unit, theme: Int = 0) = ankoView(::RichView, theme, init)