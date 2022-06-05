package presentation.navigation

import Green
import Red
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import presentation.ui.composables.ProgressChart

@Composable
fun ChartsScreen() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(contentAlignment = Alignment.Center) {
            ProgressChart(
                total = 80,
                completed = 40,
                size = 200.dp
            )
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "40/80",
                    color = Green,
                    fontSize = 24.sp
                )
                Text(
                    text = "200.dp",
                    color = Red,
                    fontSize = 24.sp
                )
            }
        }
        Spacer(modifier = Modifier.size(30.dp))
        Box(contentAlignment = Alignment.Center) {
            ProgressChart(
                total = 30,
                completed = 10,
                size = 120.dp
            )
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "10/30",
                    color = Green,
                    fontSize = 20.sp
                )
                Text(
                    text = "120.dp",
                    color = Red,
                    fontSize = 20.sp
                )
            }
        }
    }
}