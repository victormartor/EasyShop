package victor.easyshop.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import victor.easyshop.R;

/*
 * Autor: Víctor Martín Torres - 18/9/17
 *
 * Clase AdaptadorDeCirculos: adaptador para personalizar la lista de circulos en la actividad detalle
 */
public class CirculoAdapter
        extends RecyclerView.Adapter<CirculoAdapter.CirculoAdapterViewHolder>
{
    private int n;
    private static int creado;

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

    public CirculoAdapter(int n) {
        this.n = n;
        creado = 0;
    }

    @Override
    public CirculoAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_circulos, viewGroup, false);

        return new CirculoAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CirculoAdapterViewHolder viewHolder, int pos)
    {
        viewHolder.bindCirculo(pos);
    }

    @Override
    public int getItemCount() {
        return n;
    }
}
