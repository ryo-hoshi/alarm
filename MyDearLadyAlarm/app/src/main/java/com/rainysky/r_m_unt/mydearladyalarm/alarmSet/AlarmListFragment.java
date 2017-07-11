package com.rainysky.r_m_unt.mydearladyalarm.alarmSet;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.rainysky.r_m_unt.mydearladyalarm.AlarmConstant;
import com.rainysky.r_m_unt.mydearladyalarm.alarm.AlarmController;
import com.rainysky.r_m_unt.mydearladyalarm.AlarmDialogFragment;
import com.rainysky.r_m_unt.mydearladyalarm.R;
import com.rainysky.r_m_unt.mydearladyalarm.preferences.AlarmSetting;
import com.rainysky.r_m_unt.mydearladyalarm.preferences.AlarmSettingInfo;

/**
 *
 */
public class AlarmListFragment extends ListFragment {

    private static final String TAG = "AlarmListFragment";
    AlarmInfoAdapter alarmInfoAdapter = null;

    private AlarmSetting alarmSetting;

    private List<AlarmSettingInfo> alarmSettingInfoList;

    private int alarmSelectIndex = 0;

    //private Switch switchAlarm;

    private ListView alarmListView;

    // Fragmentで表示するViewを作成するメソッド
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // レイアウトをここでViewとして作成します
        return inflater.inflate(R.layout.fragment_alarm_list, container, false);
    }

    // Viewが生成し終わった時に呼ばれるメソッド
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        alarmSetting = AlarmSetting.getInstance(this.getActivity().getApplicationContext());
        alarmListView = getListView();
       // switchAlarm = (Switch)getView().findViewById(R.id.switch_listAlarmTime);

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        // アラーム設定情報リストを取得
//        alarmSettingInfoList = alarmSetting.getAlarmSettingInfoList();
//        // アラーム設定一覧に設定
//        alarmInfoAdapter = new AlarmInfoAdapter(getActivity(), 0, alarmSettingInfoList);
//        setListAdapter(alarmInfoAdapter);

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

//        // リスト長押しの処理
//        alarmListView.setOnItemLongClickListener(
//            new AdapterView.OnItemLongClickListener() {
//                @Override
//                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                    Log.d(TAG, "onItemLongClick arg2 => " + position + " : arg3 => " + id);
//
//                    alarmSelectIndex = position;
////                    new AlertDialog.Builder(getActivity())
////                            .setTitle("確認")
////                            .setMessage("削除しますか？")
////                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
////                                @Override
////                                public void onClick(DialogInterface dialog, int which) {
////                                    // OK ボタン押下時
////                                    Log.d(TAG, "OKボタンを押下しました");
////
////                                }
////                            })
////                            .setNegativeButton("Cancel", null)
////                            .show();
//
////                    new AlertDialog.Builder(getActivity())
////                            .setTitle("確認")
////                            .setMessage("削除しますか？")
////                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
////                                @Override
////                                public void onClick(DialogInterface dialog, int which) {
////                                    // OK ボタン押下時
////                                    Log.d(TAG, "OKボタンを押下しました。"+ "which:"+ which);
////                                    removeAlarmInfo(alarmSelectIndex);
////                                }
////                            })
////                            .setNegativeButton("Cancel",  null)
////                            .show();
//                    AlarmDialogFragment alarmDialogFragment = new AlarmDialogFragment() {
//                        @Override
//                        public Dialog onCreateDialog(Bundle savedInstanceState) {
//                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                                builder.setTitle("確認")
//                                        .setMessage("削除しますか？")
//                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        // OK ボタン押下時
//                                        Log.d(TAG, "OKボタンを押下しました。" + "which:" + which);
//                                        removeAlarmInfo(alarmSelectIndex);
//                                    }
//                                })
//                                .setNegativeButton("Cancel", null);
//
//                                return builder.create();
//                            }
//                        };
//                    alarmDialogFragment.show(getFragmentManager(), "AlarmDialogFragment");
//
//                    return true;
//                }
//            }
//        );

