package ab.todoapp.domain.usecase

import ab.todoapp.common.Constant
import ab.todoapp.domain.model.ColorType
import ab.todoapp.domain.model.ToDoTask
import ab.todoapp.domain.repository.FakeToDoRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class AddTaskUseCaseTest {

    private lateinit var repository: FakeToDoRepository
    private lateinit var updateTaskUseCase: UpdateTaskUseCase
    private lateinit var addTaskUseCase: AddTaskUseCase

    @BeforeTest
    fun setUp() {
        repository = FakeToDoRepository()
        updateTaskUseCase = UpdateTaskUseCase(repository)
        addTaskUseCase = AddTaskUseCase(repository, updateTaskUseCase)
    }

    @Test
    fun `should add when id is INVALID_TASK_ID`() = runTest {
        val task = ToDoTask(
            id = Constant.INVALID_TASK_ID,
            title = "Test Task",
            description = "Some Description",
            isCompleted = false,
            colorType = ColorType.GREEN
        )
        addTaskUseCase(task)
        assertTrue(repository.tasks.value.contains(task))
    }

    @Test
    fun `should update when id is valid`() = runTest {
        val task = ToDoTask(
            id = 1L,
            title = "Old Task",
            description = "Old Description",
            isCompleted = false,
            colorType = ColorType.RED
        )
        repository.addTask(task)
        val updated = task.copy(
            title = "Updated Task",
            isCompleted = true,
            colorType = ColorType.BLUE
        )
        addTaskUseCase(updated)
        assertTrue(repository.tasks.value.contains(updated))
    }
}