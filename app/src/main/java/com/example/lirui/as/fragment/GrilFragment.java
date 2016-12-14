package com.example.lirui.as.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.lirui.R;

/**
 * Created by 275073 on 2016/8/10.
 */
//美女
public class GrilFragment extends Fragment{
    ImageView img ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gril_fragment, container,
                false);
        img = (ImageView)view.findViewById(R.id.img_left);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        return view;
    }
}
