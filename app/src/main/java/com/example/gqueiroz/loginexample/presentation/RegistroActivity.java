package com.example.gqueiroz.loginexample.presentation;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.gqueiroz.loginexample.R;
import com.example.gqueiroz.loginexample.model.Usuario;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;

public class RegistroActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.nomeInput)
    EditText nomeInput;

    @Bind(R.id.sobrenomeInput)
    EditText sobrenomeInput;

    @Bind(R.id.emailInput)
    EditText emailInput;

    @Bind(R.id.senhaInput)
    EditText senhaInput;

    @Bind(R.id.dataNasc)
    EditText dataNasc;

    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;

    private DatePickerDialog datePicker;
    private SimpleDateFormat dateFormatter;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        setDateTimeField();

        dataNasc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSoftKeyboard();
                datePicker.show();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (requiredField(nomeInput)
                 || requiredField(emailInput)
                 || requiredField(senhaInput)
                 || requiredField(dataNasc))
                    return;

                createNewUser();
                Intent i = new Intent(RegistroActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void setDateTimeField() {
        calendar = Calendar.getInstance();
        datePicker = new DatePickerDialog(RegistroActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(year, monthOfYear, dayOfMonth);
                dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                dataNasc.setText(dateFormatter.format(calendar.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    }

    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(dataNasc.getWindowToken(), 0);
    }

    private void createNewUser() {
        Realm realm = Realm.getInstance(this.getApplicationContext());

        int key;

        try {
            Number max = realm.where(Usuario.class).max("id");
            key = max == null ? 0 : max.intValue() + 1;
        } catch (ArrayIndexOutOfBoundsException ex) {
            key = 0;
        }

        Usuario usuario = new Usuario(
                key,
                nomeInput.getText().toString(),
                sobrenomeInput.getText().toString(),
                calendar.getTime(),
                emailInput.getText().toString(),
                senhaInput.getText().toString());

        realm.beginTransaction();

        realm.copyToRealm(usuario);

        Log.i("INFO", "New User created: " + usuario.toString());

        realm.commitTransaction();
    }

    private boolean requiredField(EditText editText) {
        if (TextUtils.isEmpty(editText.getText())) {
            editText.setError("Campo obrigatorio");
            editText.setFocusable(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registro, menu);
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
