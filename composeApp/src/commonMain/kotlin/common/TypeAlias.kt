package common

import domain.model.ColorType
import presentation.screens.home.HomeScreenEvent
import presentation.screens.task.TaskScreenEvent

typealias onClick = () -> Unit

//Navigation
typealias navigateToHome = () -> Unit
typealias navigateToTask = () -> Unit
typealias navigateToTaskDetail = () -> Unit

//TaskScreenEvent
typealias onTaskEvent = (TaskScreenEvent) -> Unit

//HomeScreenEVent
typealias onHomeEvent = (HomeScreenEvent) -> Unit

typealias onColorSelected = (ColorType) -> Unit