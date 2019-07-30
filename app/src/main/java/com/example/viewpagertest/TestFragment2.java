package com.example.viewpagertest;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TestFragment2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        TextView textView = view.findViewById(R.id.tv_title);
        textView.setText("TWO");

        ImageView imageView = view.findViewById(R.id.img_bg);
        imageView.setBackgroundResource(R.mipmap.bg_2);

        LinearLayout lineBg = view.findViewById(R.id.line_bg);
        lineBg.setBackgroundColor(Color.parseColor("#00660F"));

        return view;
    }

}
