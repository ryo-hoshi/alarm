package com.rainysky.r_m_unt.mydearladyalarm;

import android.support.v4.app.DialogFragment;

/**
 * Created by ryota on 2017/05/03.
 */
public class AlarmDialogFragment extends DialogFragment {

//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//    }

    @Override
    public void onPause() {
        super.onPause();

        dismiss();
    }
}
