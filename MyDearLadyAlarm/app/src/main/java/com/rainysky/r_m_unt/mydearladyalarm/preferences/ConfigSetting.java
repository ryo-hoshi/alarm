package com.rainysky.r_m_unt.mydearladyalarm.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ryota on 2017/05/09.
 */
public class ConfigSetting {

    private ConfigSetting() {
        // singleton
    }

    private static final String CONFIG_SETTING_DATA = "CONFIG_SETTING_DATA";

    private static final String CONFIG_SETTING_VOICE_KEY = "CONFIG_SETTING_VOICE_KEY";

    private static SharedPreferences prefs;

    //private boolean isAlarmVoicePlay;

    public static void setIsAlarmVoicePlay(Context context, boolean isAlarmVoicePlay) {
        getSharedPreferences(context).edit().putBoolean(CONFIG_SETTING_VOICE_KEY, isAlarmVoicePlay).apply();
    }

    public static boolean getIsAlarmVoicePlay(Context context) {
        return getSharedPreferences(context).getBoolean(CONFIG_SETTING_VOICE_KEY, true);
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        if (prefs == null) {
            prefs = context.getSharedPreferences(CONFIG_SETTING_DATA, Context.MODE_PRIVATE);
        }
        return prefs;
    }
//    private static ConfigSettingInfo configSettingInfo;
//
//
//    private class ConfigSettingInfo {
//        boolean isAlarmVoicePlay;
//    }
}
