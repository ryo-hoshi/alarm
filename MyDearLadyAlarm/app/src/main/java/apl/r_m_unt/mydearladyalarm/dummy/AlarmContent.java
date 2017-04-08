package apl.r_m_unt.mydearladyalarm.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apl.r_m_unt.mydearladyalarm.AlarmInfo;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class AlarmContent {

    /**
     * An array of sample (dummy) items.
     */
//    public static List<AlarmItem> ITEMS = new ArrayList<AlarmItem>();
    public static List<AlarmInfo> ITEMS = new ArrayList<AlarmInfo>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    //public static Map<String, AlarmItem> ITEM_MAP = new HashMap<String, AlarmItem>();
    public static Map<String, AlarmInfo> ITEM_MAP = new HashMap<String, AlarmInfo>();

    //private static final int COUNT = 10;

//    static {
//        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createAlarmItem(i));
//        }
//    }

    public static void setItem(List<AlarmInfo> item) {
        ITEMS = item;

        for (AlarmInfo alarmInfo : item) {
            ITEM_MAP.put(alarmInfo.getId(), alarmInfo);
        }
    }

//    private static void addItem(AlarmItem item) {
//        ITEMS.add(item);
//        ITEM_MAP.put(item.id, item);
//    }

//    private static AlarmItem createAlarmItem(int position) {
//        return new AlarmItem(String.valueOf(position), "アラーム情報設定", makeDetails(position));
//    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
//    public static class AlarmItem {
//        public String id;
//        public String content;
//        public String details;
//
//        public AlarmItem(String id, String content, String details) {
//            this.id = id;
//            this.content = content;
//            this.details = details;
//        }
//
//        @Override
//        public String toString() {
//            return content;
//        }
//    }
//    public static class AlarmItem {
//        public String id;
//        public int hour;
//        public int minute;
//        public String memo;
//        public boolean alarmSwitch;
//
//        public AlarmItem(String id, int hour, int minute, String memo, boolean alarmSwitch) {
//            this.id = id;
//            this.hour = hour;
//            this.minute = minute;
//            this.memo = memo;
//            this.alarmSwitch = alarmSwitch;
//        }
//
//        @Override
//        public String toString() {
//            return String.valueOf(hour) + ":" + String.valueOf(minute);
//        }
//    }
}
