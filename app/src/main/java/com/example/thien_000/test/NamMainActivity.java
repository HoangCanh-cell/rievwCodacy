package com.example.thien_000.test;


import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class NamMainActivity extends Activity {
    String TAG="NamMainActivity";
    private ExpandListAdapter ExpAdapter;
    private ArrayList<Nhom> ExpListItems;
    private ExpandableListView ExpandList;
    Spinner sp;
    private String tvkhoanthu = "Khoản Thu";
    private String tvkhoanchi = "Khoản Chi";
    List<String> list;
    DatabaseHandle db;
    int sotienthu =0;
    int sotienchi =0;


    @SuppressLint("NewApi") @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nam_main);
        Log.i(TAG,"Vua vao onCreate NamActivity");
//        ActionBar actionBar = getActionBar();
////        actionBar.setHomeAsUpIndicator(R.drawable.cash);
//        actionBar.setDisplayHomeAsUpEnabled(true);




        db = new DatabaseHandle(this);
        db.open();

        if (db.tongtiennamnay(tvkhoanthu).get(0) != null) {
            sotienthu = Integer.parseInt(db.tongtiennamnay(tvkhoanthu).get(0)
                    .toString());

        } else {
            sotienthu = 0;

        }
        if (db.tongtiennamnay(tvkhoanchi).get(0) != null) {
            sotienchi = Integer.parseInt(db.tongtiennamnay(tvkhoanchi).get(0)
                    .toString());
        } else {
            sotienchi = 0;
        }
        final List<String> list = new ArrayList<String>();
        list.add("Tất Cả");
        list.add("Tháng Này");
        list.add("Năm Này");

        sp = (Spinner) findViewById(R.id.spinthongke);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        sp.setAdapter(adapter);
        sp.setSelection(2);
        ExpandList = (ExpandableListView) findViewById(R.id.lvExpnam);

        sp.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub
                if (parent.getItemAtPosition(position).toString()
                        .equals(list.get(0).toString())) {
                    Intent i = new Intent(getApplicationContext(),
                            ThongKeMainActivity.class);
                    startActivityForResult(i, 10);
                    finish();

                }
                if (parent.getItemAtPosition(position).toString()
                        .equals(list.get(1).toString())) {
                    Intent i = new Intent(getApplicationContext(),
                            ThangMainActivity.class);
                    startActivityForResult(i, 10);
                    finish();

                }

//                if (parent.getItemAtPosition(position).toString()
//                        .equals(list.get(2).toString())) {
//
//                    Intent i = new Intent(getApplicationContext(),
//                            NamMainActivity.class);
//                    startActivityForResult(i, 10);
//                    finish();
//                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        try {
            ExpListItems = SetStandardGroups();
            ExpAdapter = new ExpandListAdapter(getApplicationContext(),
                    ExpListItems, sotienthu, sotienchi);

            ExpandList.setAdapter(ExpAdapter);
            ExpandList.expandGroup(0);
            ExpandList.expandGroup(1);
        } catch (NullPointerException e) {
            // TODO: handle exception
            Toast.makeText(getApplicationContext(), "Chưa có dữ liệu",
                    Toast.LENGTH_LONG).show();
        }
    }

    public ArrayList<Nhom> SetStandardGroups() {

        ArrayList<Nhom> list = new ArrayList<Nhom>();

        ArrayList<Child> ch_list;
        List<String> sotien = db.getloggiaodichnamnay(tvkhoanthu);

        List<String> sotien1 = db.getloggiaodichnamnay(tvkhoanchi);

        Nhom gru = new Nhom();

        gru.setPhanloai("Khoản Thu");

        ch_list = new ArrayList<Child>();
        for (int j = 0; j < db.getloggiaodichnamnay(tvkhoanthu).size(); j++) {
            Child ch = new Child();

            ch.setPhanLoai(db.getloggiaodichnamnay(tvkhoanthu).get(j));
            ch.setKhoanThuKhoanChi(db.getsotiennamnay(sotien.get(j),
                    "Khoản Thu").get(0));
            ch_list.add(ch);

        }
        gru.setItems(ch_list);
        list.add(gru);
        gru = new Nhom();
        gru.setPhanloai("Khoản Chi");
        ch_list = new ArrayList<Child>();
        for (int j = 0; j < db.getloggiaodichnamnay(tvkhoanchi).size(); j++) {
            Child ch = new Child();

            ch.setPhanLoai(db.getloggiaodichnamnay(tvkhoanchi).get(j));
            ch.setKhoanThuKhoanChi(db.getsotiennamnay(sotien1.get(j) + "",
                    "Khoản Chi").get(0));
            ch_list.add(ch);
        }
        gru.setItems(ch_list);
        list.add(gru);

        return list;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}