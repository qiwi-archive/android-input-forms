package ru.mw.android.inputforms;

import android.content.Context;
import android.database.DataSetObservable;
import android.database.DataSetObserver;

/**
 * Created by nixan on 23.12.13.
 */
public abstract class Field<T> {

    private DataSetObservable mDataSetObservable = new DataSetObservable();

    private T mValue;

    private CharSequence mTitle;

    private String mIdentifier;

    public Field(String identifier) {
        setIdentifier(identifier);
    }

    public Field() {
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        mDataSetObservable.registerObserver(dataSetObserver);
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        mDataSetObservable.unregisterObserver(dataSetObserver);
    }

    public void notifyDataChanged() {
        mDataSetObservable.notifyChanged();
    }

    public void setValue(T value) {
        mValue = value;
        notifyDataChanged();
    }

    public void setFieldTitle(CharSequence title) {
        mTitle = title;
        notifyDataChanged();
    }

    public CharSequence getFieldTitle() {
        return mTitle;
    }

    public T getValue() {
        return mValue;
    }

    public void setIdentifier(String identifier) {
        mIdentifier = identifier;
    }

    public String getIdentifier() {
        return mIdentifier;
    }

    public abstract FieldView<T> updateView(Context context, FieldView<T> view);

    public FieldView<T> newView(Context context) {
        try {
            return getViewClass().getConstructor(Context.class).newInstance(context);
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannon create instance of the view class!");
        }
    }

    public abstract Class<? extends FieldView> getViewClass();

    public FieldView<T> getView(Context context, FieldView<T> fieldView) {
        if (fieldView == null) {
            fieldView = newView(context);
        }
        updateTitle(context, fieldView);
        return updateView(context, fieldView);
    }

    public void updateTitle(Context context, FieldView<T> fieldView) {
        fieldView.setFieldTitle(getFieldTitle());
    }
}
