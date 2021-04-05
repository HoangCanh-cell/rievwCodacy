package com.example.thien_000.test;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class GioiThieuMainActivity extends Activity {
    TextView tv;
    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioi_thieu_main);
//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
        tv = (TextView) findViewById(R.id.tvgioithieu);
        String a = "+ Các chức năng chính của phần mềm:" + "\n"
                + "- Có 3 Tab chức năng chính: Thu Chi, Thống Kê, Thiết lập" + "\n"
                + "- Thu chi: Tiền mặt, tiền tiết kiệm, thẻ tín dụng và số dư" + "\n"
                +"\t"+"+Thêm giao dịch"+ "\n"
                +"\t"+"+Xem lịch sử giao dịch"+ "\n"
                +"\t"+"+Thống kê theo tháng, năm"+ "\n"
                +"- Thống kê: Thống kê tất cả, theo tháng, theo năm"+ "\n"
                +"- Thiết lập: Thêm tiêu mục cho thu và chỉ"+ "\n"
                +"Huỳnh Thiên Phước - 12520678"+ "\n"
                +"Nguyễn Đình Minh Nhật - 12520884"+ "\n";



        tv.setText(a);
    }

}
