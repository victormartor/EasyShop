package victor.easyshop.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import victor.easyshop.R;
import victor.easyshop.adapters.Articulo_ColorAdapter;
import victor.easyshop.adapters.Articulo_TallaAdapter;
import victor.easyshop.adapters.CirculoAdapter;
import victor.easyshop.clases.Articulo;
import victor.easyshop.clases.Articulo_Color;
import victor.easyshop.clases.Articulo_Talla;
import victor.easyshop.clases.Cliente;
import victor.easyshop.clases.Marca;
import victor.easyshop.data.EasyShop;

/**
 * Autor: Víctor Martín Torres - 30/8/17
 *
 * Clase ActividadDetalle: muestra la imagen del artículo en grande y sus características a la derecha. Bajo las
 * características se encuentra el botón para añadir el artículo al carrito. Debajo se encuentran la lista
 * de colores y la lista de tallas.
 *
 * También tiene una Toolbar a la izquierda que contiene:
 *    -un botón para volver atrás.
 *    -un botón con la imagen de la marca que si es presionado lleva directamente a las categorías de la marca.
 *    -un botón para acceder al carrito.
 *    -un botón con el icono de la aplicación que lleva al menú de marcas.
 */
public class UnArticuloActivity extends AppCompatActivity
{
    public static final String EXTRA_ARTICULO = "articulo";
    public static final String EXTRA_CATEGORIA = "categoria";
    public static final String EXTRA_MARCA = "marca";
    public static final String EXTRA_COMBINACIONES = "combinaciones";

    public static final String EXTRA_ARTICULO_ANTERIOR = "articulo_anterior";
    public static final String EXTRA_MARCA_ANTERIOR = "marca_anterior";
    public static final String EXTRA_CATEGORIA_ANTERIOR = "categoria_anterior";
    public static final String EXTRA_COLOR_ANTERIOR = "color_anterior";
    public static final String EXTRA_TALLA_ANTERIOR = "talla_anterior";

    private Articulo _Articulo;
    private Articulo_Color Color_Sel;
    private Articulo_Talla Talla_Sel;

    //posicion de la imagen en la galería
    int numImagen;
    int numTalla;
    //int numColor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_articulo);

        //Personalizar Toolbar
        usarToolbar();

        //Inicializar atributos
        numImagen = 0;
        //numColor = 0;
        numTalla = 0;
        new cargarArticulo().execute(getIntent().getIntExtra(EXTRA_MARCA, 0),
                                   getIntent().getIntExtra(EXTRA_ARTICULO, 0));
        //new cargarTallas().execute(getIntent().getIntExtra(EXTRA_ARTICULO, 0));
        //new cargarColores().execute(getIntent().getIntExtra(EXTRA_ARTICULO, 0));
        /*
        Marca marca = Marca.getMarcadeLista(ActividadSplash.basedeDatos.getMarcas(),getIntent().getIntExtra(EXTRA_MARCA, 0));
        Categoria categoria = Categoria.getCategoriadeLista(marca.getCategoriasVector(), getIntent().getIntExtra(EXTRA_CATEGORIA, 0));
        Articulo articulo = Articulo.getArticulodeLista(categoria.getArticulosVector(),getIntent().getIntExtra(EXTRA_ARTICULO,0));
        mArticulo = new Articulo(articulo);
        */

        // Imagenes del articulo
        /*
        numImagen = 0;
        numColor = 0;
        //cargarColores tarea = new cargarColores();
        cargarColores_v2 tarea = new cargarColores_v2();
        tarea.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        */

        //Imagen de la marca
        /*
        ImageView imagenMarca = (ImageView) findViewById(R.id.imageViewMarca);
        imagenMarca.setImageBitmap(marca.getImagenBitmap());
        */



        //Botones de las tallas
        /*
        numTalla = 0;
        cargarTallas_v2 tarea2 = new cargarTallas_v2();
        tarea2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        mArticulo.setTalla(mArticulo.getTallas().get(numTalla));
        */

        //Inactividad
        //if(inactividad != null) inactividad.onProgressUpdate(this);

