import assertk.assertThat
import assertk.assertions.isEqualTo
import data.local.LocalDataSource
import data.local.LocalDataSourceImpl
import data.local.MockDatabase
import domain.model.ToDoTaskEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class LocalDataSourceImplTest {

    private lateinit var localDataSource: LocalDataSource

    private val scope = TestScope()

    @OptIn(ExperimentalCoroutinesApi::class)
    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher(scope.testScheduler))
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `addTask should add a task to the data source`() = scope.runTest {
        // Initialize MockDatabase and LocalDataSourceImpl
        val mockDatabase = MockDatabase()
        localDataSource = LocalDataSourceImpl(
            db = mockDatabase,
            dispatcher = StandardTestDispatcher()
        )

        // Perform the test
        val task = ToDoTaskEntity(id = 1, title = "Test Task", description = "Description")
        localDataSource.addTask(task)

        // Verify the result
        val tasks = localDataSource.getAllTasks().first()
        assertThat(tasks.size).isEqualTo(1)
        assertThat(tasks[0].title).isEqualTo("Test Task")
    }

    @Test
    fun `updateTask should update a task in the data source`() = scope.runTest {
        val mockDatabase = MockDatabase()
        localDataSource = LocalDataSourceImpl(
            db = mockDatabase,
            dispatcher = StandardTestDispatcher()
        )

        val initialTask =
            ToDoTaskEntity(id = 1, title = "Old Task", description = "Old Description")
        localDataSource.addTask(initialTask)

        val updatedTask =
            ToDoTaskEntity(id = 1, title = "Updated Task", description = "Updated Description")
        localDataSource.updateTask(updatedTask)

        val tasks = localDataSource.getAllTasks().first()
        assertThat(tasks.size).isEqualTo(1)
        assertThat(tasks[0].title).isEqualTo("Updated Task")
    }

    @Test
    fun `deleteTask should remove a task from the data source`() = scope.runTest {
        val mockDatabase = MockDatabase()
        localDataSource = LocalDataSourceImpl(
            db = mockDatabase,
            dispatcher = StandardTestDispatcher()
        )

        val taskToDelete =
            ToDoTaskEntity(id = 1, title = "Task to Delete", description = "Description")
        localDataSource.addTask(taskToDelete)

        // Delete the task
        localDataSource.deleteTask(taskToDelete.id)

        val tasks = localDataSource.getAllTasks().first()
        assertThat(tasks.size).isEqualTo(0)  // There should be no tasks after deletion
    }

    @Test
    fun `getTaskById should return the correct task`() = scope.runTest {
        val mockDatabase = MockDatabase()
        localDataSource = LocalDataSourceImpl(
            db = mockDatabase,
            dispatcher = StandardTestDispatcher()
        )

        val task = ToDoTaskEntity(id = 1, title = "Test Task", description = "Description")
        localDataSource.addTask(task)

        val fetchedTask = localDataSource.getTaskById(1)
        assertThat(fetchedTask?.title).isEqualTo("Test Task")
    }
}
