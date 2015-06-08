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


    public static final String EXTRA_TIME="com.younxin.criminalintent.time";//����ʱ���key
    private Date mDate;

    //����Ļ����ʾDialogFragmentʱ���й�activity��FragmentManager����ø÷���
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        DateFormat format=new SimpleDateFormat("yyyy��MM��dd�� kkʱmm�� E");
        try {
            mDate=format.parse(getArguments().getSerializable(EXTRA_TIME).toString());
            //Log.d("C",getArguments().getSerializable(EXTRA_DATE).toString()+"dh");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //����һ��Calendar����ȡ������
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(mDate);
        final int year=calendar.get(Calendar.YEAR);
        final int monthOfYear=calendar.get(Calendar.MONTH);
        final int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);


        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);



        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_time, null);

        //TimePicker���
        TimePicker timePicker=(TimePicker)v.findViewById(R.id.dialog_time_timePicker);
        //��ʼ�����
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                //��������תΪһ��date����
                mDate = new GregorianCalendar(year, monthOfYear, dayOfMonth,hourOfDay,minute).getTime();
                //���²���
                getArguments().putSerializable(EXTRA_TIME, mDate);
            }
        });



        //ʹ�� AlertDialog.Builder �࣬�����ӿڣ�fluent interface���ķ�ʽ������һ�� AlertDialog ʵ��
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.time_picker_title)
                        //.setPositiveButton(android.R.string.ok,null).create();
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);//�����ȷ��ʱ�����÷��ؽ��
                    }
                }).create();
    }


    //�����µ�TimePickerFragmentʵ��ʱ����ʼ����ʱ��
    public static TimePickerFragment newInstance(String date){
        Bundle args=new Bundle();
        args.putSerializable(EXTRA_TIME, date);

        TimePickerFragment fragment=new TimePickerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    //���÷��ؽ��
    private void sendResult(int resultCode){
        if(getTargetFragment()==null)
            return;
        Intent i=new Intent();
        i.putExtra(EXTRA_TIME,mDate);
        //����1���봫�� setTargetFragment(...) ������ƥ���������룬���Ը�֪Ŀ��fragment���ؽ�����������
        //����2��������һ���ò�ȡʲô�ж��Ľ�����롣
        //����3������extra������Ϣ�� Intent ��
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);
    }

}