        //Botones de las flechas
        //setFlechas();

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

    //clase cargar un articulo
    private class cargarArticulo extends AsyncTask<Integer, Void, Void>
    {
        ProgressDialog _pDialog;
        String _sRespuesta;
        private Marca _marca;

        @Override
        protected Void doInBackground(Integer... params)
        {
            int iId_Marca = params[0];
            int iId_Articulo = params[1];

            String sIP_Servidor = ((EasyShop)getApplicationContext()).getIP_Servidor();
            String sPuerto = "5000";
            try {
                _marca = Cliente.getMarca(iId_Marca, sIP_Servidor, sPuerto);
                _marca.getImagen().cargarImagen();
                _Articulo = Cliente.getUnArticulo(iId_Articulo, sIP_Servidor, sPuerto);
                _sRespuesta = "conectado";
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
            _pDialog = new ProgressDialog(UnArticuloActivity.this);
            _pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            _pDialog.setMessage(getString(R.string.cargando));
            _pDialog.setCancelable(false);

            _pDialog.show();
        }

        @Override
        protected void onPostExecute(Void avoid)
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

                //Texto características artículo
                TextView textoMarca = findViewById(R.id.marca);
                textoMarca.setText(_marca.getNombre());

                TextView textoDescripcion = findViewById(R.id.articulo);
                String texto = _Articulo.getNombre();
                textoDescripcion.setText(texto);
                TextView textoPrecio = findViewById(R.id.precio_articulo);
                String precio = String.format("%.2f",_Articulo.getPVP())+" €";
                textoPrecio.setText(precio);

                //new cargarColores().execute(_Articulo.getId());
                new cargarTallas().execute(_Articulo.getId());
            }
            else{
                Toast toast = Toast.makeText(UnArticuloActivity.this,
                        getString(R.string.error_conexion)+"\n"+_sRespuesta, Toast.LENGTH_SHORT);
                toast.show();
                findViewById(R.id.datos_articulo).setVisibility(View.INVISIBLE);
                findViewById(R.id.button_recargar).setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected void onCancelled() {
            _pDialog.dismiss();
            Toast toast = Toast.makeText(UnArticuloActivity.this,
                    getString(R.string.error_conexion)+"\n"+_sRespuesta, Toast.LENGTH_SHORT);
            toast.show();
            findViewById(R.id.datos_articulo).setVisibility(View.INVISIBLE);
            findViewById(R.id.button_recargar).setVisibility(View.VISIBLE);
        }
    }

    //clase cargar colores
    private class cargarColores extends AsyncTask<Integer, Void, Articulo_ColorAdapter>
    {
        ProgressDialog pDialog;
        String _sRespuesta;

        @Override
        protected Articulo_ColorAdapter doInBackground(Integer... params)
        {
            int iId_Articulo = params[0];
            String sIP_Servidor = ((EasyShop)getApplicationContext()).getIP_Servidor();
            String sPuerto = "5000";
            try {
                Articulo_ColorAdapter adaptador = new Articulo_ColorAdapter(
                        Cliente.getColoresArticulo(iId_Articulo, sIP_Servidor, sPuerto));
                return adaptador;
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

            pDialog = new ProgressDialog(UnArticuloActivity.this);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.setMessage(getString(R.string.cargando));
            pDialog.setCancelable(false);

            pDialog.show();
        }

        @Override
        protected void onPostExecute(Articulo_ColorAdapter adaptador) {
            pDialog.dismiss();
            if(adaptador != null){
                despliega_colores(adaptador);
                Color_Sel = adaptador.getItem(0);

                ImageView imagenArticulo = findViewById(R.id.imagen_extendida);

                if(Color_Sel.getImagenes().get(0).getBitmap() != null)
                {
                    imagenArticulo.setImageBitmap(Color_Sel.getImagenes().get(0).getBitmap());
                }
                else
                {
                    imagenArticulo.setImageResource(R.drawable.ic_file);
                    imagenArticulo.setScaleType(ImageView.ScaleType.CENTER);
                }

                despliega_circulos();
                setFlechas();
            }
            else{
                Toast toast = Toast.makeText(UnArticuloActivity.this,
                        getString(R.string.error_conexion)+"\n"+_sRespuesta, Toast.LENGTH_SHORT);
                toast.show();
                findViewById(R.id.datos_articulo).setVisibility(View.INVISIBLE);
                findViewById(R.id.button_recargar).setVisibility(View.VISIBLE);
            }
            //new cargarTallas().execute();
        }

        @Override
        protected void onCancelled() {
            pDialog.dismiss();
            Toast toast = Toast.makeText(UnArticuloActivity.this,
                    getString(R.string.error_conexion)+"\n"+_sRespuesta, Toast.LENGTH_SHORT);
            toast.show();
            findViewById(R.id.datos_articulo).setVisibility(View.INVISIBLE);
            findViewById(R.id.button_recargar).setVisibility(View.VISIBLE);
        }
    }

