package victor.easyshop.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import victor.easyshop.Adapters.CategoriaAdapter;
import victor.easyshop.Adapters.MarcaAdapter;
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
public class CategoriasActivity extends AppCompatActivity //implements AdapterView.OnItemClickListener
{
    public static final String EXTRA_MARCA = "marca";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        //Personalizar Toolbar
        usarToolbar();

        //Inicializar atributos
        //Marca marca = Marca.getMarcadeLista(ActividadSplash.basedeDatos.getMarcas(),getIntent().getIntExtra(EXTRA_MARCA, 0));

        //GridView
        new cargarCategorias().execute(getIntent().getIntExtra(EXTRA_MARCA, 0));

        //Imagen de la marca
        /*
        ImageView imagenMarca = findViewById(R.id.imageViewMarca);
        imagenMarca.setImageBitmap(marca.getImagenBitmap());
        */

        //Inactividad
        //if(inactividad != null) inactividad.onProgressUpdate(this);

        //Carrito
        /*
        TextView num_art_carrito = (TextView)findViewById(R.id.numero_art_carrito);
        int n = carrito.getArticulos().size();
        if (n > 0)
        {
            num_art_carrito.setVisibility(View.VISIBLE);
            num_art_carrito.setText(String.format(Locale.ENGLISH,"%d",n));
        }
        else num_art_carrito.setVisibility(View.INVISIBLE);
        */
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //MÉTODOS PROPIOS
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //CARGAR DATOS//////////////////////////////////////

    //clase cargar categorias
    private class cargarCategorias extends AsyncTask<Integer, Void, CategoriaAdapter>
    {
        private String _sRespuesta;
        private ProgressDialog _pDialog;
        private Marca _marca;

        @Override
        protected CategoriaAdapter doInBackground(Integer... params) {

            int iId_Marca = params[0];
            String sIP_Servidor = ((EasyShop)getApplicationContext()).getIP_Servidor();
            String sPuerto = "5000";
            try {
                _marca = Cliente.getMarca(iId_Marca, sIP_Servidor, sPuerto);
                _marca.getImagen().cargarImagen();
                CategoriaAdapter categoriaAdapter = new CategoriaAdapter(CategoriasActivity.this,
                        Cliente.getCategorias(iId_Marca, sIP_Servidor, sPuerto));
                //MarcaAdapter marcaAdapter = new MarcaAdapter(MarcasActivity.this,
                        //Cliente.getMarcas(sIP_Servidor, sPuerto));
                _sRespuesta = "conectado";
                return categoriaAdapter;
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
            _pDialog = new ProgressDialog(CategoriasActivity.this);
            _pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            _pDialog.setMessage(getString(R.string.cargando));
            _pDialog.setCancelable(false);

            _pDialog.show();
        }

        @Override
        protected void onPostExecute(CategoriaAdapter categoriaAdapter)
        {
            _pDialog.dismiss();
            if(_sRespuesta.equals("conectado")) {
                //Imagen de la marca
                ImageView imagenMarca = findViewById(R.id.imageViewMarca);
                imagenMarca.setImageBitmap(_marca.getImagenBitmap());

                //GRIDVIEW
                GridView gridView = findViewById(R.id.grid);
                gridView.setAdapter(categoriaAdapter);
                //gridView.setOnItemClickListener(MarcasActivity.this);
            }
            else{
                Toast toast = Toast.makeText(CategoriasActivity.this,
                        getString(R.string.error_conexion)+"\n"+_sRespuesta, Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        @Override
        protected void onCancelled() {
            _pDialog.dismiss();
            Toast toast = Toast.makeText(CategoriasActivity.this,
                    getString(R.string.error_conexion)+"\n"+_sRespuesta, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    //NAVEGAR A OTRA ACTIVIDAD/////////////////////////

    //Acción al pulsar en una de las categorias
    /*
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
        {
            inactividad = new Inactividad();
            inactividad.execute(this);
        }
        Categoria item = (Categoria) parent.getItemAtPosition(position);

        Intent intent = new Intent(this, ActividadArticulos.class);
        intent.putExtra(ActividadArticulos.EXTRA_CATEGORIA, item.getId());
        intent.putExtra(ActividadArticulos.EXTRA_MARCA, item.getMarca().getId());

        startActivity(intent);
        finish();
    }*/

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //MÉTODOS GENERALES
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //Función para personalizar la Toolbar
    private void usarToolbar()
    {
        Toolbar toolbar = findViewById(R.id.toolbar);

        //Añadir el botón flecha para volver atrás
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //Acción del botón para volver atrás
    @Override
    public boolean onSupportNavigateUp()
    {
        onBackPressed();
        return false;
    }

    //Acción al pulsar el icono de la marca (en esta actividad no hace nada pero hay que declararlo igualmente)
    public void iraMarca(View view) {}

    //Ación al pulsar el botón del carrito
    public void verCarro(View view)
    {
        /*
        if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
        {
            inactividad = new Inactividad();
            inactividad.execute(this);
        }
        */
        Intent intent = new Intent(this, CarritoActivity.class);
        startActivity(intent);
    }

    //Acción al pulsar el icono de la aplicación
    public void portada(View view)
    {
        /*
        if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
        {
            inactividad = new Inactividad();
            inactividad.execute(this);
        }
        */
        Intent intent = new Intent(this, MarcasActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

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

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MarcasActivity.class);

        /*
        if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
        {
            inactividad = new Inactividad();
            inactividad.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,this);
        }
        */

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
