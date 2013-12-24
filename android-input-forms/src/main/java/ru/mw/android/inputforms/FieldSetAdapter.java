package ru.mw.android.inputforms;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.util.ArrayList;

/**
 * Created by nixan on 23.12.13.
 */
public class FieldSetAdapter implements Adapter {

    private DataSetObservable mDataSetObservable = new DataSetObservable();

    private DataSetObserver mFieldsObserver = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            notifyDataSetChanged();
        }
    };

    private ArrayList<Field> mFields = new ArrayList<Field>();

    public void setFields(ArrayList<Field> fields) {
        for (Field field : mFields) {
            field.unregisterDataSetObserver(mFieldsObserver);
        }
        mFields.clear();
        if (fields != null) {
            mFields.addAll(fields);
            for (Field field : fields) {
                field.registerDataSetObserver(mFieldsObserver);
            }
        }
        notifyDataSetChanged();
    }

    public Field findFieldByIdentifier(String identifier) {
        for (Field field : mFields) {
            if (identifier.equals(field.getIdentifier())) {
                return field;
            }
        }
        return null;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {
        mDataSetObservable.registerObserver(dataSetObserver);
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
        mDataSetObservable.unregisterObserver(dataSetObserver);
    }

    public void notifyDataSetChanged() {
        mDataSetObservable.notifyChanged();
    }

    @Override
    public int getCount() {
        return mFields.size();
    }

    @Override
    public Field getItem(int i) {
        return mFields.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return getItem(i).getView(viewGroup.getContext(), (FieldView) view);
    }

    @Override
    public int getItemViewType(int i) {
        return Adapter.IGNORE_ITEM_VIEW_TYPE;
    }

    @Override
    public int getViewTypeCount() {
        return Adapter.IGNORE_ITEM_VIEW_TYPE;
    }

    @Override
    public boolean isEmpty() {
        return mFields.isEmpty();
    }
}