    //clase cargar tallas
    private class cargarTallas extends AsyncTask<Integer, Void, Articulo_TallaAdapter>
    {
        ProgressDialog pDialog;
        String _sRespuesta;

        @Override
        protected Articulo_TallaAdapter doInBackground(Integer... params)
        {
            int iId_Articulo = params[0];
            String sIP_Servidor = ((EasyShop)getApplicationContext()).getIP_Servidor();
            String sPuerto = "5000";
            try {
                Articulo_TallaAdapter adaptador = new Articulo_TallaAdapter(
                        Cliente.getTallasArticulo(iId_Articulo, sIP_Servidor, sPuerto), _Articulo.getTalla_Es_Numero());
                return adaptador;
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

            pDialog = new ProgressDialog(UnArticuloActivity.this);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.setMessage(getString(R.string.cargando));
            pDialog.setCancelable(false);

            pDialog.show();
        }

        @Override
        protected void onPostExecute(Articulo_TallaAdapter adaptador) {
            pDialog.dismiss();

            if(adaptador!= null){
                despliega_tallas(adaptador);
                Talla_Sel = adaptador.getItem(0);

                new cargarColores().execute(_Articulo.getId());
            }
            else
            {
                Toast toast = Toast.makeText(UnArticuloActivity.this,
                        getString(R.string.error_conexion)+"\n"+_sRespuesta, Toast.LENGTH_SHORT);
                toast.show();
                findViewById(R.id.datos_articulo).setVisibility(View.INVISIBLE);
                findViewById(R.id.button_recargar).setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected void onCancelled() {
            pDialog.dismiss();
            Toast toast = Toast.makeText(UnArticuloActivity.this,
                    getString(R.string.error_conexion)+"\n"+_sRespuesta, Toast.LENGTH_SHORT);
            toast.show();
            findViewById(R.id.datos_articulo).setVisibility(View.INVISIBLE);
            findViewById(R.id.button_recargar).setVisibility(View.VISIBLE);
        }
    }

    //MODIFICADORES////////////////////////////////////

    //Función para desplegar la lista de colores
    public void despliega_colores(Articulo_ColorAdapter adaptador)
    {
        LinearLayoutManager layoutManager= new LinearLayoutManager(UnArticuloActivity.this,LinearLayoutManager.HORIZONTAL, false);
        RecyclerView mRecyclerView = findViewById(R.id.lista_colores);
        mRecyclerView.setLayoutManager(layoutManager);
        adaptador.setOnClickListener(new ClickColor());
        mRecyclerView.setAdapter(adaptador);
    }


    //Función para desplegar la lista de tallas
    public void despliega_tallas(Articulo_TallaAdapter adaptador)
    {
        LinearLayoutManager layoutManager= new LinearLayoutManager(UnArticuloActivity.this,LinearLayoutManager.HORIZONTAL, false);
        RecyclerView mRecyclerView = findViewById(R.id.lista_tallas);
        mRecyclerView.setLayoutManager(layoutManager);
        if(!_Articulo.getTalla_Es_Numero()) adaptador.setOnClickListener(new ClickTalla());
        mRecyclerView.setAdapter(adaptador);
    }

    //Acción al pulsar uno de los colores de la lista
    private class ClickColor implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            /*
            if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
            {
                inactividad = new Inactividad();
                inactividad.execute(ActividadDetalle.this);
            }
            inactividad.onProgressUpdate(ActividadDetalle.this);
            */


            RecyclerView mRecyclerView = findViewById(R.id.lista_colores);
            int numColor = mRecyclerView.getChildLayoutPosition(view);
            Articulo_ColorAdapter adapter = (Articulo_ColorAdapter)mRecyclerView.getAdapter();
            Color_Sel = adapter.getItem(numColor);
            for(int i=0; i< adapter.getItemCount();i++)
            {
                View mView = mRecyclerView.getChildAt(i);
                if(mView!=null) mView.findViewById(R.id.seleccionado).setVisibility(View.INVISIBLE);
            }
            mRecyclerView.findContainingItemView(view).findViewById(R.id.seleccionado).setVisibility(View.VISIBLE);

            ImageView imagenColor = findViewById(R.id.imagen_extendida);
            imagenColor.setImageBitmap(Color_Sel.getImagenes().get(0).getBitmap());
            numImagen=0;
            setFlechas();
            despliega_circulos();
        }
    }

