package ru.mw.android.inputforms;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by nixan on 23.12.13.
 */
public abstract class FieldView<K> extends FrameLayout {


    public FieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FieldView(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        View childView = getViewContent(context);
        addView(childView);
        setLayoutParams(childView.getLayoutParams());
        disableSaveStates(this);
    }

    public abstract View getViewContent(Context context);

    public abstract void setFieldValue(K value);

    public void setFieldTitle(CharSequence title) {
        ((TextView) findViewById(R.id.fieldTitle)).setText(title);
    }

    private void disableSaveStates(View view) {
        if (view != null) {
            view.setSaveEnabled(false);
            if (view instanceof ViewGroup) {
                for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                    disableSaveStates(((ViewGroup) view).getChildAt(i));
                }
            }
        }
    }

}
