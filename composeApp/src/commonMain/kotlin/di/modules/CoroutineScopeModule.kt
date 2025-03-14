package di.modules

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val coroutineScopeModule = module {
    single { CoroutineScope(SupervisorJob()) }
}
