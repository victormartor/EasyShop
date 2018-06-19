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
import victor.easyshop.clases.Categoria;

/**
 * Adaptador para personalizar la vista de las marcas en la ActividadPrincipal
 * @author Víctor Martín Torres
 */
public class CategoriaAdapter extends BaseAdapter
{
    private Context _context;
    private ArrayList<Categoria> _aCategorias;

    /**
     * Constructor a partir de un array de elementos
     * @param context el contexto donde se va a desplegar el grid
     * @param aCategorias lista de elementos
     * @throws IOException error al cargar las imágenes
     */
    public CategoriaAdapter(Context context, ArrayList<Categoria> aCategorias) throws IOException
    {
        _context = context;
        _aCategorias = aCategorias;
        for(Categoria categoria : _aCategorias)
            categoria.getImagen().cargarImagen();
    }

    /**
     * Devuelve el número de elementos de la lista
     * @return el tamaño de la lista
     */
    @Override
    public int getCount()
    {
        return _aCategorias.size();
    }

    /**
     * Devuelve un elemento de la lista
     * @param index la posicion del elemento
     * @return el elemento en esa posicion
     */
    @Override
    public Categoria getItem(int index)
    {
        return _aCategorias.get(index);
    }

    /**
     * Devuelve el Id de un elemento de la lista
     * @param index la posicion del elemento
     * @return el Id del elemento en esa posicion
     */
    @Override
    public long getItemId(int index)
    {
        return getItem(index).getId();
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

        ImageView imagenCategoria = view.findViewById(R.id.imagen);
        TextView textoNombre = view.findViewById(R.id.texto);
        Categoria item = getItem(position);

        if(item.getImagenBitmap() != null)
        {
            imagenCategoria.setImageBitmap(item.getImagenBitmap());
        }
        else
        {
            imagenCategoria.setImageResource(R.drawable.ic_file);
            imagenCategoria.setScaleType(ImageView.ScaleType.CENTER);
        }

        textoNombre.setText(item.getNombre());

        return view;
    }
}
