package ab.todoapp.domain.usecase

import ab.todoapp.domain.model.ColorType
import ab.todoapp.domain.model.ToDoTask
import ab.todoapp.domain.repository.FakeToDoRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class UpdateTaskUseCaseTest {

    private lateinit var repository: FakeToDoRepository
    private lateinit var updateTaskUseCase: UpdateTaskUseCase

    @BeforeTest
    fun setUp() {
        repository = FakeToDoRepository()
        updateTaskUseCase = UpdateTaskUseCase(repository)
    }

    @Test
    fun `should update task`() = runTest {
        val task = ToDoTask(
            id = 1L,
            title = "Old Task",
            description = "Old Description",
            isCompleted = false,
            colorType = ColorType.PURPLE
        )
        repository.addTask(task)

        val updated = task.copy(
            title = "Updated Title",
            description = "Updated Desc",
            isCompleted = true,
            colorType = ColorType.GREEN
        )

        updateTaskUseCase(updated)

        assertTrue(repository.tasks.value.contains(updated))
    }
}