package com.example.usuario1.basedatos1;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected DBmanager manager=new DBmanager(this);

    private Cursor cursor;
    private ListView Lista;
    SimpleCursorAdapter adapter;


    private EditText txtCodigo;
    private EditText txtNombre;
    private EditText txtApellido;
    private EditText txtDireccion;
    private EditText txtResultado;


    private Button btnInsertar;
    private Button btnActualizar;
    private Button btnEliminar;
    private Button btnConsultar;

    private SQLiteDatabase db;

    public MainActivity() {
       // if (manager == null) {
          //  try (Cursor ignored=cursor=Cursor) {
         //   }
        //} else {
          //  cursor=(Cursor) manager.cargadatos();
        //}
    }


    @SuppressLint("WrongViewCast")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtCodigo=findViewById(R.id.editCodigo);
        txtNombre=findViewById(R.id.editNombre);
        txtApellido=findViewById(R.id.editApellido);
        txtDireccion=findViewById(R.id.editdireccion);

        btnInsertar=findViewById(R.id.butInsertar);
        btnActualizar=findViewById(R.id.butActualizar);
        btnConsultar=findViewById(R.id.butConsulta);
        btnEliminar=findViewById(R.id.butEliminar);

//        Lista=(ListView) findViewById(R.id.txtresultado);


     //   String[] from=new String[]{manager.txtCodigo, manager.txtNombre};
       // int[] to=new int[]{android.R.id.text1, android.R.id.text2};


      //  adapter=new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, cursor, from, to, 0);
        //Lista.setAdapter(adapter);


        AprendizSQLiteHelper objetobBd=new AprendizSQLiteHelper(this, "DBAprendices", null, 1);


        db=objetobBd.getWritableDatabase();
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String cod=txtCodigo.getText().toString();
                String nom=txtNombre.getText().toString();
                String ape=txtApellido.getText().toString();
                String dir=txtDireccion.getText().toString();

                ContentValues nuevoRegistro=new ContentValues();
                nuevoRegistro.put("Codigo", cod);
                nuevoRegistro.put("nombre", nom);
                nuevoRegistro.put("apellido", ape);
                nuevoRegistro.put("direccion", dir);

                db.insert("Aprendiz", null, nuevoRegistro);

                Toast.makeText(MainActivity.this, "Guardado", Toast.LENGTH_LONG).show();
                btnActualizar.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        String cod=txtCodigo.getText().toString();
                        String nom=txtNombre.getText().toString();
                        String ape=txtApellido.getText().toString();
                        String dir=txtDireccion.getText().toString();


                        ContentValues valores=new ContentValues();
                        valores.put("codigo", cod);
                        valores.put("nombre", nom);
                        valores.put("apellido", ape);
                        valores.put("direccion", dir);

                        db.update("Aprendiz", valores, "codigo" + cod, null);
                        db.update("Aprendiz", valores, "nombre" + cod, null);
                        db.update("Aprendiz", valores, "apellido" + cod, null);
                        db.update("Aprendiz", valores, "direccion" + cod, null);


                        btnEliminar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // recuperar valor campo texto
                                String cod=txtCodigo.getText().toString();
                                String nom=txtNombre.getText().toString();
                                String ape=txtApellido.getText().toString();
                                String dir=txtDireccion.getText().toString();

                                db.delete("Aprendiz", "codigo=" + cod, null);
                                db.delete("Aprendiz", "nombre=" + cod, null);
                                db.delete("Aprendiz", "apellido=" + cod, null);
                                db.delete("Aprendiz", "direccion=" + cod, null);


                                btnConsultar.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Cursor c=db.rawQuery("SELECT codigo,nombre,apellido,direccion FROM Aprendiz", null);

                                        //String[] campos = new String[] ("codigo","nombre","apellido, "direccion");
                                        //Cursor c =db.query("Aprendiz",campos,null,null,null);


                                        txtResultado.setText("");
                                        if (c.moveToFirst()) {
                                            do {
                                                String cod=c.getString(0);
                                                String nom=c.getString(1);
                                                String ape=c.getString(2);
                                                String dir=c.getString(3);
                                                txtResultado.append(" " + cod + "-" + " " + nom + "-" + " " + ape + "-" + " " + dir + " ");
                                            } while (c.moveToNext());

                                        }


                                    }


                                });

                            }
                        });


                    }
                });


            }
        });


    }


}
