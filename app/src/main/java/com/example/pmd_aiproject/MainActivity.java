package com.example.pmd_aiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
    private String NOMBRE_FICHERO_RECUERDAME ="pref_login" ;
    private static final String ATRIB_LOGIN_USERNAME = "USERNAME";
    private static final String ATRIB_LOGIN_PASSWORD = "PASSWORD";

    private Intent i;
    public static boolean returnedFromMenu=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db= DBHelper.DBfabric(MainActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i=getIntent();

        SharedPreferences preferenciasLogin = getSharedPreferences(NOMBRE_FICHERO_RECUERDAME,MODE_PRIVATE);


        String username = preferenciasLogin.getString(ATRIB_LOGIN_USERNAME,null);
        String password = preferenciasLogin.getString(ATRIB_LOGIN_PASSWORD,null);
        if (username!=null && !returnedFromMenu){
            tryLogin(username,password,preferenciasLogin);
        }
        returnedFromMenu=false;
        Button loginButton = findViewById(R.id.btn_login_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = ((EditText)findViewById(R.id.txt_login_name)).getText().toString();
                String password = ((EditText)findViewById(R.id.txt_login_password)).getText().toString();
                if(username.equals("")||password.equals("")){
                    Toast.makeText(MainActivity.this,"Rellene los campos antes de iniciar sesión",Toast.LENGTH_SHORT).show();
                    return;
                }

                tryLogin(username, password, preferenciasLogin);

            }
        });

        TextView goRegister= findViewById(R.id.txt_login_goRegister);
        goRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abrirRegistro=new Intent(MainActivity.this, RegisterActivity.class);

                startActivity(abrirRegistro);
            }
        });
    }

    private void tryLogin(String username, String password, SharedPreferences preferenciasLogin) {
        User user = UserDB.getUserByName(db.getReadableDatabase(), username);
        // si guardamos hash en lugar de contraseña cambiar contraseña por su hash

        if(user!=null&&user.getUsername()!=null&&user.getPassword().equals(password)){
            CheckBox box = findViewById(R.id.box_login_remember);
            if(box.isChecked()){

                //TO-DO guardar datos en shared preferences

                SharedPreferences.Editor editor = preferenciasLogin.edit();
                editor.putString(ATRIB_LOGIN_USERNAME, username);
                editor.putString(ATRIB_LOGIN_PASSWORD, password);

                editor.commit();
            }

            String action=i.getAction();
            String type = i.getType();
            if(Intent.ACTION_SEND.equals(action) && type != null) {
                if("text/plain".equals(type)) {
                    // Es comparticion de texto
                    Intent abrirTexto=new Intent(MainActivity.this, TextToImageActivity.class);
                    abrirTexto.putExtra(NOMBRE_PARAMETRO_1, username);
                    String userKey=user.getKey();
                    abrirTexto.putExtra(NOMBRE_PARAMETRO_2, userKey);
                    String sharedText=i.getStringExtra(Intent.EXTRA_TEXT);
                    abrirTexto.putExtra("shared",sharedText);
                    startActivity(abrirTexto);
                }else if(type.startsWith("image/")) {
                    // Es comparticion de imagen
                    Intent abrirImage=new Intent(MainActivity.this, ImageToImageActivity.class);
                    abrirImage.putExtra(NOMBRE_PARAMETRO_1, username);
                    String userKey=user.getKey();
                    abrirImage.putExtra(NOMBRE_PARAMETRO_2, userKey);
                    Uri sharedImage=i.getParcelableExtra(Intent.EXTRA_STREAM);
                    abrirImage.putExtra("shared",sharedImage);
                    startActivity(abrirImage);
                }
            }else{
                // Acceder a menu con key y usuario pasados como extras
                Intent abrirMenu=new Intent(MainActivity.this, MenuActivity.class);
                abrirMenu.putExtra(NOMBRE_PARAMETRO_1, username);
                String userKey=user.getKey();
                abrirMenu.putExtra(NOMBRE_PARAMETRO_2, userKey);
                Toast.makeText(MainActivity.this,"Login con exito",Toast.LENGTH_SHORT).show();
                startActivity(abrirMenu);
            }

        }else{
            Toast.makeText(MainActivity.this,"Usuario o contraseña incorrecta",Toast.LENGTH_SHORT).show();
        }
    }

    public DBHelper getDB(){
        return this.db;
    }
}