package ab.todoapp.domain.usecase

import ab.todoapp.common.Constant
import ab.todoapp.domain.model.ColorType
import ab.todoapp.domain.model.ToDoTask
import ab.todoapp.domain.repository.FakeToDoRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class SaveTaskUseCaseTest {

    private lateinit var repository: FakeToDoRepository
    private lateinit var saveTaskUseCase: SaveTaskUseCase

    @BeforeTest
    fun setUp() {
        repository = FakeToDoRepository()
        saveTaskUseCase = SaveTaskUseCase(
            addTaskUseCase = AddTaskUseCase(repository),
            updateTaskUseCase = UpdateTaskUseCase(repository)
        )
    }

    @Test
    fun `should add task when id is INVALID_TASK_ID`() = runTest {
        val task = ToDoTask(
            id = Constant.INVALID_TASK_ID,
            title = "New",
            description = "Add",
            isCompleted = false,
            colorType = ColorType.RED
        )

        saveTaskUseCase(task)

        assertTrue(repository.tasks.value.contains(task))
    }

    @Test
    fun `should update task when id is valid`() = runTest {
        val task = ToDoTask(
            id = 1L,
            title = "Initial",
            description = "Old",
            isCompleted = false,
            colorType = ColorType.BLUE
        )
        repository.addTask(task)

        val updated = task.copy(title = "Updated", isCompleted = true, colorType = ColorType.GREEN)

        saveTaskUseCase(updated)

        assertTrue(repository.tasks.value.contains(updated))
    }
}