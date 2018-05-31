package victor.easyshop.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import victor.easyshop.adapters.MarcaAdapter;
import victor.easyshop.R;
import victor.easyshop.clases.Cliente;
import victor.easyshop.clases.Marca;
import victor.easyshop.data.EasyShop;

/*
 * Autor: Víctor Martín Torres - 30/8/17
 *
 * Clase ActividadPrincipal: muestra un GridView con las diferentes marcas.
 *
 * También tiene una Toolbar a la izquierda que contiene:
 *    -un botón para acceder al carrito.
 */
public class MarcasActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    //public static Carro carrito;
    //public static Inactividad inactividad;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        //Toolbar
        ImageView imageView = findViewById(R.id.imageViewMarca);
        imageView.setBackgroundColor(Color.BLACK);

        //GridView
        /*
        GridView gridView = findViewById(R.id.grid);
        MarcaAdapter marcaAdapter = new MarcaAdapter(this, new ArrayList<Marca>());
        gridView.setAdapter(marcaAdapter);
        */
        //gridView.setOnItemClickListener(this);
        new cargarMarcas().execute();

        //carro
        /*
        if(carrito == null) carrito = new Carro();
        TextView num_art_carrito = (TextView)findViewById(R.id.numero_art_carrito);
        int n = carrito.getArticulos().size();
        if (n > 0)
        {
            num_art_carrito.setVisibility(View.VISIBLE);
            num_art_carrito.setText(String.format(Locale.ENGLISH,"%d",n));
        }
        else num_art_carrito.setVisibility(View.INVISIBLE);
        */

        //Inactividad
        /*
        if(inactividad != null) inactividad.onProgressUpdate(this);
        */
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //MÉTODOS PROPIOS
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //CARGAR DATOS//////////////////////////////////////

    //clase cargar marcas
    private class cargarMarcas extends AsyncTask<Void, Void, MarcaAdapter>
    {
        private String _sRespuesta;
        private ProgressDialog _pDialog;

        @Override
        protected MarcaAdapter doInBackground(Void... params) {

            String sIP_Servidor = ((EasyShop)getApplicationContext()).getIP_Servidor();
            String sPuerto = "5000";
            try {
                MarcaAdapter marcaAdapter = new MarcaAdapter(MarcasActivity.this,
                        Cliente.getMarcas(sIP_Servidor, sPuerto));
                _sRespuesta = "conectado";
                return marcaAdapter;
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
            _pDialog = new ProgressDialog(MarcasActivity.this);
            _pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            _pDialog.setMessage(getString(R.string.cargando));
            _pDialog.setCancelable(false);

            _pDialog.show();
        }

        @Override
        protected void onPostExecute(MarcaAdapter marcaAdapter)
        {
            _pDialog.dismiss();
            if(_sRespuesta.equals("conectado")) {
                GridView gridView = findViewById(R.id.grid);
                gridView.setAdapter(marcaAdapter);
                gridView.setOnItemClickListener(MarcasActivity.this);
            }
            else{
                Toast toast = Toast.makeText(MarcasActivity.this,
                        getString(R.string.error_conexion)+"\n"+_sRespuesta, Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        @Override
        protected void onCancelled() {
            _pDialog.dismiss();
            Toast toast = Toast.makeText(MarcasActivity.this,
                    getString(R.string.error_conexion)+"\n"+_sRespuesta, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    //NAVEGAR A OTRA ACTIVIDAD/////////////////////////

    //Acción al pulsar en una de las marcas
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Marca item = (Marca) parent.getItemAtPosition(position);
        Intent intent = new Intent(this, CategoriasActivity.class);
        intent.putExtra(CategoriasActivity.EXTRA_MARCA, item.getId());

        /*
        if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
        {
            inactividad = new Inactividad();
            inactividad.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,this);
        }
        */

        startActivity(intent);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //MÉTODOS GENERALES
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //Acción al pulsar el icono de la marca (en esta actividad no hace nada pero hay que declararlo igualmente)
    public void iraMarca(View view) {}

    //Acción al pulsar en el icono del carrito
    public void verCarro(View view)
    {
        /*
        if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
        {
            inactividad = new Inactividad();
            inactividad.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,this);
        }

        Intent intent = new Intent(this, ActividadCarrito.class);
        startActivityForResult(intent,0);
        */
        Intent intent = new Intent(this, CarritoActivity.class);
        startActivity(intent);
    }


    //Acción al pulsar el icono de la aplicación (no hace nada)
    public void portada(View view){}

    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        inactividad.onProgressUpdate(this);
        TextView num_art_carrito = (TextView)findViewById(R.id.numero_art_carrito);
        int n = carrito.getArticulos().size();
        if (n > 0)
        {
            num_art_carrito.setVisibility(View.VISIBLE);
            num_art_carrito.setText(String.format(Locale.ENGLISH,"%d",n));
        }
        else num_art_carrito.setVisibility(View.INVISIBLE);
    }
    */

    /*
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        carrito = null;
        if(inactividad != null)
        {
            inactividad.cancel(true);
            inactividad = null;
        }
        basedeDatos = null;
        nombreMaquina = null;
        numCliente = 1;
        finish();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    */
}