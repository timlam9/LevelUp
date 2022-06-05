import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.github.tkuenneth.nativeparameterstoreaccess.MacOSDefaults
import com.github.tkuenneth.nativeparameterstoreaccess.NativeParameterStoreAccess
import com.github.tkuenneth.nativeparameterstoreaccess.WindowsRegistry
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

var isInDarkMode by Delegates.observable(false) { _, oldValue, newValue ->
    onIsInDarkModeChanged?.let { it(oldValue, newValue) }
}
var onIsInDarkModeChanged: ((Boolean, Boolean) -> Unit)? = null

fun colors(): Colors = if (isInDarkMode) {
    darkColors()
} else {
    lightColors()
}

fun getDarkMode() {
    GlobalScope.launch {
        while (isActive) {
            val newMode = isSystemInDarkTheme()
            if (isInDarkMode != newMode) {
                isInDarkMode = newMode
            }
            delay(1000)
        }
    }
}

fun isSystemInDarkTheme(): Boolean {
    return when {
        NativeParameterStoreAccess.IS_WINDOWS -> {
            val result = WindowsRegistry.getWindowsRegistryEntry(
                "HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize",
                "AppsUseLightTheme"
            )
            result == 0x0
        }
        NativeParameterStoreAccess.IS_MACOS -> {
            val result = MacOSDefaults.getDefaultsEntry("AppleInterfaceStyle")
            result == "Dark"
        }
        else -> false
    }
}


@Stable
val Blue = Color(0xFF4285F4)

@Stable
val Green = Color(0xFF2AD135)

@Stable
val LightGray = Color(0xFFF3F3F3)

@Stable
val Black = Color(0xFF0E0E0F)

@Stable
val White = Color(0xFFFFFFFF)

@Stable
val Red = Color(0xFFEB4335)