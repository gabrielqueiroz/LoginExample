package com.example.gqueiroz.loginexample.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gqueiroz.loginexample.R;
import com.example.gqueiroz.loginexample.model.Usuario;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.loginText)
    EditText usuario;

    @Bind(R.id.passText)
    EditText pass;

    @Bind(R.id.loginButton)
    Button login;

    @Bind(R.id.registerButton)
    Button registre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        login.setOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Realm realm = Realm.getInstance(v.getContext());

                 RealmResults<Usuario> query = realm.where(Usuario.class)
                         .contains("email",usuario.getText().toString())
                         .contains("senha",pass.getText().toString())
                         .findAll();

                 if(query.isEmpty()){
                     Toast.makeText(v.getContext(),"Usuario nao cadastrado",Toast.LENGTH_LONG).show();
                 } else {
                     Toast.makeText(v.getContext(),"Carregando usuario",Toast.LENGTH_LONG).show();
                     Intent i = new Intent(MainActivity.this, HomeActivity.class);
                     i.putExtra("usuario", query.get(0).toString());
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
