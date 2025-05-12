import android.app.TimePickerDialog
import android.widget.TimePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.profile.R
import java.util.Calendar

@Composable
fun TimePickerField(
    modifier: Modifier = Modifier,
    label: String = "Время",
    initialHour: Int = Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
    initialMinute: Int = Calendar.getInstance().get(Calendar.MINUTE),
    is24Hour: Boolean = true,
    onTimeChange: (hour: Int, minute: Int) -> Unit = { _, _ -> },
    onValidationChange: (isValid: Boolean) -> Unit = {}
) {
    val context = LocalContext.current

    // Состояние для часов и минут
    var hour by remember { mutableIntStateOf(initialHour) }
    var minute by remember { mutableIntStateOf(initialMinute) }

    // Текстовое поле для ручного ввода
    var timeText by remember { mutableStateOf(String.format("%02d:%02d", hour, minute)) }
    var isError by remember { mutableStateOf(false) }

    LaunchedEffect(isError) {
        onValidationChange(!isError)
    }

    // Создаём диалог выбора времени
    val timePickerDialog = remember {
        TimePickerDialog(
            context,
            { _: TimePicker, selectedHour: Int, selectedMinute: Int ->
                hour = selectedHour
                minute = selectedMinute
                timeText = String.format("%02d:%02d", hour, minute)
                isError = false
                onTimeChange(hour, minute)
                onValidationChange(true)
            },
            hour,
            minute,
            is24Hour
        )
    }

    Column(modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = timeText,
            onValueChange = { newText ->
                timeText = newText
                val parts = newText.split(":")
                val valid = if (parts.size == 2) {
                    val h = parts[0].toIntOrNull()
                    val m = parts[1].toIntOrNull()
                    h != null && m != null && h in 0..23 && m in 0..59
                } else false

                if (valid) {
                    hour = parts[0].toInt()
                    minute = parts[1].toInt()
                    onTimeChange(hour, minute)
                }
                isError = !valid
                onValidationChange(valid)
            },
            label = { Text(label) },
            isError = isError,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            suffix = {
                IconButton(
                    onClick = { timePickerDialog.show() },
                ) {
                    Icon(
                        painter = painterResource(R.drawable.clock),
                        contentDescription = "Выбрать время",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        )

        if (isError) Text("Неверный формат времени", color = MaterialTheme.colorScheme.error)
    }
}
