package apl.r_m_unt.mydearladyalarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ryota on 2017/04/08.
 */
public class AlarmInfoAdapter extends ArrayAdapter<AlarmInfo> {

    // レイアウトxmlファイルからIDを指定してViewが使用可能
    private LayoutInflater mLayoutInflater;

    public AlarmInfoAdapter(Context context, int resourceId, List<AlarmInfo> objects) {
            super(context, resourceId, objects);
            // getLayoutInflater()メソッドはActivityじゃないと使えない
            mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

    // getView()メソッドは各行を表示しようとした時に呼ばれる
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 特定行(position)のデータを得る
        AlarmInfo item = (AlarmInfo)getItem(position);
        // convertViewは使いまわされている可能性があるのでnullの時だけ新しく作る
        if (null == convertView) convertView = mLayoutInflater.inflate(R.layout.list_item, null);

        // AlarmInfoのデータをViewの各Widgetにセットする
        TextView textView = (TextView)convertView.findViewById(R.id.check_box);
        textView.setText(item.getTextData());

        return convertView;
    }

}
