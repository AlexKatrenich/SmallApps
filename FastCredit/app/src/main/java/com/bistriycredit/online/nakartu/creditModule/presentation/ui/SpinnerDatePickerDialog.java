package com.bistriycredit.online.nakartu.creditModule.presentation.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.button.MaterialButton;
import com.bistriycredit.online.nakartu.R;

public class SpinnerDatePickerDialog extends AppCompatDialogFragment {
    private DatePicker mDatePicker;
    private MaterialButton btnOk, btnCancel;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_spinner_date_picker_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init(view);
    }

    private void init(View v) {
        btnOk = v.findViewById(R.id.btn_ok_date_picker_dialog);
        btnOk.setOnClickListener(v1 -> {
            if(mDateSetListener != null) mDateSetListener.onDateSet(mDatePicker, mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth());
            dismiss();
        });
        btnCancel = v.findViewById(R.id.btn_cancel_date_picker_dialog);
        btnCancel.setOnClickListener(v1 -> dismiss());
        mDatePicker = v.findViewById(R.id.dp_fragment_date_picker_dialog);
    }

    public void setDateSetListener(DatePickerDialog.OnDateSetListener dateSetListener) {
        mDateSetListener = dateSetListener;
    }
}
