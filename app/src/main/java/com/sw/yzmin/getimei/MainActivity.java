package com.sw.yzmin.getimei;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.IllegalFormatCodePointException;

public class MainActivity extends AppCompatActivity {
    private Button getImeiBtn;
    private TextView showTv;
    private Class mTelephonyManager;
    private Constructor mConstructor;
    private Object mObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getImeiBtn = (Button)findViewById(R.id.getImei_btn);
        showTv = (TextView)findViewById(R.id.show_tv);

        try {
            //1、获取类对象
            mTelephonyManager = Class.forName("android.telephony.TelephonyManager");
            Log.d("TEST","Class = " + mTelephonyManager.getName());
            //2、获取构造函数
            mConstructor = mTelephonyManager.getConstructor(Context.class);
            //3、创建实例对象
            mObject = mConstructor.newInstance(this);
            Log.d("TEST","Object = " + mObject);

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        getImeiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    //4、获取某个成员方法
                    Method method = mTelephonyManager.getDeclaredMethod("getImei");
                    //5、调用成员方法
                    String imei = (String)method.invoke(mObject);
                    showTv.setText("IMEI = " + imei);
                }catch (NoSuchMethodException e){
                    e.printStackTrace();
                }catch(InvocationTargetException e){
                    e.printStackTrace();
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
