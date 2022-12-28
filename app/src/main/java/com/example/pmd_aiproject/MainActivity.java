package com.example.pmd_aiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pmd_aiproject.db.DBHelper;
import com.example.pmd_aiproject.db.UserDB;
import com.example.pmd_aiproject.model.User;


public class MainActivity extends AppCompatActivity {

    DBHelper db;


    public static final String NOMBRE_PARAMETRO_1="usuario:";
    public static final String NOMBRE_PARAMETRO_2="key:";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);

        TextView noCuenta = findViewById(R.id.id_txt_noTienesCuenta);

        noCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirRegister = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(abrirRegister);
            }
        });



        Button loginButton = findViewById(R.id.btn_main_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((EditText)findViewById(R.id.id_txt_name_login)).getText().toString();
                String password = ((EditText)findViewById(R.id.id_txt_password_login)).getText().toString();
                if(username.equals("")||password.equals("")){
                    Toast.makeText(MainActivity.this,"Rellene los campos antes de iniciar sesión",Toast.LENGTH_SHORT).show();
                    return;
                }

                User user = UserDB.getUserByName(db.getReadableDatabase(), username);
                // si guardamos hash en lugar de contraseña cambiar contraseña por su hash

                if(user!=null&&user.getUsername()!=null&&user.getPassword().equals(password)){
                    CheckBox box = findViewById(R.id.box_main_remember);
                    if(box.isActivated()){
                        //guardar datos en shared preferences
                        Toast.makeText(MainActivity.this,"Checkbox activada",Toast.LENGTH_SHORT).show();


                    }
                    Toast.makeText(MainActivity.this,"Login con exito",Toast.LENGTH_SHORT).show();

                    // Acceder a menu con key y usuario pasados como extras
                }

            }
        });







    }
}