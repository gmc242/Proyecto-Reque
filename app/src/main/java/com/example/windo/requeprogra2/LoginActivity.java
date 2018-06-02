package com.example.windo.requeprogra2;

import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.windo.requeprogra2.Controller.Controller;

public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {
    private EditText mEmailView;
    private EditText mPasswordView;
    private Button btnEmail;

    // Por alguna razon se esta implementando LoaderCallbacks, entonces tiene que implementar los métodos
    // Sino mejor quite LoaderCallbacks

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        btnEmail = (Button) findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                login(); // El boton hace el login con los datos de los edits
            }
        });
    }

    protected void login() {
        try {
            boolean valido = Controller.verificarPassword(mEmailView.getText().toString(),
                    mPasswordView.getText().toString());
            if (valido) {
                // Hace algo para pasar a la siguiente pantalla
            } else {
                throw new Exception("No se ha podido iniciar sesión");
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
