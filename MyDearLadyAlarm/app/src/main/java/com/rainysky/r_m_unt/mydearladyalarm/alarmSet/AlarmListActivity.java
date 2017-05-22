package com.rainysky.r_m_unt.mydearladyalarm.alarmSet;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.rainysky.r_m_unt.mydearladyalarm.R;

/**
 * Created by ryota on 2017/04/12.
 */
public class AlarmListActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);

        // 追加ボタンをクリックした時の処理
        // ListFragmentの要素ではないのでここで設定
        findViewById(R.id.button_alarm_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // フラグメントの処理を呼び出す
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_alarm_list);
                if (fragment != null && fragment instanceof AlarmListFragment) {
                    ((AlarmListFragment) fragment).addAlarmInfo();
                }
            }
        });

        // 完了ボタンをクリックした時の処理
        findViewById(R.id.button_alarm_comp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 当アクティビティを終了する
                finish();
            }
        });

//        // アラームON／OFFを切り替えた時の処理
//        findViewById(R.id.switch_listAlarmTime).setOnClickListener(new View.OnClickListener() {
//        //switchAlarm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // フラグメントの処理を呼び出す
//                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_alarm_list);
//                if (fragment != null && fragment instanceof AlarmListFragment) {
//                    ((AlarmListFragment) fragment).setAlarmSetting();
//                }
//            }
//        });
    }



//        public class AlarmInfoAdapter extends ArrayAdapter<AlarmSettingInfo> {
//
//            // レイアウトxmlファイルからIDを指定してViewが使用可能
//            private LayoutInflater mLayoutInflater;
//
//            //public AlarmInfoAdapter(Context context, int resourceId, List<AlarmInfo> objects) {
//            public AlarmInfoAdapter(Context context, int resourceId, List<AlarmSettingInfo> objects) {
//                super(context, resourceId, objects);
//                // getLayoutInflater()メソッドはActivityじゃないと使えない
//                mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            }
//
//            // getView()メソッドは各行を表示しようとした時に呼ばれる
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//                // 特定行(position)のデータを得る
//                //AlarmInfo item = (AlarmInfo)getItem(position);
//                AlarmSettingInfo item = (AlarmSettingInfo)getItem(position);
//                // convertViewは使いまわされている可能性があるのでnullの時だけ新しく作る
//                if (null == convertView) convertView = mLayoutInflater.inflate(R.layout.list_item, null);
//
//                // AlarmInfoのデータをViewの各Widgetにセットする
//                TextView textViewListAlarmTime = (TextView)convertView.findViewById(R.id.textView_listAlarmTime);
//                textViewListAlarmTime.setText(item.getAlarmTime());
//
//                TextView textViewListMemo = (TextView)convertView.findViewById(R.id.textView_listMemo);
//                textViewListMemo.setText(item.getMemo());
//
//                Switch switchListAlarmTime = (Switch)convertView.findViewById(R.id.switch_listAlarmTime);
//                switchListAlarmTime.setChecked(item.getAlarmSwitch());
//
//                switchListAlarmTime.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // フラグメントの処理を呼び出す
//                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_alarm_list);
//                    if (fragment != null && fragment instanceof AlarmListFragment) {
//                        ((AlarmListFragment) fragment).setAlarmSetting();
//                    }
//                }
//            });
//
//            return convertView;
//        }
//    }
}
