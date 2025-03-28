package ab.todoapp.domain.usecase

import ab.todoapp.domain.model.ColorType
import ab.todoapp.domain.model.ToDoTask
import ab.todoapp.domain.repository.FakeToDoRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class AddTaskUseCaseTest {

    private lateinit var repository: FakeToDoRepository
    private lateinit var addTaskUseCase: AddTaskUseCase

    @BeforeTest
    fun setUp() {
        repository = FakeToDoRepository()
        addTaskUseCase = AddTaskUseCase(repository)
    }

    @Test
    fun `should add task to repository`() = runTest {
        val task = ToDoTask(
            id = 0L,
            title = "New Task",
            description = "Test",
            isCompleted = false,
            colorType = ColorType.GREEN
        )

        addTaskUseCase(task)

        assertTrue(repository.tasks.value.contains(task))
    }
}