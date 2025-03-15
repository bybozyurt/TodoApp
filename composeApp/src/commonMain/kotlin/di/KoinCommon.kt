package di

import ab.todoapp.core.di.coroutineScopeModule
import ab.todoapp.core.di.dispatcherModule
import ab.todoapp.data.di.dataModule
import ab.todoapp.data.modules.platformModule
import ab.todoapp.domain.di.domainModule
import ab.todoapp.feature.home.di.homeModule
import ab.todoapp.feature.taskeditor.di.taskEditorModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            platformModule,
            dataModule,
            domainModule,
            homeModule,
            taskEditorModule,
            dispatcherModule,
            coroutineScopeModule,
        )
    }


//using in iOS
fun initKoin() = initKoin {}
