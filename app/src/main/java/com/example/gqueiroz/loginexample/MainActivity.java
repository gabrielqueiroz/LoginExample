package com.example.gqueiroz.loginexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.loginText)
    EditText usuario;

    @Bind(R.id.loginWatcher)
    TextInputLayout loginWatcher;

    @Bind(R.id.passText)
    EditText pass;

    @Bind(R.id.passWatcher)
    TextInputLayout passWatcher;

    @Bind(R.id.loginButton)
    Button login;

    @Bind(R.id.registerButton)
    Button registre;

    private HashMap<String, String> usuarios = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        login.setOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 if(!usuarios.containsKey(usuario.getText().toString())){
                     Toast.makeText(getApplicationContext(), "Credenciais Incorretas!", Toast.LENGTH_SHORT).show();
                     return;
                 }
                 if(usuarios.get(usuario.getText().toString()).equals(pass.getText().toString())){
                     Toast.makeText(getApplicationContext(), "Carregando...", Toast.LENGTH_SHORT).show();
                     Intent i = new Intent(MainActivity.this,HomeActivity.class);
                     String item = usuario.getText().toString();
                     i.putExtra("item", item);
                     startActivity(i);
                 }
             }
         }
        );

        registre.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
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
