package ru.mw.android.inputforms;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;

/**
 * Created by nixan on 23.12.13.
 */
public class FormView extends AdapterView<FieldSetAdapter> {

    private class AdapterDataSetObserver extends DataSetObserver {

        @Override
        public void onChanged() {
            super.onChanged();
            removeAllViewsInLayout();
            requestLayout();
        }
    }

    private AdapterDataSetObserver mAdapterDataSetObserver = new AdapterDataSetObserver();

    private FieldSetAdapter mAdapter;

    public FormView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public FormView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FormView(Context context) {
        super(context);
    }

    @Override
    public void setAdapter(FieldSetAdapter fieldSetAdapter) {

        mAdapter = fieldSetAdapter;
        if (mAdapter != null) {
            mAdapter.registerDataSetObserver(mAdapterDataSetObserver);
        }
        removeAllViewsInLayout();
        requestLayout();
    }

    @Override
    public FieldSetAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public View getSelectedView() {
        throw new UnsupportedOperationException("Cannot select views");
    }

    @Override
    public void setSelection(int i) {
        throw new UnsupportedOperationException("Cannot select views");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (changed) {
            if (mAdapter == null || mAdapter.isEmpty()) {
                return;
            }
            layoutChildren();
        }
    }

    private void layoutChildren() {
        if (getChildCount() == 0) {
            int position = 0;
            int bottomEdge = 0;
            while (bottomEdge < getHeight() && position < mAdapter.getCount()) {
                View newBottomChild = mAdapter.getView(position, null, this);
                addAndMeasureChild(newBottomChild);
                bottomEdge += newBottomChild.getMeasuredHeight();
                position++;
            }
        }
        positionItems();
    }

    private void addAndMeasureChild(View child) {
        LayoutParams params = child.getLayoutParams();
        if (params == null) {
            params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        }
        addViewInLayout(child, -1, params, true);

        int itemWidth = getWidth();
        child.measure(MeasureSpec.EXACTLY | itemWidth, MeasureSpec.UNSPECIFIED);
    }

    private void positionItems() {
        int top = getPaddingTop();

        for (int index = 0; index < getChildCount(); index++) {
            View child = getChildAt(index);

            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            int left = (getWidth() - width) / 2;

            child.layout(left, top, left + width, top + height);
            top += height;
        }
    }
}