//        // アラームON／OFFを切り替えた時の処理
//        //view.findViewById(R.id.switch_listAlarmTime).setOnClickListener(new View.OnClickListener() {
//        switchAlarm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                setAlarmSetting();
//            }
//        });
    }

    public void onStart() {
        super.onStart();

//        // 追加ボタンをクリックした時の処理
//        this.getView().findViewById(R.id.button_alarm_add).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addAlarmInfo();
//            }
//        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "AlarmListFragment onResume起動");

        // アラーム設定情報リストを取得
        alarmSettingInfoList = alarmSetting.getAlarmSettingInfoList();
        // アラーム設定一覧に設定
        alarmInfoAdapter = new AlarmInfoAdapter(getActivity(), 0, alarmSettingInfoList);
        setListAdapter(alarmInfoAdapter);

    }

    /**
     * アラーム情報リストの更新
     */
    public void updateAlarmInfoList() {
        alarmSettingInfoList = alarmSetting.getAlarmSettingInfoList();
        alarmInfoAdapter = new AlarmInfoAdapter(getActivity(), 0, alarmSettingInfoList);
        setListAdapter(alarmInfoAdapter);

        // アラーム情報の保存
        alarmSetting.saveInstance(getActivity().getApplicationContext());
    }

    /*
     * アラームの追加
     */
    public void addAlarmInfo() {

        if (AlarmConstant.ALARM_SET_MAX_SIZE <= alarmSetting.getAlarmSetSize()) {
            //TODO 10件すでに登録済みの場合はメッセージを出す
            AlarmDialogFragment alarmDialogFragment = new AlarmDialogFragment() {
                @Override
                public Dialog onCreateDialog(Bundle savedInstanceState) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("確認")
                            .setMessage("アラームの設定数は10件までです")
                            .setPositiveButton("OK", null);

                    return builder.create();
                }
            };
            alarmDialogFragment.show(getFragmentManager(), "AlarmDialogFragment");
            return;
        }

        // アラーム情報の追加
//        alarmSettingInfoList = alarmSetting.getAddedAlarmSettingInfoList();
        int alarmIndex = alarmSetting.addAlarmSettingInfo();
        Toast.makeText(this.getActivity().getApplicationContext(),"生成したインデックス：" + String.valueOf(alarmIndex), Toast.LENGTH_SHORT).show();
        //updateAlarmInfoList();
        // 追加したアラームの明細画面に遷移
        int position = alarmSetting.getAlarmSetSize() - 1;
        Intent alarmDetailIntent = new Intent(getActivity(), AlarmDetailActivity.class);
        alarmDetailIntent.putExtra(AlarmDetailFragment.ALARM_SELECT_NO, position);
        startActivity(alarmDetailIntent);
    }

    /*
     * アラームの更新
     */
    public void updateAlarmInfo(int listIndex, AlarmSettingInfo alarmSettingInfo) {

        if (alarmSelectIndex < 0 || alarmSetting.getAlarmSetSize() <= alarmSelectIndex) {
            return;
        }

        // 画面設定情報をアラーム設定情報に反映
        alarmSetting.setAlarmSettingInfo(listIndex, alarmSettingInfo);

        // アラーム実行の更新
        AlarmController alarmController = new AlarmController(getActivity());
        alarmController.setAlarm(alarmSettingInfo);

        updateAlarmInfoList();
    }

    /*
     * アラームの削除
     */
    public void removeAlarmInfo(int listIndex) {

        if (listIndex < 0 || alarmSetting.getAlarmSetSize() <= listIndex) {
            return;
        }

        // アラーム情報の削除
        //alarmSettingInfoList = alarmSetting.getRemovedAlarmSettingInfoList(listIndex);
        alarmSetting.removedAlarmSettingInfo(listIndex);
        updateAlarmInfoList();
    }

    private void onDeleteClick(int position) {

        alarmSelectIndex = position;

        AlarmDialogFragment alarmDialogFragment = new AlarmDialogFragment() {
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("確認")
                        .setMessage("削除しますか？")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // OK ボタン押下時
                                Log.d(TAG, "OKボタンを押下しました。" + "which:" + which);
                                removeAlarmInfo(alarmSelectIndex);
                            }
                        })
                        .setNegativeButton("Cancel", null);

                return builder.create();
            }
        };
        alarmDialogFragment.show(getFragmentManager(), "AlarmDialogFragment");
    }

    /*
     * アイテムのクリック
     *
     * @see
     * android.support.v4.app.ListFragment#onListItemClick(android.widget.ListView
     * , android.view.View, int, long)
     */
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.d(TAG, "onListItemClick position => " + position + " : id => " + id);
//        Intent configDetailIntent = new Intent(getActivity(), ConfigDetailActivity.class);
//        configDetailIntent.putExtra(ConfigDetailFragment.CONFIG_SELECT_NO, position);
//        startActivity(configDetailIntent);
        Intent alarmDetailIntent = new Intent(getActivity(), AlarmDetailActivity.class);
        alarmDetailIntent.putExtra(AlarmDetailFragment.ALARM_SELECT_NO, position);
        startActivity(alarmDetailIntent);
    }

