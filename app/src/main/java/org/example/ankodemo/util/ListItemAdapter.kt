package org.example.ankodemo.util

import android.content.Context
import android.widget.ArrayAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.anko.*
import android.view.Gravity
import kotlin.properties.Delegates

public abstract class ListItemAdapter(ctx: Context, items: List<ListItem>) : ArrayAdapter<ListItem>(ctx, 0, items) {
    public inline fun <T> dsl(f: Context.() -> T): T = getContext().let { it.f() }
    protected abstract val listItemClasses: List<Class<out ListItem>>

    private val types: Map<Class<out ListItem>, Int> by Delegates.lazy {
        listItemClasses.withIndex().fold(hashMapOf<Class<out ListItem>, Int>()) {
            map, t -> map.put(t.value, t.index); map
        }
    }

    override fun getViewTypeCount(): Int = types.size()
    override fun getItemViewType(position: Int) = types.get(getItem(position)?.javaClass) ?: 0

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val item = getItem(position)
        if (item != null) {
            val v = convertView ?: item.create(this)
            // Here we use findViewById method, but if you rich widget hierarchy in list item layouts,
            // implement ViewHolder as described here:
            // http://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder
            (v as? TextView ?: v.findViewById(android.R.id.text1) as? TextView)?.setText(item.text)
            return v
        } else return convertView
    }
}

public trait ListItem {
    val text: CharSequence
    open fun create(a: ListItemAdapter): View = a.dsl {
        textView {
            id = android.R.id.text1
            gravity = Gravity.CENTER_VERTICAL
            padding = dip(20)
            textSize = 18f
        }
    }
}