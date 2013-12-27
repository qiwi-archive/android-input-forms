package ru.mw.android.inputforms.fieldviews;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ru.mw.android.inputforms.FieldView;
import ru.mw.android.inputforms.R;

/**
 * Created by nixan on 23.12.13.
 */
public class EditTextFieldView extends FieldView<CharSequence> {

    public interface TextChangeListener {

        public void onTextChanged(Editable editable);
    }

    private TextChangeListener mTextChangeListener;

    private boolean mValueWasChangedFromIME = true;

    public TextWatcher getTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public synchronized void afterTextChanged(Editable editable) {
                if (mTextChangeListener != null && mValueWasChangedFromIME) {
                    mTextChangeListener.onTextChanged(editable);
                }
            }
        };
    }

    public EditTextFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextFieldView(Context context) {
        super(context);
    }

    @Override
    public View getViewContent(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View result = layoutInflater.inflate(R.layout.fieldview_edit_text, null);
        ((EditText) result.findViewById(R.id.fieldValue)).addTextChangedListener(getTextWatcher());
        return result;
    }

    @Override
    public synchronized void setFieldValue(CharSequence value) {
        mValueWasChangedFromIME = false;
        ((EditText) findViewById(R.id.fieldValue)).setTextKeepState(value == null ? "" : value);
        mValueWasChangedFromIME = true;
    }

    public boolean isInEditMode() {
        return ((EditText) findViewById(R.id.fieldValue)).isInEditMode();
    }

    public void setTextHint(CharSequence hint) {
        ((EditText) findViewById(R.id.fieldValue)).setHint(hint);
    }

    public void setTextHint(int hint) {
        ((EditText) findViewById(R.id.fieldValue)).setHint(hint);
    }

    public void setFieldTitle(CharSequence title) {
        ((TextView) findViewById(R.id.fieldTitle)).setText(title);
    }

    public void setTextWatcher(TextChangeListener textChangeListener) {
        mTextChangeListener = textChangeListener;
    }
}
