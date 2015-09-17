package org.example.ankodemo

import android.app.ListActivity
import android.os.Bundle
import android.content.Context
import android.widget.ArrayAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import org.jetbrains.anko.*
import android.view.Gravity
import android.graphics.Color
import org.example.ankodemo.util.*

public class CountriesActivity : ListActivity() {

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

class CountriesAdapter(ctx: Context, items: List<ListItem>) : ListItemAdapter(ctx, items) {
    override val listItemClasses = listOf(javaClass<ContinentItem>(), javaClass<CountryItem>())
}

// Default implementation
class CountryItem(override val text: String) : ListItem

// Custom implementation
class ContinentItem(override val text: String) : ListItem {
    override fun create(a: ListItemAdapter) = a.dsl {
        textView {
            gravity = Gravity.CENTER_VERTICAL
            horizontalPadding = dip(20)
            verticalPadding = dip(10)
            backgroundColor = 0x99CCCCCC.toInt()
            textSize = 17f
            textColor = Color.BLUE
        }
    }
}