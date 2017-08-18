package com.rainysky.r_m_unt.mydearladyalarm.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * アラーム設定クラス
 * アラーム情報の管理を行う
 *
 */
public class AlarmSetting {

    private static final String TAG = "AlarmSetting";
    private static final String ALARM_SETTING_DATA = "ALARM_SETTING_DATA";
    private static AlarmSetting instance;
    private List<AlarmSettingInfo> alarmSettingInfoList = new ArrayList<>();

    private AlarmSetting() {
        // singleton
    }

    // KEY
    private static final String ALARM_SETTING_KEY = "ALARM_SETTING";

    // 保存情報取得
    public static AlarmSetting getInstance(Context context) {

        // 初回の場合
        if (instance == null) {
            // 保存情報を取得
            //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences prefs = context.getSharedPreferences(ALARM_SETTING_DATA, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String alarmSettingString = prefs.getString(ALARM_SETTING_KEY, "");

            // 保存したオブジェクトを取得
            if ( !TextUtils.isEmpty(alarmSettingString)) {
                instance = gson.fromJson(alarmSettingString, AlarmSetting.class);
            } else {
                // 何も保存されていない　初期時点はデフォルト値を入れる
                instance = getDefaultInstance();
            }
        }

        return instance;
    }

    // デフォルト値の入ったオブジェクトを返す
    private static AlarmSetting getDefaultInstance() {
        AlarmSetting instance = new AlarmSetting();
        //instance.alarmSettingInfoList = new ArrayList<>();
        // アラーム情報のインデックスに0を指定
        instance.alarmSettingInfoList.add(new AlarmSettingInfo(0, 0));

        return instance;
    }

    public int getAlarmSetSize() {
        return alarmSettingInfoList.size();
    }

    public List<AlarmSettingInfo> getAlarmSettingInfoList() {
        return alarmSettingInfoList;
    }

    public void setAlarmSettingInfo(int index, AlarmSettingInfo alarmSettingInfo) {
        alarmSettingInfoList.set(index, alarmSettingInfo);
    }

    //    public List<AlarmSettingInfo> getAddedAlarmSettingInfoList() {
//        alarmSettingInfoList.add(new AlarmSettingInfo());
//        return alarmSettingInfoList;
//    }
    public int addAlarmSettingInfo() {
        int alarmNo = this.getAlarmNo();
        int alarmIndex = alarmSettingInfoList.size();
        Log.d(TAG, "生成したアラームNo：" + alarmNo);
        alarmSettingInfoList.add(new AlarmSettingInfo(alarmNo, alarmIndex));
        return alarmNo;
    }

//    public List<AlarmSettingInfo> getRemovedAlarmSettingInfoList(int index) {
//        alarmSettingInfoList.remove(index);
//        return alarmSettingInfoList;
//    }

    public void removedAlarmSettingInfo(int index) {
        alarmSettingInfoList.remove(index);
    }

    // 状態保存
    public void saveInstance(Context context) {
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences prefs = context.getSharedPreferences(ALARM_SETTING_DATA, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        // 現在のインスタンスの状態を保存
        prefs.edit().putString(ALARM_SETTING_KEY, gson.toJson(this)).apply();
    }

    /**
     * アラーム情報インデックスを取得
     * @return
     */
    private int getAlarmNo() {

        // アラーム情報が存在しない場合は無条件で0
        if (alarmSettingInfoList.isEmpty()) {
            return 0;
        }

        // 既存のアラームNoとかぶらない値を取得
        // 候補のアラームNoを取得
        int alarmNo = alarmSettingInfoList.size();
        int maxIndex = 0;
        boolean isIndexExist = false;

        for (AlarmSettingInfo alarmSettingInfo : alarmSettingInfoList) {
            if (alarmNo == alarmSettingInfo.getAlarmNo()) {
                isIndexExist = true;
            }
            if (maxIndex < alarmNo) {
                maxIndex = alarmNo;
            }
        }

        // 候補のアラームNoが使われていたら最大値＋１を取得
        // intの上限まではいかない想定
        if (isIndexExist) {
            return maxIndex + 1;

            // 使われていなければ候補をそのまま取得
        } else {
            return alarmNo;
        }
    }
}
