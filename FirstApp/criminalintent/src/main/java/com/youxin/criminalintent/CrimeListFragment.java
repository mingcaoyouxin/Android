package com.youxin.criminalintent;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //调用 Activity.setTitle(int) 方法，将显示在操作栏（旧版本设备上为标题栏）上的标题文字替换为传入的字符串资源中设定的文字
        getActivity().setTitle(R.string.crimes_title);
        //首先利用get方法返回CrimeLab对象，再返回其中的Crimes
        mCrimes = CrimeLab.get(getActivity()).getCrimes();

        //创建ArrayAdapter
        //参数1：Context对象
        //参数2：可定位ArrayAdapter用来创建View对象的布局的资源ID
        //参数3：数据集
        //ArrayAdapter<Crime> adapter = new ArrayAdapter<Crime>(getActivity(), android.R.layout.simple_list_item_1, mCrimes);

        CrimeAdapter adapter=new CrimeAdapter(mCrimes);
        setListAdapter(adapter);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //Crime c = (Crime) (getListAdapter()).getItem(position);
        Crime c=((CrimeAdapter)getListAdapter()).getItem(position);
        Log.d(TAG, c.getTitle() + "was clicked");

        //启动activity
        Intent i=new Intent(getActivity(),CrimeActivity.class);
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID,c.getId());
        startActivity(i);
    }

    private class CrimeAdapter extends ArrayAdapter<Crime> {
        public CrimeAdapter(ArrayList<Crime> crimes) {
            //这里需调用超类的构造方法来绑定 Crime 对象的数组列表。由于不打算使用预定义布局，我们传入0作为布局ID参数。
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
