package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.android.android_me.R;

public class MainActivity
        extends AppCompatActivity
        implements OnImageClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Define the behaviour for onImageSelected
    public void onImageSelected(int position) {
        Toast.makeText(this, "Postition clicked = " + position, Toast.LENGTH_SHORT).show();
    }
}
