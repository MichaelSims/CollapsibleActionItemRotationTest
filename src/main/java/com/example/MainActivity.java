package com.example;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class MainActivity extends Activity {

    private static final String TAG = "CollapsibleActionItemRotationTest";

    private Handler handler = new Handler();

    private EditText searchEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.main);
        getActionBar().setTitle("Testing");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        final MenuItem search = menu.findItem(R.id.menu_search);
        searchEditText = (EditText) search.getActionView();

        search.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                enterSearchMode();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                exitSearchMode();
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void enterSearchMode() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                searchEditText.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT);
            }
        });
    }

    private void exitSearchMode() {
        searchEditText.setText("");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchEditText.getWindowToken(), 0);
    }

}

