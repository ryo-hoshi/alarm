package com.rainysky.r_m_unt.mydearladyalarm.alarmSet;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.rainysky.r_m_unt.mydearladyalarm.R;

/**
 * Created by ryota on 2017/04/12.
 */
public class AlarmDetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_detail);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            // 明細画面に渡すリスト選択情報を設定
            Bundle arguments = new Bundle();
            arguments.putInt(AlarmDetailFragment.ALARM_SELECT_NO,
                    getIntent().getIntExtra(AlarmDetailFragment.ALARM_SELECT_NO, 0));

            // アラーム明細画面フラグメントを生成
            AlarmDetailFragment alarmDetailFragment = new AlarmDetailFragment();
            // リスト選択情報を設定
            alarmDetailFragment.setArguments(arguments);
            // コンフィグ明細画面フラグメントを追加
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.alarm_detail_container, alarmDetailFragment)
                    .commit();
        }
    }
}
