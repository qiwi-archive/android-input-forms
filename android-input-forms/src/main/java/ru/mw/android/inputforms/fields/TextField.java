package ru.mw.android.inputforms.fields;

import android.content.Context;

import ru.mw.android.inputforms.Field;
import ru.mw.android.inputforms.FieldView;
import ru.mw.android.inputforms.fieldviews.TextOnlyFieldView;

/**
 * Created by nixan on 23.12.13.
 */
public class TextField extends Field<CharSequence> {

    public TextField(String identifier) {
        super(identifier);
    }

    public TextField() {
        super();
    }

    @Override
    public FieldView updateView(Context context, FieldView view) {
        view.setFieldValue(getValue());
        return view;
    }

    @Override
    public FieldView newView(Context context) {
        return new TextOnlyFieldView(context);
    }
}
