package com.neiljaywarner.jsonplaceholderscaffold

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.neiljaywarner.jsonplaceholderscaffold.di.myModule
import com.neiljaywarner.jsonplaceholderscaffold.ui.photos.PhotoRepository
import com.neiljaywarner.jsonplaceholderscaffold.ui.photos.PhotosViewModel
import org.koin.android.ext.android.startKoin
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import java.lang.ref.WeakReference

class JPHSApplication : Application(), Application.ActivityLifecycleCallbacks {


    // Koin for SL/DI
    // Can separate into a few modules/lists, one for viewmodels, one for repos, etc


    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin(this, listOf(myModule))
        registerActivityLifecycleCallbacks(this)

        //enableCrashlyticsIfRelease()
        //TODO: onLowMemory callbacks for crashlytics logging.

    }

    override fun onTerminate() {
        super.onTerminate()

        unregisterActivityLifecycleCallbacks(this)
    }

    override fun onActivityPaused(activity: Activity?) {
        if (activity == null) {
            return
        }
        //Crashlytics.log("onActivityPaused ${activity.localClassName}")
    }

    override fun onActivityResumed(activity: Activity?) {
        if (activity == null) {
            return
        }
        currentActivity = activity
        //Crashlytics.log("onActivityResumed ${activity.localClassName}")
    }

    override fun onActivityStarted(activity: Activity?) {
        currentActivity = activity
    }

    override fun onActivityDestroyed(activity: Activity?) {

    }

    // TODO: onConfiguration changed logging possibly with anko for readability
    // https://github.com/Kotlin/anko/blob/master/anko/library/static/commons/src/main/java/Helpers.kt
    // onrotation especially..

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        if (activity == null) {
            return
        }
        //Crashlytics.log("onActivitySaveInstanceState ${activity.localClassName}")

    }

    override fun onActivityStopped(activity: Activity?) {

    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        currentActivity = activity
    }

    private fun enableCrashlyticsIfRelease() {
        if (BuildConfig.DEBUG.not()) {
            //Fabric.with(this, Crashlytics())
        }
    }

    private lateinit var currentActivityWeakReference: WeakReference<Activity?>
    var currentActivity: Activity?
        get() = currentActivityWeakReference.get()
        set(value) {
            currentActivityWeakReference = WeakReference(value)
        }

    companion object {
        lateinit var instance: JPHSApplication
            private set
        val currentActivity: Activity?
            get() = JPHSApplication.instance.currentActivity
    }
}