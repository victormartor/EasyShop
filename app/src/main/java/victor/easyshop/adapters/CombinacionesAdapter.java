package victor.easyshop.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;

import victor.easyshop.R;
import victor.easyshop.clases.Articulo;

/*
 * Autor: Víctor Martín Torres - 30/8/17
 *
 * Clase AdaptadorDeCombinaciones: adaptador para personalizar la vista de las combinaciones en la ActividadCombinaciones
 */
public class CombinacionesAdapter
        extends RecyclerView.Adapter<CombinacionesAdapter.CombinacionesViewHolder>
        implements View.OnClickListener
{
    private View.OnClickListener listener;
    private ArrayList<Articulo> datos;

    static class CombinacionesViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imagen;

        CombinacionesViewHolder(View itemView)
        {
            super(itemView);

            imagen = itemView.findViewById(R.id.imagen);
        }

        void bindArticulo(Articulo a)
        {
            if(a.getImagenBitmap() != null)
            {
                imagen.setImageBitmap(a.getImagenBitmap());
            }
            else
            {
                imagen.setImageResource(R.drawable.ic_file);
                imagen.setScaleType(ImageView.ScaleType.CENTER);
            }
        }
    }

    public CombinacionesAdapter(ArrayList<Articulo> datos) throws IOException {
        this.datos = datos;
        for(Articulo articulo : datos)
            articulo.getImagen().cargarImagen();
    }

    @Override
    public CombinacionesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_combinaciones, viewGroup, false);

        itemView.setOnClickListener(this);

        return new CombinacionesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CombinacionesViewHolder viewHolder, int pos)
    {
        Articulo item = datos.get(pos);

        viewHolder.bindArticulo(item);
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

    public Articulo getItem(int position) {
        return datos.get(position);
    }
}
