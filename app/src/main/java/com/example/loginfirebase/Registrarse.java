package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registrarse extends AppCompatActivity {

    private FirebaseAuth mAuTh;
    private EditText correo, password, vpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        mAuTh = FirebaseAuth.getInstance();
        correo = findViewById(R.id.et_emailInicio);
        password = findViewById(R.id.et_password);
        vpassword = findViewById(R.id.et_passwordInicio);
    }
    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuTh.getCurrentUser();
    }

    public void registrarUsuario(View view){
        if (password.getText().toString().equals(vpassword.getText().toString())){
            mAuTh.createUserWithEmailAndPassword(correo.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Usuario Registrado", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuTh.getCurrentUser();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }else {
                        Toast.makeText(getApplicationContext(), "autenticacion fallida", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            {

            }
        }
    }

}