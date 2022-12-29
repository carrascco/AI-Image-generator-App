package com.example.pmd_aiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pmd_aiproject.db.DBHelper;
import com.example.pmd_aiproject.db.UserDB;
import com.example.pmd_aiproject.model.User;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    DBHelper db;


    public static final String NOMBRE_PARAMETRO_1="usuario:";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db= DBHelper.DBfabric(MainActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);

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

                        //TO-DO guardar datos en shared preferences
                        Toast.makeText(MainActivity.this,"Checkbox activada",Toast.LENGTH_SHORT).show();
                    }
                    Toast.makeText(MainActivity.this,"Login con exito",Toast.LENGTH_SHORT).show();

                    // Acceder a menu con key y usuario pasados como extras
                    Intent abrirMenu=new Intent(MainActivity.this, MenuActivity.class);
                    abrirMenu.putExtra(NOMBRE_PARAMETRO_1, username);
                    startActivity(abrirMenu);
                }else{
                    List<User> list = UserDB.getAll(db.getReadableDatabase());
                    for (int i = 0; i < list.size(); i++) {
                        Log.i("db",list.get(i).getUsername()+" "+list.get(i).getPassword());
                    }
                    Log.i("db","mensaje");

                    Toast.makeText(MainActivity.this,"Usuario o contraseña incorrecta",Toast.LENGTH_SHORT).show();
                }

            }
        });

        TextView goRegister= findViewById(R.id.id_txt_goRegister);
        goRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirRegistro=new Intent(MainActivity.this, RegisterActivity.class);

                startActivity(abrirRegistro);
            }
        });
    }

    public DBHelper getDB(){
        return this.db;
    }
}