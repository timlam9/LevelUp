package presentation.ui.composables

import Blue
import EMPTY
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun ExpandableCard(
    title: String,
    titleFontSize: TextUnit = 30.sp,
    titleFontWeight: FontWeight = FontWeight.Bold,
    showDetailsIcons: Boolean,
    description: String,
    descriptionMaxLines: Int = 6,
    shape: Shape = CutCornerShape(20.dp),
    color: Color = MaterialTheme.colors.background,
    padding: Dp = 12.dp,
    completed: Boolean,
    keyboardActionCode: Int,
    onUpdateClicked: (String) -> Unit,
    onDeletedClicked: () -> Unit,
    onCompletedClicked: () -> Unit,
) {
    var expandedState by remember { mutableStateOf(false) }
    var noteInput by remember { mutableStateOf(description) }
    val rotationState by animateFloatAsState(if (expandedState) 180f else 0f)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = shape,
        backgroundColor = color,
        onClick = {
            if (keyboardActionCode != 0) {
                expandedState = !expandedState
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = title,
                    fontSize = titleFontSize,
                    fontWeight = titleFontWeight,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                AnimatedVisibility(showDetailsIcons) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            modifier = Modifier.alpha(ContentAlpha.medium),
                            onClick = onDeletedClicked
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete"
                            )
                        }
                        IconButton(
                            modifier = Modifier.alpha(ContentAlpha.medium),
                            onClick = onCompletedClicked
                        ) {
                            if (completed) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Check"
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Check"
                                )
                            }
                        }
                    }
                }
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }
            AnimatedVisibility(expandedState) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    NoteTextField(
                        text = noteInput,
                        label = EMPTY,
                        maxLines = descriptionMaxLines,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.None,
                            keyboardType = KeyboardType.Text,
                            capitalization = KeyboardCapitalization.Words,
                            autoCorrect = false,
                        )
                    ) {
                        noteInput = it
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    DefaultButton(
                        text = "Update note",
                        color = Blue,
                        onclick = {
                            onUpdateClicked(noteInput)
                            expandedState = !expandedState
                        }
                    )
                }
            }
        }
    }
}