package ab.todoapp.feature.home

import ab.todoapp.common.Constant
import ab.todoapp.domain.model.ColorType
import ab.todoapp.domain.model.ToDoTask
import ab.todoapp.domain.repository.FakeToDoRepository
import ab.todoapp.domain.usecase.*
import ab.todoapp.feature.home.HomeScreenContract.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import kotlin.test.*

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)

    private lateinit var repository: FakeToDoRepository
    private lateinit var saveTaskUseCase: SaveTaskUseCase
    private lateinit var getTasksUseCase: GetTasksUseCase
    private lateinit var viewModel: HomeViewModel

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        repository = FakeToDoRepository()
        saveTaskUseCase = SaveTaskUseCase(
            addTaskUseCase = AddTaskUseCase(repository),
            updateTaskUseCase = UpdateTaskUseCase(repository)
        )
        getTasksUseCase = GetTasksUseCase(repository)
        viewModel = HomeViewModel(dispatcher, saveTaskUseCase, getTasksUseCase)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `onEvent OnCheckedChange should add task when id is INVALID_TASK_ID`() = scope.runTest {
        val task = ToDoTask(
            id = Constant.INVALID_TASK_ID,
            title = "New Task",
            description = "New",
            isCompleted = false,
            colorType = ColorType.RED
        )

        viewModel.onEvent(Event.OnCheckedChange(task))

        dispatcher.scheduler.advanceUntilIdle()

        assertTrue(repository.tasks.value.contains(task))
    }

    @Test
    fun `onEvent OnCheckedChange should update task when id is valid`() = scope.runTest {
        val task = ToDoTask(
            id = 1L,
            title = "Old Task",
            description = "Initial",
            isCompleted = false,
            colorType = ColorType.BLUE
        )
        repository.addTask(task)

        val updatedTask = task.copy(
            title = "Updated Task",
            isCompleted = true,
            colorType = ColorType.GREEN
        )

        viewModel.onEvent(Event.OnCheckedChange(updatedTask))

        dispatcher.scheduler.advanceUntilIdle()

        assertTrue(repository.tasks.value.contains(updatedTask))
    }

    @Test
    fun `getTasks should emit initial empty list`() = scope.runTest {
        val result = viewModel.getTasks.value
        assertEquals(null, result) // Because initialValue = null in stateIn()
    }
}