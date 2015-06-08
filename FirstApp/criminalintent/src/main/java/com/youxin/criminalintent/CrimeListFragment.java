package com.youxin.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by pc on 2015/6/4.
 */
public class CrimeListFragment extends ListFragment {
    private ArrayList<Crime> mCrimes;
    private static final String TAG = "CrimeListFragment";
    private static final int REQUEST_CRIME=1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //���� Activity.setTitle(int) ����������ʾ�ڲ��������ɰ汾�豸��Ϊ���������ϵı��������滻Ϊ������ַ�����Դ���趨������
        getActivity().setTitle(R.string.crimes_title);
        //��������get��������CrimeLab�����ٷ������е�Crimes
        mCrimes = CrimeLab.get(getActivity()).getCrimes();

        //����ArrayAdapter
        //����1��Context����
        //����2���ɶ�λArrayAdapter��������View����Ĳ��ֵ���ԴID
        //����3�����ݼ�
        //ArrayAdapter<Crime> adapter = new ArrayAdapter<Crime>(getActivity(), android.R.layout.simple_list_item_1, mCrimes);

        CrimeAdapter adapter=new CrimeAdapter(mCrimes);
        setListAdapter(adapter);


    }

    //������ϸ��Ϣ����ʱ����ϸ�б���µ����ݣ�Ҫ���ظ��б�
    @Override
    public void onResume(){
        super.onResume();
        ((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
    }



    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //Crime c = (Crime) (getListAdapter()).getItem(position);
        Crime c=((CrimeAdapter)getListAdapter()).getItem(position);
        Log.d(TAG, c.getTitle() + "was clicked");

        //����activity
        //Intent i=new Intent(getActivity(),CrimeActivity.class);
        //ʹ���µ�activity
        Intent i=new Intent(getActivity(),CrimePagerActivity.class);
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getId());
        startActivity(i);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode,Intent data){
        if(resultCode==REQUEST_CRIME){

        }
    }

    //����Ӧ֪ͨ�й�activity���ؽ��ֵ
    public void returnResult(){
        getActivity().setResult(Activity.RESULT_OK,null);
    }





    private class CrimeAdapter extends ArrayAdapter<Crime> {
        public CrimeAdapter(ArrayList<Crime> crimes) {
            //��������ó���Ĺ��췽������ Crime ����������б����ڲ�����ʹ��Ԥ���岼�֣����Ǵ���0��Ϊ����ID������
            super(getActivity(), 0, crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null){
                convertView=getActivity().getLayoutInflater().inflate(R.layout.list_item_crime,null);
            }
            Crime c=getItem(position);

            TextView titleTextView=(TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());

            TextView dateTextView=(TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(c.getDate());

            CheckBox solvedCheckBox=(CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.isSolved());

            return convertView;
        }
    }


}
