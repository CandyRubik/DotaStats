package ru.rubik.dotastats.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.rubik.dotastats.R
import ru.rubik.dotastats.di.dependency.FeatureExternalDependenciesContainer
import ru.rubik.dotastats.di.dependency.FeatureExternalDependenciesProvider
import ru.rubik.dotastats.ext.appComponent
import javax.inject.Inject

class RootActivity : AppCompatActivity(R.layout.activity_root),
    FeatureExternalDependenciesProvider {

    @Inject
    override lateinit var dependencies: FeatureExternalDependenciesContainer
        internal set

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }
}