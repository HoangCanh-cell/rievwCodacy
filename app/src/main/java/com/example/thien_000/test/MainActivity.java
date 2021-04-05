package com.example.thien_000.test;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Database.BaseActivity;
import Database.DatabaseHandler;
import User.User;


public class MainActivity extends BaseActivity {

    DatabaseHandler db = new DatabaseHandler(MainActivity.this);
    EditText editdn_name, editdn_pass, editdk_name, editdk_pass, editdk_repass;
    Button btndn, btndk,btngt;
    DatabaseHandler helper = new DatabaseHandler(this);
    ArrayList<User> arrUser;
    public static final String name = "pre";
    final Context context = this;
    ArrayList<User> arrayList = new ArrayList<User>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFormWidgets();
        addEventFormWidgets();

        TabHost tabHost = getTabHost();
        this.setNewTab(this, tabHost, "tab1", R.string.tab1, android.R.drawable.star_on, R.id.tab1);
        this.setNewTab(this, tabHost, "tab2", R.string.tab2, android.R.drawable.star_on, R.id.tab2);

    }

    //getFormWidgets
    public void getFormWidgets() {
        editdn_name = (EditText) findViewById(R.id.edtusername);
        editdn_pass = (EditText) findViewById(R.id.edtpassword);
        editdk_name = (EditText) findViewById(R.id.eddk1);
        editdk_pass = (EditText) findViewById(R.id.eddk2);
        editdk_repass = (EditText) findViewById(R.id.eddk3);
        btndn = (Button) findViewById(R.id.btndn);
        btndk = (Button) findViewById(R.id.btndk);

        arrUser = db.getAllUser();
    }

    //addEventFormWidgets
    public void addEventFormWidgets() {
        btndk.setOnClickListener(new MyButtonEvent());
        btndn.setOnClickListener(new MyButtonEvent());
    }

    //Xu ly EventButton
    private class MyButtonEvent implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btndk:
                    processRegister();
                    break;
                case R.id.btndn:
                    processLogin();
                    break;
            }
        }
    }

    //Tab host
    private void setNewTab(Context context, TabHost tabHost, String tag, int title, int icon, int contentID) {
        TabHost.TabSpec tabSpec = tabHost.newTabSpec(tag);
        tabSpec.setIndicator(getTabIndicator(tabHost.getContext(), title, icon)); // new function to inject our own tab layout
        tabSpec.setContent(contentID);
        tabHost.addTab(tabSpec);
    }

    private View getTabIndicator(Context context, int title, int icon) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_layout, null);
        ImageView iv = (ImageView) view.findViewById(R.id.imageView);
        iv.setImageResource(icon);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(title);
        return view;
    }

    public void processRegister() {
        User user = new User(editdk_name.getText().toString(), editdk_pass.getText().toString(),
                editdk_repass.getText().toString());
        if (editdk_repass.getText().toString().equals("")
                || editdk_pass.getText().toString().equals("")
                || editdk_name.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Dữ liệu các ô không được để trống !", Toast.LENGTH_SHORT).show();
//        } else if (editdk_pass.getText().toString() != editdk_repass.getText().toString()) {
//            Toast.makeText(getApplicationContext(), "Password không trùng nhau !", Toast.LENGTH_SHORT).show();

        } else if ((editdk_pass.getText().toString()).equals(editdk_repass.getText().toString())) {
            arrUser.add(user);
            db.addData_DangKy(user);
            Toast.makeText(getApplicationContext(), "Đăng ký thành công !", Toast.LENGTH_SHORT).show();
            editdk_name.setText("");
            editdk_pass.setText("");
            editdk_repass.setText("");
        }else {
            Toast.makeText(getApplicationContext(), "Đăng ký không thành công !", Toast.LENGTH_SHORT).show();
            editdk_name.setText("");
            editdk_pass.setText("");
            editdk_repass.setText("");
        }

    }
    public void processLogin() {
        DatabaseHandler databaseHandler = new DatabaseHandler(context);
        arrayList = databaseHandler.getAllUser();
        if (editdn_name.getText().toString().equals("")
                || editdn_pass.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(),
                    "Tên đăng nhập và mật khẩu không được trống", Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < arrayList.size(); i++) {
                User user = arrayList.get(i);
                String username = user.getUsername();
                String password = user.getPassword();
                if (editdn_name.getText().toString().equals(username)
                        || editdn_pass.getText().toString().equals(password)) {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công"
                            , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intent);
                    savePreferences(username, password);
                    editdn_name.setText("");
                    editdn_pass.setText("");
                    break;
                }
            }
        }
    }

    protected void savePreferences(String uSname, String pass) {
        SharedPreferences share = getSharedPreferences(name,
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString("a", uSname);
        editor.putString("b", pass);
        editor.commit();

    }


}