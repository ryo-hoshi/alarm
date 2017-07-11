package com.rainysky.r_m_unt.mydearladyalarm;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

//    @Override
//    public void onPause() {
//        super.onPause();
//
//        dismiss();
//    }

    public static AlarmDialogFragment newInstance() {
        AlarmDialogFragment fragment = new AlarmDialogFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Activity activity = getActivity();
        if (activity == null) {
            return super.onCreateDialog(savedInstanceState);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("test")
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null);
        return builder.create();
    }
}
