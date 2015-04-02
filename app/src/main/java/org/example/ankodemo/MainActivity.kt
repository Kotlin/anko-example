package org.example.ankodemo

import android.app.*
import android.content.Context
import android.view.*
import android.widget.*
import kotlinx.android.anko.*
import android.os.Bundle
import android.text.InputType.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val customStyle = { v: Any ->
            when (v) {
                is Button -> v.textSize = 26f
                is EditText -> v.textSize = 24f
            }
        }

        verticalLayout {
            padding = dip(32)

            imageView(android.R.drawable.ic_menu_manage).layoutParams {
                margin = dip(16)
                gravity = Gravity.CENTER
            }

            val name = editText {
                hintResource = R.string.name
            }
            val password = editText {
                hintResource = R.string.password
                inputType = TYPE_CLASS_TEXT or TYPE_TEXT_VARIATION_PASSWORD
            }

            button("Log in") {
                onClick {
                    tryLogin(name.text, password.text)
                }
            }
        }.style(customStyle)
    }

    private fun tryLogin(name: CharSequence?, password: CharSequence?) {
        if (checkCredentials(name.toString(), password.toString())) {
            toast("Logged in! :)")
            startActivity<CountriesActivity>()
        } else {
            toast("Wrong password :( Enter user:password")
        }
    }

    private fun checkCredentials(name: String, password: String) =
            name == "user" && password == "password"

}