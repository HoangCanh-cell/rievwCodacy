package com.example.thien_000.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ThietLapMainActivity extends Activity {
    TextView tv, tv1;
    Spinner sp, spthuchi;
    ExpandableListView expcaidat;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listdulieucha;
    EditText themthuchi;
    ImageView btnthemchuchi;
    HashMap<String, List<String>> listdulieucon;//lưu các giá trị theo cặp(key,value)
    List<String> list;
    List<String> listthuchi;
    List<String> listphanloai;
    List<String> list1;
    List<String> list2;
    public String tvkhoanthu = "Khoản Thu";
    private String tvkhoanchi = "Khoản Chi";
    DatabaseHandle db;
    private int pos;
    Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thiet_lap);
        tv = (TextView) findViewById(R.id.ListItem);
        tv1 = (TextView) findViewById(R.id.ListItem1);
        db = new DatabaseHandle(this);
        db.open();

        list = new ArrayList<String>();
        list.add("Khoản thu");
        list.add("Khoản chi");

        listphanloai = new ArrayList<String>();
        listphanloai.add("Tất cả");
        listphanloai.add("Khoản chi");
        listphanloai.add("Khoản thu");

        listthuchi = new ArrayList<String>();
        listthuchi.add("Khoản Chi");
        listthuchi.add("Khoản Thu");

        sp = (Spinner) findViewById(R.id.spincaidat);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listphanloai);
        sp.setAdapter(adapter);

        spthuchi = (Spinner) findViewById(R.id.spinthemthuchicaidat);
        ArrayAdapter<String> adthuchi = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listthuchi);
        spthuchi.setAdapter(adthuchi);

        themthuchi = (EditText) findViewById(R.id.edcaidat);
        btnthemchuchi = (ImageView) findViewById(R.id.addcaidat);

        expListView = (ExpandableListView) findViewById(R.id.expcaidat);
        ListData();
        listAdapter = new ExpandableListAdapter(this, listdulieucha, listdulieucon);
        //  list adapter
        expListView.setAdapter(listAdapter);

        btnthemchuchi.setOnClickListener(new OnClickListener() {

            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {


                if (themthuchi.getText().toString().length() > 0) {
                    String spinthuchi = spthuchi.getSelectedItem().toString();

                    if (db.kiemtra(spinthuchi, themthuchi.getText().toString()) == true) {
                        db.themkhoanthuchi(spthuchi.getSelectedItem().toString(), themthuchi.getText().toString());
                        LayoutInflater inflater = getLayoutInflater();
                        View mToastView = inflater.inflate(R.layout.them_thanhcong,
                                null);
                        mToast = new Toast(ThietLapMainActivity.this);
                        mToast.setView(mToastView);
                        mToast.show();


                        Intent intent = new Intent(ThietLapMainActivity.this, Main2Activity.class);
                        startActivity(intent);
                        themthuchi.setText("");
                        db.close();
                    } else {
                        Toast.makeText(getApplicationContext(), "Bạn đã nhập giá trị này rồi", Toast.LENGTH_SHORT).show();
                    }

                } else {

                    LayoutInflater inflater = getLayoutInflater();
                    View mToastView = inflater.inflate(R.layout.warning,
                            null);
                    mToast = new Toast(ThietLapMainActivity.this);
                    mToast.setView(mToastView);
                    mToast.show();
                    themthuchi.requestFocus();
                }


            }
        });
        sp.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                if (parent.getItemAtPosition(position).toString().equals(listphanloai.get(0).toString())) {

                    expListView.expandGroup(0);
                    expListView.expandGroup(1);

                }
                if (parent.getItemAtPosition(position).toString().equals(listphanloai.get(1).toString())) {

                    expListView.expandGroup(1);
                    expListView.collapseGroup(0);

                }

                if (parent.getItemAtPosition(position).toString().equals(listphanloai.get(2).toString())) {
                    expListView.expandGroup(0);
                    expListView.collapseGroup(1);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        expListView.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                pos = position;
                if (parent.getItemAtPosition(position).equals(list.get(0)) ||
                        parent.getItemAtPosition(position).equals(list.get(1))) {
                    Toast.makeText(ThietLapMainActivity.this, "Không Thấy", Toast.LENGTH_LONG).show();

                } else {
                    if (ExpandableListView.getPackedPositionGroup(id) == 0) {
                        xoa(position, tvkhoanthu);

                    } else if (ExpandableListView.getPackedPositionGroup(id) == 1) {
                        xoa(position, tvkhoanchi);

                    }

                }


                return false;
            }
        });
    }
    /////chú ý
    public void xoa(final int location, final String thuchi) {
        new AlertDialog.Builder(ThietLapMainActivity.this)
                .setTitle("Chú ý?")
                .setMessage("Bạn có muốn xóa không?")
                .setIcon(R.drawable.xoa)
                .setPositiveButton("Có",
                        new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                db.Delete(expListView.getItemAtPosition(location).toString(), thuchi);

                                LayoutInflater inflater = getLayoutInflater();
                                View mToastView = inflater.inflate(R.layout.xoa_custom,
                                        null);
                                mToast = new Toast(ThietLapMainActivity.this);
                                mToast.setView(mToastView);
                                mToast.show();
                                db.close();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivityForResult(intent, 8);
                                finish();
                            }
                        })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }
    private void ListData() {
        listdulieucha = new ArrayList<String>();
        listdulieucon = new HashMap<String, List<String>>();
        for (int i = 0; i < list.size(); i++) {
            listdulieucha.add(list.get(i));

        }
        list1 = new ArrayList<String>();
        for (int j = 0; j < db.getphanloai(tvkhoanthu).size(); j++) {

            list1.add(db.getphanloai(tvkhoanthu).get(j));


        }

        list2 = new ArrayList<String>();
        for (int j = 0; j < db.getphanloai(tvkhoanchi).size(); j++) {

            list2.add(db.getphanloai(tvkhoanchi).get(j));
        }

        listdulieucon.put(listdulieucha.get(0), list1);
        listdulieucon.put(listdulieucha.get(1), list2);
    }

}
