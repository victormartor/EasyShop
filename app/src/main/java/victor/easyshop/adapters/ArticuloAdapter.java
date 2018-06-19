package victor.easyshop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import victor.easyshop.R;
import victor.easyshop.clases.Articulo;

/**
 * Adaptador para personalizar la vista de los articulos en la ActividadArticulos
 * @author Víctor Martín Torres
 */
public class ArticuloAdapter extends BaseAdapter
{
    private Context _context;
    private ArrayList<Articulo> _aArticulos;

    /**
     * Constructor a partir de un array de elementos
     * @param context el contexto donde se va a desplegar el grid
     * @param aArticulos lista de elementos
     * @throws IOException error al cargar las imágenes
     */
    public ArticuloAdapter(Context context, ArrayList<Articulo> aArticulos) throws IOException
    {
        _context = context;
        _aArticulos = aArticulos;
        for(Articulo articulo : _aArticulos)
            articulo.getImagen().cargarImagen();
    }

    /**
     * Devuelve el número de elementos de la lista
     * @return el tamaño de la lista
     */
    @Override
    public int getCount()
    {
        return _aArticulos.size();
    }

    /**
     * Devuelve un elemento de la lista
     * @param index la posicion del elemento
     * @return el elemento en esa posicion
     */
    @Override
    public Articulo getItem(int index)
    {
        return _aArticulos.get(index);
    }

    /**
     * Devuelve el Id de un elemento de la lista
     * @param index la posicion del elemento
     * @return el Id del elemento en esa posicion
     */
    @Override
    public long getItemId(int index)
    {
        return _aArticulos.get(index).getId();
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
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item, viewGroup, false);
        }

        ImageView imagenArticulo = view.findViewById(R.id.imagen);
        TextView textoPrecio = view.findViewById(R.id.texto);

        Articulo item = getItem(position);
        if(item.getImagenBitmap() != null)
        {
            imagenArticulo.setImageBitmap(item.getImagenBitmap());
        }
        else
        {
            imagenArticulo.setImageResource(R.drawable.ic_file);
            imagenArticulo.setScaleType(ImageView.ScaleType.CENTER);
        }

        String texto = String.format("%.2f", item.getPVP())+" €";
        textoPrecio.setText(texto);

        return view;
    }
}
