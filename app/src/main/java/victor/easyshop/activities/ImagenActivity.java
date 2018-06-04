package victor.easyshop.activities;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import victor.easyshop.R;

/*
 * Autor: Víctor Martín Torres - 30/8/17
 *
 * Clase ActividadMasDetalle: muestra la imagen del artículo en pantalla completa pudiéndose hacer zoom en ella.
 * También tiene un botón para volver atrás.
 */
public class ImagenActivity extends AppCompatActivity
{
    public static final String EXTRA_IMAGEN = "imagen";
    private Bitmap imagenBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);

        String URL = getIntent().getStringExtra(EXTRA_IMAGEN);

        new CargaImagenHD().execute(URL);
        //nuevaTarea.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,URL);

        //Inactividad
        //if(inactividad != null) inactividad.onProgressUpdate(this);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //MÉTODOS PROPIOS
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private class CargaImagenHD extends AsyncTask<String, Void, Void>
    {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            pDialog = new ProgressDialog(ImagenActivity.this);
            pDialog.setMessage(getString(R.string.cargar_imagen));
            pDialog.setCancelable(false);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(String... params)
        {
            String url = params[0];
            descargarImagen(url);
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
            pDialog.dismiss();

            ImageView imgImagen = findViewById(R.id.imagen_extendida);

            if(imagenBitmap != null)
            {
                imgImagen.setImageBitmap(imagenBitmap);
                Toast t = Toast.makeText(ImagenActivity.this,R.string.zoom,Toast.LENGTH_LONG);
                t.show();
            }
            else
            {
                findViewById(R.id.imagen_extendida).setVisibility(View.INVISIBLE);
                findViewById(R.id.button_recargar).setVisibility(View.VISIBLE);
            }
        }

        private void descargarImagen (String imageHttpAddress)
        {
            URL imageUrl;
            try{
                imageUrl = new URL(imageHttpAddress);
                HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                conn.connect();
                imagenBitmap = BitmapFactory.decodeStream(conn.getInputStream());
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }

    //Cargar datos de nuevo
    public void recargar(View view){
        findViewById(R.id.imagen_extendida).setVisibility(View.VISIBLE);
        findViewById(R.id.button_recargar).setVisibility(View.INVISIBLE);
        new CargaImagenHD().execute(getIntent().getStringExtra(EXTRA_IMAGEN));
    }

    //Acción al pulsar el botón de volver
    public void volver(View view)
    {
        /*
        if(inactividad == null || inactividad.getStatus() == AsyncTask.Status.FINISHED)
        {
            inactividad = new Inactividad();
            inactividad.execute(this);
        }
        */
        if(imagenBitmap!= null) imagenBitmap.recycle();
        //setResult(RESULT_CANCELED, null);
        onBackPressed();
        finish();
    }
}
