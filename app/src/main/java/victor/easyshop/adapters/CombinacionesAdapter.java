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

/**
 * Adaptador para personalizar la vista de las combinaciones en la ActividadCombinaciones
 * @author Víctor Martín Torres
 */
public class CombinacionesAdapter
        extends RecyclerView.Adapter<CombinacionesAdapter.CombinacionesViewHolder>
        implements View.OnClickListener
{
    private View.OnClickListener listener;
    private ArrayList<Articulo> datos;

    /**
     * Clase ViewHolder
     */
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

    /**
     * Constructor
     * @param datos Array con los datos para la lista
     * @throws IOException Error al cargar las imagenes
     */
    public CombinacionesAdapter(ArrayList<Articulo> datos) throws IOException
    {
        this.datos = datos;
        for(Articulo articulo : datos)
            articulo.getImagen().cargarImagen();
    }

    /**
     * Crear el ViewHolder
     * @param viewGroup grupo de viewholders
     * @param viewType tipo de view
     * @return Devuelve el ViewHolder creado
     */
    @Override
    public CombinacionesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_combinaciones, viewGroup, false);

        itemView.setOnClickListener(this);

        return new CombinacionesViewHolder(itemView);
    }

    /**
     * Recargar el viewholder cuando aparezca en pantalla
     * @param viewHolder el viewholder
     * @param pos la posicion
     */
    @Override
    public void onBindViewHolder(CombinacionesViewHolder viewHolder, int pos)
    {
        Articulo item = datos.get(pos);

        viewHolder.bindArticulo(item);
    }

    /**
     * El número de elementos de la lista
     * @return tamaño de la lista
     */
    @Override
    public int getItemCount() {
        return datos.size();
    }

    /**
     * Escuchar acción hacia el elemento
     * @param listener encargado de escuchar la acción
     */
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    /**
     * Acción al pulsar en un elemento
     * @param view la vista
     */
    @Override
    public void onClick(View view)
    {
        if(listener != null)
            listener.onClick(view);
    }

    /**
     * Obtener un elemento de la lista
     * @param position la posicion del elemento
     * @return el elemento
     */
    public Articulo getItem(int position) {
        return datos.get(position);
    }
}
