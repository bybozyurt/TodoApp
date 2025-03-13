package common

import domain.model.ColorType
import presentation.screens.home.HomeScreenEvent

typealias onClick = () -> Unit

//Navigation
typealias navigateToHome = () -> Unit
typealias navigateToTask = () -> Unit
typealias navigateToTaskDetail = () -> Unit

//HomeScreenEVent
typealias onHomeEvent = (HomeScreenEvent) -> Unit

typealias onColorSelected = (ColorType) -> Unit