package victor.easyshop.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;

import victor.easyshop.R;
import victor.easyshop.clases.Cliente;

/*
 * Autor: Víctor Martín Torres - 30/8/17
 *
 * Clase ActividadSplash: muestra una pantalla de carga mientras se obtienen los datos de internet.
 * contiene un campo para insertar la IP del servidor donde se encuentra la aplicación para PC.
 */
public class MainActivity extends Activity
{
    //public static BasedeDatos basedeDatos;
    //public static int numCliente = 1;
    public static String sIP_Servidor;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void confirmar(View view)
    {
        //OBTENER LA IP
        EditText txtIP = findViewById(R.id.direccionIP_1oct);
        sIP_Servidor = txtIP.getText().toString()+".";

        txtIP = findViewById(R.id.direccionIP_2oct);
        sIP_Servidor += txtIP.getText().toString()+".";

        txtIP = findViewById(R.id.direccionIP_3oct);
        sIP_Servidor += txtIP.getText().toString()+".";

        txtIP = findViewById(R.id.direccionIP_4oct);
        sIP_Servidor += txtIP.getText().toString();

        findViewById(R.id.boton_confirmar).setVisibility(View.INVISIBLE);
        findViewById(R.id.layout_elementos_splash).setVisibility(View.INVISIBLE);

        findViewById(R.id.progreso).setVisibility(View.VISIBLE);

        ConectarServidor tarea = new ConectarServidor();
        tarea.execute();
    }


    private class ConectarServidor extends AsyncTask<Void, Void, Void>
    {
        private String _sRespuesta;
        @Override
        protected Void doInBackground(Void... params) {

            String sPuerto = "5000";
            try {
                _sRespuesta = Cliente.conectar(sIP_Servidor, sPuerto);
            } catch (Exception e) {
                _sRespuesta = e.toString();
                cancel(false);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }

        @Override
        protected void onPreExecute()
        {
            _sRespuesta = "";
            ProgressBar progressBar = findViewById(R.id.progreso);
            progressBar.setProgress(0);
        }

        @Override
        protected void onPostExecute(Void result)
        {
            if(_sRespuesta.equals("conectado")){
                Intent intent = new Intent(MainActivity.this, MarcasActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                Toast toast = Toast.makeText(MainActivity.this,
                        getString(R.string.error_conexion)+"\n"+_sRespuesta, Toast.LENGTH_SHORT);
                toast.show();

                findViewById(R.id.boton_confirmar).setVisibility(View.VISIBLE);
                findViewById(R.id.layout_elementos_splash).setVisibility(View.VISIBLE);
                findViewById(R.id.progreso).setVisibility(View.INVISIBLE);
            }
        }

        @Override
        protected void onCancelled() {
            Toast toast = Toast.makeText(MainActivity.this,
                    getString(R.string.error_conexion)+"\n"+_sRespuesta, Toast.LENGTH_SHORT);
            toast.show();

            findViewById(R.id.boton_confirmar).setVisibility(View.VISIBLE);
            findViewById(R.id.layout_elementos_splash).setVisibility(View.VISIBLE);
            findViewById(R.id.progreso).setVisibility(View.INVISIBLE);
        }
    }

}

