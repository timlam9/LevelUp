package presentation.ui.composables

import Blue
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
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
fun OutlinedButton(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 20.sp,
    textColor: Color = Blue,
    color: Color = Blue,
    shape: Shape = MaterialTheme.shapes.small.copy(CornerSize(30)),
    stroke: Dp = 2.dp,
    onclick: () -> Unit
) {
    androidx.compose.material.OutlinedButton(
        onClick = onclick,
        modifier = modifier.height(60.dp),
        border = BorderStroke(stroke, color),
        shape = shape,
        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = MaterialTheme.colors.background)
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
