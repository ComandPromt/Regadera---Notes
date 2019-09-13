package com.regadera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Nota extends AppCompatActivity {

    ListView listaNotas;
    ArrayList<String> listaBd=new ArrayList<String>();
    ArrayAdapter<String> adaptador;
    Connection conn;

    int resultado;

    private String eliminarEspacios(String nota) {

        nota=nota.trim();

        nota=nota.replace("  "," ");

        if(nota==" "){
            nota="";
        }

        return nota;
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {

            try {

               Class.forName("com.mysql.jdbc.Driver");

                String url = "jdbc:mysql://IP:3306/NAME_BD?useUnicode=true&characterEncoding=UTF-8";

                java.sql.Connection conn = DriverManager.getConnection(url, "USER_BD", "PASS_BD");

                Statement stmt = conn.createStatement();

                String sql = "SELECT nombre FROM notas ORDER BY nombre";

                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    listaBd.add(rs.getString(1));
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        listaNotas=(ListView)findViewById(R.id.notas);
                        adaptador = new ArrayAdapter(Nota.this,android.R.layout.simple_list_item_1,listaBd);
                        listaNotas.setAdapter(adaptador);
                    }
                });

                sql = "SELECT count(id) FROM notas WHERE nombre='"+MainActivity.getNotaBusqueda()+"'";
                rs = stmt.executeQuery(sql);

                rs.next();

               resultado=Integer.parseInt(rs.getString("count(id)"));

                if(resultado==1){

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(Nota.this, "Registro duplicado", Toast.LENGTH_LONG).show();
                        }
                    });

                }

                else{

                    String nota=MainActivity.getNotaBusqueda();
                    String tipo=MainActivity.getNotaTipo();
                    String descripcion=MainActivity.getNotaDescripcion();

                    nota=eliminarEspacios(nota);
                    tipo=eliminarEspacios(nota);
                    descripcion=eliminarEspacios(nota);

                    if(!nota.equals("") && !tipo.equals("")&& !descripcion.equals("")){

                        sql = "INSERT INTO notas (nombre,tipo,descripcion) VALUES('"+MainActivity.getNotaBusqueda()+"','"+MainActivity.getNotaTipo()+"','"+MainActivity.getNotaDescripcion()+"')";
                        stmt.executeUpdate(sql);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Toast.makeText(Nota.this, "Registro insertado correctamente", Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                }

                conn.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.notas);
        new Thread(runnable).start();
    }

    public void Anterior(View view){
        Intent anterior = new Intent(this, MainActivity.class);
        startActivity(anterior);
    }
}
