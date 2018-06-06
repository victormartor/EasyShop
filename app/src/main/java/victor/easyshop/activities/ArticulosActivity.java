package victor.easyshop.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import victor.easyshop.R;
import victor.easyshop.adapters.ArticuloAdapter;
import victor.easyshop.adapters.CategoriaAdapter;
import victor.easyshop.clases.Articulo;
import victor.easyshop.clases.Cliente;
import victor.easyshop.clases.Marca;
import victor.easyshop.data.EasyShop;

/*
 * Autor: Víctor Martín Torres - 30/8/17
 *
 * Clase ActividadArticulos: muestra un GridView con los artículos de la categoría elegida mostrando sus precios.
 *
 * También tiene una Toolbar a la izquierda que contiene:
 *    -un botón para volver atrás.
 *    -un botón con la imagen de la marca que si es presionado lleva directamente a las categorías de la marca.
 *    -un botón para acceder al carrito.
 *    -un botón con el icono de la aplicación que lleva al menú de marcas.
 */
public class ArticulosActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    public static final String EXTRA_CATEGORIA = "categoria";
    public static final String EXTRA_MARCA = "marca";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        //Personalizar Toolbar
        usarToolbar();

        //Inicializar atributos
        //Marca marca = Marca.getMarcadeLista(ActividadSplash.basedeDatos.getMarcas(),getIntent().getIntExtra(EXTRA_MARCA, 0));

        //Gridview
        new cargarArticulos().execute(getIntent().getIntExtra(EXTRA_MARCA, 0),
                                      getIntent().getIntExtra(EXTRA_CATEGORIA, 0));

        /*
        //Imagen de marca
        ImageView imagenMarca = (ImageView) findViewById(R.id.imageViewMarca);
        imagenMarca.setImageBitmap(marca.getImagenBitmap());

        //Inactividad
        if(inactividad != null) inactividad.onProgressUpdate(this);

        //Carrito
        TextView num_art_carrito = (TextView)findViewById(R.id.numero_art_carrito);
        int n = carrito.getArticulos().size();
        if (n > 0)
        {
            num_art_carrito.setVisibility(View.VISIBLE);
            num_art_carrito.setText(String.format(Locale.ENGLISH,"%d",n));
        }
        else num_art_carrito.setVisibility(View.INVISIBLE);
        */
        //Carrito
        actualizar_carrito();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //MÉTODOS PROPIOS
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //CARGAR DATOS//////////////////////////////////////

    //clase cargar articulos
    private class cargarArticulos extends AsyncTask<Integer, Void, ArticuloAdapter>
    {
        private String _sRespuesta;
        private ProgressDialog _pDialog;
        private Marca _marca;

        @Override
        protected ArticuloAdapter doInBackground(Integer... params) {

            int iId_Marca = params[0];
            int iId_Categoria = params[1];
            String sIP_Servidor = ((EasyShop)getApplicationContext()).getIP_Servidor();
            String sPuerto = "5000";
            try {
                _marca = Cliente.getMarca(iId_Marca, sIP_Servidor, sPuerto);
                _marca.getImagen().cargarImagen();
                ArticuloAdapter articuloAdapter = new ArticuloAdapter(ArticulosActivity.this,
                        Cliente.getArticulos(iId_Categoria, sIP_Servidor, sPuerto));

                _sRespuesta = "conectado";
                return articuloAdapter;
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
            _pDialog = new ProgressDialog(ArticulosActivity.this);
            _pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            _pDialog.setMessage(getString(R.string.cargando));
            _pDialog.setCancelable(false);

            _pDialog.show();
        }

        @Override
        protected void onPostExecute(ArticuloAdapter articuloAdapter)
        {
            _pDialog.dismiss();
            if(_sRespuesta.equals("conectado")) {
                //Imagen de la marca
                ImageView imagenMarca = findViewById(R.id.imageViewMarca);
                if(_marca.getImagenBitmap() != null)
                {
                    imagenMarca.setImageBitmap(_marca.getImagenBitmap());
                }
                else
                {
                    imagenMarca.setImageResource(R.drawable.ic_file);
                    imagenMarca.setScaleType(ImageView.ScaleType.CENTER);
                }

                //GRIDVIEW
                GridView gridView = findViewById(R.id.grid);
                gridView.setAdapter(articuloAdapter);
                gridView.setOnItemClickListener(ArticulosActivity.this);
            }
            else{
                Toast toast = Toast.makeText(ArticulosActivity.this,
                        getString(R.string.error_conexion)+"\n"+_sRespuesta, Toast.LENGTH_SHORT);
                toast.show();
                findViewById(R.id.grid).setVisibility(View.INVISIBLE);
                findViewById(R.id.button_recargar).setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected void onCancelled() {
            _pDialog.dismiss();
            Toast toast = Toast.makeText(ArticulosActivity.this,
                    getString(R.string.error_conexion)+"\n"+_sRespuesta, Toast.LENGTH_SHORT);
            toast.show();
            findViewById(R.id.grid).setVisibility(View.INVISIBLE);
            findViewById(R.id.button_recargar).setVisibility(View.VISIBLE);
        }
    }

    //NAVEGAR A OTRA ACTIVIDAD/////////////////////////

    //Acción al pulsar en uno de los artículos del GridView
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Articulo item = (Articulo) parent.getItemAtPosition(position);

        Intent intent = new Intent(this, UnArticuloActivity.class);
        intent.putExtra(UnArticuloActivity.EXTRA_ARTICULO, item.getId());
        intent.putExtra(UnArticuloActivity.EXTRA_CATEGORIA, getIntent().getIntExtra(EXTRA_CATEGORIA, 0));
        intent.putExtra(UnArticuloActivity.EXTRA_MARCA, getIntent().getIntExtra(EXTRA_MARCA, 0));

        /*
        if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
        {
            inactividad = new Inactividad();
            inactividad.execute(this);
        }
        */
        startActivity(intent);
        finish();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //MÉTODOS GENERALES
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //Cargar datos de nuevo
    public void recargar(View view){
        findViewById(R.id.grid).setVisibility(View.VISIBLE);
        findViewById(R.id.button_recargar).setVisibility(View.INVISIBLE);
        new cargarArticulos().execute(getIntent().getIntExtra(EXTRA_MARCA, 0),
                getIntent().getIntExtra(EXTRA_CATEGORIA, 0));
    }

    //Actualizar carrito
    private void actualizar_carrito(){
        TextView num_art_carrito = findViewById(R.id.numero_art_carrito);
        int n = ((EasyShop)this.getApplication()).getCarrito().getNumArticulos();
        if (n > 0)
        {
            num_art_carrito.setVisibility(View.VISIBLE);
            num_art_carrito.setText(String.format("%d",n));
        }
        else num_art_carrito.setVisibility(View.INVISIBLE);
    }

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

    //Acción al pulsar la imagen de la marca
    public void iraMarca(View view)
    {
        /*
        if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
        {
            inactividad = new Inactividad();
            inactividad.execute(this);
        }
        */
        Intent intent = new Intent(this, CategoriasActivity.class);
        intent.putExtra(CategoriasActivity.EXTRA_MARCA, getIntent().getIntExtra(EXTRA_MARCA, 0));
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    //Acción al pulsar el botón del carrito
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
        Intent intent = new Intent(this, CategoriasActivity.class);
        intent.putExtra(CategoriasActivity.EXTRA_MARCA, getIntent().getIntExtra(EXTRA_MARCA, 0));

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
