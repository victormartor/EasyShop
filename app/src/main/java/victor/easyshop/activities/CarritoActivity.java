package victor.easyshop.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import victor.easyshop.R;

/*
 * Autor: Víctor Martín Torres - 30/8/17
 *
 * Clase ActividadCarrito: muestra una lista con scroll hacia abajo de los artículos en el carrito. A la derecha está
 * el ticket de compra con los diferentes artículos, su referencia y su precio. Bajo el ticket se encuentra el botón
 * para confirmar la compra y otro para vaciar el carrito.
 *
 * También tiene una Toolbar a la izquierda que contiene:
 *    -un botón para volver atrás.
 *    -un botón con el icono de la aplicación que lleva al menú de marcas.
 */
public class CarritoActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        //Personalizar Toolbar
        usarToolbar();
        pulsar_carrito();

        //Actualizar lista de artículos para mostrar según los que haya en el carrito
        //actualizarLista();

        //Inactividad
        //if(inactividad != null) inactividad.onProgressUpdate(this);

        //Texto vaciar carro
        TextView texto_vaciar_carro = findViewById(R.id.texto_vaciar_carro);
        texto_vaciar_carro.setPaintFlags(texto_vaciar_carro.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //MÉTODOS PROPIOS
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //CARGAR DATOS//////////////////////////////////////

    //cargar imagenes de los artículos
    /*
    private class cargarImagenesCarro extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog pDialog;

        @Override
        protected Void doInBackground(Void... params) {

            for (Carro.ArticuloCarrito i : carrito.getArticulos())
            {
                i.cargarImagen();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }

        @Override
        protected void onPreExecute() {

            pDialog = new ProgressDialog(ActividadCarrito.this);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.setMessage(getString(R.string.cargando));
            pDialog.setCancelable(false);

            pDialog.show();
        }

        @Override
        protected void onPostExecute(Void resultado) {

            GridView gridView = (GridView) findViewById(R.id.grid);
            AdaptadorDeCarro adaptador = new AdaptadorDeCarro(ActividadCarrito.this, ActividadPrincipal.carrito.getArticulos());
            gridView.setAdapter(adaptador);
            pDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
        }
    }
    */

    //Función para actualizar la lista de artículos
    /*
    private void actualizarLista()
    {
        //TICKET
        //Columna con la lista de artículos y su referencia
        TextView textoArticulos = (TextView) findViewById(R.id.carrito);
        String listaArticulos = "";
        String moneda = getString(R.string.moneda);

        //Columna con la lista de precios
        TextView textoPrecios = (TextView) findViewById(R.id.carritoPrecios);
        String listaPrecios = "";
        float total = 0;

        if(!ActividadPrincipal.carrito.vacio())
        {
            for(Carro.ArticuloCarrito a : ActividadPrincipal.carrito.getArticulos())
            {
                listaArticulos = listaArticulos+a.getReferencia()+
                        "-"+a.getColor().hashCode()+"-"+a.getTalla()+"\n\n";
                listaPrecios = listaPrecios+String.format(Locale.ENGLISH,"%.2f",a.getPrecio())+" "+moneda+"\n\n";
                total += a.getPrecio();
            }

            textoArticulos.setText(listaArticulos);
            textoPrecios.setText(listaPrecios);
        }
        else
        {
            textoArticulos.setText(R.string.carro_vacio);
            textoPrecios.setText("");
        }

        //Total
        String texto = String.format(Locale.ENGLISH,"%.2f",total)+" "+moneda;
        TextView precioTotal = (TextView) findViewById(R.id.totalPrecio);
        precioTotal.setText(texto);

        //GRIDVIEW
        cargarImagenesCarro tarea = new cargarImagenesCarro();
        tarea.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        //Carrito
        actualizar_carrito();
    }
    */

    //MODIFICAR TOOLBAR
    private void pulsar_carrito(){
        ImageView imageView = findViewById(R.id.imageViewMarca);
        imageView.setBackgroundColor(Color.BLACK);
        FrameLayout frameLayout = findViewById(R.id.fondo_carrito);
        frameLayout.setBackgroundColor(Color.WHITE);
        imageView = findViewById(R.id.imagenCarrito);
        imageView.setImageResource(R.drawable.ic_carrito_negro);
        TextView textView = findViewById(R.id.numero_art_carrito);
        textView.setTextColor(Color.WHITE);
    }

    //MODIFICADORES////////////////////////////////////

    //Acción para vaciar el carrito
    public void vaciar(View view)
    {
        /*
        if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
        {
            inactividad = new Inactividad();
            inactividad.execute(this);
        }
        inactividad.onProgressUpdate(this);

        //Insertar cuadro de diálogo para confirmación
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);

        dialogo.setTitle(R.string.vaciar_carro);

        if(ActividadPrincipal.carrito.vacio()) dialogo.setMessage(R.string.carro_vacio);
        else dialogo.setMessage(R.string.confirmacion_vaciar_carro);

        dialogo.setCancelable(true);

        if(!ActividadPrincipal.carrito.vacio())
        {
            dialogo.setPositiveButton(R.string.confirmar, new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialogo1, int id)
                {
                    ActividadPrincipal.carrito.vaciarCarro();
                    actualizarLista();
                    actualizar_carrito();
                }
            });
        }

        dialogo.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialogo1, int id) {}
        });

        dialogo.show();
        */
    }

    //Acción para eliminar un artículo de la lista
    /*
    public void eliminar(View view)
    {
        if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
        {
            inactividad = new Inactividad();
            inactividad.execute(this);
        }
        inactividad.onProgressUpdate(this);
        final View mi_view = view;

        //Insertar cuadro de diálogo para confirmación
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);

        dialogo.setTitle(R.string.eliminar_item);
        dialogo.setMessage(R.string.confirmacion_eliminar_item);
        dialogo.setCancelable(true);

        dialogo.setPositiveButton(R.string.confirmar, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialogo1, int id)
            {
                GridView gridView = (GridView) findViewById(R.id.grid);
                Carro.ArticuloCarrito item = (Carro.ArticuloCarrito) gridView.getItemAtPosition(gridView.getPositionForView(mi_view));

                ActividadPrincipal.carrito.getArticulos().remove(item);

                actualizarLista();
                actualizar_carrito();
            }
        });
        dialogo.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialogo1, int id) {}
        });

        dialogo.show();
    }
    */

    //Accion al pulsar el botón de comprar y pagar en caja
    public void comprar(View view)
    {
        /*
        if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
        {
            inactividad = new Inactividad();
            inactividad.execute(this);
        }
        inactividad.onProgressUpdate(this);

        //Insertar cuadro de diálogo para confirmación
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);

        dialogo.setTitle(R.string.compra);

        if(ActividadPrincipal.carrito.vacio()) dialogo.setMessage(R.string.carro_vacio);
        else dialogo.setMessage(R.string.confirmacion_compra);

        dialogo.setCancelable(false);

        if(!ActividadPrincipal.carrito.vacio())
        {
            dialogo.setPositiveButton(R.string.confirmar, new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface dialogo1, int id)
                {
                    //ENVIAR TICKET DE COMPRA
                    enviarCompra enviar = new enviarCompra();
                    enviar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            });
        }

        dialogo.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialogo1, int id) {}
        });

        dialogo.show();
        */
    }

    //clase enviar compra
    /*
    private class enviarCompra extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog pDialog;
        boolean enviado;

        @Override
        protected Void doInBackground(Void... params) {

            try {
                String numPuerto = "5000"; // número de puerto por defecto
                ClienteAuxiliar.conectar(nombreMaquina, numPuerto, carrito);
            } // fin de try
            catch (Exception ex) {
                ex.printStackTrace( );
                cancel(true);
            } // fin de catch

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }

        @Override
        protected void onPreExecute() {

            enviado = true;
            pDialog = new ProgressDialog(ActividadCarrito.this);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.setMessage(getString(R.string.enviando_ticket));
            pDialog.setCancelable(true);
            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    enviado = false;
                }
            });

            pDialog.show();
        }

        @Override
        protected void onPostExecute(Void resultado) {

            pDialog.dismiss();

            if(enviado)
            {
                carrito.vaciarCarro();
                actualizarLista();

                //Insertar cuadro de diálogo para confirmación
                AlertDialog.Builder dialogo = new AlertDialog.Builder(ActividadCarrito.this);

                dialogo.setTitle(getString(R.string.ticket_enviado));

                String mensaje = getString(R.string.ticket_enviado_confirmacion)+"\n"+getString(R.string.recuerde)+numCliente;
                dialogo.setMessage(mensaje);

                dialogo.setCancelable(false);


                dialogo.setPositiveButton(getString(R.string.aceptar), new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialogo1, int id) {}
                });

                dialogo.show();

                numCliente++;
            }
        }

        @Override
        protected void onCancelled() {
            pDialog.dismiss();
            if(enviado)
            {
                Toast t = Toast.makeText(ActividadCarrito.this,getString(R.string.error),Toast.LENGTH_LONG);
                t.show();
            }
        }
    }
    */

    //actualizar numero del carrito
    /*
    private void actualizar_carrito()
    {
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

    //NAVEGAR A OTRA ACTIVIDAD/////////////////////////

    //Acción al pulsar la imagen del artículo dentro de la lista
    /*
    public void ampliar(View view)
    {
        if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
        {
            inactividad = new Inactividad();
            inactividad.execute(this);
        }
        Intent intent = new Intent(this, ActividadMasDetalle.class);

        GridView gridView = (GridView) findViewById(R.id.grid);
        Carro.ArticuloCarrito item = (Carro.ArticuloCarrito) gridView.getItemAtPosition(gridView.getPositionForView(view));
        intent.putExtra(ActividadMasDetalle.EXTRA_ARTICULO, item.getImagenUrl());

        startActivityForResult(intent,0);
    }
    */

    //Acción al pulsar el texto del articulo del carrito
    /*
    public void iraArticulo(View view)
    {
        GridView gridView = (GridView) findViewById(R.id.grid);
        Carro.ArticuloCarrito item = (Carro.ArticuloCarrito) gridView.getItemAtPosition(gridView.getPositionForView(view));

        Intent intent = new Intent(this, ActividadDetalle.class);
        intent.putExtra(ActividadDetalle.EXTRA_ARTICULO, item.getId());
        intent.putExtra(ActividadDetalle.EXTRA_MARCA, item.getIdMarca());
        intent.putExtra(ActividadDetalle.EXTRA_CATEGORIA, item.getIdCategoria());

        if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
        {
            inactividad = new Inactividad();
            inactividad.execute(this);
        }
        startActivity(intent);
        finish();
    }
    */

    //atras
    public void atras(View view)
    {
        onBackPressed();
    }

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

    //Acción al pulsar en el icono del carrito
    public void verCarro(View view)
    {
        onBackPressed();
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
    }
    */

    @Override
    public void onBackPressed()
    {
        /*
        if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
        {
            inactividad = new Inactividad();
            inactividad.execute(this);
        }
        setResult(RESULT_CANCELED, null);
        */
        super.onBackPressed();
        finish();
    }
}