    //Acción al pulsar uno de los colores de la lista
    private class ClickTalla implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            /*
            if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
            {
                inactividad = new Inactividad();
                inactividad.execute(ActividadDetalle.this);
            }
            inactividad.onProgressUpdate(ActividadDetalle.this);
            */

            RecyclerView mRecyclerView = findViewById(R.id.lista_tallas);
            int n = mRecyclerView.getChildLayoutPosition(view);
            Articulo_TallaAdapter adapter = (Articulo_TallaAdapter)mRecyclerView.getAdapter();
            for(int i=0; i< adapter.getItemCount();i++)
            {
                View mView = mRecyclerView.getChildAt(i);
                if(mView!=null) mView.findViewById(R.id.seleccionado).setVisibility(View.INVISIBLE);
            }
            mRecyclerView.findContainingItemView(view).findViewById(R.id.seleccionado).setVisibility(View.VISIBLE);

            //actualizar_tallas(talla);
            //mArticulo.setTalla(talla);
            Talla_Sel = adapter.getItem(n);
        }
    }

    //Acción al pulsar la flecha hacia la izquierda

    public void anteriorImagen(View view)
    {
        RecyclerView mRecyclerView = findViewById(R.id.lista_colores);
        //Articulo_Color articulo_color = ((Articulo_ColorAdapter)mRecyclerView.getAdapter()).getItem(numColor);

        if(numImagen > 0)
        {
            /*
            if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
            {
                inactividad = new Inactividad();
                inactividad.execute(this);
            }

            inactividad.onProgressUpdate(this);
            */

            numImagen--;
            ImageView imagenArticulo = findViewById(R.id.imagen_extendida);
            //imagenArticulo.setImageBitmap(mArticulo.getColor().getImagenBitMap(numImagen));
            imagenArticulo.setImageBitmap(Color_Sel.getImagenes().get(numImagen).getBitmap());

            setFlechas();
            actualizar_circulos();
        }
    }

    //Acción al pulsar la flecha hacia la derecha
    public void siguienteImagen(View view)
    {
        RecyclerView mRecyclerView = findViewById(R.id.lista_colores);
        //Articulo_Color articulo_color = ((Articulo_ColorAdapter)mRecyclerView.getAdapter()).getItem(numColor);
        if(numImagen < Color_Sel.getImagenes().size()-1)
        {
            /*
            if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
            {
                inactividad = new Inactividad();
                inactividad.execute(this);
            }
            inactividad.onProgressUpdate(this);
            */

            numImagen++;
            ImageView imagenArticulo = findViewById(R.id.imagen_extendida);
            //imagenArticulo.setImageBitmap(mArticulo.getColor().getImagenBitMap(numImagen));
            imagenArticulo.setImageBitmap(Color_Sel.getImagenes().get(numImagen).getBitmap());

            setFlechas();
            actualizar_circulos();
        }

    }

