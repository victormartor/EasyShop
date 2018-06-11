package victor.easyshop.clases;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AlertDialog;

import victor.easyshop.R;
import victor.easyshop.activities.MarcasActivity;
import victor.easyshop.data.EasyShop;

/**
 * Autor: Víctor Martín Torres - 30/8/17
 *
 * Clase Inactividad: si la aplicación no se usa después de un tiempo establecido se vacia el carrito y
 * se vuelve al menu principal
 */
public class Inactividad extends AsyncTask<Context, Context, Void>
{
    private int i;
    private Context mContext;

    @Override
    protected Void doInBackground(Context... params)
    {
        mContext = params[0];
        int sec = 60;
        for(i=0; i<sec; i++)
        {
            try{ Thread.sleep(1000); }catch (InterruptedException e){e.printStackTrace();}
        }

        return null;
    }

    @Override
    public void onProgressUpdate(Context... context)
    {
        mContext = context[0];
        i = 0;
    }

    @Override
    protected void onPreExecute(){
    }

    @Override
    protected void onPostExecute(Void values)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setNegativeButton(R.string.cancelar, new AlertDialog.OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Inactividad inactividad = new Inactividad();
                ((EasyShop)mContext.getApplicationContext()).setInactividad(inactividad);
                inactividad.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,mContext);
            }
        });
        builder.setTitle(R.string.inactividad);
        builder.setMessage(R.string.texto_inactividad);
        mAlertDialog = builder.create();
        mAlertDialog.show();
        mAlertDialog.setCancelable(false);
        // dismiss dialog in TIME_OUT ms
        mHandler.sendEmptyMessageDelayed(MSG_DISMISS_DIALOG, TIME_OUT);
    }

    @Override
    protected void onCancelled() {
    }

    public void setContext(Context context){mContext = context;}

    ////////////////////////////////////////////////////
    private static final int TIME_OUT = 10000;

    private static final int MSG_DISMISS_DIALOG = 0;

    private AlertDialog mAlertDialog;


    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case MSG_DISMISS_DIALOG:
                    if (mAlertDialog != null && mAlertDialog.isShowing())
                    {
                        Carrito carrito = ((EasyShop)mContext.getApplicationContext()).getCarrito();
                        if(carrito != null) carrito.vaciarCarro();
                        Intent intent = new Intent(mContext, MarcasActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        mContext.startActivity(intent);
                        mAlertDialog.dismiss();
                    }
                    break;

                default:
                    break;
            }
        }
    };
}
