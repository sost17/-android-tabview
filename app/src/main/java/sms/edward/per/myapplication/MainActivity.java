package sms.edward.per.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class MainActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void one(View view) {
        Intent intent = new Intent(this, one.class);
        startActivity(intent);
    }

    public void two(View view) {
        Intent intent = new Intent(this, two.class);
        startActivity(intent);
    }

    public void three(View view) {
        Intent intent = new Intent(this, three.class);
        startActivity(intent);
    }

    public void four(View view) {
        Intent intent = new Intent(this, four.class);
        startActivity(intent);
    }

    public void five(View view) {
        Intent intent = new Intent(this, five.class);
        startActivity(intent);
    }
}