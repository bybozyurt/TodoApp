package di

import di.modules.coroutineScopeModule
import di.modules.dispatcherModule
import di.modules.platformModule
import di.modules.repositoryModule
import di.modules.viewModelsModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            platformModule,
            repositoryModule,
            viewModelsModule,
            dispatcherModule,
            coroutineScopeModule,
        )
    }


//using in iOS
fun initKoin() = initKoin {}
