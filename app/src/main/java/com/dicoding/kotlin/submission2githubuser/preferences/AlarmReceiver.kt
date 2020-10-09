package com.dicoding.kotlin.submission2githubuser.preferences

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.dicoding.kotlin.submission2githubuser.MainActivity
import com.dicoding.kotlin.submission2githubuser.R
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    companion object{
        const val NOTIFICATION_ID = 1
        const val CHANNEL_ID = "channel_01"
        const val CHANNEL_NAME: String = "GithubChannel"
        private const val ALARM_ID = 101

        fun setRepeatingAlarm(context: Context?, status: Boolean) {
            val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val calendar = Calendar.getInstance().apply {
                timeInMillis = System.currentTimeMillis()
                set(Calendar.HOUR_OF_DAY, 9)
            }
            val alarmIntent = Intent(context, AlarmReceiver::class.java).let { intent ->
                PendingIntent.getBroadcast(context, ALARM_ID, intent, 0)
            }
            if (status){
                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, alarmIntent)
                Toast.makeText(context, "Reminder is On", Toast.LENGTH_SHORT).show()
            }else{
                alarmManager.cancel(alarmIntent)
                Toast.makeText(context, "Reminder is Off", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onReceive(context: Context?, intent: Intent) {
        sendNotification(context)
    }

    fun sendNotification(context: Context?){
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val mNotificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notifSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val mBuilder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_baseline_notifications_on)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_baseline_notifications_gray))
            .setContentTitle(context.resources?.getString(R.string.notification_title))
            .setContentText(context.resources?.getString(R.string.notification_content))
            .setSound(notifSound)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = CHANNEL_NAME
            mBuilder.setChannelId(CHANNEL_ID)
            mNotificationManager.createNotificationChannel(channel)
        }

        val notification = mBuilder.build()

        mNotificationManager.notify(NOTIFICATION_ID, notification)
    }


}
