package www.uzmd.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyForground : Service() {
    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate() {
        super.onCreate()
        Log.d("MY_TAG", "onCreate: Yaratildi")
        createChannel()
        startForeground(1, notification())
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MY_TAG", "onStartCommand: Ishga tushdi")
        scope.launch {
            for (i in 1..30) {
                delay(1000)
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
        Log.d("MY_TAG", "onDestroy: Kill Bo`ldi")
    }

    fun createChannel() {
        val notificationManager = getSystemService(NotificationManager::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Bu habara jonatish uchun",
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationManager.createNotificationChannel(channel)
        }
    }

    fun notification(): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentText("Bu yozuv test uchun ishlatilyapti habardor bo`ling")
            .setContentTitle("Yangi habar")
            .setSmallIcon(R.drawable.ic_launcher_background)

            .build()

    }


    companion object{
        fun newInstanse(context: Context):Intent{
            return Intent(context,MyForground::class.java)
        }
    }


    private val CHANNEL_ID = "SENDTXT"

}