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
    private EditText mTitleField;//�������ĵط�
    private Button mDateButton;//ʱ�䰴ť
    private CheckBox mSolvedCheckBox;//��ѡ��
    private static final int REQUEST_DATE = 0;//����Ŀ��fragment��������룬date����
    private static final int REQUEST_DATE_TIME = 1;//date time ����
    private static final int REQUEST_TIME = 2;//time����_

    //������������ͬ��Activity��onCreate��������Ϊ��Ҫ���й�fragment���κ�activity����
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //����һ����ȡ���ݣ����Ǿ�����ĳ�������activity
        //UUID crimeId=(UUID)getActivity().getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        //mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);

        //������
        UUID crimeId = (UUID) getArguments().getSerializable(CrimeFragment.EXTRA_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
    }

    public CrimeFragment() {
        // Required empty public constructor
    }

    //fragment����ͼ��ֱ��ͨ������ LayoutInflater.inflate(...) ���������벼�ֵ���ԴID���ɵġ�
    // �ڶ�����������ͼ�ĸ���ͼ��ͨ��������Ҫ����ͼ����ȷ���������
    // ������������֪�����������Ƿ����ɵ���ͼ��Ӹ�����ͼ��������Ǵ����� false ��������Ϊ���ǽ�ͨ��activity����ķ�ʽ�����ͼ
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crime, container, false);//��ȡfragment����ͼ
        mTitleField = (EditText) v.findViewById(R.id.crime_title);//��ȡ��ͼ���
        mTitleField.setText(mCrime.getTitle());

        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            //CharSequence �������û����룩
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());//���ı��ı�ʱ������ΪmCrime��title
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //��ȡbutton��ť
        mDateButton = (Button) v.findViewById(R.id.crime_date);
        updateDate();//��ȡʱ��
        //mDateButton.setEnabled(false);//���ý���
        //��button����¼�����dialog
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //��ȡfragmentManager
                FragmentManager fm = getActivity().getSupportFragmentManager();
                //��ʾdialog
                //DatePickerFragment dialog=new DatePickerFragment();

                //DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
                //dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);//����dialog��Ŀ��fragmentΪCrimeFragment���������Ϊ0
                // dialog.show(fm, DIALOG_DATE);

                DateOrTimeFragment dialog = new DateOrTimeFragment();
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE_TIME);//�������������Ϊ1�����ڽ���
                dialog.show(fm, DIALOG_DATE);

            }
        });

        //��ȡcheckBox
        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);//��checkbox��ѡ��״̬��ֵ��mCrime
            }
        });


        return v;
    }

    //���fragmentʵ����bundle����Ĵ�����Ȼ��argument����bundle�У�����ٸ��Ӹ�fragment��
    public static CrimeFragment newInstance(UUID crimeId) {
        //���ø�fragment��Bundle
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CRIME_ID, crimeId);//��bundle���һ��argument����һ��key-value

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);//����argument��fragment
        return fragment;
    }

    //��ȡDatePickerFragment�����������ݣ�ͬʱ����mCrime��mDateButton
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) return;
        //�����Date Dialog����
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
            updateDate();
        } else if (requestCode == REQUEST_DATE_TIME) {
            boolean isDateChecked = (boolean) data.getSerializableExtra(DateOrTimeFragment.EXTRA_DATE_TIME);
            if (isDateChecked) {
                //����Date Dialog
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DatePickerFragment dialog = DatePickerFragment.newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);//����dialog��Ŀ��fragmentΪCrimeFragment���������Ϊ0
                dialog.show(fm, DIALOG_DATE);
            }
            else {
                //����Date Dialog
                FragmentManager fm = getActivity().getSupportFragmentManager();
                TimePickerFragment dialog = TimePickerFragment.newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_TIME);//����dialog��Ŀ��fragmentΪCrimeFragment���������Ϊ0
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
