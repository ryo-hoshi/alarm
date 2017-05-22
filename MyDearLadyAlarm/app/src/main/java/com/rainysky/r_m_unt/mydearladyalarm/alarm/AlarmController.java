package com.rainysky.r_m_unt.mydearladyalarm.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.util.Calendar;

import com.rainysky.r_m_unt.mydearladyalarm.preferences.AlarmSettingInfo;

public class AlarmController {

    private AlarmManager alarmManager;
    private Context context;
    //private int notificztionId = 0;

    private static final String TAG = "AlarmController";
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_alarm_control);
//    }

    public AlarmController(Context context) {
        this.context = context;
        alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
    }

    public void setAlarm(AlarmSettingInfo alarmSettingInfo) {

        PendingIntent operation = getAlarmPendingIntent(alarmSettingInfo.getAlarmNo());

        // アラームをONに設定した場合
        if (alarmSettingInfo.getAlarmSwitch()) {

            // 設定情報の日時をカレンダーに設定
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.HOUR_OF_DAY, alarmSettingInfo.getHour());
            calendar.set(Calendar.MINUTE, alarmSettingInfo.getMinute());
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);


            Log.d(TAG, "アラーム設定元時間（HOUR）：" + alarmSettingInfo.getHour());
            Log.d(TAG, "アラーム設定元時間（MINUTE）：" + alarmSettingInfo.getMinute());
            Log.d(TAG, "アラーム設定時間（HOUR）：" + calendar.HOUR_OF_DAY);
            Log.d(TAG, "アラーム設定時間（MINUTE）：" + calendar.MINUTE);
            Log.d(TAG, "アラーム設定時間初期時刻：" + calendar.getTime());
            Log.d(TAG, "アラーム設定時間初期triggerAtMillis：" + calendar.getTimeInMillis());

            Log.d(TAG, "現在時刻からのtriggerAtMillis：" + System.currentTimeMillis());
            Log.d(TAG, "判定後のtriggerAtMillis：" + calendar.getTimeInMillis());


            // 設定日時が過去日の場合は翌日にする
            if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }
            long triggerAtMillis = calendar.getTimeInMillis();
            Log.d(TAG, "アラーム設定時刻：" + calendar.getTime());

            int currentApiVersion = Build.VERSION.SDK_INT;

            Log.d(TAG, "currentApiVersion：" + currentApiVersion);

            // API Levelが19未満
            if (currentApiVersion < Build.VERSION_CODES.KITKAT) {
                // setを使う
                alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, operation);

                // API Levelが21未満
            } else if (currentApiVersion < Build.VERSION_CODES.LOLLIPOP) {
                // setExactを使う
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, operation);

                // API Levelが21以上
            } else {
                boolean syoudenryoku = false;

                // 省電力の場合
                if (syoudenryoku) {
                    // API Levelが23以上
                    if (Build.VERSION_CODES.M <= currentApiVersion) {
                        // setExactAndAlloWhileIdleを使う
                        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, operation);

                        // API Levelが23未満
                    } else {
                        // setExactを使う
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, operation);
                    }

                    // 省電力ではない（正確を優先）の場合
                } else {
                    // setAlarmClock
                    alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(triggerAtMillis,null), operation);
                }
            }
            Log.d(TAG, "triggerAtMillis：" + triggerAtMillis);

            // アラームをOFFに設定した場合
        } else {
            alarmManager.cancel(operation);
        }
    }

//    public void stopAlarm() {
//        alarmManager.cancel();
//    }

    /**
     *
     * @param index
     * @return
     */
    private PendingIntent getAlarmPendingIntent(int index) {
        Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
        // Notificationの識別子
        intent.putExtra("alarmIndex", index);
        return PendingIntent.getBroadcast(context, index, intent, PendingIntent.FLAG_CANCEL_CURRENT);
    }
}
