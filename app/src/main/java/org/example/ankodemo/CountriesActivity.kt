package org.example.ankodemo

import android.app.ListActivity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import org.example.ankodemo.util.ListItem
import org.example.ankodemo.util.ListItemAdapter
import org.example.ankodemo.util.TextListItem
import org.jetbrains.anko.*

class CountriesActivity : ListActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val items = listOf(
                "America" to listOf("Brazil", "Canada", "United States"),
                "Asia" to listOf("China", "India", "Japan"),
                "Europe" to listOf("France", "Germany", "Spain", "United Kingdom"))

        val listItems = items.flatMap {
            listOf(ContinentItem(it.first)) + it.second.map { CountryItem(it) }
        }

        listAdapter = CountriesAdapter(this, listItems)
    }
}

internal class CountriesAdapter(ctx: Context, items: List<ListItem>) : ListItemAdapter(ctx, items) {
    // All ListItem implementations
    override val listItemClasses = listOf(ContinentItem::class.java, CountryItem::class.java)
}

// Default implementation
// DSL preview plugin requires AnkoComponent inheritors to have an empty constructor
internal class CountryItem(text: String = "") : TextListItem(text)

// Custom implementation
internal class ContinentItem(text: String = "") : TextListItem(text) {
    override fun createView(ui: AnkoContext<ListItemAdapter>) = createTextView(ui) {
        gravity = Gravity.CENTER_VERTICAL
        horizontalPadding = ui.dip(20)
        verticalPadding = ui.dip(10)
        backgroundColor = 0x99CCCCCC.toInt()
        textSize = 17f
        textColor = Color.BLUE
    }
}