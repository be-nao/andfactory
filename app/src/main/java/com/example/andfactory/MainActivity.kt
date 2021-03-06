package com.example.andfactory

import android.os.Bundle
import com.example.andfactory.ui.projects.ProjectListFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ProjectListFragment.newInstance())
                .commitNow()
        }
    }

}
