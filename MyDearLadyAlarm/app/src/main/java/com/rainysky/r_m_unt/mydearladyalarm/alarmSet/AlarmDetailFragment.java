package com.rainysky.r_m_unt.mydearladyalarm.alarmSet;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.rainysky.r_m_unt.mydearladyalarm.AlarmDialogFragment;
import com.rainysky.r_m_unt.mydearladyalarm.R;
import com.rainysky.r_m_unt.mydearladyalarm.alarm.AlarmController;
import com.rainysky.r_m_unt.mydearladyalarm.preferences.AlarmSetting;
import com.rainysky.r_m_unt.mydearladyalarm.preferences.AlarmSettingInfo;

/**
 * Created by ryota on 2017/04/09.
 */
public class AlarmDetailFragment extends Fragment {

    public static final String ALARM_SELECT_NO = "ALARM_SELECT_NO";

    private static final String TAG = "AlarmDetailFragment";

    private int listIndex;
    private AlarmSetting alarmSetting;
    private TextView textViewAlarmNo;
    //private final String ALARM_DETAIL_SETTING = "alarm_detail_setting";

    private Spinner spinnerAlarmMsg;
    private TimePicker timePicker;
//    private Switch switchSnooze;
//    private Spinner spinnerSnoozeTime;
    private EditText editTextMemo;
//    private ArrayAdapter<CharSequence> adapterSnoozeTime;
 //   private boolean isSnoozeOn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_alarm_detail, container, false);

        return rootView;
    }

    // Viewが生成し終わった時に呼ばれるメソッド
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 各画面要素を取得
        spinnerAlarmMsg = (Spinner)getView().findViewById(R.id.spinner_message);
        timePicker = (TimePicker)getView().findViewById(R.id.timePicker);
//        switchSnooze = (Switch)getView().findViewById(R.id.switch_snooze);
//        spinnerSnoozeTime = (Spinner)getView().findViewById(R.id.spinner_snooze);
        editTextMemo = (EditText)getView().findViewById(R.id.editText_memo);
        textViewAlarmNo = (TextView)getView().findViewById(R.id.textView_alarm_no);

        // アラームメッセージ種類の設定
        // ArrayAdapterインスタンスをResourceXMLから取得してアラームメッセージの選択内容を作成
        ArrayAdapter<CharSequence> adapterAlarmMsg = ArrayAdapter.createFromResource(
                getActivity(), R.array.alarm_msg,
                android.R.layout.simple_spinner_item);
        adapterAlarmMsg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAlarmMsg.setAdapter(adapterAlarmMsg);

        // アラームを24時間表記に設定
        timePicker.setIs24HourView(true);

//        // スヌーズ時間の選択値を設定
//        adapterSnoozeTime = ArrayAdapter.createFromResource(
//                getActivity(), R.array.snooze_time_val,
//                android.R.layout.simple_spinner_item);
//        adapterSnoozeTime.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerSnoozeTime.setAdapter(adapterSnoozeTime);
//        adapterSnoozeTime = new ArrayAdapter<>(
//                getActivity(),
//                R.layout.support_simple_spinner_dropdown_item,
        //R.array.snooze_time_val);
        // new Integer[]{1,2,3,4,5});
        //AlarmConstant.snoozeTimeVal);
        //spinnerSnoozeTime.setAdapter(adapterSnoozeTime);


        //----------------------------
        // リスト画面から取得したインデックスを取得
        Bundle arguments =  getArguments();
        listIndex = arguments.getInt(AlarmDetailFragment.ALARM_SELECT_NO);
        Log.d(TAG, "onViewCreated listIndexの値を更新" + listIndex);
        // アラーム設定情報を取得
        alarmSetting = AlarmSetting.getInstance(getActivity().getApplicationContext());
        // 表示するアラーム1件分の情報を取得
        AlarmSettingInfo alarmInfo = alarmSetting.getAlarmSettingInfoList().get(listIndex);
        setScreenValue(alarmInfo);
//        // 保存情報を取得
//        SharedPreferences prefs = this.getActivity().getSharedPreferences(ARG_ITEM_ID + "_" + listIndex, Context.MODE_PRIVATE);
//        String hour = prefs.getString("hour", "0000");
//        String minute = prefs.getString("min", "0000");
//        boolean snoozeFlg = prefs.getBoolean("snoozeFlg", false);
//        int snoozeTime = prefs.getInt("snoozeTime", 0);
//        String msgKind = prefs.getString("msgKind", "");