    //desplegar circulos
    public void despliega_circulos()
    {
        LinearLayoutManager layoutManager= new LinearLayoutManager(UnArticuloActivity.this,LinearLayoutManager.HORIZONTAL, false);
        RecyclerView mRecyclerView = findViewById(R.id.lista_circulos);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new CirculoAdapter(Color_Sel.getImagenes().size()));
    }

    //actualizar circulos
    public void actualizar_circulos()
    {
        RecyclerView circulos = findViewById(R.id.lista_circulos);
        for(int i=0; i< Color_Sel.getImagenes().size();i++)
        {
            View mView = circulos.getChildAt(i);
            if(mView!=null)
            {
                ImageView img = mView.findViewById(R.id.imagen);
                img.setImageResource(R.drawable.ic_circle_off);
            }
        }
        View mView = circulos.getChildAt(numImagen);
        ImageView img = mView.findViewById(R.id.imagen);
        img.setImageResource(R.drawable.ic_circle_on);
    }

    //Funcion para hacer visibles las flechas o no según el artículo
    public void setFlechas()
    {
        View flechaIzq = findViewById(R.id.boton_izq);
        View flechaDer = findViewById(R.id.boton_der);
        if (numImagen == 0)
        {
            flechaIzq.setVisibility(View.INVISIBLE);

            if (Color_Sel.getImagenes().size()==1)
            {
                flechaDer.setVisibility(View.INVISIBLE);
            }
            else
            {
                flechaDer.setVisibility(View.VISIBLE);
            }
        }
        else
        {
            flechaIzq.setVisibility(View.VISIBLE);

            if (numImagen == Color_Sel.getImagenes().size()-1)
            {
                flechaDer.setVisibility(View.INVISIBLE);
            }
            else
            {
                flechaDer.setVisibility(View.VISIBLE);
            }
        }
    }

    //Acción al pulsar el boton menos en la talla
    public void talla_menos(View view)
    {
        RecyclerView mRecyclerView = findViewById(R.id.lista_tallas);
        Articulo_TallaAdapter adapter = (Articulo_TallaAdapter)mRecyclerView.getAdapter();
        if(numTalla > 0)
        {
            /*
            if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
            {
                inactividad = new Inactividad();
                inactividad.execute(this);
            }
            inactividad.onProgressUpdate(this);
            */
            numTalla--;
            Talla_Sel = adapter.getItem(numTalla);

            //actualizar_tallas(mArticulo.getTallas().get(numTalla));
            //RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.lista_tallas);
            TextView textView = mRecyclerView.getChildAt(0).findViewById(R.id.numero_talla);
            textView.setText(Talla_Sel.getNombre());

            //mArticulo.setTalla(mArticulo.getTallas().get(numTalla));
        }
    }

    //Acción al pulsar el boton mas en la talla
    public void talla_mas(View view)
    {
        RecyclerView mRecyclerView = findViewById(R.id.lista_tallas);
        Articulo_TallaAdapter adapter = (Articulo_TallaAdapter)mRecyclerView.getAdapter();
        if(numTalla < adapter.getArraySize()-1)
        {
            /*
            if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
            {
                inactividad = new Inactividad();
                inactividad.execute(this);
            }
            inactividad.onProgressUpdate(this);
            */
            numTalla++;
            Talla_Sel = adapter.getItem(numTalla);

            //actualizar_tallas(mArticulo.getTallas().get(numTalla));
            //RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.lista_tallas);
            TextView textView = mRecyclerView.getChildAt(0).findViewById(R.id.numero_talla);
            textView.setText(Talla_Sel.getNombre());

            //mArticulo.setTalla(mArticulo.getTallas().get(numTalla));
        }
    }

    //NAVEGAR A OTRA ACTIVIDAD/////////////////////////

