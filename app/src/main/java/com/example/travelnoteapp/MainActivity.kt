package com.example.travelnoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.travelnoteapp.ui.theme.TravelNoteAppTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.travelnoteapp.data.DBProvider
import com.example.travelnoteapp.data.TravelNoteViewModel
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.travelnoteapp.data.TravelNoteViewModelFactory
import com.example.travelnoteapp.note_creation.viewmodel.CreateNoteScreen
import com.example.travelnoteapp.ui.theme.NoteListScreen
import com.example.travelnoteapp.ui.theme.NoteEditorScreen
import com.example.travelnoteapp.data.Note

/********************************
 *
 ********************************/
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

   // Access the room database
        val database = DBProvider.getDatabase(applicationContext)
        val dao = database.noteDao()
        val factory = TravelNoteViewModelFactory(dao)

        setContent {
            TravelNoteAppTheme {
                val viewModel: TravelNoteViewModel = viewModel(factory = factory)
                var currentScreen by remember { mutableStateOf("list") }
                var selectedNote by remember { mutableStateOf<Note?>(null) }

                when (currentScreen) {
                    "list" -> NoteListScreen(viewModel = viewModel,
                        onEditNote =  { note -> selectedNote = note; currentScreen = "edit"},
                        onAddNoteRequested =  { currentScreen =  "add" }
                    )
                    "add" -> CreateNoteScreen(viewModel = viewModel) {
                        currentScreen = "list"
                    }
                    "edit" -> selectedNote?.let { note ->
                        NoteEditorScreen(viewModel = viewModel, note = note) {
                            currentScreen = "list"
                            selectedNote = null
                        }
                }
            }
        }
        }
    }
}







    /********************************
     * Composable SearchBar function
     * Creates search bar ui
     * Includes a text field for user to enter a note
     * they want to search for.
     * Colors are taken from the colors in Color.kt
     * Modifier is used to have searchbar fill max space
     * and also gives the searchbar a minimum height.
     ********************************/
//    @Composable
//    fun SearchBar(
//        modifier: Modifier = Modifier
//    ) {
//        TextField(
//            value = "Search",
//            onValueChange = {},
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Search,
//                    contentDescription = null
//                )
//            },
//            colors = TextFieldDefaults.colors(
//                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
//                focusedContainerColor = MaterialTheme.colorScheme.surface
//            ),
//            modifier = modifier
//                .fillMaxWidth()
//                .heightIn(min = 56.dp)
//
//        )
//    }


















