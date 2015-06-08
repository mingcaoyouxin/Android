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
    public static final String EXTRA_DATE_TIME="com.younxin.criminalintent.dateOrTime";//保存选择日期还是时间的key
    private boolean mIsDateChecked;
    public DateOrTimeFragment() {
        // Required empty public constructor
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v=getActivity().getLayoutInflater().inflate(R.layout.dialog_timeordate,null);
        mDate=(RadioButton)v.findViewById(R.id.date);
        mTime=(RadioButton)v.findViewById(R.id.time);

        //使用 AlertDialog.Builder 类，以流接口（fluent interface）的方式创建了一个 AlertDialog 实例
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
                        sendResult(Activity.RESULT_OK);//当点击确定时，设置返回结果
                    }
                }).create();
    }

    //设置返回结果
    private void sendResult(int resultCode){
        if(getTargetFragment()==null)
            return;
        Intent i=new Intent();
        i.putExtra(EXTRA_DATE_TIME,mIsDateChecked);
        //参数1：与传入 setTargetFragment(...) 方法相匹配的请求代码，用以告知目标fragment返回结果来自于哪里。
        //参数2：决定下一步该采取什么行动的结果代码。
        //参数3：含有extra数据信息的 Intent 。
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultCode,i);
    }

}
