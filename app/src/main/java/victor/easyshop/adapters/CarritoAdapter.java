package victor.easyshop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import victor.easyshop.R;
import victor.easyshop.clases.Carrito;

/**
 * Adaptador para personalizar la vista de los articulos en la ActividadCarrito
 * @author Víctor Martín Torres
 */
public class CarritoAdapter extends BaseAdapter
{
    private Context context;
    private ArrayList<Carrito.ArticuloCarrito> articulos;

    /**
     * Constructor a partir de un array de elementos
     * @param context el contexto donde se va a desplegar el grid
     * @param articulos lista de elementos
     */
    public CarritoAdapter(Context context, ArrayList<Carrito.ArticuloCarrito> articulos)
    {
        this.context = context;
        this.articulos = articulos;
    }

    /**
     * Devuelve el número de elementos de la lista
     * @return el tamaño de la lista
     */
    @Override
    public int getCount()
    {
        return articulos.size();
    }

    /**
     * Devuelve un elemento de la lista
     * @param position la posicion del elemento
     * @return el elemento en esa posicion
     */
    @Override
    public Carrito.ArticuloCarrito getItem(int position)
    {
        return articulos.get(position);
    }

    /**
     * Devuelve el Id de un elemento de la lista
     * @param position la posicion del elemento
     * @return el Id del elemento en esa posicion
     */
    @Override
    public long getItemId(int position)
    {
        Carrito.ArticuloCarrito item = getItem(position);
        String id = String.format("%d",item.getId_Articulo())+String.format("%d",item.getId_Color())
                +String.format("%d",item.getId_Talla());
        return Long.parseLong(id);
    }

    /**
     * Cargar la vista de ese elemento en el grid
     * @param position la posicion del elemento
     * @param view la vista donde se va a cargar
     * @param viewGroup el grupo de vistas
     * @return La vista ya cargada
     */
    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        if (view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_carrito, viewGroup, false);
        }

        ImageView imagenArticulo = view.findViewById(R.id.imagen);
        TextView textoMarca = view.findViewById(R.id.marca);
        TextView textoDescripcion = view.findViewById(R.id.descripcion);
        TextView textoPrecio = view.findViewById(R.id.precio_item_carro);

        final Carrito.ArticuloCarrito item = getItem(position);

        if(item.getBitmap() != null)
        {
            imagenArticulo.setImageBitmap(item.getBitmap());
        }
        else
        {
            imagenArticulo.setImageResource(R.drawable.ic_file);
            imagenArticulo.setScaleType(ImageView.ScaleType.CENTER);
        }

        String talla = context.getString(R.string.talla);
        String color = context.getString(R.string.color);

        String texto = item.getNombre()+"\n"+talla+" "+item.getTalla()+"\n"+color+" "+item.getColor();
        textoMarca.setText(item.getMarca());
        textoDescripcion.setText(texto);

        String precio = String.format("%.2f",item.getPVP())+" €";
        textoPrecio.setText(precio);

        return view;
    }
}

