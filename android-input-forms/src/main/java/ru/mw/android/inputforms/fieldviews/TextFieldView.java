package ru.mw.android.inputforms.fieldviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import ru.mw.android.inputforms.FieldView;
import ru.mw.android.inputforms.R;

/**
 * Created by nixan on 23.12.13.
 */
public class TextFieldView extends FieldView<CharSequence> {

    public TextFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextFieldView(Context context) {
        super(context);
    }

    @Override
    public View getViewContent(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View result = layoutInflater.inflate(R.layout.fieldview_text, null);
        return result;
    }

    @Override
    public void setFieldValue(CharSequence value) {
        ((TextView) findViewById(R.id.fieldValue)).setText(value.toString());
    }

}
