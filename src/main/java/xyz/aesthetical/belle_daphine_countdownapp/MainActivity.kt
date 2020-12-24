package xyz.aesthetical.belle_daphine_countdownapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.*

class MainActivity : AppCompatActivity() {
  @RequiresApi(Build.VERSION_CODES.O)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    
    val uploadDate = Calendar.getInstance()
    uploadDate.set(2020, 11, 25, 8, 30)
    val time = uploadDate.timeInMillis
    
    while (true) {
      val today = Calendar.getInstance().timeInMillis
      if (today >= time) {
        this.createNotification(
          "SHE UPLOADS TODAY! GO GO GO",
          "GO CHECK HER ONLYFANS"
        )
      }
      
      Thread.sleep(30000)
    }
  }
  
  @RequiresApi(Build.VERSION_CODES.O)
  private fun createNotification(title: String, content: String) {
    this.channel()
  
    val intent = Intent(this, MainActivity::class.java).apply {
      flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
  
    val fullScreenIntent = Intent(this, MainActivity::class.java)
    val fullScreenPendingIntent = PendingIntent.getActivity(this, 0,
      fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT)
    
    val builder = NotificationCompat.Builder(this, "ABCD556")
      .setSmallIcon(R.drawable.ic_launcher_background)
      .setContentTitle(title)
      .setContentText(content)
      .setContentIntent(pendingIntent)
      .setPriority(NotificationCompat.PRIORITY_HIGH)
      .setFullScreenIntent(fullScreenPendingIntent, true)
    
    NotificationManagerCompat.from(this).notify(34924384, builder.build())
    
    val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    
    if (vibrator.hasVibrator()) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        vibrator.vibrate(VibrationEffect.createOneShot(5000, VibrationEffect.DEFAULT_AMPLITUDE))
      } else {
        vibrator.vibrate(5000)
      }
    }
  }
  
  @RequiresApi(Build.VERSION_CODES.O)
  private fun channel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val channel = NotificationChannel(
        "ABCD556",
        "ONLYFANS!!",
        NotificationManager.IMPORTANCE_DEFAULT
      )
      
      val notificationManager: NotificationManager =
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
      return notificationManager.createNotificationChannel(channel)
    }
  }
}
