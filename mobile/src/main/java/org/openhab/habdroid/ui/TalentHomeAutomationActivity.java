package org.openhab.habdroid.ui;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import org.openhab.habdroid.R;
import org.openhab.habdroid.util.Util;

public class TalentHomeAutomationActivity extends AppCompatActivity implements
        FragmentManager.OnBackStackChangedListener {
    private final static String TAG = TalentHomeAutomationActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Util.setActivityTheme(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_talent_home_automation);
        getSupportFragmentManager().addOnBackStackChangedListener(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Toolbar toolbar = findViewById(R.id.openhab_toolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        updateTitle();
    }

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
}
