package apl.r_m_unt.mydearladyalarm;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import apl.r_m_unt.mydearladyalarm.dummy.AlarmContent;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private AlarmContent.AlarmItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {

    }

    private String listIndex;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = AlarmContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.toString());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            //((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.details);
//            ((LinearLayout)rootView.findViewById(R.id.item_detail)).setLayoutParams(mItem.details);
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // アラームメッセージ種類の設定
        Spinner spinnerAlarmMsg = (Spinner)getView().findViewById(R.id.spinner_message);

        // ArrayAdapterインスタンスをResourceXMLから取得
        ArrayAdapter<CharSequence> adapterAlarmMsg = ArrayAdapter.createFromResource(
                getActivity(), R.array.alarm_msg,
                android.R.layout.simple_spinner_item);
        // アラームメッセージの選択内容を作成
        adapterAlarmMsg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAlarmMsg.setAdapter(adapterAlarmMsg);

        // アラームを24時間表記に設定
        TimePicker timePicker = (TimePicker)getView().findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);

//        RadioGroup radioGroup = (RadioGroup)getView().findViewById(R.id.RadioGroup);
//        radioGroup.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//
//            }
//        });
    }

    @Override
    public void onStart() {
        super.onStart();

        // リスト画面から取得したインデックスを取得
        Bundle arguments =  getArguments();
        listIndex = arguments.getString(ItemDetailFragment.ARG_ITEM_ID);

        // 保存情報を取得
        SharedPreferences prefs = this.getActivity().getSharedPreferences(ARG_ITEM_ID + "_" + listIndex, Context.MODE_PRIVATE);
        String hour = prefs.getString("hour", "0000");
        String minute = prefs.getString("min", "0000");
        boolean snoozeFlg = prefs.getBoolean("snoozeFlg", false);
        int snoozeTime = prefs.getInt("snoozeTime", 0);
        String msgKind = prefs.getString("msgKind", "");



        // ----- 保存情報を画面に設定 ---- /
        //メモ
        EditText editTextMemo = (EditText)getView().findViewById(R.id.editText_memo);
        editTextMemo.setText(listIndex);

        // 時間
//        EditText editTextTime = (EditText)getView().findViewById(R.id.editText_time);
//        editTextTime.setText(time);
        TimePicker timePicker = (TimePicker)getView().findViewById(R.id.timePicker);

//        // スヌーズ設定
//        RadioGroup radioGroup = (RadioGroup)getView().findViewById(R.id.RadioGroup);
//        if (snoozeFlg) {
//            radioGroup.check(R.id.radioButton_on);
//            Spinner spinnerSnooze = (Spinner)getView().findViewById(R.id.spinner_snooze);
//
//            // TODO ここに設定するのはインデックス
//            spinnerSnooze.setSelection(snoozeTime);
//        } else {
//            radioGroup.check(R.id.radioButton_off);
//        }

        SharedPreferences workPrefs = this.getActivity().getSharedPreferences("alarm" + ARG_ITEM_ID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = workPrefs.edit();
        editor.putBoolean("isPauseSave", false);
    }

    @Override
    public void onResume() {
        super.onResume();

        // onStart()後、onPause()から復帰

        // 一時保存情報を取得
        SharedPreferences prefs = this.getActivity().getSharedPreferences("alarm" + ARG_ITEM_ID, Context.MODE_PRIVATE);

        // ポーズ保存フラグ
        boolean isPauseSave = prefs.getBoolean("isPauseSave", false);
        // リスト番号
        String saveListIndex = prefs.getString("listIndex", "dummy");

        // 一時退避保存情報がonPauseで保存され、選択したリストの場合
        if (isPauseSave && saveListIndex.equals(listIndex)) {

            // 保存情報を取得して画面に設定する
            String time = prefs.getString("time", "0000");
            boolean snoozeFlg = prefs.getBoolean("snoozeFlg", false);
            int snoozeTime = prefs.getInt("snoozeTime", 0);
            String msgKind = prefs.getString("msgKind", "");

            // リスト画面から取得したインデックス（お試し）
            Bundle arguments =  getArguments();
            String index = arguments.getString(ItemDetailFragment.ARG_ITEM_ID);

            // ----- 保存情報を設定 ---- /
            //メモ
            EditText editTextMemo = (EditText)getView().findViewById(R.id.editText_memo);
            editTextMemo.setText(index);

            // 時間
//        EditText editTextTime = (EditText)getView().findViewById(R.id.editText_time);
//        editTextTime.setText(time);
            TimePicker timePicker = (TimePicker)getView().findViewById(R.id.timePicker);

//            // スヌーズ設定
//            RadioGroup radioGroup = (RadioGroup)getView().findViewById(R.id.RadioGroup);
//            if (snoozeFlg) {
//                radioGroup.check(R.id.radioButton_on);
//                Spinner spinnerSnooze = (Spinner)getView().findViewById(R.id.spinner_snooze);
//
//                // TODO ここに設定するのはインデックス
//                spinnerSnooze.setSelection(snoozeTime);
//            } else {
//                radioGroup.check(R.id.radioButton_off);
//            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        Resources res = this.getActivity().getResources();
        // スヌーズ設定ラジオボタン(ON)
        String rbSnoozeOn = res.getString(R.string.alarm_set_snooze_on);

//        最終保存用
//        // ----- 設定情報を取得してSharedPreferenceに保存 ---- /
//        SharedPreferences prefs = this.getActivity().getSharedPreferences("alarm" + ARG_ITEM_ID, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();

        // ----- 設定情報を取得してSharedPreferenceに保存（一時退避用） ---- /
        SharedPreferences prefs = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // 時間
//        EditText editTextTime = (EditText)getView().findViewById(R.id.editText_time);
//        String time = editTextTime.getText().toString();
//        editor.putString("time", time);
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
        editor.putInt("hour", hour);
        editor.putInt("minute", minute);

        // TODO 値設定

//        // スヌーズ設定
//        RadioGroup radioGroup = (RadioGroup)getView().findViewById(R.id.RadioGroup);
//        // チェックされているラジオボタンの ID を取得
//        int id = radioGroup.getCheckedRadioButtonId();
//        // チェックされているラジオボタンオブジェクトを取得
//        RadioButton radioButton = (RadioButton)getView().findViewById(id);
//        // ラジオボタンのテキストを取得
//        String radioText = radioButton.getText().toString();
//        boolean snoozeFlg = false;
//        String snoozeTime = "";

//        // ONの場合はtrueを設定
//        if (rbSnoozeOn.equals(radioText)) {
//            snoozeFlg = true;
//
//            // スヌーズ時間のSpinnerオブジェクトを取得
//            Spinner spinnerSnooze = (Spinner)getView().findViewById(R.id.spinner_snooze);
////            // 選択されているアイテムのIndexを取得
////            int idx = spinnerSnooze.getSelectedItemPosition();
//
//            // 選択されているアイテムを取得
//            snoozeTime = (String)spinnerSnooze.getSelectedItem();
//        }
//        editor.putBoolean("snoozeFlg", snoozeFlg);
//        editor.putString("snoozeTime", snoozeTime);

        // メッセージ種類
        Spinner spinnerMsg = (Spinner)getView().findViewById(R.id.spinner_message);
        String msgId =  (String)spinnerMsg.getSelectedItem();
        editor.putString("msgKind", msgId);

        // ポーズ保存フラグ
        editor.putBoolean("isPauseSave", true);
        // リスト番号
        editor.putString("listIndex", listIndex);

        // 保存
        editor.apply();
    }
}
