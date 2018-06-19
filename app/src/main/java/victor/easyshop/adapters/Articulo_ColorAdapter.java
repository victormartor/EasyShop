package victor.easyshop.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;

import victor.easyshop.R;
import victor.easyshop.clases.Articulo_Color;
import victor.easyshop.clases.Imagen;

/**
 * Adaptador para personalizar la vista de los colores en la ActividadDetalle
 * @author Víctor Martín Torres
 */
public class Articulo_ColorAdapter
        extends RecyclerView.Adapter<Articulo_ColorAdapter.Articulo_ColorAdapterViewHolder>
        implements View.OnClickListener
{
    private View.OnClickListener listener;
    private ArrayList<Articulo_Color> datos;
    private static int creado;

    /**
     * Clase ViewHolder
     */
    static class Articulo_ColorAdapterViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imagen;
        private View linea_seleccion;

        Articulo_ColorAdapterViewHolder(View itemView)
        {
            super(itemView);

            imagen = itemView.findViewById(R.id.imagen);
            itemView.findViewById(R.id.texto_talla).setVisibility(View.INVISIBLE);
            linea_seleccion = itemView.findViewById(R.id.seleccionado);
        }

        void bindColor(Articulo_Color c, int pos)
        {
            if(c.getImagenes().get(0).getBitmap() != null)
            {
                imagen.setImageBitmap(c.getImagenes().get(0).getBitmap());
            }
            else
            {
                imagen.setImageResource(R.drawable.ic_file);
                imagen.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }

            if(creado == 0 && pos == 0)
            {
                linea_seleccion.setVisibility(View.VISIBLE);
                creado = 1;
            }
        }
    }

    /**
     * Constructor
     * @param datos Array con los datos para la lista
     * @throws IOException Error al cargar las imagenes
     */
    public Articulo_ColorAdapter(ArrayList<Articulo_Color> datos) throws IOException
    {
        int n = datos.size();
        this.datos = datos;
        for(Articulo_Color a_c : datos)
            for(Imagen imagen : a_c.getImagenes())
                imagen.cargarImagen();

        creado = 0;
    }

    /**
     * Crear el ViewHolder
     * @param viewGroup grupo de viewholders
     * @param viewType tipo de view
     * @return Devuelve el ViewHolder creado
     */
    @Override
    public Articulo_ColorAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_horizontal, viewGroup, false);

        itemView.setOnClickListener(this);

        return new Articulo_ColorAdapterViewHolder(itemView);
    }

    /**
     * Recargar el viewholder cuando aparezca en pantalla
     * @param viewHolder el viewholder
     * @param pos la posicion
     */
    @Override
    public void onBindViewHolder(Articulo_ColorAdapterViewHolder viewHolder, int pos)
    {
        Articulo_Color item = datos.get(pos);

        viewHolder.bindColor(item, pos);
    }

    /**
     * El número de elementos de la lista
     * @return tamaño de la lista
     */
    @Override
    public int getItemCount()
    {
        return datos.size();
    }

    /**
     * Escuchar acción hacia el elemento
     * @param listener encargado de escuchar la acción
     */
    public void setOnClickListener(View.OnClickListener listener)
    {
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
    public Articulo_Color getItem(int position)
    {
        return datos.get(position);
    }

    /**
     * Obtener el id de un elemento de la lista
     * @param index la posicion
     * @return el id del elemento en esa posicion
     */
    @Override
    public long getItemId(int index)
    {
        return datos.get(index).getId();
    }
}
