package com.example.gqueiroz.loginexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends Activity {

    private EditText usuario;
    private EditText pass;
    private Button login;
    private Button registre;

    private HashMap<String, String> usuarios = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = (EditText)findViewById(R.id.loginText);
        pass = (EditText)findViewById(R.id.passText);

        login = (Button)findViewById(R.id.loginButton);
        login.setOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(!usuarios.containsKey(usuario.getText().toString())){
                     Toast.makeText(getApplicationContext(), "Credenciais Incorretas!", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 if(usuarios.get(usuario.getText().toString()).equals(pass.getText().toString())){
                 //if(usuario.getText().toString().equals("admin") && pass.getText().toString().equals("admin")){
                     Toast.makeText(getApplicationContext(), "Carregando...", Toast.LENGTH_SHORT).show();
                     Intent i = new Intent(MainActivity.this,HomeActivity.class);
                     String item = usuario.getText().toString();
                     i.putExtra("item", item);
                     startActivity(i);
                 }
             }
         }
        );

        registre = (Button)findViewById(R.id.registreButton);
        registre.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Novo Usuário Registrado", Toast.LENGTH_SHORT).show();
                usuarios.put(usuario.getText().toString(),pass.getText().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
