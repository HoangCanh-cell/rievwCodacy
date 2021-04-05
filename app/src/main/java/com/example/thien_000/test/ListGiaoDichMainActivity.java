package com.example.thien_000.test;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListGiaoDichMainActivity extends Activity {
    static final int DATE_DIALOG_ID = 0;
    private ListView mListView;
    private CustomAdapter mCustomAdapter;
    Toast mToast;
    private ArrayList<Lichsu> _Contacts = new ArrayList<>();
    DatabaseHandle db;
    Lichsu contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_giao_dich_main);
//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
        db = new DatabaseHandle(this);
        db.open();

        for (int i = 0; i < db.lichsugiaodich().size(); i++) {
            contacts = new Lichsu();
            contacts.setTime(db.lichsugiaodich().get(i).getTime());
            contacts.setPhanloai(db.lichsugiaodich().get(i).getPhanloai());
            contacts.setSotien(db.lichsugiaodich().get(i).getSotien());
            contacts.setTaikhoan(db.lichsugiaodich().get(i).getTaikhoan());
            _Contacts.add(contacts);

        }
        mListView = (ListView) findViewById(R.id.listView1);

        mCustomAdapter = new CustomAdapter(ListGiaoDichMainActivity.this,
                _Contacts);
        mListView.setAdapter(mCustomAdapter);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                // TODO Auto-generated method stub
                delcaidat(position);

                return false;
            }
        });
    }

    public void delcaidat(final int position) {
        new AlertDialog.Builder(ListGiaoDichMainActivity.this)
                .setTitle("Chú ý")
                .setMessage("Bạn có chắc xóa không")
                .setIcon(R.drawable.xoa)
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        LayoutInflater inflater = getLayoutInflater();
                        View mToastView = inflater.inflate(R.layout.xoa_custom,
                                null);
                        mToast = new Toast(ListGiaoDichMainActivity.this);
                        mToast.setView(mToastView);
                        mToast.show();
                        db.Deletels(
                                db.lichsugiaodich().get(position).getTime(), db
                                        .lichsugiaodich().get(position)
                                        .getPhanloai(), db.lichsugiaodich()
                                        .get(position).getSotien(), db
                                        .lichsugiaodich().get(position)
                                        .getTaikhoan());
                        Intent intent = new Intent(getApplicationContext(),
                                ListGiaoDichMainActivity.class);
                        finish();
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Không",
                        new DialogInterface.OnClickListener() {
                            @TargetApi(11)
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        }).show();

    }
}
