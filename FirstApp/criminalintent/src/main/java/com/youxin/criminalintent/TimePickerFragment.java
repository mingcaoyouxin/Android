package com.youxin.criminalintent;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class TimePickerFragment extends DialogFragment {


    public static final String EXTRA_TIME="com.younxin.criminalintent.time";//保存时间的key
    private Date mDate;

    //在屏幕上显示DialogFragment时，托管activity的FragmentManager会调用该方法
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        DateFormat format=new SimpleDateFormat("yyyy年MM月dd日 kk时mm分 E");
        try {
            mDate=format.parse(getArguments().getSerializable(EXTRA_TIME).toString());
            //Log.d("C",getArguments().getSerializable(EXTRA_DATE).toString()+"dh");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //创建一个Calendar来获取日月年
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(mDate);
        final int year=calendar.get(Calendar.YEAR);
        final int monthOfYear=calendar.get(Calendar.MONTH);
        final int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);


        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);



        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_time, null);

        //TimePicker组件
        TimePicker timePicker=(TimePicker)v.findViewById(R.id.dialog_time_timePicker);
        //初始化组件
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                //把日月年转为一个date类型
                mDate = new GregorianCalendar(year, monthOfYear, dayOfMonth,hourOfDay,minute).getTime();
                //更新参数
                getArguments().putSerializable(EXTRA_TIME, mDate);
            }
        });



        //使用 AlertDialog.Builder 类，以流接口（fluent interface）的方式创建了一个 AlertDialog 实例
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.time_picker_title)
                        //.setPositiveButton(android.R.string.ok,null).create();
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);//当点击确定时，设置返回结果
                    }
                }).create();
    }


    //创建新的TimePickerFragment实例时，初始化其时间
    public static TimePickerFragment newInstance(String date){
        Bundle args=new Bundle();
        args.putSerializable(EXTRA_TIME, date);

        TimePickerFragment fragment=new TimePickerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    //设置返回结果
    private void sendResult(int resultCode){
        if(getTargetFragment()==null)
            return;
        Intent i=new Intent();
        i.putExtra(EXTRA_TIME,mDate);
        //参数1：与传入 setTargetFragment(...) 方法相匹配的请求代码，用以告知目标fragment返回结果来自于哪里。
        //参数2：决定下一步该采取什么行动的结果代码。
        //参数3：含有extra数据信息的 Intent 。
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);
    }

}
