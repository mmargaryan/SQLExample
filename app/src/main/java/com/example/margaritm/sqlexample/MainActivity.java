package com.example.margaritm.sqlexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private SQLManager sqlManager;

    private Button btnAddUser;
    private Button btnViewData;
    private Button btnGetData;
    private Button btnUpdate;
    private Button btnDelete;
    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtNameToSearch;
    private TextView textData;

    View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.add_button:
                    addUser();
                    break;
                case R.id.view_details:
                    String dataText = sqlManager.getAllData();
                    textData.setText(dataText);
                    break;
                case R.id.getData:
                    if (edtUsername.getText().toString().equals("") || edtPassword.getText().toString().equals("")) {
                        getAllData();
                    } else {
                        getUserData();
                    }
                    break;
                case R.id.update:
                    updateName();
                    getAllData();
                    break;
                case R.id.delete:
                    deleteRow();
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqlManager = new SQLManager(this);

        edtUsername = (EditText) findViewById(R.id.username);
        edtPassword = (EditText) findViewById(R.id.password);
        btnAddUser = (Button) findViewById(R.id.add_button);
        btnAddUser.setOnClickListener(mOnclickListener);
        textData = (TextView) findViewById(R.id.data_text);
        btnViewData = (Button) findViewById(R.id.view_details);
        btnViewData.setOnClickListener(mOnclickListener);
        edtNameToSearch = (EditText) findViewById(R.id.search_by_name);
        btnGetData = (Button) findViewById(R.id.getData);
        btnGetData.setOnClickListener(mOnclickListener);
        btnDelete = (Button) findViewById(R.id.delete);
        btnDelete.setOnClickListener(mOnclickListener);
        btnUpdate = (Button) findViewById(R.id.update);
        btnUpdate.setOnClickListener(mOnclickListener);
    }

    private void addUser() {
        String username = edtUsername.getText().toString();
        String password = edtPassword.getText().toString();
        long id = sqlManager.insertData(username, password);

        if (id < 0) {
            Log.d("sql", "Not succeed");
        } else {
            Log.d("sql", "Successfully added");
        }
    }

    private void getAllData() {
        String result = sqlManager.getAllData();
        textData.setText(result);
    }

    private void getUserData() {
        String nameToSearch = edtNameToSearch.getText().toString();
        String sub1 = nameToSearch.substring(0, nameToSearch.indexOf(" "));
        String sub2 = nameToSearch.substring(nameToSearch.indexOf(" ") + 1);

        String result = sqlManager.getUserData(sub1, sub2);
        textData.setText(result);
    }

    private void updateName() {
        String username = edtNameToSearch.getText().toString();
        sqlManager.updateName("111", username);
    }

    private void deleteRow() {
        int count = sqlManager.delete(edtNameToSearch.getText().toString());
        Log.d("sql", "deleted " + count + "row(s)");

    }

}
