package com.example.pmd_aiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pmd_aiproject.db.DBHelper;
import com.example.pmd_aiproject.db.UserDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RegisterActivity extends AppCompatActivity {

    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //db=MainActivity.this.getDB();
        //(Busco recibir la misma database del login para que funcione)

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button register = findViewById(R.id.btn_register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = ((EditText)findViewById(R.id.id_txt_register_name)).getText().toString();
                String password1= ((EditText)findViewById(R.id.id_register_txt_password1)).getText().toString();
                String password2= ((EditText)findViewById(R.id.id_register_txt_password2)).getText().toString();

                if(username.equals("") ||password1.equals("") ||password2.equals("")){
                    Toast.makeText(RegisterActivity.this,"Rellene todos los campos para el registro",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    if(UserDB.getUserByName(db.getReadableDatabase(),username)!=null){
                        Toast.makeText(RegisterActivity.this,"Este usuario ya existe",
                                Toast.LENGTH_SHORT).show();
                    }else{
                    // el nombre de usuario no esta cogido
                        int numCount=0;
                        int capCount=0;
                        int signCount=0;
                        for (int x =0; x < password1.length(); x++) {
                            if ((password1.charAt(x) >= 47 && password1.charAt(x) <= 58) || (password1.charAt(x) >= 64 && password1.charAt(x) <= 91) ||
                                    (password1.charAt(x) >= 97 && password1.charAt(x) <= 122)) {
                            }
                            if ((password1.charAt(x) > 47 && password1.charAt(x) < 58)) { // Cuenta la cantidad de numero
                                numCount ++;
                            }
                            if ((password1.charAt(x) > 64 && password1.charAt(x) < 91)) { // Cuenta la cantidad de mayuscula
                                capCount ++;
                            }
                            if ((password1.charAt(x) > 32 && password1.charAt(x) < 48)) { // Cuenta la cantidad de caracteres especiales
                                signCount ++;
                            }
                        } // Final del bucle

                    if(password1.length()>10 || password1.length()<5 ||  signCount==0 || capCount==0 || numCount==0){
                        Toast.makeText(RegisterActivity.this,"La contraseña debe tener entre 5 y 10 caracteres, incluyendo mayúsculas, números y caracteres especiales",
                                Toast.LENGTH_LONG).show();
                    }else{
                    //password1 pasa un control de contraseña, longitud maxima, caracteres especiales ...
                    if(!(password1.equals(password2))){
                        Toast.makeText(RegisterActivity.this,"Las contraseñas no coinciden",
                                Toast.LENGTH_SHORT).show();
                    }else{

                    //password2 es igual a password1

                    // guardar contraseña o hash de contraseña?
                    // si se guarda hash podria añadirse la opcion de recuperar contraseña enviando una al correo

                    UserDB.postUser(db.getWritableDatabase(), username, password1, "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");

                    Toast.makeText(RegisterActivity.this, "Usuario registrado con exito", Toast.LENGTH_SHORT).show();
                }}}}
            }
        });

        FloatingActionButton returnFromRegister=findViewById(R.id.btn_return_fromRegister);
        returnFromRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volverDeRegistro=new Intent(RegisterActivity.this, MainActivity.class);
                //abrirSegundaActividad.putExtra("NOMBRE_PARAMETRO_1", "texto");
                startActivity(volverDeRegistro);
            }
        });

    }
}