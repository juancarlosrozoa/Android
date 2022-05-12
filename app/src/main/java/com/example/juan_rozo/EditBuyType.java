package com.example.juan_rozo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditBuyType extends AppCompatActivity {

    EditText txtEditBuyType, txtEditCityBuyType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_buy_type);

        txtEditBuyType=(EditText)findViewById(R.id.txtEditBuyType);
        txtEditCityBuyType=(EditText)findViewById(R.id.txtEditCityBuyType);


    }

    public void Crear(View view) {

        AdminDB admin = new AdminDB(getApplicationContext(), "TipoCompras", 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String buyType = txtEditBuyType.getEditableText().toString();
        String ciudad = txtEditCityBuyType.getEditableText().toString();

        if (!buyType.isEmpty()) {

            ContentValues valores = new ContentValues();
            valores.put("tipoCompra", buyType);
            valores.put("ciudad", ciudad);

            long newRowId = db.insert("tipoCompra", null, valores);

            if (newRowId == -1) {
                Toast.makeText(this, "El tipo de compra " + buyType + " ya se encuentra registrado", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Tipo de compra registrado exitosamente", Toast.LENGTH_LONG).show();
            }
        }else {
            txtEditBuyType.setError("El campo de tipo de compra no puede estar vacío");
        }

        txtEditBuyType.setText("");
        txtEditCityBuyType.setText("");
    }

    public void leer(View view) {
        AdminDB admin = new AdminDB(getApplicationContext(), "TipoCompras", 1);
        SQLiteDatabase db = admin.getWritableDatabase();

       String buyType = txtEditBuyType.getEditableText().toString();

       if (!buyType.isEmpty()) {
           Cursor tipoCompra = db.rawQuery("select * from tipoCompra where tipoCompra="+buyType, null);
           if (tipoCompra.moveToFirst()){
               txtEditCityBuyType.setText(tipoCompra.getString(1));
           }else{
               Toast.makeText(getApplicationContext(),"Tipo de compra no existe",Toast.LENGTH_SHORT).show();
           }
       }else {
           Toast.makeText(getApplicationContext(), "Ingrese un tipo de compra para buscar", Toast.LENGTH_SHORT).show();
       }


    }

    public void actualizar(View view) {
        AdminDB admin = new AdminDB(getApplicationContext(), "TipoCompras", 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String buyType = txtEditBuyType.getEditableText().toString();
        String ciudad = txtEditCityBuyType.getEditableText().toString();

        if (!buyType.isEmpty()) {

            ContentValues valores = new ContentValues();
            valores.put("ciudad", ciudad);

    }
    public void eliminar(View view) {

        AdminDB admin = new AdminDB(getApplicationContext(), "TipoCompras", 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        String buyType = txtEditBuyType.getEditableText().toString();


        if(!buyType.isEmpty()){
            int i =db.delete("tipocompra", "tipoCompra="+buyType,null);
            if(i!=0){
                Toast.makeText(this,"Tipo de compra eliminada",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"El tipo de compra no esta registrado en la base de datos",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"Ingrese el tipo de compra a eliminar",Toast.LENGTH_SHORT).show();
        }

    }
}