package com.youxin.criminalintent;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class CrimeFragment extends Fragment {
    public static final String EXTRA_CRIME_ID = "com.youxin.criminalintent.crime_id";
    private static final String DIALOG_DATE = "date";

    private Crime mCrime;
    private EditText mTitleField;//输入标题的地方
    private Button mDateButton;//时间按钮
    private CheckBox mSolvedCheckBox;//单选框
    private static final int REQUEST_DATE = 0;//设置目标fragment的请求代码，date请求
    private static final int REQUEST_DATE_TIME = 1;//date time 请求
    private static final int REQUEST_TIME = 2;//time请求_

    //公共方法，不同于Activity的onCreate方法，因为需要被托管fragment的任何activity调用
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //方法一：获取数据，但是局限于某个具体的activity
        //UUID crimeId=(UUID)getActivity().getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        //mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);

        //方法二
        UUID crimeId = (UUID) getArguments().getSerializable(CrimeFragment.EXTRA_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    public CrimeFragment() {
        // Required empty public constructor
    }

    //fragment的视图是直接通过调用 LayoutInflater.inflate(...) 方法并传入布局的资源ID生成的。
    // 第二个参数是视图的父视图，通常我们需要父视图来正确配置组件。
    // 第三个参数告知布局生成器是否将生成的视图添加给父视图。这里，我们传入了 false 参数，因为我们将通过activity代码的方式添加视图
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);//获取fragment的视图
        mTitleField = (EditText) v.findViewById(R.id.crime_title);//获取视图组件
        mTitleField.setText(mCrime.getTitle());

        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //CharSequence （代表用户输入）
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());//当文本改变时，设置为mCrime的title
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //获取button按钮
        mDateButton = (Button) v.findViewById(R.id.crime_date);
        updateDate();//获取时间
        //mDateButton.setEnabled(false);//设置禁用
        //给button添加事件弹出dialog
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取fragmentManager
                FragmentManager fm = getActivity().getSupportFragmentManager();
                //显示dialog
                //DatePickerFragment dialog=new DatePickerFragment();

                //DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
                //dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);//设置dialog的目标fragment为CrimeFragment，请求代码为0
                // dialog.show(fm, DIALOG_DATE);

                DateOrTimeFragment dialog = new DateOrTimeFragment();
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE_TIME);//设置其请求代码为1，便于接收
                dialog.show(fm, DIALOG_DATE);

            }
        });

        //获取checkBox
        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);//把checkbox的选中状态赋值给mCrime
            }
        });


        return v;
    }

    //完成fragment实例及bundle对象的创建，然后将argument放入bundle中，最后再附加给fragment。
    public static CrimeFragment newInstance(UUID crimeId) {
        //设置该fragment的Bundle
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID, crimeId);//给bundle添加一个argument，即一个key-value

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);//附加argument给fragment
        return fragment;
    }

    //获取DatePickerFragment传过来的数据，同时更新mCrime和mDateButton
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        //如果是Date Dialog请求
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        } else if (requestCode == REQUEST_DATE_TIME) {
            boolean isDateChecked = (boolean) data.getSerializableExtra(DateOrTimeFragment.EXTRA_DATE_TIME);
            if (isDateChecked) {
                //启动Date Dialog
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);//设置dialog的目标fragment为CrimeFragment，请求代码为0
                dialog.show(fm, DIALOG_DATE);
            }
            else {
                //启动Date Dialog
                FragmentManager fm = getActivity().getSupportFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_TIME);//设置dialog的目标fragment为CrimeFragment，请求代码为0
                dialog.show(fm, DIALOG_DATE);
            }
        }
        else if (requestCode == REQUEST_TIME) {
            Date date = (Date) data.getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            mCrime.setDate(date);
            updateDate();
        }
    }

    public void updateDate() {
        mDateButton.setText(mCrime.getDate());
    }

}
