package com.example.duan_n7_cp17303.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan_n7_cp17303.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link KhacFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class KhacFragment extends Fragment {

    public KhacFragment() {
        // Required empty public constructor
    }


    public static KhacFragment newInstance() {
        KhacFragment fragment = new KhacFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_khac, container, false);
    }
}