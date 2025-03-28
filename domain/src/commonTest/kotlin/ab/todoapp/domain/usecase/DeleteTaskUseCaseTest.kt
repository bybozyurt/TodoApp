package ab.todoapp.domain.usecase

import ab.todoapp.domain.model.ColorType
import ab.todoapp.domain.model.ToDoTask
import ab.todoapp.domain.repository.FakeToDoRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse

class DeleteTaskUseCaseTest {

    private lateinit var repository: FakeToDoRepository
    private lateinit var deleteTaskUseCase: DeleteTaskUseCase

    @BeforeTest
    fun setUp() {
        repository = FakeToDoRepository()
        deleteTaskUseCase = DeleteTaskUseCase(repository)
    }

    @Test
    fun `should remove task by id`() = runTest {
        val task = ToDoTask(
            id = 1L,
            title = "Task to Delete",
            description = "Will be gone",
            isCompleted = false,
            colorType = ColorType.GRAY
        )
        repository.addTask(task)

        deleteTaskUseCase(task.id)

        assertFalse(repository.tasks.value.contains(task))
    }
}