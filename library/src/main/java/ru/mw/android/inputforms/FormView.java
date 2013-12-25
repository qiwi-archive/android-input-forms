package ru.mw.android.inputforms;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by nixan on 23.12.13.
 */
public class FormView extends LinearLayout {

    private static final int FIELDS_DEFAULT_PADDING = 0;

    private static final int FIELDS_DEFAULT_MARGIN = 0;

    private int mFieldsLeftPadding = FIELDS_DEFAULT_PADDING;

    private int mFieldsRightPadding = FIELDS_DEFAULT_PADDING;

    private int mFieldsTopPadding = FIELDS_DEFAULT_PADDING;

    private int mFieldsBottomPadding = FIELDS_DEFAULT_PADDING;

    private int mFieldsLeftMargin = FIELDS_DEFAULT_MARGIN;

    private int mFieldsRightMargin = FIELDS_DEFAULT_MARGIN;

    private int mFieldsTopMargin = FIELDS_DEFAULT_MARGIN;

    private int mFieldsBottomMargin = FIELDS_DEFAULT_MARGIN;

    private class AdapterDataSetObserver extends DataSetObserver {

        @Override
        public void onChanged() {
            super.onChanged();
            redrawChildren();
        }
    }

    private AdapterDataSetObserver mAdapterDataSetObserver = new AdapterDataSetObserver();

    private FieldSetAdapter mAdapter;

    public FormView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FormView);

        int fieldsPadding = typedArray
                .getDimensionPixelSize(R.styleable.FormView_fieldsPadding, FIELDS_DEFAULT_PADDING);
        mFieldsLeftPadding = typedArray
                .getDimensionPixelSize(R.styleable.FormView_fieldsLeftPadding, fieldsPadding);
        mFieldsRightPadding = typedArray
                .getDimensionPixelSize(R.styleable.FormView_fieldsRightPadding, fieldsPadding);
        mFieldsTopPadding = typedArray
                .getDimensionPixelSize(R.styleable.FormView_fieldsTopPadding, fieldsPadding);
        mFieldsBottomPadding = typedArray
                .getDimensionPixelSize(R.styleable.FormView_fieldsBottomPadding, fieldsPadding);

        int fieldsMargin = typedArray
                .getDimensionPixelSize(R.styleable.FormView_fieldsMargin, FIELDS_DEFAULT_MARGIN);
        mFieldsLeftMargin = typedArray
                .getDimensionPixelSize(R.styleable.FormView_fieldsLeftMargin, fieldsMargin);
        mFieldsRightMargin = typedArray
                .getDimensionPixelSize(R.styleable.FormView_fieldsRightMargin, fieldsMargin);
        mFieldsTopMargin = typedArray
                .getDimensionPixelSize(R.styleable.FormView_fieldsTopMargin, fieldsMargin);
        mFieldsBottomMargin = typedArray
                .getDimensionPixelSize(R.styleable.FormView_fieldsBottomMargin, fieldsMargin);
        typedArray.recycle();

        setOrientation(LinearLayout.VERTICAL);
    }

    public FormView(Context context) {
        super(context);
        setOrientation(LinearLayout.VERTICAL);
    }

    public void setFieldsPadding(int left, int right, int top, int bottom) {
        mFieldsLeftPadding = left;
        mFieldsRightPadding = right;
        mFieldsTopPadding = top;
        mFieldsBottomPadding = top;
    }

    public void setFieldsMargin(int left, int right, int top, int bottom) {
        mFieldsLeftMargin = left;
        mFieldsRightMargin = right;
        mFieldsTopMargin = top;
        mFieldsBottomMargin = bottom;
    }

    public void setAdapter(FieldSetAdapter fieldSetAdapter) {

        mAdapter = fieldSetAdapter;
        if (mAdapter != null) {
            mAdapter.registerDataSetObserver(mAdapterDataSetObserver);
        }
        redrawChildren();
    }

    public FieldSetAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void addView(View child) {
        throw new UnsupportedOperationException("You can only add subviews using adapter!");
    }

    @Override
    public void addView(View child, int index) {
        throw new UnsupportedOperationException("You can only add subviews using adapter!");
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        throw new UnsupportedOperationException("You can only add subviews using adapter!");
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        throw new UnsupportedOperationException("You can only add subviews using adapter!");
    }

    @Override
    public void addView(View child, int width, int height) {
        throw new UnsupportedOperationException("You can only add subviews using adapter!");
    }

    public void redrawChildren() {
        if (mAdapter == null || mAdapter.isEmpty()) {
            removeAllViews();
            return;
        }

        int viewId = 0;
        int fieldId = 0;
        for (; fieldId < mAdapter.getCount() && viewId < getChildCount(); fieldId++) {
            View foundView = null;
            int i = viewId;
            while (i < getChildCount() && foundView == null) {
                foundView = getChildAt(i);
                if (foundView.getClass().equals(mAdapter.getItem(fieldId).getViewClass())) {
                    mAdapter.getView(fieldId, foundView, this);
                } else {
                    foundView = null;
                    i++;
                }
            }
            if (i != viewId) {
                if (foundView != null) {
                    removeView(foundView);
                } else {
                    foundView = mAdapter.getView(fieldId, null, this);
                }
                addViewInLayout(foundView, viewId, foundView.getLayoutParams(), true);
            }
            viewId++;
        }

        if (viewId == getChildCount() && fieldId < mAdapter.getCount()) {
            for (; fieldId < mAdapter.getCount(); fieldId++) {
                View view = mAdapter.getView(fieldId, null, this);
                addViewInLayout(view, -1, view.getLayoutParams(), true);
            }
        } else if (viewId < getChildCount() && fieldId == mAdapter.getCount()) {
            for (; viewId < getChildCount(); viewId++) {
                removeViewAt(viewId);
            }
        }
        requestLayout();
    }

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params) {
        child.setPadding(mFieldsLeftPadding, mFieldsTopPadding, mFieldsRightPadding,
                mFieldsBottomPadding);
        return super.addViewInLayout(child, index, params);
    }

    @Override
    protected boolean addViewInLayout(View child, int index, ViewGroup.LayoutParams params,
            boolean preventRequestLayout) {
        child.setPadding(mFieldsLeftPadding, mFieldsTopPadding, mFieldsRightPadding,
                mFieldsBottomPadding);
        return super.addViewInLayout(child, index, params, preventRequestLayout);
    }
}
