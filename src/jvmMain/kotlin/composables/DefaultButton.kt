package composables

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    text: String,
    height: Dp = 60.dp,
    fontSize: TextUnit = 20.sp,
    textColor: Color = MaterialTheme.colors.background,
    color: Color = MaterialTheme.colors.onBackground,
    cornerSize: CornerSize = CornerSize(60),
    shape: Shape = MaterialTheme.shapes.small.copy(cornerSize),
    onclick: () -> Unit
) {
    Button(
        onClick = onclick,
        modifier = modifier.height(height),
        shape = shape,
        colors = ButtonDefaults.buttonColors(backgroundColor = color)
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = textColor,
                fontWeight = FontWeight.Bold,
                fontSize = fontSize
            )
        )
    }
}
