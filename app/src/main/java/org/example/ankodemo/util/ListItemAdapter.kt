package org.example.ankodemo.util

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import org.jetbrains.anko.*

abstract class ListItemAdapter(ctx: Context, items: List<ListItem>) : ArrayAdapter<ListItem>(ctx, 0, items) {
    // Reusable context allows adding multiple views, so it can be used in adapters without overhead
    private val ankoContext = AnkoContext.createReusable(ctx, this)

    protected abstract val listItemClasses: List<Class<out ListItem>>

    private val types: Map<Class<out ListItem>, Int> by lazy {
        listItemClasses.withIndex().fold(hashMapOf<Class<out ListItem>, Int>()) {
            map, t -> map.put(t.value, t.index); map
        }
    }

    override fun getViewTypeCount(): Int = types.size
    override fun getItemViewType(position: Int) = types[getItem(position)?.javaClass as Class<out ListItem>] ?: 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val item = getItem(position)
        if (item != null) {
            val view = convertView ?: item.createView(ankoContext)
            item.apply(view)
            return view
        } else return convertView
    }
}

interface ListItem : AnkoComponent<ListItemAdapter> {
    fun apply(convertView: View)
}

internal open class TextListItem(val text: String = "") : ListItem {
    protected inline fun createTextView(ui: AnkoContext<ListItemAdapter>, init: TextView.() -> Unit) = ui.apply {
        textView {
            id = android.R.id.text1
            text = "Text list item" // default text (for the preview)
            init()
        }
    }.view

    override fun createView(ui: AnkoContext<ListItemAdapter>) = createTextView(ui) {
        gravity = Gravity.CENTER_VERTICAL
        padding = ui.dip(20)
        textSize = 18f
    }

    private fun getHolder(convertView: View): Holder {
        return (convertView.tag as? Holder) ?: Holder(convertView as TextView).apply {
            convertView.tag = this
        }
    }

    override fun apply(convertView: View) {
        val h = getHolder(convertView)
        h.textView.text = text
    }

    internal class Holder(val textView: TextView)
}