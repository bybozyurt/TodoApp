package ab.todoapp.domain.usecase

import ab.todoapp.domain.model.ColorType
import ab.todoapp.domain.model.ToDoTask
import ab.todoapp.domain.repository.FakeToDoRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetTaskByIdUseCaseTest {

    private lateinit var repository: FakeToDoRepository
    private lateinit var getTaskByIdUseCase: GetTaskByIdUseCase

    @BeforeTest
    fun setUp() {
        repository = FakeToDoRepository()
        getTaskByIdUseCase = GetTaskByIdUseCase(repository)
    }

    @Test
    fun `should return correct task by id`() = runTest {
        val task = ToDoTask(
            id = 1L,
            title = "Find Me",
            description = "This is it",
            isCompleted = false,
            colorType = ColorType.ORANGE
        )
        repository.addTask(task)

        val result = getTaskByIdUseCase(1)
        assertEquals(task, result)
    }
}