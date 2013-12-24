package ru.mw.android.inputforms.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ru.mw.android.inputforms.Field;
import ru.mw.android.inputforms.FieldSetAdapter;
import ru.mw.android.inputforms.FormView;
import ru.mw.android.inputforms.fields.TextField;
import ru.mw.android.inputforms.fields.TextWithTitleField;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            DummyFragment dummyFragment = new DummyFragment();
            dummyFragment.setRetainInstance(true);
            getSupportFragmentManager().beginTransaction().add(R.id.container, dummyFragment)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A dummy fragment containing a simple view.
     */
    public static class DummyFragment extends Fragment {

        private FormView mFormView;

        private FieldSetAdapter mFieldSetAdapter = new FieldSetAdapter();

        private boolean mTested = false;

        public DummyFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            mFormView = (FormView) rootView.findViewById(R.id.formView);
            mFormView.setAdapter(mFieldSetAdapter);
            if (!mTested) {
                mTested = true;
                mFormView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<Field> fields = new ArrayList<Field>();
                        fillWithFields(fields);
                        mFieldSetAdapter.setFields(fields);
                    }
                }, 5000);
                mFormView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mFieldSetAdapter.findFieldByIdentifier("field-manual-2")
                                .setValue("Field 2 new observed value");
                    }
                }, 10000);
            }
            return rootView;
        }


        private void fillWithFields(ArrayList<Field> fields) {

            TextField textField;
            textField = new TextField("field-manual-1");
            textField.setValue("Field 1 value");
            fields.add(textField);
            textField = new TextField("field-manual-2");
            textField.setValue("Field 2 super value");
            fields.add(textField);
            textField = new TextField("field-manual-3");
            textField.setValue("Field 3 super mega value");
            fields.add(textField);
            TextWithTitleField textWithTitleField = new TextWithTitleField("field-manual-4");
            textWithTitleField.setFieldTitle("Field Title");
            textWithTitleField.setValue("Field 4 super mega value");
            fields.add(textWithTitleField);

            for (int i = 0; i < 100; i++) {
                TextField field = new TextField("field" + String.valueOf(i));
                field.setValue("Field " + String.valueOf(i) + " value.");
                fields.add(field);
            }
        }
    }

}
