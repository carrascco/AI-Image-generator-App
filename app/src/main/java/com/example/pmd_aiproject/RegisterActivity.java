package com.example.pmd_aiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pmd_aiproject.db.DBHelper;
import com.example.pmd_aiproject.db.UserDB;

public class RegisterActivity extends AppCompatActivity {

    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        db = new DBHelper(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button register = findViewById(R.id.btn_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = ((EditText)findViewById(R.id.id_txt_register_name)).getText().toString();
                String key= ((EditText)findViewById(R.id.id_txt_register_key)).getText().toString();
                String password1= ((EditText)findViewById(R.id.id_register_txt_password1)).getText().toString();
                String password2= ((EditText)findViewById(R.id.id_register_txt_password2)).getText().toString();


                // el nombre de usuario no esta cogido

                // key no esta vacio o tiene longitud indicada (si hay una definida)

                //password1 pasa un control de contraseña, longitud maxima, caracteres especiales ...

                //password2 es igual a password1

                // guardar contraseña o hash de contraseña?
                // si se guarda hash podria añadirse la opcion de recuperar contraseña enviando una al correo

                UserDB.postUser(db.getWritableDatabase(),username, password1, key);

                Toast.makeText(RegisterActivity.this,"Usuario registrado con exito",Toast.LENGTH_SHORT).show();

            }
        });

    }
}