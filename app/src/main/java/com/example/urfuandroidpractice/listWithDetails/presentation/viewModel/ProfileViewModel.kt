package com.example.urfuandroidpractice.listWithDetails.presentation.viewModel

import android.app.AlarmManager
import android.app.DownloadManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.icu.util.Calendar
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.urfuandroidpractice.listWithDetails.data.receivers.NotificationReceiver
import com.example.urfuandroidpractice.listWithDetails.domain.models.UserProfile
import com.example.urfuandroidpractice.listWithDetails.domain.repository.IProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class ProfileViewModel(
    private val repository: IProfileRepository
) : ViewModel() {

    private val _notificationTime = MutableStateFlow(Pair(0, 0))

    val profile: StateFlow<UserProfile> = repository
        .getProfileFlow()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UserProfile()
        )

    fun saveProfile(profile: UserProfile) {
        viewModelScope.launch { repository.saveProfile(profile) }
    }

    fun storeBitmapToCache(context: Context, bitmap: Bitmap): Uri {
        val cacheDir = File(context.cacheDir, "images").apply { mkdirs() }
        val file = File(cacheDir, "avatar_${System.currentTimeMillis()}.png")
        FileOutputStream(file).use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        return Uri.fromFile(file)
    }

    fun enqueueDownloadRequest(url: String, context: Context) {
        val req = DownloadManager.Request(Uri.parse(url))

        with(req) {
            setTitle("Resume.pdf")
            setMimeType("pdf")
            setDescription("Downloading pdf...")
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                "Resume.pdf"
            )
        }

        val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        manager.enqueue(req)
    }

    fun saveNotificationTime(context: Context, hour: Int, minute: Int) {
        _notificationTime.value = Pair(hour, minute)

        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        prefs.edit()
            .putInt("notif_hour", hour)
            .putInt("notif_minute", minute)
            .apply()
    }

    fun scheduleNotification(context: Context) {
        val (hour, minute) = _notificationTime.value

        // считаем когда должен сработать Alarm
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra("notification_id", 1001)
            putExtra("notification_title", "Напоминание")
            putExtra("notification_content", "Пора выполнить запланированное действие")
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            1001,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            pendingIntent
        )
    }
}