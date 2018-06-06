package victor.easyshop.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import victor.easyshop.R;
import victor.easyshop.clases.Carrito;
import victor.easyshop.clases.Cliente;
import victor.easyshop.data.EasyShop;

/*
 * Autor: Víctor Martín Torres - 30/8/17
 *
 * Clase ActividadSplash: muestra una pantalla de carga mientras se obtienen los datos de internet.
 * contiene un campo para insertar la IP del servidor donde se encuentra la aplicación para PC.
 */
public class MainActivity extends Activity
{

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
        String sIP_Servidor = txtIP.getText().toString()+".";

        txtIP = findViewById(R.id.direccionIP_2oct);
        sIP_Servidor += txtIP.getText().toString()+".";

        txtIP = findViewById(R.id.direccionIP_3oct);
        sIP_Servidor += txtIP.getText().toString()+".";

        txtIP = findViewById(R.id.direccionIP_4oct);
        sIP_Servidor += txtIP.getText().toString();

        ((EasyShop)this.getApplication()).setIP_Servidor(sIP_Servidor);
        ((EasyShop)this.getApplication()).setCarrito(new Carrito());

        new ConectarServidor().execute();
    }

    private class ConectarServidor extends AsyncTask<Void, Void, Void>
    {
        private String _sRespuesta;
        private ProgressDialog _pDialog;

        @Override
        protected Void doInBackground(Void... params) {

            publishProgress();

            String sIP_Servidor = ((EasyShop)getApplicationContext()).getIP_Servidor();
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
            _pDialog = new ProgressDialog(MainActivity.this);
            _pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            _pDialog.setMessage(getString(R.string.cargando));
            _pDialog.setCancelable(false);

            _pDialog.show();
        }

        @Override
        protected void onPostExecute(Void result)
        {
            _pDialog.dismiss();
            if(_sRespuesta.equals("conectado")) {
                Intent intent = new Intent(MainActivity.this, MarcasActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                Toast toast = Toast.makeText(MainActivity.this,
                        getString(R.string.error_conexion)+"\n"+_sRespuesta, Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        @Override
        protected void onCancelled() {
            _pDialog.dismiss();
            Toast toast = Toast.makeText(MainActivity.this,
                    getString(R.string.error_conexion)+"\n"+_sRespuesta, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}

