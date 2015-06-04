package com.youxin.criminalintent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class CrimeFragment extends Fragment {
    public static final String EXTRA_CRIME_ID ="com.youxin.criminalintent.crime_id" ;
    private Crime mCrime;
    private EditText mTitleField;//�������ĵط�
    private Button mDateButton;//ʱ�䰴ť
    private CheckBox mSolvedCheckBox;//��ѡ��

    //������������ͬ��Activity��onCreate��������Ϊ��Ҫ���й�fragment���κ�activity����
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //��ȡ���ݣ����Ǿ�����ĳ�������activity
        UUID crimeId=(UUID)getActivity().getIntent().getSerializableExtra(EXTRA_CRIME_ID);
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
        mDateButton.setText(mCrime.getDate().toString());//��ȡʱ��
        mDateButton.setEnabled(false);//���ý���

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


}
