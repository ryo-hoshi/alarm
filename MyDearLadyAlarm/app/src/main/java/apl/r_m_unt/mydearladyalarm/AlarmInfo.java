package apl.r_m_unt.mydearladyalarm;

/**
 * Created by ryota on 2017/04/06.
 */
public class AlarmInfo {
    private String id;
    private int hour;
    private int minute;
    private String memo;
    private boolean alarmSwitch;

    public AlarmInfo(String id, int hour, int minute, String memo, boolean alarmSwitch) {
        this.id = id;
        this.hour = hour;
        this.minute = minute;
        this.memo = memo;
        this.alarmSwitch = alarmSwitch;
    }

    public String getId() {
        return id;
    }

    public String getAlarmTime() {
        return hour + ":" + minute;
    }

    public String getMemo() {
        return memo;
    }

    public boolean getAlarmSwitch() {
        return alarmSwitch;
    }
}
