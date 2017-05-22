package com.rainysky.r_m_unt.mydearladyalarm.config;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import com.rainysky.r_m_unt.mydearladyalarm.R;
import com.rainysky.r_m_unt.mydearladyalarm.preferences.ConfigSetting;

/**
 * Created by ryota on 2017/04/09.
 */
public class ConfigDetailFragment extends Fragment {

    public static final String CONFIG_SELECT_NO = "CONFIG_SELECT_NO";

    private static final String TAG = "ConfigDetailFragment";

    private static final int ALARM_INFO = 0;
    private static final int USAGE = 1;
    private static final int SETTING = 2;

    private List<String> mList = new ArrayList<>();

    private int configListIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // 設定一覧画面から取得したインデックスを取得
        Bundle arguments =  getArguments();
        configListIndex = arguments.getInt(ConfigDetailFragment.CONFIG_SELECT_NO);

        View rootView;
        if (isConfigCfcAlarmInfo()) {
            rootView = inflater.inflate(R.layout.fragment_config_version, container, false);
        } else if (isConfigCfcUsage()) {
            rootView = inflater.inflate(R.layout.fragment_config_usage, container, false);
        } else {
            rootView = inflater.inflate(R.layout.fragment_config_setting, container, false);
        }

//        switch (AlarmConstant.ConfigCfc.getConfigCfc(listIndex)) {
//            case AlarmConstant.ConfigCfc.ALARM_INFO:
//                rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
//                break;
//            case AlarmConstant.ConfigCfc.SETTING:
//                rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
//                break;
//            default:
//                rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);
//        }


        return rootView;
    }

    // Viewが生成し終わった時に呼ばれるメソッド
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    if (isConfigCfcSetting()) {
        Switch switchConfigVoice = (Switch)getView().findViewById(R.id.switch_configVoice);

        switchConfigVoice.setChecked(ConfigSetting.getIsAlarmVoicePlay(getActivity()));

        // 設定変更時の処理
        switchConfigVoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ConfigSetting.setIsAlarmVoicePlay(getActivity(), isChecked);
            }
        });
    }


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


    private boolean isConfigCfcAlarmInfo() {
        return ALARM_INFO == configListIndex;
    }

    private boolean isConfigCfcUsage() {
        return USAGE == configListIndex;
    }

    private boolean isConfigCfcSetting() {
        return SETTING == configListIndex;
    }
}
