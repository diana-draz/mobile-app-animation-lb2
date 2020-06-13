package com.diana.draz.lb2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class ActivityBase extends AppCompatActivity {

    private static String TAG = "LIFECYCLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, this.getClass().getName() + " CREATED");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, this.getClass().getName() + " STARTED");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, this.getClass().getName() + " RESUMED");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, this.getClass().getName() + " PAUSED");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, this.getClass().getName() + " DESTROYED");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, this.getClass().getName() + " STOPPED");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, this.getClass().getName() + " RESTARTED");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, this.getClass().getName() + " BACK PRESSED");
    }
}
