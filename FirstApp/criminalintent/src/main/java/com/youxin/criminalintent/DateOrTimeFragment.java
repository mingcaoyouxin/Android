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
import android.widget.RadioButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DateOrTimeFragment extends DialogFragment {
    private RadioButton mDate;
    private RadioButton mTime;
    public static final String EXTRA_DATE_TIME="com.younxin.criminalintent.dateOrTime";//����ѡ�����ڻ���ʱ���key
    private boolean mIsDateChecked;
    public DateOrTimeFragment() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_timeordate,null);
        mDate=(RadioButton)v.findViewById(R.id.date);
        mTime=(RadioButton)v.findViewById(R.id.time);

        //ʹ�� AlertDialog.Builder �࣬�����ӿڣ�fluent interface���ķ�ʽ������һ�� AlertDialog ʵ��
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.dateOrTimeTile)
                        //.setPositiveButton(android.R.string.ok,null).create();
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(mDate.isChecked())
                            mIsDateChecked=true;
                        else
                            mIsDateChecked=false;
                        sendResult(Activity.RESULT_OK);//�����ȷ��ʱ�����÷��ؽ��
                    }
                }).create();
    }

    //���÷��ؽ��
    private void sendResult(int resultCode){
        if(getTargetFragment()==null)
            return;
        Intent i=new Intent();
        i.putExtra(EXTRA_DATE_TIME,mIsDateChecked);
        //����1���봫�� setTargetFragment(...) ������ƥ���������룬���Ը�֪Ŀ��fragment���ؽ�����������
        //����2��������һ���ò�ȡʲô�ж��Ľ�����롣
        //����3������extra������Ϣ�� Intent ��
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);
    }

}
