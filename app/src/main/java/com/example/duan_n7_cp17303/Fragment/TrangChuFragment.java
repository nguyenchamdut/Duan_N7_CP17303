package com.example.duan_n7_cp17303.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan_n7_cp17303.Adapter.TrangChuAdapter;
import com.example.duan_n7_cp17303.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrangChuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrangChuFragment extends Fragment {

    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private TrangChuAdapter adapter;

    public TrangChuFragment() {
        // Required empty public constructor
    }


    public static TrangChuFragment newInstance() {
        TrangChuFragment fragment = new TrangChuFragment();

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
        return inflater.inflate(R.layout.fragment_trang_chu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager2 = view.findViewById(R.id.id_viewPager);
        tabLayout = view.findViewById(R.id.id_Tablayout);
        adapter = new TrangChuAdapter(getActivity());
         viewPager2.setAdapter(adapter);
        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0 :
                        tab.setText("Áo");
                        break;
                    case 1 :
                        tab.setText("Quần");
                        break;
                    case 2 :
                        tab.setText("Khác");
                        break;
                }
            }
        });
        mediator.attach();
    }
}