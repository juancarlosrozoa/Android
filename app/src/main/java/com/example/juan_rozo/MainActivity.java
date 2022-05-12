package com.example.juan_rozo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button irRegistrar, irIniciarSesion;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        irRegistrar=findViewById(R.id.btnIrRegistrar);
        irIniciarSesion=findViewById(R.id.btnIrIniciarSesion);

        email=(EditText) findViewById(R.id.txtEmail);


        irRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Validar que campo email tenga formato de correo
                String emailInput = email.getEditableText().toString().trim();
                if (emailInput.isEmpty()) {
                    email.setError("El campo de correo electrónico no puede estar vacío");
                } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
                    email.setError("Ingrese un correo electrónico valido");
                    // Ir a registro
                } else {
                    Intent intent = new Intent(MainActivity.this, Registro.class);
                    intent.putExtra("Email",email.getText().toString());
                    startActivity(intent);
                }



            }
        });

        irIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IniciarSesion.class);
                startActivity(intent);
            }
        });

    }
}