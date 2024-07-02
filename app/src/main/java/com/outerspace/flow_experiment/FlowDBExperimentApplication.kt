package com.outerspace.flow_experiment

import android.app.Application
import android.content.Context

class FlowDBExperimentApplication : Application() {

    companion object{

        lateinit var appC: Context
        fun appContext() = appC
    }

    override fun onCreate() {
        super.onCreate()
        appC= applicationContext
    }

}