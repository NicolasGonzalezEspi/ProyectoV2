package com.vedruna.gonzalezespinosa01;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class CrearFragment extends Fragment {

    private EditText etCodigo, etNombre, etDescripcion, etPrecio;
    private Button btnAgregar;

    public CrearFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crear, container, false);

        etCodigo = view.findViewById(R.id.etCodigo);
        etNombre = view.findViewById(R.id.etNombre);
        etDescripcion = view.findViewById(R.id.etDescripcion);
        etPrecio = view.findViewById(R.id.etPrecio);
        btnAgregar = view.findViewById(R.id.btnAgregar);

        // Configurar el filtro para el campo de precio
        etPrecio.setFilters(new InputFilter[]{new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                // Verificar si el texto ingresado contiene solo números o punto decimal
                if (source.toString().matches("[0-9.]+")) {
                    return source;
                }
                return "";
            }
        }});

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener los datos ingresados por el usuario
                String codigo = etCodigo.getText().toString().trim();
                String nombre = etNombre.getText().toString().trim();
                String descripcion = etDescripcion.getText().toString().trim();
                String precio = etPrecio.getText().toString().trim();

                // Verificar si el código es válido
                if (!codigo.isEmpty() && Integer.parseInt(codigo) > 0) {
                    // Verificar si el nombre y la descripción no están vacíos
                    if (!nombre.isEmpty() && !descripcion.isEmpty()) {
                        // Agregar el nuevo producto a la base de datos
                        AdminSQliteOpenHelper admin = new AdminSQliteOpenHelper(getContext());
                        SQLiteDatabase bd = admin.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.put("codigo", codigo);
                        values.put("nombre", nombre);
                        values.put("descripcion", descripcion);
                        values.put("precio", precio);
                        long result = bd.insert("articulos", null, values);
                        bd.close();

                        // Mostrar un Toast con el resultado de la operación
                        if (result != -1) {
                            Toast.makeText(getContext(), "Producto agregado correctamente", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Error al agregar el producto", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Mostrar un Toast si el nombre o la descripción están vacíos
                        Toast.makeText(getContext(), "Por favor, ingrese un nombre y una descripción", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Mostrar un Toast si el código no es válido
                    Toast.makeText(getContext(), "El código debe ser un número positivo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}