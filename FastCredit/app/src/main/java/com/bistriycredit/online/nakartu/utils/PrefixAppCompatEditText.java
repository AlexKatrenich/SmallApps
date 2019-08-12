package com.bistriycredit.online.nakartu.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatEditText;

// custom EditText with prefix
public class PrefixAppCompatEditText extends AppCompatEditText {
    private static final String TAG = "PrefixAppCompatEditText";
    float mOriginalLeftPadding = -1;

    public PrefixAppCompatEditText(Context context) {
        super(context);
    }

    public PrefixAppCompatEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PrefixAppCompatEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec,
                             int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        calculatePrefix();
    }

    private void calculatePrefix() {
        if (mOriginalLeftPadding == -1) {
            String prefix = (String) getTag();
            Log.i(TAG, "calculatePrefix: " + prefix);
            float[] widths = new float[prefix.length()];
            getPaint().getTextWidths(prefix, widths);
            float textWidth = 0;
            for (float w : widths) {
                textWidth += w;
            }
            mOriginalLeftPadding = getCompoundPaddingLeft();
            setPadding((int) (textWidth + mOriginalLeftPadding),
                    getPaddingRight(), getPaddingTop(),
                    getPaddingBottom());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String prefix = (String) getTag();
        canvas.drawText(prefix, mOriginalLeftPadding,
                getLineBounds(0, null), getPaint());
    }

}