//    @Override
//    public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//        Log.d(TAG, "onItemLongClick arg2 => " + arg2 + " : arg3 => " + arg3);
//        return true;
//    }


    /**
     *
     */
    public class AlarmInfoAdapter extends ArrayAdapter<AlarmSettingInfo> {

        // レイアウトxmlファイルからIDを指定してViewが使用可能
        private LayoutInflater mLayoutInflater;

        //public AlarmInfoAdapter(Context context, int resourceId, List<AlarmInfo> objects) {
        public AlarmInfoAdapter(Context context, int resourceId, List<AlarmSettingInfo> objects) {
            super(context, resourceId, objects);
            // getLayoutInflater()メソッドはActivityじゃないと使えない
            mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        // getView()メソッドは各行を表示しようとした時に呼ばれる
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View rowView = convertView;

            // 特定行(position)のデータを得る
            //AlarmInfo item = (AlarmInfo)getItem(position);
            AlarmSettingInfo item = (AlarmSettingInfo)getItem(position);
            // convertViewは使いまわされている可能性があるのでnullの時だけ新しく作る
            if (null == rowView) rowView = mLayoutInflater.inflate(R.layout.list_item, null);

            // AlarmInfoのデータをViewの各Widgetにセットする
            // アラーム情報インデックス（非表示）
            TextView textViewListIndex = (TextView)rowView.findViewById(R.id.textView_listIndex);
            Log.i(TAG, "textViewListIndexの値："+textViewListIndex);
            Log.i(TAG, "itemの値："+item);
            textViewListIndex.setText(String.valueOf(item.getAlarmNo()));

            // アラーム設定時間
            TextView textViewListAlarmTime = (TextView)rowView.findViewById(R.id.textView_listAlarmTime);
            textViewListAlarmTime.setText(item.getAlarmTime());
            // アラームメモ
            TextView textViewListMemo = (TextView)rowView.findViewById(R.id.textView_listMemo);
            textViewListMemo.setText(item.getMemo());
            // アラームON/OFF
            Switch switchListAlarmTime = (Switch)rowView.findViewById(R.id.switch_listAlarmTime);
            switchListAlarmTime.setChecked(item.getAlarmSwitch());

            // 削除ボタン
            ImageButton imageButtonDelete = (ImageButton)rowView.findViewById(R.id.imageButton_delete);

            // ---------- 初回のみイベント登録 ----------
            // アラームスイッチ
            if (switchListAlarmTime.getTag() == null) {
                switchListAlarmTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 最新のpositionを取得
                        int switchPosition = (int)v.getTag();
                        // 上記positionからデータを取得
                        AlarmSettingInfo alarmSettingInfo = alarmInfoAdapter.getItem(switchPosition);
                        Log.d(TAG, "switchListAlarmTime onClick呼ばれた" + "switch:" + alarmSettingInfo.getAlarmSwitch() + "switchPosition:" + switchPosition);
                        // アラーム設定ON/OFFを反転させる
                        alarmSettingInfo.setAlarmSwitch(!alarmSettingInfo.getAlarmSwitch());
                        // アラーム設定を更新
                        updateAlarmInfo(switchPosition, alarmSettingInfo);
                    }
                });
            }
            // 削除ボタン
            if (imageButtonDelete.getTag() == null) {
                imageButtonDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // 最新のpositionを取得
                        int position = (int)v.getTag();
                        // 上記positionからデータを取得
                        AlarmSettingInfo alarmSettingInfo = alarmInfoAdapter.getItem(position);
                        Log.d(TAG, "imageButtonDelete onClick呼ばれた" + "position:" + position);
                        // アラームを削除する
                        //onDeleteClick(position);
                        removeAlarmInfo(position);
//                        // アラーム設定を更新
//                        updateAlarmInfo(position, alarmSettingInfo);
                    }
                });
            }

            // 最新のpositionをOnクリックイベントで使用するために保存する
            switchListAlarmTime.setTag(position);
            imageButtonDelete.setTag(position);

            return rowView;
        }
    }
}