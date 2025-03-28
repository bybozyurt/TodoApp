package ab.todoapp.domain.usecase

import ab.todoapp.domain.model.ColorType
import ab.todoapp.domain.model.ToDoTask
import ab.todoapp.domain.repository.FakeToDoRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetTasksUseCaseTest {

    private lateinit var repository: FakeToDoRepository
    private lateinit var getTasksUseCase: GetTasksUseCase

    @BeforeTest
    fun setUp() {
        repository = FakeToDoRepository()
        getTasksUseCase = GetTasksUseCase(repository)
    }

    @Test
    fun `should return all tasks`() = runTest {
        val task1 = ToDoTask(
            id = 1L,
            title = "Task 1",
            description = "Description 1",
            isCompleted = false,
            colorType = ColorType.RED
        )
        val task2 = ToDoTask(
            id = 2L,
            title = "Task 2",
            description = "Description 2",
            isCompleted = true,
            colorType = ColorType.BLUE
        )

        repository.addTask(task1)
        repository.addTask(task2)

        val result = getTasksUseCase().first()
        assertEquals(2, result.size)
    }
}