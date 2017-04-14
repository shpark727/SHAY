package com.yuanta.sunghwan.shay;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public int mCount = 0;
    public Thread mCountThread = null;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    setContentView(R.layout.activity_main);

                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.start_btn: {
                if (mCountThread == null) {
                    mCountThread = new Thread("Count Thread") {
                        public void run() {
                            try {


                                while (!Thread.currentThread().isInterrupted()) {
                                    mCount++;

                                    Thread.sleep(1000);
                                }
                            } catch (InterruptedException ex) {
                                return;

                            }
                        }

                    };
                    mCountThread.start();
                }

                break;

            }

            case R.id.stop_btn: {

                if (mCountThread != null) {
                    mCountThread.interrupt();
                    mCountThread = null;

                }
                break;
            }

            case R.id.show_btn: {
                Toast.makeText(this, "Current Number : " + mCount, Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }




}
