package com.example.thien_000.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThuChiMainActivity extends Activity {
    Button btnthemGD, btnthang, btnnam, btngt,btnls;
    TextView sodu, tindung, tietkiem, tienmat;
    DatabaseHandle db;
    int sodutienmat;
    int sodutindung;
    int sodutietkiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu_chi);
        db = new DatabaseHandle(this);
        db.open();
        getFormWidget();
        processTV();
        processButton();
    }

    public void getFormWidget(){
        btnthemGD=(Button)findViewById(R.id.btnadd);
        btnthang=(Button)findViewById(R.id.btnthang);
        btnnam=(Button)findViewById(R.id.btnnam);
        btngt=(Button)findViewById(R.id.btngt);
        sodu = (TextView) findViewById(R.id.tvSoDu);
        tietkiem = (TextView) findViewById(R.id.TvTietKiem);
        tindung = (TextView) findViewById(R.id.TvTinDung);
        tienmat = (TextView) findViewById(R.id.TvTienMat);
        btnls=(Button)findViewById(R.id.btnlichsu);
        btngt=(Button) findViewById(R.id.btngt);
    }
    public void processTV(){
        if (db.taikhoan("Tín Dụng").get(0) == null) {
            sodutindung = 0;

        } else {
            sodutindung = Integer.parseInt(db.taikhoan("Tín Dụng").get(0));
        }
        if (db.taikhoan("Tiền Mặt").get(0) == null) {
            sodutienmat = 0;

        } else {
            sodutienmat = Integer.parseInt(db.taikhoan("Tiền Mặt").get(0));
        }
        if (db.taikhoan("Tiết Kiệm").get(0) == null) {
            sodutietkiem = 0;

        } else {
            sodutietkiem = Integer.parseInt(db.taikhoan("Tiết Kiệm").get(0));
        }

        tietkiem.setText(sodutietkiem + "");

        tindung.setText(sodutindung + "");

        tienmat.setText(sodutienmat + "");

        sodu.setText(sodutienmat + sodutindung + sodutietkiem + "");
    }
    public void processButton(){
        btnthemGD.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(ThuChiMainActivity.this,ThemGDActivity.class);
                        startActivity(i);
                    }
                }

        );
        btnnam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ThuChiMainActivity.this,NamMainActivity.class);
                startActivity(i);
            }
        });
        btnthang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ThuChiMainActivity.this,ThangMainActivity.class);
                startActivity(i);
            }
        });
        btnls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ThuChiMainActivity.this,ListGiaoDichMainActivity.class);
                startActivity(i);
            }
        });
        btngt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ThuChiMainActivity.this,GioiThieuMainActivity.class);
                startActivity(i);
            }
        });
    }
}
