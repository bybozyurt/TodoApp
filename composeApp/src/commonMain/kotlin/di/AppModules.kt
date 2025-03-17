package di

import ab.todoapp.common.di.commonModule
import ab.todoapp.data.di.dataModule
import ab.todoapp.domain.di.domainModule
import ab.todoapp.feature.home.di.homeModule
import ab.todoapp.feature.taskeditor.di.taskEditorModule

val appModules = dataModule + domainModule + commonModule

val featureModules = homeModule + taskEditorModule