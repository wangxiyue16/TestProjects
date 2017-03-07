package com.example.an.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        find();
        set();
    }

    public void init() {
        String phoneString = "saaajifen积分 (){}[],好 !@#uo558,zxv85w5581!@2,我的2222";
        // 提取数字
        // 1
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(phoneString);
        String all = matcher.replaceAll("");
        System.out.println("phone111:" + all);
        // 2
        Pattern.compile("[^0-9]").matcher(phoneString).replaceAll("");
        System.out.println("phone222:" + all);
    }

    public void find(){
//        String ss="saaa,好558,zxv85w5581!@2,我的";
        String ss="saaajifen积分 (){}[],好 !@#uo558,zxv85w5581!@2,我的2222";
        Pattern p=Pattern.compile("(\\d+)");
        Matcher m=p.matcher(ss);
        if(m.find()){
            System.out.println("phone333:"+m.group(1));
        }
    }

    public void set(){
        String ss = "saaajifen积分 (){}[],好 !@#uo558,zxv85w5581!@2,我的2222";
        Pattern p = Pattern.compile("[^0-9]");
        Matcher m = p.matcher(ss);
        if (m.find()){
            System.out.println("phone444:"+m.group(1));
        }
        String all = m.replaceAll("");
        System.out.println("phone555:" + all);
    }
}
