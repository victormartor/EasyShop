package victor.easyshop.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import victor.easyshop.R;
import victor.easyshop.adapters.CombinacionesAdapter;
import victor.easyshop.clases.Articulo;
import victor.easyshop.clases.Cliente;
import victor.easyshop.clases.Inactividad;
import victor.easyshop.clases.Marca;
import victor.easyshop.data.EasyShop;

/*
 * Autor: Víctor Martín Torres - 30/8/17
 *
 * Clase ActividadCombinaciones: muestra una lista de artículos que combinan con el que ha sido comprado.
 *
 * También tiene una Toolbar a la izquierda que contiene:
 *    -un botón para volver atrás.
 *    -un botón con la imagen de la marca que si es presionado lleva directamente a las categorías de la marca.
 *    -un botón para acceder al carrito.
 *    -un botón con el icono de la aplicación que lleva al menú de marcas.
 */
public class CombinacionesActivity extends AppCompatActivity implements View.OnClickListener
{
    public static final String EXTRA_ARTICULO = "articulo";
    public static final String EXTRA_CATEGORIA = "categoria";
    public static final String EXTRA_MARCA = "marca";

    public static final String EXTRA_COLOR = "color";
    public static final String EXTRA_TALLA = "talla";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combinaciones);

        //Personalizar Toolbar
        usarToolbar();

        //Inicializar atributos
        /*
        Marca marca = Marca.getMarcadeLista(ActividadSplash.basedeDatos.getMarcas(),getIntent().getIntExtra(EXTRA_MARCA, 0));
        Categoria categoria = Categoria.getCategoriadeLista(marca.getCategoriasVector(), getIntent().getIntExtra(EXTRA_CATEGORIA, 0));
        Articulo mArticulo = Articulo.getArticulodeLista(categoria.getArticulosVector(),getIntent().getIntExtra(EXTRA_ARTICULO,0));

        Articulo articulo = new Articulo(mArticulo);
        articulo.setColor(articulo.getVectorColores()[getIntent().getIntExtra(EXTRA_COLOR,0)]);
        articulo.setTalla(getIntent().getStringExtra(EXTRA_TALLA));
        */
        new cargarCombinaciones().execute(getIntent().getIntExtra(EXTRA_MARCA, 0),
                getIntent().getIntExtra(EXTRA_ARTICULO,0));

        //Imagen de la marca
        /*
        ImageView imagenMarca = (ImageView) findViewById(R.id.imageViewMarca);
        imagenMarca.setImageBitmap(marca.getImagenBitmap());
        */

        //CARGAR LAS IMAGENES DE LAS COMBINACIONES
        /*
        cargarCombinaciones tarea = new cargarCombinaciones();
        tarea.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        */

        //Inactividad
        reiniciar_inactividad();
        //if(inactividad != null) inactividad.onProgressUpdate(this);

        //Carrito
        actualizar_carrito();
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

    //clase cargar combinaciones
    private class cargarCombinaciones extends AsyncTask<Integer, Void, CombinacionesAdapter>
    {
        ProgressDialog _pDialog;
        Marca _marca;
        String _sRespuesta;

        @Override
        protected CombinacionesAdapter doInBackground(Integer... params)
        {
            int iId_Marca = params[0];
            int iId_Articulo = params[1];
            String sIP_Servidor = ((EasyShop)getApplicationContext()).getIP_Servidor();
            String sPuerto = "5000";
            try {
                _marca = Cliente.getMarca(iId_Marca, sIP_Servidor, sPuerto);
                _marca.getImagen().cargarImagen();
                CombinacionesAdapter articuloAdapter = new CombinacionesAdapter(
                        Cliente.getCombinacionesArticulo(iId_Articulo, sIP_Servidor, sPuerto));

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
        protected void onPreExecute() {

            _sRespuesta = "";
            _pDialog = new ProgressDialog(CombinacionesActivity.this);
            _pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            _pDialog.setMessage(getString(R.string.cargando));
            _pDialog.setCancelable(false);

            _pDialog.show();
        }

        @Override
        protected void onPostExecute(CombinacionesAdapter combinaciones)
        {
            _pDialog.dismiss();
            if(combinaciones!=null){
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

                //CREACION DEL RECYCLERVIEW
                LinearLayoutManager layoutManager= new LinearLayoutManager(CombinacionesActivity.this,LinearLayoutManager.HORIZONTAL, false);
                RecyclerView mRecyclerView = findViewById(R.id.lista_combinaciones);
                mRecyclerView.setLayoutManager(layoutManager);
                combinaciones.setOnClickListener(CombinacionesActivity.this);
                mRecyclerView.setAdapter(combinaciones);
            }
            else{
                Toast toast = Toast.makeText(CombinacionesActivity.this,
                        getString(R.string.error_conexion)+"\n"+_sRespuesta, Toast.LENGTH_SHORT);
                toast.show();
                findViewById(R.id.lista_combinaciones).setVisibility(View.INVISIBLE);
                findViewById(R.id.button_recargar).setVisibility(View.VISIBLE);
            }

        }

        @Override
        protected void onCancelled() {
            _pDialog.dismiss();
            Toast toast = Toast.makeText(CombinacionesActivity.this,
                    getString(R.string.error_conexion)+"\n"+_sRespuesta, Toast.LENGTH_SHORT);
            toast.show();
            findViewById(R.id.lista_combinaciones).setVisibility(View.INVISIBLE);
            findViewById(R.id.button_recargar).setVisibility(View.VISIBLE);
        }
    }

    //NAVEGAR A OTRA ACTIVIDAD/////////////////////////

    //Acción al pulsar en uno de los articulos de la lista de combinaciones
    @Override
    public void onClick(View view)
    {
        /*
        if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
        {
            inactividad = new Inactividad();
            inactividad.execute(this);
        }
        */
        RecyclerView mRecyclerView = findViewById(R.id.lista_combinaciones);
        int n = mRecyclerView.getChildLayoutPosition(view);
        CombinacionesAdapter adaptador = (CombinacionesAdapter) mRecyclerView.getAdapter();
        Articulo art = adaptador.getItem(n);

        Intent intent = new Intent(this, UnArticuloActivity.class);
        intent.putExtra(UnArticuloActivity.EXTRA_ARTICULO, art.getId());
        intent.putExtra(UnArticuloActivity.EXTRA_CATEGORIA, art.getId_Categoria());
        intent.putExtra(UnArticuloActivity.EXTRA_MARCA, art.getId_Marca());
        intent.putExtra(UnArticuloActivity.EXTRA_COMBINACIONES,true);

        intent.putExtra(UnArticuloActivity.EXTRA_ARTICULO_ANTERIOR,getIntent().getIntExtra(EXTRA_ARTICULO,0));
        intent.putExtra(UnArticuloActivity.EXTRA_CATEGORIA_ANTERIOR,getIntent().getIntExtra(EXTRA_CATEGORIA,0));
        intent.putExtra(UnArticuloActivity.EXTRA_MARCA_ANTERIOR,getIntent().getIntExtra(EXTRA_MARCA,0));
        intent.putExtra(UnArticuloActivity.EXTRA_COLOR_ANTERIOR,getIntent().getIntExtra(EXTRA_COLOR,0));
        intent.putExtra(UnArticuloActivity.EXTRA_TALLA_ANTERIOR,getIntent().getIntExtra(EXTRA_TALLA,0));

        startActivity(intent);
        finish();

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //MÉTODOS GENERALES
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //Inactividad
    public void reiniciar_inactividad(){
        Inactividad inactividad = ((EasyShop)this.getApplication()).getInactividad();
        if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
        {
            inactividad = new Inactividad();
            ((EasyShop)this.getApplication()).setInactividad(inactividad);
            inactividad.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,this);
        }
        else{
            inactividad.onProgressUpdate(this);
        }
    }

    //Cargar datos de nuevo
    public void recargar(View view){
        reiniciar_inactividad();
        findViewById(R.id.lista_combinaciones).setVisibility(View.VISIBLE);
        findViewById(R.id.button_recargar).setVisibility(View.INVISIBLE);
        new cargarCombinaciones().execute(getIntent().getIntExtra(EXTRA_MARCA, 0),
                getIntent().getIntExtra(EXTRA_ARTICULO,0));
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

    //Acción al pulsar en la imagen de la marca
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
        startActivityForResult(intent,0);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);
        reiniciar_inactividad();
        //inactividad.onProgressUpdate(this);
        actualizar_carrito();
    }

    @Override
    public void onBackPressed()
    {
        /*
        if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
        {
            inactividad = new Inactividad();
            inactividad.execute(this);
        }
        */

        Intent intent = new Intent(this, UnArticuloActivity.class);
        intent.putExtra(UnArticuloActivity.EXTRA_ARTICULO, getIntent().getIntExtra(EXTRA_ARTICULO, 0));
        intent.putExtra(UnArticuloActivity.EXTRA_MARCA, getIntent().getIntExtra(EXTRA_MARCA, 0));
        intent.putExtra(UnArticuloActivity.EXTRA_CATEGORIA, getIntent().getIntExtra(EXTRA_CATEGORIA, 0));

        startActivity(intent);
        finish();
    }
}
