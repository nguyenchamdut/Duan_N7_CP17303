package com.example.duan_n7_cp17303.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.duan_n7_cp17303.Adapter.Adapter_YeuThich;
import com.example.duan_n7_cp17303.DAO.Daoyeuthich;
import com.example.duan_n7_cp17303.DTO.YeuThich;
import com.example.duan_n7_cp17303.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link YeuThichFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YeuThichFragment extends Fragment {

    private RecyclerView recyclerView;
    private Adapter_YeuThich adapterYeuThich;
    private Daoyeuthich daoyeuthich;
    private List<YeuThich> list = new ArrayList<>();
    public YeuThichFragment() {
        // Required empty public constructor
    }

    public static YeuThichFragment newInstance() {
        YeuThichFragment fragment = new YeuThichFragment();

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
        return inflater.inflate(R.layout.fragment_yeu_thich, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rec_yeuthich);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Login", getActivity().MODE_PRIVATE);
        String u = sharedPreferences.getString("name", "");
        Log.e("uYT",u);
        try {
           daoyeuthich = new Daoyeuthich();
           list = daoyeuthich.get_YT_theo_UserName(u);
//            list = daoyeuthich.getAll();
           adapterYeuThich = new Adapter_YeuThich(getActivity(),list);
           recyclerView.setAdapter(adapterYeuThich);
       }catch (Exception e){
           e.printStackTrace();

       }
    }
}