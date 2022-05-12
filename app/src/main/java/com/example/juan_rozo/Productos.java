package com.example.juan_rozo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Productos extends AppCompatActivity {

    EditText presentacion, producto, precio;
    Button crear, eliminar, actualizar, buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);

        producto=findViewById(R.id.editTextProducto);
        presentacion=findViewById(R.id.editTextPresentacion);
        precio=findViewById(R.id.editTextPrecio);

        crear=findViewById(R.id.btnCreate);
        eliminar=findViewById(R.id.btnDelete);
        actualizar=findViewById(R.id.btnUpdate);
        buscar=findViewById(R.id.btnRead);

    }

    public void Registrar(View view){
        producto=findViewById(R.id.editTextProducto);
        presentacion=findViewById(R.id.editTextPresentacion);
        precio=findViewById(R.id.editTextPrecio);

        String productoInput = producto.getEditableText().toString();
        String presentacionInput=presentacion.getEditableText().toString();
        String precioInput=precio.getEditableText().toString();

        if (productoInput.isEmpty()){
            producto.setError("El campo de producto no puede estar vacío");
        } else {

            AdminDB admin = new AdminDB(getApplicationContext(), "Compras", 1);
            SQLiteDatabase db = admin.getWritableDatabase();

            String inputProducto =producto.getText().toString();
            String inputPresentacion =presentacion.getText().toString();
            String inptuPrecio =precio.getText().toString();


            ContentValues valores = new ContentValues();
            valores.put("producto",inputProducto);
            valores.put("presentacion", inputPresentacion);
            valores.put("precio",inptuPrecio);


            long newRowId = db.insert("compra",null,valores);

            if (newRowId==-1){
                Toast.makeText(this,"El producto "+inputProducto+" ya se encuentra registrado",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this, "Producto registrado exitosamente", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Productos.this, BuyTypes.class);
                startActivity(intent);
            }
        }

        producto.setText("");
        presentacion.setText("");
        precio.setText("");

    }


}