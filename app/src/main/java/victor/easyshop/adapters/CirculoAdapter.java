package victor.easyshop.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import victor.easyshop.R;

/**
 * Adaptador para personalizar la lista de circulos en la actividad detalle
 * @author Víctor Martín Torres
 */
public class CirculoAdapter
        extends RecyclerView.Adapter<CirculoAdapter.CirculoAdapterViewHolder>
{
    private int n;
    private static int creado;

    /**
     * Clase ViewHolder
     */
    static class CirculoAdapterViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imagen;

        CirculoAdapterViewHolder(View itemView)
        {
            super(itemView);

            imagen = itemView.findViewById(R.id.imagen);
        }

        void bindCirculo(int pos)
        {

            imagen.setImageResource(R.drawable.ic_circle_off);


            if(creado == 0 && pos == 0)
            {
                imagen.setImageResource(R.drawable.ic_circle_on);
                creado = 1;
            }
        }
    }

    /**
     * Constructor
     * @param n número de elementos que debe tener la lista
     */
    public CirculoAdapter(int n) {
        this.n = n;
        creado = 0;
    }

    /**
     * Crear el ViewHolder
     * @param viewGroup grupo de viewholders
     * @param viewType tipo de view
     * @return Devuelve el ViewHolder creado
     */
    @Override
    public CirculoAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_circulos, viewGroup, false);

        return new CirculoAdapterViewHolder(itemView);
    }

    /**
     * Recargar el viewholder cuando aparezca en pantalla
     * @param viewHolder el viewholder
     * @param pos la posicion
     */
    @Override
    public void onBindViewHolder(CirculoAdapterViewHolder viewHolder, int pos)
    {
        viewHolder.bindCirculo(pos);
    }

    /**
     * El número de elementos de la lista
     * @return tamaño de la lista
     */
    @Override
    public int getItemCount() {
        return n;
    }
}
