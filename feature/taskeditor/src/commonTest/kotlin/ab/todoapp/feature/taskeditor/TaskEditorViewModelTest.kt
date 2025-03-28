package ab.todoapp.feature.taskeditor

import ab.todoapp.common.Constant
import ab.todoapp.domain.model.ColorType
import ab.todoapp.domain.model.ToDoTask
import ab.todoapp.domain.repository.FakeToDoRepository
import ab.todoapp.domain.usecase.*
import ab.todoapp.feature.taskeditor.TaskScreenContract.*
import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.test.*

@OptIn(ExperimentalCoroutinesApi::class)
class TaskEditorViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private lateinit var repository: FakeToDoRepository
    private lateinit var viewModel: TaskEditorViewModel

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        repository = FakeToDoRepository()
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initTask should load task and update UI`() = runTest {
        val task = ToDoTask(1L, "Title", "Desc", true, ColorType.GREEN)
        repository.addTask(task)

        val savedStateHandle = SavedStateHandle().apply {
            set("id", 1L) // ✅ sadece id'yi veriyoruz
        }

        viewModel = TaskEditorViewModel(
            ioDispatcher = dispatcher,
            saveTaskUseCase = SaveTaskUseCase(AddTaskUseCase(repository), UpdateTaskUseCase(repository)),
            deleteTaskUseCase = DeleteTaskUseCase(repository),
            getTaskByIdUseCase = GetTaskByIdUseCase(repository),
            savedStateHandle = savedStateHandle
        )

        dispatcher.scheduler.advanceUntilIdle()

        assertEquals("Title", viewModel.currentUiState.title)
        assertEquals("Desc", viewModel.currentUiState.description)
        assertEquals(true, viewModel.currentUiState.isCompleted)
        assertEquals(ColorType.GREEN, viewModel.currentUiState.selectedColor)
    }

    @Test
    fun `onAction SaveTaskScreen should save task and emit SideEffect`() = runTest {
        val savedStateHandle = SavedStateHandle().apply {
            set("id", Constant.INVALID_TASK_ID)
        }

        viewModel = TaskEditorViewModel(
            ioDispatcher = dispatcher,
            saveTaskUseCase = SaveTaskUseCase(AddTaskUseCase(repository), UpdateTaskUseCase(repository)),
            deleteTaskUseCase = DeleteTaskUseCase(repository),
            getTaskByIdUseCase = GetTaskByIdUseCase(repository),
            savedStateHandle = savedStateHandle
        )

        viewModel.updateUiState {
            copy(
                title = "New Task",
                description = "Desc",
                isCompleted = false,
                selectedColor = ColorType.RED
            )
        }

        viewModel.onAction(Events.SaveTaskScreen(id = Constant.INVALID_TASK_ID))

        dispatcher.scheduler.advanceUntilIdle()

        assertTrue(repository.tasks.value.any { it.title == "New Task" })

        val effect = withTimeoutOrNull(1000) { viewModel.uiEffect.first() }
        assertEquals(SideEffect.NavigateToBack, effect)
    }

    @Test
    fun `onAction OnDeleteTask should delete task and emit SideEffect`() = runTest {
        val task = ToDoTask(1L, "To Delete", "desc", false, ColorType.GRAY)
        repository.addTask(task)

        val savedStateHandle = SavedStateHandle().apply {
            set("id", 1L)
        }

        viewModel = TaskEditorViewModel(
            ioDispatcher = dispatcher,
            saveTaskUseCase = SaveTaskUseCase(AddTaskUseCase(repository), UpdateTaskUseCase(repository)),
            deleteTaskUseCase = DeleteTaskUseCase(repository),
            getTaskByIdUseCase = GetTaskByIdUseCase(repository),
            savedStateHandle = savedStateHandle
        )

        viewModel.onAction(Events.OnDeleteTask(id = 1L))

        dispatcher.scheduler.advanceUntilIdle()

        assertFalse(repository.tasks.value.contains(task))

        val effect = withTimeoutOrNull(1000) { viewModel.uiEffect.first() }
        assertEquals(SideEffect.NavigateToBack, effect)
    }
}