//        // スヌーズ設定変更時
//        switchSnooze.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked == true) {
//                    spinnerSnoozeTime.setEnabled(true);
//                    isSnoozeOn = true;
//                } else {
//                    spinnerSnoozeTime.setEnabled(false);
//                    isSnoozeOn = false;
//                }
//            }
//        });

        // 保存ボタン押下時の処理
        view.findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //alarmInfo.setAlarmInfo();
                AlarmSettingInfo currentAlarmSet = getScreenValue();

                // 画面設定情報をアラーム設定情報に反映
                alarmSetting.setAlarmSettingInfo(listIndex, currentAlarmSet);
                // アラーム設定を保存
                alarmSetting.saveInstance(getActivity().getApplicationContext());

//                alarmSetting.getAlarmSettingInfoList().set(listIndex, currentAlarmSet);

//                // AlarmListFragmentの処理を呼び出す
//                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragment_alarm_list);
//                if (fragment != null && fragment instanceof AlarmListFragment) {
//                    ((AlarmListFragment) fragment).updateAlarmInfo(listIndex, currentAlarmSet);
//                }

                // アラーム実行の準備
                AlarmController alarmController = new AlarmController(getActivity());
                alarmController.setAlarm(currentAlarmSet);

                // 当画面のActivityを終了する
                getActivity().finish();
            }
        });

        // 削除ボタン押下時の処理
        view.findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listIndex < 0 || alarmSetting.getAlarmSetSize() <= listIndex) {
                    return;
                }

//                new AlertDialog.Builder(getActivity())
//                        .setTitle("確認")
//                        .setMessage("削除しますか？")
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                // OK ボタン押下時
//                                Log.d(TAG, "OKボタンを押下しました。" + "which:" + which);
//                                // アラーム設定情報削除
//                                alarmSetting.removedAlarmSettingInfo(listIndex);
//                                // アラーム設定を保存
//                                alarmSetting.saveInstance(getActivity().getApplicationContext());
//                                // 当画面のActivityを終了する
//                                getActivity().finish();
//                            }
//                        })
//                        .setNegativeButton("Cancel",  null)
//                        .show();
                AlarmDialogFragment alarmDialogFragment = new AlarmDialogFragment() {
                    @Override
                    public Dialog onCreateDialog(Bundle savedInstanceState) {
                        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(getActivity());
                        builder.setTitle("確認")
                                .setMessage("削除しますか？")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // OK ボタン押下時
                                        Log.d(TAG, "OKボタンを押下しました。" + "which:" + which);
                                        // アラーム設定情報削除
                                        alarmSetting.removedAlarmSettingInfo(listIndex);
                                        // アラーム設定を保存
                                        alarmSetting.saveInstance(getActivity().getApplicationContext());
                                        // 当画面のActivityを終了する
                                        getActivity().finish();
                                    }
                                })
                                .setNegativeButton("Cancel",  null);

                        return builder.create();
                    }
                };
                alarmDialogFragment.show(getFragmentManager(), "AlarmDialogFragment");

//                // アラーム設定情報削除
//                alarmSetting.removedAlarmSettingInfo(listIndex);
//                // アラーム設定を保存
//                alarmSetting.saveInstance(getActivity().getApplicationContext());


            }
        });


        // キャンセルボタン押下時の処理
        view.findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 当画面のActivityを終了する
                getActivity().finish();
            }
        });

//        // 一時保存情報の初期化
//        SharedPreferences workPrefs = this.getActivity().getSharedPreferences(ALARM_DETAIL_KEY, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = workPrefs.edit();
//        editor.putBoolean("isPauseSave", false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

//        mList.add("テスト１");
//        mList.add("テスト２");
//        mList.add("テスト３");
//        //ListView listViewInfoConfig = (ListView) getView().findViewById(R.id.listView_infoConfig);
//
//        // アダプターの生成
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
//                android.R.layout.simple_list_item_1, mList);
//
//        // アダプターの設定
//        setListAdapter(adapter);


    }

