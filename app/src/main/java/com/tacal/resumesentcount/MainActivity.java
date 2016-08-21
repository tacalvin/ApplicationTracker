package com.tacal.resumesentcount;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    public String filename = "counter.txt";
    int numSent = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        readData();
    }

    private void readData() {
        System.out.println("here");
        try {
            FileInputStream fis = this.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line = bufferedReader.readLine();
            numSent = Integer.parseInt(line);
        } catch (Exception e) {
            System.out.println();
            saveData();
        }
        display();
    }

    private void saveData() {
        File file = new File(this.getFilesDir(), filename);
        String string = "" + numSent;
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void reset(View view)
    {
        numSent = 0;
        display();
    }

    protected void onPause() {
        super.onPause();
        saveData();
    }

    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }

    public void add(View view) {
        numSent++;
        display();
    }

    public void display()
    {
        TextView num = (TextView) findViewById(R.id.counter);
        num.setText("" + numSent);
    }
}
