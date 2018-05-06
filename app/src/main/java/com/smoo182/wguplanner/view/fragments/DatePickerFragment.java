package com.smoo182.wguplanner.view.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

import com.smoo182.wguplanner.R;
import com.smoo182.wguplanner.view.activities.BaseSecondaryActivity;

import java.util.Calendar;
import java.util.Locale;

public class DatePickerFragment extends DialogFragment {

    //datePicker types
    static final int START_DATE = 1;
    static final int STOP_DATE = 2;

    private int originatingDatePicker;
    int returningDatePicker = 0;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), (BaseSecondaryActivity) getActivity(), year, month, day);
    }
}



