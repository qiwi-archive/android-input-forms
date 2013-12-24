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

    public T getValue() {
        return mValue;
    }

    public void setIdentifier(String identifier) {
        mIdentifier = identifier;
    }

    public String getIdentifier() {
        return mIdentifier;
    }

    public abstract FieldView updateView(Context context, FieldView view);

    public abstract FieldView newView(Context context);

    public FieldView getView(Context context, FieldView fieldView) {
        if (fieldView == null) {
            fieldView = newView(context);
        }
        return updateView(context, fieldView);
    }
}
