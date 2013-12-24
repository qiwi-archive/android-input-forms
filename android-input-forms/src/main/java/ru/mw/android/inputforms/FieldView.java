package ru.mw.android.inputforms;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by nixan on 23.12.13.
 */
public abstract class FieldView extends FrameLayout {


    public FieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public FieldView(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        addView(getViewContent(context));
    }

    public abstract View getViewContent(Context context);

    public abstract void setFieldValue(Object value);

}
