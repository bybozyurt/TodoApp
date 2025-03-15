package ab.todoapp.shared

import ab.todoapp.shared.resources.Res
import ab.todoapp.shared.resources.add_new_note_info
import ab.todoapp.shared.resources.add_new_task
import ab.todoapp.shared.resources.all_notes
import ab.todoapp.shared.resources.completed
import ab.todoapp.shared.resources.description
import ab.todoapp.shared.resources.no_notes_available
import ab.todoapp.shared.resources.save_task
import ab.todoapp.shared.resources.title
import ab.todoapp.shared.resources.update_task
import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.stringResource

object Resources {

    object String {
        @Composable fun add_new_task() = stringResource(Res.string.add_new_task)
        @Composable fun update_task() = stringResource(Res.string.update_task)
        @Composable fun save_task() = stringResource(Res.string.save_task)
        @Composable fun completed() = stringResource(Res.string.completed)
        @Composable fun description() = stringResource(Res.string.description)
        @Composable fun title() = stringResource(Res.string.title)
        @Composable fun no_notes_available() = stringResource(Res.string.no_notes_available)
        @Composable fun add_new_note_info() = stringResource(Res.string.add_new_note_info)
        @Composable fun all_notes() = stringResource(Res.string.all_notes)
    }
}