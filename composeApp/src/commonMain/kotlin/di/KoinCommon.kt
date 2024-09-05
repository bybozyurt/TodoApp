package di

import di.modules.coroutineScopeModule
import di.modules.dispatcherModule
import di.modules.platformModule
import di.modules.repositoryModule
import di.modules.roomDatabaseModule
import di.modules.useCaseModule
import di.modules.viewModelsModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            platformModule,
            repositoryModule,
            roomDatabaseModule,
            viewModelsModule,
            dispatcherModule,
            coroutineScopeModule,
            useCaseModule,
        )
    }


//using in iOS
fun initKoin() = initKoin {}
