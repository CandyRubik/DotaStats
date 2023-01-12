package ru.rubik.dotastats

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import ru.rubik.dotastats.login.presentation.ui.LoginFragment
import ru.rubik.dotastats.settings.presentation.ui.SettingsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState ?: supportFragmentManager.commit {
            replace<LoginFragment>(R.id.container)
        }
    }
}