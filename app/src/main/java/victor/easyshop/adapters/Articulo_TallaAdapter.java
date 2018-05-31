package victor.easyshop.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import victor.easyshop.R;
import victor.easyshop.clases.Articulo_Talla;

/*
 * Autor: Víctor Martín Torres - 18/9/17
 *
 * Clase AdaptadorDeTallas: adaptador para personalizar la vista de las tallas en la ActividadDetalle
 */
public class Articulo_TallaAdapter
        extends RecyclerView.Adapter<Articulo_TallaAdapter.Articulo_TallaAdapterViewHolder>
        implements View.OnClickListener
{
    private View.OnClickListener listener;
    private ArrayList<Articulo_Talla> datos;
    private static int creado;
    private static boolean _bTalla_Es_Numero;

    static class Articulo_TallaAdapterViewHolder extends RecyclerView.ViewHolder
    {
        private View linea_seleccion;

        Articulo_TallaAdapterViewHolder(View itemView)
        {
            super(itemView);
            linea_seleccion = itemView.findViewById(R.id.seleccionado);
        }

        void bindTalla(Articulo_Talla talla, int pos)
        {
            if (!_bTalla_Es_Numero)
            {
                TextView texto = itemView.findViewById(R.id.texto_talla);
                texto.setText(talla.getNombre());

                if(creado == 0 && pos == 0)
                {
                    linea_seleccion.setVisibility(View.VISIBLE);
                    creado = 1;
                }
            }
            else
            {
                TextView numero_talla = itemView.findViewById(R.id.numero_talla);
                numero_talla.setText(talla.getNombre());
            }

        }
    }

    public Articulo_TallaAdapter(ArrayList<Articulo_Talla> datos, boolean bTalla_Es_Numero) {

        this.datos = datos;
        creado = 0;
        _bTalla_Es_Numero = bTalla_Es_Numero;
    }

    @Override
    public Articulo_TallaAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View itemView;

        if (!_bTalla_Es_Numero)
        {
            itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_item_horizontal, viewGroup, false);
        }
        else
        {
            itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.list_item_talla_numero, viewGroup, false);
        }

        itemView.setOnClickListener(this);

        return new Articulo_TallaAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Articulo_TallaAdapterViewHolder viewHolder, int pos)
    {
        Articulo_Talla item = datos.get(pos);

        viewHolder.bindTalla(item, pos);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view)
    {
        if(listener != null)
            listener.onClick(view);
    }

    public Articulo_Talla getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int index){
        return datos.get(index).getId();
    }
}
