package com.example.duan_n7_cp17303.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;


import android.os.FileUriExposedException;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duan_n7_cp17303.Activity.ThemSPActivity;
import com.example.duan_n7_cp17303.Adapter.TrangChuAdapter;
import com.example.duan_n7_cp17303.DAO.Daosanpham;
import com.example.duan_n7_cp17303.DTO.Sanpham;
import com.example.duan_n7_cp17303.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrangChuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrangChuFragment extends Fragment {

    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    private TrangChuAdapter adapter;

    private ImageView imgAnhSP,imgChonanhSP;
    private TextView tvTenSP,tvGiaTien,tvSoLuong;
    private TextInputLayout tilTenSP,tilGiaTien,tilSoLuong;
    private Button btnHuy,btnThem;


    int temp = 0;
    Daosanpham daosanpham;
    List<Sanpham> list;


    private Bitmap bitmapOld, bitmapNew;
    private LinearLayout lnlChupAnh, lnlChonFile;
    private ActivityResultLauncher<Intent> launcherCamera;
    private ActivityResultLauncher<Intent> launcherFlie;
    private AppCompatActivity appCompatActivity;
    private Drawable drawable;

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


        view.findViewById(R.id.id_fabthemsp).setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ThemSPActivity.class);
            startActivity(intent);
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            LayoutInflater inflater = getLayoutInflater();
//            View view1 = inflater.inflate(R.layout.dialog_themsp,null);
//            imgAnhSP = view1.findViewById(R.id.imgAnhSP);
//            imgChonanhSP = view1.findViewById(R.id.imgChonAnhSP);
//            tvTenSP = view1.findViewById(R.id.id_tensp);
//            tvGiaTien = view1.findViewById(R.id.id_giatien);
//            tvSoLuong = view1.findViewById(R.id.id_soluong);
//            tilTenSP = view1.findViewById(R.id.til_tensp);
//            tilGiaTien = view1.findViewById(R.id.til_giatien);
//            tilSoLuong = view1.findViewById(R.id.til_soluong);
//            btnHuy = view1.findViewById(R.id.id_btnhuy);
//            btnThem = view1.findViewById(R.id.id_btnthem);
//
//
//            appCompatActivity = (AppCompatActivity) getActivity();
//            launcherCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if(result.getResultCode() == appCompatActivity.RESULT_OK && result.getData() != null){
//                        Bundle bundle = result.getData().getExtras();
//                        Bitmap bitmap = (Bitmap) bundle.get("data");
//                        imgAnhSP.setImageBitmap(bitmap);
//                    }
//                }
//            });
//            launcherFlie = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//                @Override
//                public void onActivityResult(ActivityResult result) {
//                    if (result.getResultCode() == appCompatActivity.RESULT_OK && result.getData() != null){
//                        Uri uri = result.getData().getData();
//                        try {
//                            InputStream inputStream = appCompatActivity.getContentResolver().openInputStream(uri);
//                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                            imgAnhSP.setImageBitmap(bitmap);
//                        }catch (FileNotFoundException e){
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            });
//            appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            appCompatActivity.getSupportActionBar().setTitle("Thêm Ảnh");
//            drawable = getActivity().getDrawable(R.drawable.ic_backspace);
//            appCompatActivity.getSupportActionBar().setHomeAsUpIndicator(drawable);
//
//            daosanpham = new Daosanpham();
//            bitmapOld = ((BitmapDrawable)imgAnhSP.getDrawable()).getBitmap();
//            imgChonanhSP.setOnClickListener(v1 -> {
//                clickChonAnh();
//            });
//            builder.setView(view);
//            AlertDialog alertDialog = builder.create();
//            alertDialog.show();
//            btnHuy.setOnClickListener(v2 -> {
//                alertDialog.dismiss();
//            });
//
//            btnThem.setOnClickListener(new View.OnClickListener() {
//                Sanpham sp = new Sanpham();
//                @Override
//                public void onClick(View v) {
//                    validate();
//                    if(temp == 0){
////                   sp.set
//                    }
//                }
//            });
        });
    }

//    public void showDialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        LayoutInflater inflater = getLayoutInflater();
//        View view = inflater.inflate(R.layout.dialog_themsp,null);
//        imgAnhSP = view.findViewById(R.id.imgAnhSP);
//        imgChonanhSP = view.findViewById(R.id.imgChonAnhSP);
//        tvTenSP = view.findViewById(R.id.id_tensp);
//        tvGiaTien = view.findViewById(R.id.id_giatien);
//        tvSoLuong = view.findViewById(R.id.id_soluong);
//        tilTenSP = view.findViewById(R.id.til_tensp);
//        tilGiaTien = view.findViewById(R.id.til_giatien);
//        tilSoLuong = view.findViewById(R.id.til_soluong);
//        btnHuy = view.findViewById(R.id.id_btnhuy);
//        btnThem = view.findViewById(R.id.id_btnthem);
//
//
//        appCompatActivity = (AppCompatActivity) getActivity();
//        launcherCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//            @Override
//            public void onActivityResult(ActivityResult result) {
//                if(result.getResultCode() == appCompatActivity.RESULT_OK && result.getData() != null){
//                    Bundle bundle = result.getData().getExtras();
//                    Bitmap bitmap = (Bitmap) bundle.get("data");
//                    imgAnhSP.setImageBitmap(bitmap);
//                }
//            }
//        });
//        launcherFlie = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//            @Override
//            public void onActivityResult(ActivityResult result) {
//                if (result.getResultCode() == appCompatActivity.RESULT_OK && result.getData() != null){
//                    Uri uri = result.getData().getData();
//                    try {
//                        InputStream inputStream = appCompatActivity.getContentResolver().openInputStream(uri);
//                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                        imgAnhSP.setImageBitmap(bitmap);
//                    }catch (FileNotFoundException e){
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        appCompatActivity.getSupportActionBar().setTitle("Thêm Ảnh");
//        drawable = getActivity().getDrawable(R.drawable.ic_backspace);
//        appCompatActivity.getSupportActionBar().setHomeAsUpIndicator(drawable);
//
//        daosanpham = new Daosanpham();
//        bitmapOld = ((BitmapDrawable)imgAnhSP.getDrawable()).getBitmap();
//        imgChonanhSP.setOnClickListener(v -> {
//            clickChonAnh();
//        });
//        builder.setView(view);
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//        btnHuy.setOnClickListener(v -> {
//            alertDialog.dismiss();
//        });
//
//        btnThem.setOnClickListener(new View.OnClickListener() {
//            Sanpham sp = new Sanpham();
//            @Override
//            public void onClick(View v) {
//               validate();
//               if(temp == 0){
////                   sp.set
//               }
//            }
//        });
//    }
    public void validate(){

    }
    public void clickChonAnh(){
        View viewDialog = appCompatActivity.getLayoutInflater().inflate(R.layout.bottom_sheet, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(appCompatActivity);
        bottomSheetDialog.setContentView(viewDialog);
        lnlChupAnh = viewDialog.findViewById(R.id.lnlChupAnh);
        lnlChonFile = viewDialog.findViewById(R.id.lnlChonAnh);

        lnlChupAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                launcherCamera.launch(intent);
                bottomSheetDialog.cancel();
            }
        });
        lnlChonFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                launcherFlie.launch(intent);
                bottomSheetDialog.cancel();
            }
        });
        bottomSheetDialog.show();
    }
}