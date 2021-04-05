package com.example.thien_000.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ThangMainActivity extends Activity {
    private ExpandListAdapter ExpAdapter;
    private ArrayList<Nhom> ExpListItems;
    private ExpandableListView ExpandList;
    Spinner sp;
    private String tvkhoanthu = "Khoản Thu";
    private String tvkhoanchi = "Khoản Chi";
    List<String> list;
    DatabaseHandle db;
    int sotienthu;
    int sotienchi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thang_main);
        db = new DatabaseHandle(this);
        db.open();
        if(db.tongtienthangnay(tvkhoanthu).get(0)!=null){
            sotienthu = Integer.parseInt(db.tongtienthangnay(tvkhoanthu).get(0).toString());

        }else{
            sotienthu=0;

        }
        if(db.tongtienthangnay(tvkhoanchi).get(0)!=null){
            sotienchi = Integer.parseInt(db.tongtienthangnay(tvkhoanchi).get(0).toString());
        }else{
            sotienchi=0;
        }
        final List<String> list=new ArrayList<String>();
        list.add("Tất Cả");
        list.add("Tháng Này");
        list.add("Năm Này");
        sp = (Spinner) findViewById(R.id.spinthongke);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        sp.setAdapter(adapter);
        sp.setSelection(1);
        ExpandList = (ExpandableListView) findViewById(R.id.lvExp);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if(parent.getItemAtPosition(position).toString().equals(list.get(0).toString())){
                    Intent i = new Intent(getApplicationContext(), ThongKeMainActivity.class);
                    startActivityForResult(i, 10);
                    finish();

                }

                if(parent.getItemAtPosition(position).toString().equals(list.get(2).toString())){

                    Intent i = new Intent(getApplicationContext(), NamMainActivity.class);
                    startActivityForResult(i, 10);
                    finish();

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
        try {
            ExpListItems = SetStandardGroups();
            ExpAdapter = new ExpandListAdapter(getApplicationContext(), ExpListItems,sotienthu,sotienchi);

            ExpandList.setAdapter(ExpAdapter);
            ExpandList.expandGroup(0);
            ExpandList.expandGroup(1);
        } catch (NullPointerException e) {
            // TODO: handle exception
            Toast.makeText(getApplicationContext(), "Chưa có dữ liệu", Toast.LENGTH_LONG).show();
        }
    }

    public ArrayList<Nhom> SetStandardGroups() {
        ArrayList<Nhom> list = new ArrayList<Nhom>();

        ArrayList<Child> ch_list;
        List<String> sotien = db.getloggiaodichthangnay(tvkhoanthu);

        List<String> sotien1 = db.getloggiaodichthangnay(tvkhoanchi);


        Nhom gru = new Nhom();

        gru.setPhanloai("Khoản Thu");

        ch_list = new ArrayList<Child>();
        for (int j= 0; j < db.getloggiaodichthangnay(tvkhoanthu).size(); j++) {
            Child ch = new Child();

            ch.setPhanLoai(db.getloggiaodichthangnay(tvkhoanthu).get(j));
            ch.setKhoanThuKhoanChi(db.getsotienthangnay(sotien.get(j),"Khoản Thu").get(0));
            ch_list.add(ch);

        }
        gru.setItems(ch_list);
        list.add(gru);
        gru = new Nhom();
        gru.setPhanloai("Khoản Chi");

        ch_list = new ArrayList<Child>();
        for (int j= 0; j < db.getloggiaodichthangnay(tvkhoanchi).size(); j++) {
            Child ch = new Child();

            ch.setPhanLoai(db.getloggiaodichthangnay(tvkhoanchi).get(j));
            ch.setKhoanThuKhoanChi(db.getsotienthangnay(sotien1.get(j)+"","Khoản Chi").get(0));
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
                Intent i  = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
