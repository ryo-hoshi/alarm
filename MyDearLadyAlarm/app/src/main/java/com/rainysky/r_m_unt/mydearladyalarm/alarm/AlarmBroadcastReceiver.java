package com.rainysky.r_m_unt.mydearladyalarm.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.rainysky.r_m_unt.mydearladyalarm.R;
import com.rainysky.r_m_unt.mydearladyalarm.preferences.AlarmSetting;
import com.rainysky.r_m_unt.mydearladyalarm.preferences.AlarmSettingInfo;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

/**
 * Created by ryota on 2017/05/08.
 */
public class AlarmBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "AlarmBroadcastReceiver";

    private AlarmSetting alarmSetting;
    SoundController soundController;

    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "AlarmBroadcastReceiver onReceive");

        Intent soundPlayIntent = new Intent(context, SoundActivity.class);
        soundPlayIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //soundPlayIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(soundPlayIntent);

        /*
        // アラーム音を鳴らす
//        Ringtone ringtone = RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
//        ringtone.play();
//        SoundActivity.setMediaPlayer(context, R.raw.effect);
//        SoundActivity.loopPlay();
        soundController = SoundController.getInstance();
        soundController.setMediaPlayer(context, R.raw.effect);
        // ループ再生
        soundController.play(true);

        // intentIDを取得
        int notificationId = intent.getIntExtra("notification", 0);
        // 通知をタップしたときに起動するActivityの指定
        Intent nextIntent = new Intent(context, SoundActivity.class);
        //Intent nextIntent = new Intent(context, MainActivity.class);

        // アラーム設定情報を取得
        PendingIntent nextPendingIntent = PendingIntent.getActivity(context, notificationId, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        int alarmIndex = intent.getIntExtra("alarmIndex", 0);
        alarmSetting = AlarmSetting.getInstance(context);
        // 表示するアラーム1件分の情報を取得
        AlarmSettingInfo alarmInfo = alarmSetting.getAlarmSettingInfoList().get(alarmIndex);

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        Toast.makeText(context, "AlarmBroadcastReceiver onReceive", Toast.LENGTH_SHORT);

        // TODO　アラーム設定情報をセットする
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.laala)
                // ステータスバーに表示されるテキスト
                .setTicker("時間です")
                .setWhen(System.currentTimeMillis())
                // 開いたときに表示されるタイトル
                .setContentTitle("TestAlarm")
                // 開いたときに表示されるサブタイトル
                .setContentText("時間になりました：" + alarmInfo.getAlarmTime())
                //.setPriority(Notification.PRIORITY_DEFAULT)
                .setPriority(Notification.PRIORITY_MAX)
                .setAutoCancel(true)
                .addAction(R.drawable.falulu, "止める", nextPendingIntent)
                // 通知方法
                //.setDefaults(Notification.DEFAULT_SOUND)
                // 通知をタップしたときに立ち上げるActivity
                //.setContentIntent(pushedIntent)
                // アラーム音
//                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))
                //.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))
                //.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))
                .build();

        // 古い通知を削除
//        notificationManager.cancelAll();

        // 通知
        notificationManager.notify(0, notification);


//        mediaPlayer = MediaPlayer.create(context, R.raw.effect);
//        mediaPlayer.start();
*/

        Intent nextIntent = new Intent(context, SoundActivity.class);
        nextIntent.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent nextPendingIntent = PendingIntent.getActivity(context, 0, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // 表示するアラーム1件分の情報を取得
        int alarmIndex = intent.getIntExtra("alarmIndex", 0);
        alarmSetting = AlarmSetting.getInstance(context);
        AlarmSettingInfo alarmInfo = alarmSetting.getAlarmSettingInfoList().get(alarmIndex);

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.laala)
                // ステータスバーに表示されるテキスト
                .setTicker("時間です")
                .setWhen(System.currentTimeMillis())
                // 開いたときに表示されるタイトル
                .setContentTitle("TestAlarm")
                // 開いたときに表示されるサブタイトル
                .setContentText("時間になりました：" + alarmInfo.getAlarmTime())
                //.setPriority(Notification.PRIORITY_DEFAULT)
                .setPriority(Notification.PRIORITY_MAX)
                .setAutoCancel(true)
                // 通知方法
                .setDefaults(Notification.DEFAULT_ALL)
                // 通知をタップしたときに立ち上げるActivity
                .setContentIntent(nextPendingIntent)
                .build();

        // 通知
        notificationManager.notify(0, notification);
    }
}
