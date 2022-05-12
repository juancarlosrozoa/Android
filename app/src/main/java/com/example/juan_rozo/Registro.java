package com.example.juan_rozo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registro extends AppCompatActivity {

    EditText emailRegistro, contraseña ;
    Button registrar, administrador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        emailRegistro=(EditText)findViewById(R.id.txtEmailRegistro);
        contraseña=(EditText)findViewById(R.id.txtPassword);



        Bundle bundle=getIntent().getExtras();
        String dato=bundle.getString("Email").toString();
        emailRegistro.setText(dato);

    }

        public void Registrar(View view){
        emailRegistro=(EditText)findViewById(R.id.txtEmailRegistro);

        Bundle bundle=getIntent().getExtras();
        String emailRegistrado=bundle.getString("Email").toString();
        emailRegistro.setText(emailRegistrado);

        String emailInput = emailRegistro.getEditableText().toString().trim();
        if (emailRegistrado.isEmpty()){
            emailRegistro.setError("El campo de correo electrónico no puede estar vacío");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            emailRegistro.setError("Ingrese un correo electrónico valido");
            // Ir a menu
        } else {

            AdminDB admin = new AdminDB(getApplicationContext(), "Compras", 1);
            SQLiteDatabase db = admin.getWritableDatabase();

            String email =emailRegistro.getText().toString();
            String password =contraseña.getText().toString();


            ContentValues valores = new ContentValues();
            valores.put("correo",email);
            valores.put("contraseña",password);


            long newRowId = db.insert("usuario",null,valores);

            if (newRowId==-1){
                Toast.makeText(this,"El correo electronico "+email+" ya se encuentra registrado",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Usuario registrado exitosamente", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Registro.this, BuyTypes.class);
                startActivity(intent);
            }
        }

        emailRegistro.setText("");
        contraseña.setText("");



    }

    public void Administrador (View view){
        Intent intent = new Intent(Registro.this, RegistroAdministrador.class);
        startActivity(intent);
    }


}