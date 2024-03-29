package com.vedruna.gonzalezespinosa01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity"; // Etiqueta para los registros de depuración
    private TextView mensajeBienvenida;
    private SQLiteDatabase db;
    private AdminSQliteOpenHelper dbHelper;
    private static final String USUARIO_CORRECTO = "admin";
    private static final String CONTRASENA_CORRECTA = "admin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mensajeBienvenida = findViewById(R.id.textView);
        dbHelper = new AdminSQliteOpenHelper(this);
        db = dbHelper.getWritableDatabase();
    }

    // Método para el botón de iniciar sesión
    public void logearse(View view) {
        EditText nombreEditText = findViewById(R.id.editTextTextPassword);
        EditText contrasenaEditText = findViewById(R.id.editTextTextPassword2);

        String username = nombreEditText.getText().toString();
        String password = contrasenaEditText.getText().toString();

        boolean credencialesCorrectas = dbHelper.verificarCredenciales(username, password);

        if (credencialesCorrectas) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cerrar la base de datos
        db.close();
    }
}