package com.example.lirui.as.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lirui.R;

/**
 * Created by 275073 on 2016/8/10.
 */
//美女
public class OneFragemnt extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.one_fragment, container,
                false);
        return view;
    }
}
