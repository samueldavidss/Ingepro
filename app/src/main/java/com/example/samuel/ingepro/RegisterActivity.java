package com.example.samuel.ingepro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.samuel.ingepro.models.Users;
import com.example.samuel.ingepro.queries.CurrentUser;
import com.example.samuel.ingepro.queries.References;
import com.google.firebase.database.DatabaseReference;

public class RegisterActivity extends AppCompatActivity {

    DatabaseReference dbref = new References().userReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final EditText nameEdt = (EditText) findViewById(R.id.nameEdt);
        final EditText addresEdt = (EditText) findViewById(R.id.addresEdt);
        final EditText phoneEdt = (EditText) findViewById(R.id.phoneEdt);
        final String key = getIntent().getStringExtra("key");
        Button saveBtn = (Button) findViewById(R.id.guardarbt);
        nameEdt.setText(new CurrentUser().nameUser());
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Users user = new Users();
                user.setEmail(new CurrentUser().getEmail());
                user.setKey(new CurrentUser().getUid());
                user.setName(String.valueOf(nameEdt.getText()));
                user.setDireccion(String.valueOf(addresEdt.getText()));
                user.setPhone(String.valueOf(phoneEdt.getText()));
                dbref.child(key).setValue(user);

                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