// 必要なら使う
//    /*
//     * アイテムの追加
//     */
//    public void add(String message) {
//
//        mList.add(message);
//
//    }

//    /*
//     * アイテムのクリック
//     *
//     * @see
//     * android.support.v4.app.ListFragment#onListItemClick(android.widget.ListView
//     * , android.view.View, int, long)
//     */
//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        Log.d(TAG, "onListItemClick position => " + position + " : id => " + id);
//    }

//    enum ConfigCfc {
//        ALARM_INFO(0),
//        SETTING(1),
//        ;
//
//        private final int id;
//
//        private ConfigCfc(final int id) {
//            this.id = id;
//        }
//
//        public int getInt() {
//            return this.id;
//        }
//
////        public static ConfigCfc getConfigCfc(int num) {
////            // enum型全てを取得します。
////            ConfigCfc[] configCfcArray = ConfigCfc.values();
////
////            // 取得出来たenum型分ループします。
////            for(ConfigCfc configCfc : configCfcArray) {
////                if (configCfc.getInt() == num) {
////                    return configCfc;
////                }
////            }
////            return null;
////        }
//
//    }

    /**
     * 画面表示内容設定
     * @param alarmInfo
     */
    private void setScreenValue(AlarmSettingInfo alarmInfo) {
        // ----- 設定情報を画面に表示 ---- /

        // インデックス
        Log.i(TAG, "セットするalarmNo:" + alarmInfo.getAlarmNo());
        textViewAlarmNo.setText(String.valueOf(alarmInfo.getAlarmNo()));

        //メモ
        editTextMemo.setText(alarmInfo.getMemo());

        // アラーム時間
//        EditText editTextTime = (EditText)getView().findViewById(R.id.editText_time);
//        editTextTime.setText(time);
        //TimePicker timePicker = (TimePicker)getView().findViewById(R.id.timePicker);
        int currentApiVersion = Build.VERSION.SDK_INT;
        if (currentApiVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
            timePicker.setHour(alarmInfo.getHour());
            timePicker.setMinute(alarmInfo.getMinute());
        } else {
            timePicker.setCurrentHour(alarmInfo.getHour());
            timePicker.setCurrentMinute(alarmInfo.getMinute());
        }

//        // スヌーズ設定
//        switchSnooze.setChecked(alarmInfo.getSnoozeFlg());
//        // スヌーズ時間
//        spinnerSnoozeTime.setSelection(adapterSnoozeTime.getPosition(alarmInfo.getSnoozeTime()));
////        int color = Color.BLACK;
//        if (alarmInfo.getSnoozeFlg()) {
//            spinnerSnoozeTime.setEnabled(true);
//            isSnoozeOn = true;
//        } else {
//            spinnerSnoozeTime.setEnabled(false);
//            isSnoozeOn = false;
////            color = Color.GRAY;
//        }
//        // SpinnerからTextViewを取り出してテキストカラーを設定
//        TextView textView = (TextView) spinnerSnoozeTime.getChildAt(0);
//        textView.setTextColor(color);

        // アラームメッセージ
        spinnerAlarmMsg.setSelection(alarmInfo.getAlarmMsgNo());
    }


    /**
     * 画面情報取得
     * @return
     */
    private AlarmSettingInfo getScreenValue() {

        int alarmNo = Integer.parseInt(textViewAlarmNo.toString());
        // 時間
        TimePicker timePicker = (TimePicker)getView().findViewById(R.id.timePicker);
        int hour;
        int minute;
        int currentApiVersion = Build.VERSION.SDK_INT;
        if (currentApiVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
            hour = timePicker.getHour();
            minute = timePicker.getMinute();
        } else {
            hour = timePicker.getCurrentHour();
            minute = timePicker.getCurrentMinute();
        }

//        // メッセージ種類
//        spinnerAlarmMsg.getSelectedItemPosition();
//
//        editTextMemo.getText();

        // アラーム設定内容を返却（アラームスイッチは固定でONを設定）
        return new AlarmSettingInfo(alarmNo,
                hour,
                minute,
                true,
          //      isSnoozeOn,
           //     (String)spinnerSnoozeTime.getSelectedItem(),
                spinnerAlarmMsg.getSelectedItemPosition(),
                editTextMemo.getText().toString());
    }
}
