package com.example.travelnoteapp.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.travelnoteapp.data.TravelNoteViewModel
import com.example.travelnoteapp.data.Note


/********************************
 * Builds UI Note edit screen. With a textfield for the title
 * and a textfield for the note content. There is a Button
 * which validates for empty fields and then
 * saves the edited note to the room database.
 ********************************/
@Composable
fun NoteEditorScreen(
    viewModel: TravelNoteViewModel,
    note: Note,
    onNoteUpdated: () -> Unit
) {

    // Uses rememberSaveable to persist the note title and
    // note content if screen recomposes.
    var title by rememberSaveable { mutableStateOf(note.title) }
    var content by rememberSaveable { mutableStateOf(note.content) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
            verticalArrangement = Arrangement.Top
    ) {
        Text("Edit Note", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Content") },
            modifier = Modifier.fillMaxWidth().height(200.dp),
            maxLines = 15
        )
        Spacer(modifier = Modifier.height(12.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {


            Button(
                onClick = {
                    val updated = note.copy(title = title, content = content)
                    viewModel.updateNote(updated)
                    onNoteUpdated()
                }) {
                Text("Update Note")
            }
        }

    }
}