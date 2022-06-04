package composables

import Blue
import EMPTY
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun NoteTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    error: String = EMPTY,
    maxLines: Int = 800,
    singleLine: Boolean = false,
    textColor: Color = Blue,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Next,
        keyboardType = KeyboardType.Text,
        capitalization = KeyboardCapitalization.Words
    ),
    leadingIcon: ImageVector = Icons.Default.Info,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            isError = error.isNotEmpty(),
            onValueChange = { onValueChange(it) },
            label = { Text(label) },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = Blue,
                focusedBorderColor = Blue,
                focusedLabelColor = Blue
            ),
            maxLines = maxLines,
            textStyle = TextStyle(
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                color = textColor
            ),
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) },
                onDone = { focusManager.clearFocus() }
            ),
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null
                )
            }
        )
        AnimatedVisibility(visible = error.isNotEmpty()) {
            Text(
                text = error,
                style = TextStyle(
                    color = MaterialTheme.colors.error,
                    fontSize = 14.sp
                )
            )
        }
    }
}
