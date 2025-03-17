package ab.todoapp.common.di

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val coroutineScopeModule = module {
    single { CoroutineScope(SupervisorJob()) }
}

private val dispatcherModule = module {
    single(named(Dispatcher.IO)) { Dispatchers.IO }
    single(named(Dispatcher.MAIN)) { Dispatchers.Main }
    single(named(Dispatcher.DEFAULT)) { Dispatchers.Default }
    single(named(Dispatcher.UNCONFINED)) { Dispatchers.Unconfined }
}

val commonModule = listOf(
    coroutineScopeModule,
    dispatcherModule,
)

enum class Dispatcher {
    IO,
    MAIN,
    DEFAULT,
    UNCONFINED,
}
