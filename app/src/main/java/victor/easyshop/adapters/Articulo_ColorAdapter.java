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

/*
 * Autor: Víctor Martín Torres - 18/9/17
 *
 * Clase AdaptadorDeColores: adaptador para personalizar la vista de los colores en la ActividadDetalle
 */
public class Articulo_ColorAdapter
        extends RecyclerView.Adapter<Articulo_ColorAdapter.Articulo_ColorAdapterViewHolder>
        implements View.OnClickListener
{
    private View.OnClickListener listener;
    private ArrayList<Articulo_Color> datos;
    private static int creado;

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

    public Articulo_ColorAdapter(ArrayList<Articulo_Color> datos) throws IOException{
        int n = datos.size();
        this.datos = datos;
        for(Articulo_Color a_c : datos)
            for(Imagen imagen : a_c.getImagenes())
                imagen.cargarImagen();

        creado = 0;
    }

    @Override
    public Articulo_ColorAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_horizontal, viewGroup, false);

        itemView.setOnClickListener(this);

        return new Articulo_ColorAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Articulo_ColorAdapterViewHolder viewHolder, int pos)
    {
        Articulo_Color item = datos.get(pos);

        viewHolder.bindColor(item, pos);
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

    public Articulo_Color getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int index){
        return datos.get(index).getId();
    }
}
