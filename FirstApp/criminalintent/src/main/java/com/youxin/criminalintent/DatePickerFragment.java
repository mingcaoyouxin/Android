package com.youxin.criminalintent;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * Created by pc on 2015/6/5.
 */
public class DatePickerFragment extends DialogFragment {
    public static final String EXTRA_DATE="com.younxin.criminalintent.date";//����ʱ���key
    private Date mDate;

    //����Ļ����ʾDialogFragmentʱ���й�activity��FragmentManager����ø÷���
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){

        DateFormat format=new SimpleDateFormat("yyyy��MM��dd�� kkʱmm�� E");
        try {
            mDate=format.parse(getArguments().getSerializable(EXTRA_DATE).toString());
            //Log.d("C",getArguments().getSerializable(EXTRA_DATE).toString()+"dh");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //����һ��Calendar����ȡ������
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(mDate);
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);

        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_date,null);

        //DatePicker���
        DatePicker datePicker=(DatePicker)v.findViewById(R.id.dialog_date_datePicker);
        //��ʼ�����
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //��������תΪһ��date����
                mDate=new GregorianCalendar(year,monthOfYear,dayOfMonth).getTime();
                //���²���
                getArguments().putSerializable(EXTRA_DATE,mDate);
            }
        });

        //ʹ�� AlertDialog.Builder �࣬�����ӿڣ�fluent interface���ķ�ʽ������һ�� AlertDialog ʵ��
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.data_picker_title)
                //.setPositiveButton(android.R.string.ok,null).create();
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(Activity.RESULT_OK);//�����ȷ��ʱ�����÷��ؽ��
                    }
                }).create();
    }


    //�����µ�DatePickerFragmentʵ��ʱ����ʼ����ʱ��
    public static DatePickerFragment newInstance(String date){
        Bundle args=new Bundle();
        args.putSerializable(EXTRA_DATE, date);

        DatePickerFragment fragment=new DatePickerFragment();
        fragment.setArguments(args);

        return fragment;
    }

    //���÷��ؽ��
    private void sendResult(int resultCode){
        if(getTargetFragment()==null)
            return;
        Intent i=new Intent();
        i.putExtra(EXTRA_DATE,mDate);
        //����1���봫�� setTargetFragment(...) ������ƥ���������룬���Ը�֪Ŀ��fragment���ؽ�����������
        //����2��������һ���ò�ȡʲô�ж��Ľ�����롣
        //����3������extra������Ϣ�� Intent ��
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);
    }
}
