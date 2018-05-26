package sms.edward.per.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        //获取传递过来的参数值
        String temp = (String) bundle.get("my");

        View view = inflater.inflate(R.layout.fragment, null);
        TextView txtNumber = (TextView) view.findViewById(R.id.txt_number);
        txtNumber.setText(temp);
        return view;
    }
}
