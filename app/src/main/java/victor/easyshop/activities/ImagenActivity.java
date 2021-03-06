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
import victor.easyshop.clases.Inactividad;
import victor.easyshop.data.EasyShop;

/**
 * Muestra la imagen del artículo en pantalla completa pudiéndose hacer
 * zoom en ella.
 * También tiene un botón para volver atrás.
 *
 * @author Víctor Martín Torres
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

        //Inactividad
        reiniciar_inactividad();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //MÉTODOS PROPIOS
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * cargar la imagen en buena calidad
     */
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
                Toast t = Toast.makeText(ImagenActivity.this,
                        R.string.zoom,Toast.LENGTH_LONG);
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

    /**
     * Inactividad
     */
    public void reiniciar_inactividad()
    {
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
        findViewById(R.id.imagen_extendida).setVisibility(View.VISIBLE);
        findViewById(R.id.button_recargar).setVisibility(View.INVISIBLE);
        new CargaImagenHD().execute(getIntent().getStringExtra(EXTRA_IMAGEN));
    }

    /**
     * Acción al pulsar el botón de volver
     * @param view la vista
     */
    public void volver(View view)
    {
        if(imagenBitmap!= null) imagenBitmap.recycle();
        onBackPressed();
        finish();
    }
}
