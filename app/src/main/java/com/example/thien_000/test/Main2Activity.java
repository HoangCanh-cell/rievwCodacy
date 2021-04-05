package com.example.thien_000.test;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class Main2Activity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final TabHost tabHost = getTabHost();

        TabHost.TabSpec thuchi = tabHost.newTabSpec("Thu Chi");
        thuchi.setIndicator("Thu chi",getResources().getDrawable(android.R.drawable.star_on));
        Intent i = new Intent(Main2Activity.this, ThuChiMainActivity.class);
        thuchi.setContent(i);

        TabHost.TabSpec thongke = tabHost.newTabSpec("Thống Kê");

        thongke.setIndicator("Thống kê",getResources().getDrawable(android.R.drawable.star_on));
        Intent o = new Intent(this, ThongKeMainActivity.class);
        thongke.setContent(o);

        TabHost.TabSpec caidat = tabHost.newTabSpec("Cài Đặt");
        caidat.setIndicator("Thiết lập",getResources().getDrawable(android.R.drawable.star_on));
        Intent p = new Intent(this, ThietLapMainActivity.class);
        caidat.setContent(p);

        tabHost.addTab(thuchi);
        tabHost.addTab(thongke);
        tabHost.addTab(caidat);
        tabHost.setCurrentTab(0);
    }
}
