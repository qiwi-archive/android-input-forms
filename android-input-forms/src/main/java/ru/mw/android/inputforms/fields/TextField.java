package ru.mw.android.inputforms.fields;

import android.content.Context;

import ru.mw.android.inputforms.Field;
import ru.mw.android.inputforms.FieldView;
import ru.mw.android.inputforms.fieldviews.TextFieldView;

/**
 * Created by nixan on 23.12.13.
 */
public class TextField extends Field<CharSequence> {

    public TextField(String identificator) {
        super(identificator);
    }

    @Override
    public FieldView<CharSequence> updateView(Context context, FieldView<CharSequence> view) {
        ((TextFieldView) view).setFieldValue(getValue());
        return view;
    }

    @Override
    public Class<? extends FieldView> getViewClass() {
        return TextFieldView.class;
    }
}
