package ru.mw.android.inputforms.fields;

import android.content.Context;
import android.text.Editable;

import ru.mw.android.inputforms.Field;
import ru.mw.android.inputforms.FieldView;
import ru.mw.android.inputforms.fieldviews.EditTextFieldView;

/**
 * Created by nixan on 24.12.13.
 */
public class EditTextField extends Field<CharSequence>
        implements EditTextFieldView.TextChangeListener {

    public EditTextField(String identifier) {
        super(identifier);
    }

    @Override
    public FieldView<CharSequence> updateView(Context context, FieldView<CharSequence> view) {
        ((EditTextFieldView) view).setFieldValue(getValue());
        ((EditTextFieldView) view).setTextWatcher(this);
        return view;
    }


    @Override
    public Class<? extends FieldView> getViewClass() {
        return EditTextFieldView.class;
    }

    @Override
    public void onTextChanged(Editable editable) {
        if (!editable.equals(getValue())) {
            setValue(editable);
        }
    }
}
