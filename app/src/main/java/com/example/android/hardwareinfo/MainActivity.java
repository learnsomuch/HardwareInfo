package com.example.android.hardwareinfo;

import android.content.pm.PackageInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String arch = System.getProperty("os.arch");
//        Log.v("Scan Class", getDeviceName());
//        Toast.makeText(this, getDeviceName(), Toast.LENGTH_SHORT).show();
        TextView tv = (TextView) findViewById(R.id.quantity_text);
        tv.setText("***** DEVICE Information *****" + "\n");
        tv.append("Model: " + Build.MODEL + "\n");
        tv.append("Board: " + Build.BOARD + "\n");
        tv.append("Brand: " + Build.BRAND + "\n");
        tv.append("Manufacturer: " + Build.MANUFACTURER + "\n");
        tv.append("Device: " + Build.DEVICE + "\n");
        tv.append("Product: " + Build.PRODUCT + "\n");
        tv.append("TAGS: " + Build.TAGS + "\n");
        tv.append("Serial: " + Build.SERIAL + "\n");

        tv.append("\n" + "***** SOC *****" + "\n");
        tv.append("Hardware: " + Build.HARDWARE + "\n");
        tv.append("Number of cores: " + getNumberOfCores() + "\n");
        tv.append("Architecture: " + arch +  "\n");

        tv.append("\n" + "***** CPU Info *****" + "\n");
        tv.append(ReadCPUinfo() + "\n");

        tv.append("\n" + "***** Memory Info *****" + "\n");
        tv.append(getMemoryInfo() + "\n");

        tv.append("\n" + "***** OS Information *****" + "\n");
        tv.append("Build release: " + Build.VERSION.RELEASE + "\n");
        tv.append("Incremental release: " + Build.VERSION.INCREMENTAL + "\n");
        tv.append("Base OS: " + Build.VERSION.BASE_OS + "\n");
        tv.append("CODE Name: " + Build.VERSION.CODENAME + "\n");
        tv.append("Security patch: " + Build.VERSION.SECURITY_PATCH + "\n");
        tv.append("Preview SDK: " + Build.VERSION.PREVIEW_SDK_INT + "\n");
        tv.append("SDK/API version: " + Build.VERSION.SDK_INT + "\n");
        tv.append("Display build: " + Build.DISPLAY + "\n");
        tv.append("Finger print: " + Build.FINGERPRINT + "\n") ;
        tv.append("Build ID: " + Build.ID + "\n");

        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy 'at' h:mm a");
        String date = sdf.format(Build.TIME);

        tv.append("Build Time: " + date + "\n");
        tv.append("Build Type: " + Build.TYPE + "\n");
        tv.append("Build User: " + Build.USER + "\n");
        tv.append("Bootloader: " + Build.BOOTLOADER + "\n");
        tv.append("Kernel version: " + System.getProperty("os.version") + "\n");

        tv.append("\n" + "***** RADIO version *****" + "\n");
        tv.append(Build.getRadioVersion() + "\n");


        //tv.append(pInfo.installLocation);
        //tv.append(getVMVersion());

        /* List of available sensors */
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> list = sm.getSensorList(Sensor.TYPE_ALL);

        tv.append("\n" + "***** SENSORS Information *****" + "\n");
        for(Sensor s : list) {
            //Log.d("SENSORS", s.getName());
            tv.append(s.getName() + "\n");
        }

    }

//    public String getVMVersion() {
//        // catch only version 2 and not higher
//        // false for Dalvik, true for current ART, false for any new runtimes
//        String isArt = System.getProperty("java.vm.version");
//
//        return isArt;
//    }


    public String getMemoryInfo() {
        ProcessBuilder cmd;
        String result="";

        try {
            String[] args = {"/system/bin/cat", "/proc/meminfo"};
            cmd = new ProcessBuilder(args);

            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[1024];
            while(in.read(re) != -1){
                System.out.println(new String(re));
                result = result + new String(re);
            }
            in.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    private String ReadCPUinfo()
    {
        ProcessBuilder cmd;
        String result="";

        try{
            String[] args = {"/system/bin/cat", "/proc/cpuinfo"};
            cmd = new ProcessBuilder(args);

            Process process = cmd.start();
            InputStream in = process.getInputStream();
            byte[] re = new byte[1024];
            while(in.read(re) != -1){
                System.out.println(new String(re));
                result = result + new String(re);
            }
            in.close();
        } catch(IOException ex){
            ex.printStackTrace();
        }
        return result;
    }

    public int getNumberOfCores() {
        if(Build.VERSION.SDK_INT >= 17) {
            return Runtime.getRuntime().availableProcessors();
        }else {
            //Code for old SDK values
            return 0;
            //return Runtime.getRuntime().availableProcessors();
        }
    }
    /*
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return model;
        }
        return manufacturer + " " + model;
    }
    */
}
