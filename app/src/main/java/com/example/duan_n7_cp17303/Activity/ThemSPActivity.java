package com.example.duan_n7_cp17303.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan_n7_cp17303.Adapter.SpinnerLoai;
import com.example.duan_n7_cp17303.DAO.Daohang;
import com.example.duan_n7_cp17303.DAO.Daosanpham;
import com.example.duan_n7_cp17303.DTO.Loai;
import com.example.duan_n7_cp17303.DTO.Sanpham;
import com.example.duan_n7_cp17303.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ThemSPActivity extends AppCompatActivity {
    private ImageView imgAnhSP,imgChonanhSP;
    private TextView tvTenSP,tvGiaTien,tvSoLuong,tvIDSP;
    private TextInputLayout tilTenSP,tilGiaTien,tilSoLuong,tilIDSP;
    private Button btnHuy,btnThem;


    int temp = 0;
    Daosanpham daosanpham;
    List<Sanpham> list;
    Sanpham sanpham;

    Bitmap bitmapOld, bitmapNew;
    private LinearLayout lnlChupAnh, lnlChonFile;
    private ActivityResultLauncher<Intent> launcherCamera;
    private ActivityResultLauncher<Intent> launcherFlie;
    private AppCompatActivity appCompatActivity;
    private Drawable drawable;

    String maloai;
    List<Loai> loaiList;
    Daohang daohang;
    SpinnerLoai spinnerLoai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_spactivity);
        imgAnhSP = findViewById(R.id.imgAnhSP);
        imgChonanhSP = findViewById(R.id.imgChonAnhSP);
        tvTenSP = findViewById(R.id.id_tensp);
        tvGiaTien = findViewById(R.id.id_giatien);
        tvSoLuong = findViewById(R.id.id_soluong);
        tilTenSP = findViewById(R.id.til_tensp);
        tilGiaTien = findViewById(R.id.til_giatien);
        tilSoLuong = findViewById(R.id.til_soluong);
        btnHuy = findViewById(R.id.id_btnhuy);
        btnThem = findViewById(R.id.id_btnthem);


        daosanpham = new Daosanpham();
        list = daosanpham.getAll();
//        if(list.size() == 0){
//            tvIDSP.setText("1");
//        }else{
//            sanpham = daosanpham.getAll().get(list.size()-1);
//            tvIDSP.setText(String.valueOf(sanpham.getId_sp() + 1));
//        }



        Spinner spinner = findViewById(R.id.id_spnChonLoai);
        daohang = new Daohang();
        loaiList = daohang.getAll();
        spinnerLoai = new SpinnerLoai(this, (ArrayList<Loai>) daohang.getAll());
        spinner.setAdapter(spinnerLoai);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maloai = String.valueOf(loaiList.get(position).getId_loai());
                Log.e("zzzzManh",""+maloai);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        appCompatActivity = ThemSPActivity.this;

        launcherFlie = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == appCompatActivity.RESULT_OK && result.getData() != null){
                    Uri uri = result.getData().getData();
                    try{
                        InputStream inputStream = appCompatActivity.getContentResolver().openInputStream(uri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imgAnhSP.setImageBitmap(bitmap);
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        imgChonanhSP.setOnClickListener(v -> {
            clickOpenBottomShet();
        });
        btnThem.setOnClickListener(v -> {
//            if (valueDate()){
                Sanpham sp = new Sanpham();
                sp.setId_loai(Integer.parseInt(maloai));
                sp.setAnh(String.valueOf(imgAnhSP));
                sp.setTensp(tvTenSP.getText().toString());
                sp.setGiatien(tvGiaTien.getText().toString());
                sp.setSoluong(Integer.parseInt(tvSoLuong.getText().toString()));
                try {
                    daosanpham.insertSP(sp);
                    Toast.makeText(appCompatActivity, "Them thanh cong", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ThemSPActivity.this,HomeActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(appCompatActivity, "Them khong thanh cong", Toast.LENGTH_SHORT).show();
                }


//            }
        });
        btnHuy.setOnClickListener(v -> {
            Intent intent = new Intent(ThemSPActivity.this,HomeActivity.class);
            startActivity(intent);
        });

    }
    public void clickOpenBottomShet(){
        View viewDialog = appCompatActivity.getLayoutInflater().inflate(R.layout.bottom_sheet,null);
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
    public boolean valueDate(){
        return true;
    }
}