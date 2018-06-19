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
import android.widget.TextView;
import android.widget.Toast;

import victor.easyshop.adapters.MarcaAdapter;
import victor.easyshop.R;
import victor.easyshop.clases.Cliente;
import victor.easyshop.clases.Inactividad;
import victor.easyshop.clases.Marca;
import victor.easyshop.data.EasyShop;

/**
 * Muestra un GridView con las diferentes marcas.
 *
 * También tiene una Toolbar a la izquierda que contiene:
 *    -un botón para acceder al carrito.
 *
 * @author Víctor Martín Torres
 */
public class MarcasActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        //Toolbar
        ImageView imageView = findViewById(R.id.imageViewMarca);
        imageView.setBackgroundColor(Color.BLACK);

        //cargar imagenes
        new cargarMarcas().execute();

        //carro
        actualizar_carrito();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //MÉTODOS PROPIOS
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //CARGAR DATOS//////////////////////////////////////

    /**
     * clase cargar marcas
     */
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
                findViewById(R.id.grid).setVisibility(View.INVISIBLE);
                findViewById(R.id.button_recargar).setVisibility(View.VISIBLE);
            }
        }

        @Override
        protected void onCancelled()
        {
            _pDialog.dismiss();
            Toast toast = Toast.makeText(MarcasActivity.this,
                    getString(R.string.error_conexion)+"\n"+_sRespuesta, Toast.LENGTH_SHORT);
            toast.show();
            findViewById(R.id.grid).setVisibility(View.INVISIBLE);
            findViewById(R.id.button_recargar).setVisibility(View.VISIBLE);
        }
    }

    //NAVEGAR A OTRA ACTIVIDAD/////////////////////////

    /**
     * Acción al pulsar en una de las marcas
     * @param parent el adaptador
     * @param view la vista donde se ha pulsado
     * @param position la posicion en la lista
     * @param id el id del elemento
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Marca item = (Marca) parent.getItemAtPosition(position);
        Intent intent = new Intent(this, CategoriasActivity.class);
        intent.putExtra(CategoriasActivity.EXTRA_MARCA, item.getId());

        startActivityForResult(intent,0);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //MÉTODOS GENERALES
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Inactividad
     */
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

    /**
     * Cargar datos de nuevo
     * @param view la vista
     */
    public void recargar(View view)
    {
        reiniciar_inactividad();
        findViewById(R.id.grid).setVisibility(View.VISIBLE);
        findViewById(R.id.button_recargar).setVisibility(View.INVISIBLE);
        new cargarMarcas().execute();
    }

    /**
     * Actualizar carrito
     */
    private void actualizar_carrito()
    {
        TextView num_art_carrito = findViewById(R.id.numero_art_carrito);
        int n = ((EasyShop)this.getApplication()).getCarrito().getNumArticulos();
        if (n > 0)
        {
            num_art_carrito.setVisibility(View.VISIBLE);
            num_art_carrito.setText(String.format("%d",n));
        }
        else num_art_carrito.setVisibility(View.INVISIBLE);
    }

    /**
     * Acción al pulsar el icono de la marca (en esta actividad no hace nada pero hay que
     * declararlo igualmente)
     * @param view la vista
     */
    public void iraMarca(View view) {}

    /**
     * Acción al pulsar el botón del carrito
     * @param view la vista
     */
    public void verCarro(View view)
    {
        Intent intent = new Intent(this, CarritoActivity.class);
        startActivityForResult(intent,0);
    }


    /**
     * Acción al pulsar el icono de la aplicación (no hace nada)
     * @param view la vista
     */
    public void portada(View view){}


    /**
     * Acción automática al volverse abrir después de cerrarse otra actividad
     * @param requestCode código entrante
     * @param resultCode código resultante
     * @param data datos que necesitan pasarse
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Inactividad inactividad = ((EasyShop)this.getApplication()).getInactividad();
        if(inactividad != null) inactividad.setContext(this);
        if(!((EasyShop)getApplicationContext()).getCarrito().vacio())reiniciar_inactividad();
        actualizar_carrito();
    }
}