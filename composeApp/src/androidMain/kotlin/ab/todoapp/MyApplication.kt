package ab.todoapp

import ab.todoapp.feature.home.di.homeScreenModule
import ab.todoapp.feature.taskeditor.di.taskEditorScreenModule
import android.app.Application
import cafe.adriel.voyager.core.registry.ScreenRegistry
import di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@MyApplication)
        }
        ScreenRegistry {
            homeScreenModule()
            taskEditorScreenModule()
        }
    }
}