    //Acción al pulsar en la imagen del artículo
    public void ampliar(View view)
    {
        /*
        if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
        {
            inactividad = new Inactividad();
            inactividad.execute(this);
        }
        Intent intent = new Intent(this, ActividadMasDetalle.class);
        intent.putExtra(ActividadMasDetalle.EXTRA_ARTICULO, mArticulo.getColor().getImagenUrl(numImagen));
        startActivityForResult(intent,0);
        */
    }

    //Acción al pulsar en el botón de añadir al carrito
    public void alCarrito(View view)
    {
        /*
        if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
        {
            inactividad = new Inactividad();
            inactividad.execute(this);
        }

        //Insertar cuadro de diálogo para confirmación
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);

        dialogo.setTitle(R.string.annadir);
        dialogo.setMessage(R.string.confirmacion_al_carro);
        dialogo.setCancelable(false);

        dialogo.setPositiveButton(R.string.confirmar, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialogo1, int id)
            {
                Intent intent = new Intent(ActividadDetalle.this, ActividadCombinaciones.class);
                intent.putExtra(ActividadCombinaciones.EXTRA_ARTICULO, mArticulo.getId());
                intent.putExtra(ActividadCombinaciones.EXTRA_CATEGORIA, mArticulo.getCategoria().getId());
                intent.putExtra(ActividadCombinaciones.EXTRA_MARCA, mArticulo.getMarca().getId());

                Color[] colores = mArticulo.getVectorColores();
                int n = 0;
                for (int i=0;i<colores.length;i++)
                {
                    if (colores[i].getId() == mArticulo.getColor().getId()) n = i;
                }

                intent.putExtra(ActividadCombinaciones.EXTRA_COLOR, n);
                intent.putExtra(ActividadCombinaciones.EXTRA_TALLA, mArticulo.getTalla());

                carrito.insertarArticulo(mArticulo);
                startActivity(intent);
                finish();
            }
        });
        dialogo.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialogo1, int id) {}
        });

        dialogo.show();
        */
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //MÉTODOS GENERALES
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //Cargar datos de nuevo
    public void recargar(View view){
        findViewById(R.id.datos_articulo).setVisibility(View.VISIBLE);
        findViewById(R.id.button_recargar).setVisibility(View.INVISIBLE);
        new cargarArticulo().execute(getIntent().getIntExtra(EXTRA_MARCA, 0),
                getIntent().getIntExtra(EXTRA_ARTICULO, 0));
        //new cargarColores().execute(getIntent().getIntExtra(EXTRA_ARTICULO, 0));
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
    protected void onDestroy()
    {
        super.onDestroy();
        _Articulo = null;
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

        /*
        if (getIntent().getBooleanExtra(EXTRA_COMBINACIONES,false))
        {
            Intent intent = new Intent(ActividadDetalle.this, ActividadCombinaciones.class);
            intent.putExtra(ActividadCombinaciones.EXTRA_ARTICULO, getIntent().getIntExtra(EXTRA_ARTICULO_ANTERIOR,0));
            intent.putExtra(ActividadCombinaciones.EXTRA_MARCA, getIntent().getIntExtra(EXTRA_MARCA_ANTERIOR,0));
            intent.putExtra(ActividadCombinaciones.EXTRA_CATEGORIA, getIntent().getIntExtra(EXTRA_CATEGORIA_ANTERIOR,0));
            intent.putExtra(ActividadCombinaciones.EXTRA_TALLA, getIntent().getIntExtra(EXTRA_TALLA_ANTERIOR,0));
            intent.putExtra(ActividadCombinaciones.EXTRA_COLOR, getIntent().getIntExtra(EXTRA_COLOR_ANTERIOR,0));
            startActivity(intent);
        }
        else
        {
        */
            Intent intent = new Intent(this, ArticulosActivity.class);
            intent.putExtra(ArticulosActivity.EXTRA_CATEGORIA, getIntent().getIntExtra(EXTRA_CATEGORIA, 0));
            intent.putExtra(ArticulosActivity.EXTRA_MARCA, getIntent().getIntExtra(EXTRA_MARCA, 0));
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        //}
        finish();
    }
}
