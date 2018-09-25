package org.openhab.habdroid.ui;

import android.app.TimePickerDialog;
import android.content.Context;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.openhab.habdroid.R;
import org.openhab.habdroid.util.Util;

import java.util.Calendar;

public class TalentHomeAutoWizardActivity extends AppCompatActivity implements
        FragmentManager.OnBackStackChangedListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Util.setActivityTheme(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_talent_home_auto_wizard);
        getSupportFragmentManager().addOnBackStackChangedListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_talent_home_auto_wizard, menu);
        return true;
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        Util.overridePendingTransition(this, true);
    }

    @Override
    public void onBackStackChanged() {
        updateTitle();
    }

    private void updateTitle() {
        FragmentManager fm = getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();
        @StringRes int titleResId = count > 0
                ? fm.getBackStackEntryAt(count - 1).getBreadCrumbTitleRes()
                : R.string.homeauto_title;
        setTitle(titleResId);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class ItemPlaceholderFragment extends Fragment {

        public ItemPlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_talent_home_auto_item_wizard, container, false);

            return rootView;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class TimePlaceholderFragment extends Fragment {

        private TextView mTimeFrom;
        private TextView mTimeTo;
        private Context mContext;

        public TimePlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            mContext = container.getContext();

            View rootView = inflater.inflate(R.layout.fragment_talent_home_auto_time_wizard, container, false);

            mTimeFrom = rootView.findViewById(R.id.timeFrom);
            mTimeTo = rootView.findViewById(R.id.timeTo);

            mTimeFrom.setOnClickListener(view -> {
                // TODO Auto-generated method stub
                //Toast toast = Toast.makeText(mContext, "Time From click", Toast.LENGTH_LONG);
                //toast.show();

                Calendar mCurrentTime = Calendar.getInstance();
                int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(mContext, (timePicker, selectedHour, selectedMinute) -> mTimeFrom.setText( selectedHour + ":" + selectedMinute), hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            });


            mTimeTo.setOnClickListener(view -> {
                // TODO Auto-generated method stub
                //Toast toast = Toast.makeText(mContext, "Time From click", Toast.LENGTH_LONG);
                //toast.show();

                Calendar mCurrentTime = Calendar.getInstance();
                int hour = mCurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mCurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(mContext, (timePicker, selectedHour, selectedMinute) -> mTimeTo.setText( selectedHour + ":" + selectedMinute), hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            });


            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return new ItemPlaceholderFragment();
                case 1:
                    return new TimePlaceholderFragment();
                default:
                    break;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }
}
