package com.rainysky.r_m_unt.mydearladyalarm.preferences;

/**
 * Created by ryota on 2017/04/22.
 */
public class AlarmSettingInfo {

    private static final String TIME_DELIMITER = ":";

    private int alarmNo;
    private int hour;
    private int minute;
    private boolean alarmSwitch;
    //private boolean snoozeFlg;
    //private String snoozeTime;
    private int alarmMsgNo;
    private String memo;

    AlarmSettingInfo(int alarmNo, int alarmIndex) {
        this.alarmNo = alarmNo;
        this.hour = 0;
        this.minute = 0;
        this.alarmSwitch = false;
        //this.snoozeFlg = false;
        //this.snoozeTime = "0分";
        this.alarmMsgNo = 0;
        this.memo = "アラーム" + (alarmIndex + 1 );
    }

    public AlarmSettingInfo(int alarmNo,
                     int hour,
                     int minute,
                     boolean alarmSwitch,
      //               boolean snoozeFlg,
      //               String snoozeTime,
                     int alarmMsgNo,
                     String memo) {
        this.alarmNo = alarmNo;
        this.hour = hour;
        this.minute = minute;
        this.alarmSwitch = alarmSwitch;
       // this.snoozeFlg = snoozeFlg;
        //this.snoozeTime = snoozeTime;
        this.alarmMsgNo = alarmMsgNo;
        this.memo = memo;
    }

    public String getAlarmTime() {
        return String.format("%02d", hour) + TIME_DELIMITER + String.format("%02d", minute);
    }

    public String getMemo() {
        return memo;
    }

    public boolean getAlarmSwitch() {
        return alarmSwitch;
    }
    public void setAlarmSwitch(boolean alarmSwitch) {this.alarmSwitch = alarmSwitch;}

    public int getHour() { return hour; }

    public int getMinute() { return minute; }

   // public boolean getSnoozeFlg() { return snoozeFlg; }

    //public String getSnoozeTime() { return snoozeTime; }

    public int getAlarmMsgNo() { return alarmMsgNo; }

    public int getAlarmNo() { return alarmNo;}

//    public void setAlarmInfo(int hour, int minute, boolean alarmSwitch, boolean snoozeFlg, String snoozeTime, int alarmMsgNo, String memo) {
//        this.hour = hour;
//        this.minute = minute;
//        this.alarmSwitch = alarmSwitch;
//        this.snoozeFlg = snoozeFlg;
//        this.snoozeTime = snoozeTime;
//        this.alarmMsgNo = alarmMsgNo;
//        this.memo = memo;
//    }
}
