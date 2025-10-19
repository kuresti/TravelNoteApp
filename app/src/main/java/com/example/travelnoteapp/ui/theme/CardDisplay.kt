package com.example.travelnoteapp.ui.theme

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.travelnoteapp.data.Note
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.travelnoteapp.R


/********************************
 *CardDisplay
 * UI of the note card that contains
 * the content of the card
 ********************************/
@Composable
fun CardDisplay(
    note: Note,
    onEdit: (() -> Unit)? = null,
    onDelete: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    // By using rememberSaveable the note card stays expanded
    // even after it is scrolled off page.
    var expanded by rememberSaveable { mutableStateOf(false) }

    Card(modifier = modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {
                Text(
                        text = note.title,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )

                    if (expanded) {
                        Text(
                            text = note.content,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Spacer(modifier = Modifier.height(6.dp))
                        Divider()
                        Row(modifier = Modifier.fillMaxWidth()) {
                            if(onEdit != null) {
                                TextButton(onClick = onEdit) { Text("Edit") }
                            }
                            if(onDelete != null) {
                                TextButton(onClick = onDelete) { Text("Delete") }
                            }
                    }
                }
                Button(onClick = { expanded = !expanded }) {
                    Text(
                        text = if (expanded) {
                            stringResource(R.string.show_less)
                        } else {
                            stringResource(R.string.show_more)
                        }
                    )

                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardDisplayPreview() {
    val sample = Note(id = 1, title = "Spain Trip", content =  "Visited Bilboa and Gernika. Had Tapas in San Sebastian.")
    CardDisplay(note = sample)
}

