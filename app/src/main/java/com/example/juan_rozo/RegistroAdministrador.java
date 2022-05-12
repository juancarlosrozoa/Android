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

public class RegistroAdministrador extends AppCompatActivity {

    EditText emailRegistro, contraseña;
    Button registrar,buscar, actualizar, eliminar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_administrador);

        emailRegistro=(EditText)findViewById(R.id.txtEmailRegistro);
        contraseña=(EditText)findViewById(R.id.txtPassword);
     }


    public void Registrar(View view){
        emailRegistro=(EditText)findViewById(R.id.txtEmailRegistro);

        String emailInput = emailRegistro.getEditableText().toString().trim();
        if (emailInput.isEmpty()){
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
                Intent intent = new Intent(RegistroAdministrador.this, BuyTypes.class);
                startActivity(intent);
            }
        }

        emailRegistro.setText("");
        contraseña.setText("");
    }

    public void Buscar(View view){

        buscar=findViewById(R.id.btnConsultar);
        emailRegistro=(EditText)findViewById(R.id.txtEmailRegistro);

        String clave =contraseña.getText().toString();

       Toast.makeText(getApplicationContext(), "El boton sirve", Toast.LENGTH_SHORT).show();

       AdminDB admin = new AdminDB(getApplicationContext(), "Compras", 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String emailConsultar=emailRegistro.getText().toString();
        if(emailConsultar.isEmpty()) {
            //Cursor fila= db.rawQuery("SELECT contraseña FROM usuario WHERE correo=" +emailRegistro, null);
            Toast.makeText(getApplicationContext(), "Ingrese un correo electronico para la busqueda", Toast.LENGTH_SHORT).show();
        }else {
            Cursor fila= db.rawQuery("SELECT contraseña FROM usuario WHERE correo=" +emailRegistro, null);
            if (fila.moveToFirst()){
                contraseña.setText(fila.getString(1));
            }else{
                Toast.makeText(getApplicationContext(), "El correo electronico no se encuentre registrado", Toast.LENGTH_SHORT).show();
            }
        Toast.makeText(getApplicationContext(), "El boton sirve"+ "     Toma la variable email fila y esta no vacio", Toast.LENGTH_SHORT).show();
        }
    }

    public void Actualizar(View view){

    }
    public void Eliminar(View view){
        AdminDB admin = new AdminDB(getApplicationContext(), "Compras", 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String id=emailRegistro.getText().toString();
        if(!id.isEmpty()){
            int i =db.delete("compra", "contraseña="+id,null);
            if(i!=0){
                Toast.makeText(this,"Usuario eliminado",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"El usuario no esta registrado en la base de datos",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"Ingrese el documento a eliminar",Toast.LENGTH_SHORT).show();
        }
    }

}