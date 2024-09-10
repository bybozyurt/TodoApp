package common

import presentation.screens.task.TaskEvent

typealias onClick = () -> Unit

//Navigation
typealias navigateToHome = () -> Unit
typealias navigateToTask = () -> Unit
typealias navigateToTaskDetail = () -> Unit

//TaskEvent
typealias onEvent = (TaskEvent) -> Unit