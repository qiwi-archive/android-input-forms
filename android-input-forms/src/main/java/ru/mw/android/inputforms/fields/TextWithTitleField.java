package ru.mw.android.inputforms.fields;

import android.content.Context;

import ru.mw.android.inputforms.Field;
import ru.mw.android.inputforms.FieldView;
import ru.mw.android.inputforms.fieldviews.TextWithTitleFieldView;

/**
 * Created by nixan on 23.12.13.
 */
public class TextWithTitleField extends Field<CharSequence> {

    private CharSequence mTitle;

    public TextWithTitleField(String identifier) {
        super(identifier);
    }

    public TextWithTitleField() {
        super();
    }

    @Override
    public FieldView updateView(Context context, FieldView view) {
        ((TextWithTitleFieldView) view).setFieldValue(getValue());
        ((TextWithTitleFieldView) view).setFieldTitle(getFieldTitle());
        return view;
    }

    @Override
    public TextWithTitleFieldView newView(Context context) {
        return new TextWithTitleFieldView(context);
    }

    public void setFieldTitle(CharSequence title) {
        mTitle = title;
        notifyDataChanged();
    }

    public CharSequence getFieldTitle() {
        return mTitle;
    }
}
