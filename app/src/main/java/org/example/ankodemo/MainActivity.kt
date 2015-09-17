package org.example.ankodemo

import android.app.*
import android.content.Context
import android.content.res.Configuration
import android.view.*
import android.widget.*
import org.jetbrains.anko.*
import android.os.Bundle
import android.text.InputType.*
import org.jetbrains.anko.custom.customView
import java.lang.ref.WeakReference
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future

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

            imageView(android.R.drawable.ic_menu_manage).lparams {
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
        async {
            Thread.sleep(500)
            activityUiThread {
                if (checkCredentials(name.toString(), password.toString())) {
                    toast("Logged in! :)")
                    startActivity<CountriesActivity>()
                } else {
                    toast("Wrong password :( Enter user:password")
                }
            }
        }
    }

    private fun checkCredentials(name: String, password: String) =
            name == "user" && password == "password"

}