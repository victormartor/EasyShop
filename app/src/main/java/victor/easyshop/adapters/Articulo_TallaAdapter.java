package victor.easyshop.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import victor.easyshop.R;
import victor.easyshop.clases.Articulo_Talla;

/**
 * Adaptador para personalizar la vista de las tallas en la ActividadDetalle
 * @author Víctor Martín Torres
 */
public class Articulo_TallaAdapter
        extends RecyclerView.Adapter<Articulo_TallaAdapter.Articulo_TallaAdapterViewHolder>
        implements View.OnClickListener
{
    private View.OnClickListener listener;
    private ArrayList<Articulo_Talla> datos;
    private static int creado;
    private static boolean _bTalla_Es_Numero;

    /**
     * Clase ViewHolder
     */
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

    /**
     * Constructor
     * @param datos Array con los datos para la lista
     * @param bTalla_Es_Numero Booleano para saber si la talla es un número o no
     */
    public Articulo_TallaAdapter(ArrayList<Articulo_Talla> datos, boolean bTalla_Es_Numero) {

        this.datos = datos;
        creado = 0;
        _bTalla_Es_Numero = bTalla_Es_Numero;
    }

    /**
     * Crear el ViewHolder
     * @param viewGroup grupo de viewholders
     * @param viewType tipo de view
     * @return Devuelve el ViewHolder creado
     */
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

    /**
     * Recargar el viewholder cuando aparezca en pantalla
     * @param viewHolder el viewholder
     * @param pos la posicion
     */
    @Override
    public void onBindViewHolder(Articulo_TallaAdapterViewHolder viewHolder, int pos)
    {
        Articulo_Talla item = datos.get(pos);

        viewHolder.bindTalla(item, pos);
    }

    /**
     * El número de elementos de la lista, si la talla es un número devuelve solo uno para usar
     * una lista diferente
     * @return tamaño de la lista o si es un número devuelve 1
     */
    @Override
    public int getItemCount()
    {
        if(!_bTalla_Es_Numero) return datos.size();
        else return 1;
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
    public Articulo_Talla getItem(int position) {
        return datos.get(position);
    }

    /**
     * Devuelve el número de elementos de la lista
     * @return tamaño del array
     */
    public int getArraySize() {return datos.size();}
}
