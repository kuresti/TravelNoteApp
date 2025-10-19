package com.example.travelnoteapp.note_creation.viewmodel

import android.icu.number.NumberFormatter
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.travelnoteapp.data.TravelNoteViewModel
import com.example.travelnoteapp.data.Note as TravelNote




/********************************
 * Composable function for Add Note
 * Screen. Creates a text field for user
 * to enter a note. When note is created
 * user clicks or touches "Save Note"
 * Button and the note is saved in the Room
 * data base. Once note is saved will navigate
 * back to Notes list.
 ********************************/

@Composable
fun CreateNoteScreen(
    viewModel: TravelNoteViewModel,
    onNoteAdded: () -> Unit
) {
    // By using rememberSaveable the title and content
    // will persist even if the screen recomposes.
    var title by rememberSaveable { mutableStateOf("") }
    var content by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text("Create a New Note", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
           value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = content,
            onValueChange = { content = it },
            label = { Text("Content") },
            modifier = Modifier.fillMaxWidth().height(200.dp),
            maxLines = 15
        )

        // The button checks to make sure text fields
        // are not blank. Then calls viewModel.addNote()
        // which saves the note to the room database
        Button(
            onClick = {
                if (title.isNotBlank() && content.isNotBlank()) {
                    viewModel.addNote(TravelNote(title = title, content = content))
                    title = ""
                    content = ""
                    onNoteAdded()
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save Note")
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun CreateNotePreview() {
//    val fake = object : TravelNoteViewModel(noteDao = com.example.travelnoteapp.data.FakeNoteDao()
//    AddNoteScreen(viewModel = fake, onNoteAdd = {})
//}