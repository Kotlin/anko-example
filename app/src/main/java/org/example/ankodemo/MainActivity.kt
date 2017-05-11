package org.example.ankodemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType.TYPE_CLASS_TEXT
import android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUi().setContentView(this)
    }

    fun tryLogin(ui: AnkoContext<MainActivity>, name: CharSequence?, password: CharSequence?) {
        ui.doAsync {
            Thread.sleep(500)

            activityUiThreadWithContext {
                if (checkCredentials(name.toString(), password.toString())) {
                    toast("Logged in! :)")
                    startActivity<CountriesActivity>()
                } else {
                    toast("Wrong password :( Enter user:password")
                }
            }
        }
    }

    private fun checkCredentials(name: String, password: String) = name == "user" && password == "password"
}

class MainActivityUi : AnkoComponent<MainActivity> {
    private val customStyle = { v: Any ->
        when (v) {
            is Button -> v.textSize = 26f
            is EditText -> v.textSize = 24f
        }
    }

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
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
                    ui.owner.tryLogin(ui, name.text, password.text)
                }
            }

            myRichView()
        }.applyRecursively(customStyle)
    }
}