package com.ncopado.memoria;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void generarArchivo(View view){

        try {
            EditText editText=(EditText) findViewById(R.id.etNombre);
            String nombre=editText.getText().toString();

            FileOutputStream fileOutputStream=null;
            fileOutputStream=openFileOutput("MiAcrhivo.txt", Context.MODE_APPEND);
            fileOutputStream.write(nombre.getBytes());
            fileOutputStream.close();

            Toast.makeText(MainActivity.this,"El archivo se ha creado",Toast.LENGTH_LONG).show();

            editText.setText("");


        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this,"Hubo un error en la escritura",Toast.LENGTH_LONG).show();
        }
    }


    public  void guardarPeferencia(View view)
    {
        SharedPreferences sharedPreferences=
                getSharedPreferences("MisDatosPersonales",Context.MODE_PRIVATE);

        SharedPreferences.Editor editor=sharedPreferences.edit();

        EditText etnombre=(EditText) findViewById(R.id.etNombre);
        EditText etcorreo=(EditText) findViewById(R.id.etCorreo);

        String nombre=etnombre.getText().toString();
        String correo=etcorreo.getText().toString();


        editor.putString("nombre",nombre);
        editor.putString("correo",correo);

        editor.commit();

        Toast.makeText(MainActivity.this,"Se ha creado la preferencia compartida",Toast.LENGTH_LONG).show();

        etnombre.setText("");
        etcorreo.setText("");

    }


    public  void  mostrarPeferencia(View view)
    {
        SharedPreferences sharedPreferences=
                getSharedPreferences("MisDatosPersonales",Context.MODE_PRIVATE);

        String nombre=sharedPreferences.getString("nombre", "no existe esa variable");
        String correo=sharedPreferences.getString("correo", "no existe esa variable");

        TextView textView=(TextView) findViewById(R.id.tvPreference);
        String preference="\nNombre:"+nombre +"\nCorreo:"+ correo;
        textView.setText(preference);
    }
}
