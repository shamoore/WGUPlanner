package com.smoo182.wguplanner.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class KollektifTextView extends TextView {
    public KollektifTextView(Context context) {
        super(context);
        setFont();
    }

    public KollektifTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }

    public KollektifTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont();
    }

    public KollektifTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void setFont(){
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Kollektif.ttf");
        setTypeface(font, Typeface.NORMAL);
    }
}
