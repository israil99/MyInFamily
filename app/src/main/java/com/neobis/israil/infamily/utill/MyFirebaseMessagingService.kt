package com.neobis.israil.infamily.utill

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.neobis.israil.infamily.R
import com.neobis.israil.infamily.StartApplication
import com.neobis.israil.infamily.ui.main_sections.already_parent.AlreadyParentActivity



class MyFirebaseMessagingService : FirebaseMessagingService() {




    val TAG = "_________"
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        val CHANNEL_ID = "CHANEL FOR OREO"
        Log.e(TAG, "From: " + remoteMessage!!.from!!)

        // Check if message contains a data payload.
        if (remoteMessage.data.size > 0) {
            Log.e(TAG, "Message data payload: " + remoteMessage.data)
            if ( true) {

            } else {

                val intent = Intent(this, RemoteMessage.Notification::class.java)
                startActivity(intent)
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {
            Log.e(TAG, "Message Notification Body: " + remoteMessage.notification!!.body!!)
        }
        val intent = Intent(this, AlreadyParentActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.putExtra(Const.NOTIFCATION_INTENT, "performClick")
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.family_icon_for_notification)
                .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setContentTitle(remoteMessage.notification!!.title)
                .setContentText(remoteMessage.notification!!.body)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val description = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = description
            // Register the channel with the system
            val manager = (applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
            manager.createNotificationChannel(channel)

        }

        val notificationManager = NotificationManagerCompat.from(this)

        notificationManager.notify(1, mBuilder.build())

    }

}