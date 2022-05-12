package com.example.juan_rozo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class BuyTypes extends AppCompatActivity {

    Spinner spin;
    ListView listShop;
    Button editBuyType;

    //Probando con datos quemados..
    String [] buyType = {"Mercado", "Papeleria", "Ferreteria", "Jugueteria"};
    String [] shopMercado = {"Mercar Caney", "Mercar Bochalema", "Surtifamiliar Caney"};
    String [] shopPapeleria = {"Panamericana", "Agachese Caney"};
    String [] shopFerreteria = {"Carrera 50 Caney", "El paisa", "Bahia Centro"};
    String [] shopJugueteria = {"La 14", "MercadoLibre", "Alkosto"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_types);

        //Relacionar las variables con las activity
        listShop = findViewById(R.id.listShop);
        editBuyType = findViewById(R.id.btnEditBuyType);
        spin = (Spinner) findViewById(R.id.spnBuyType);


        //Ir a la activity para editar la lista de tipo de compras
        editBuyType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BuyTypes.this, EditBuyType.class);
                startActivity(intent);
            }
        });


        //consultarListaTipoCompra();

        ArrayAdapter adaptadorSpinner = new ArrayAdapter(this, android.R.layout.simple_spinner_item, buyType);
        spin.setAdapter(adaptadorSpinner);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("Mercado")) {
                    ArrayAdapter listAdapter = new ArrayAdapter(BuyTypes.this, android.R.layout.simple_list_item_1, shopMercado);
                    listShop.setAdapter(listAdapter);
                }
                if (adapterView.getItemAtPosition(i).equals("Papeleria")) {
                    ArrayAdapter listAdapter = new ArrayAdapter(BuyTypes.this, android.R.layout.simple_list_item_1, shopPapeleria);
                    listShop.setAdapter(listAdapter);
                }
                if (adapterView.getItemAtPosition(i).equals("Ferreteria")) {
                    ArrayAdapter listAdapter = new ArrayAdapter(BuyTypes.this, android.R.layout.simple_list_item_1, shopFerreteria);
                    listShop.setAdapter(listAdapter);
                }
                if (adapterView.getItemAtPosition(i).equals("Jugueteria")) {
                    ArrayAdapter listAdapter = new ArrayAdapter(BuyTypes.this, android.R.layout.simple_list_item_1, shopJugueteria);
                    listShop.setAdapter(listAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });

        listShop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent irProductos= new Intent(BuyTypes.this,Productos.class);
                startActivity(irProductos);
            }
        });

    }


}