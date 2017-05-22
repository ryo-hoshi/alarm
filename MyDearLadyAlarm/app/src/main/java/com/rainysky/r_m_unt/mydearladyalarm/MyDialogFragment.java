package com.rainysky.r_m_unt.mydearladyalarm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by ryota on 2017/05/01.
 */
public class MyDialogFragment extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        final DialogInterface.OnClickListener listener = (dialog, which) -> {
//            dismiss();
//            Intent intent = new Intent();
//            if (getArguments() != null) {
//                intent.putExtras(getArguments());
//            }
//            getParentFragment().onActivityResult(getTargetRequestCode(), which, intent);
//        };
            return new AlertDialog.Builder(getActivity())
                    .setTitle("確認")
                    .setPositiveButton("OK", null)
                    .setNegativeButton("Cancel", null)
                    .create();

    }
}
