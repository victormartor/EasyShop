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
import victor.easyshop.clases.Marca;

/**
 * Adaptador para personalizar la vista de las marcas en la ActividadPrincipal
 * @author Víctor Martín Torres
 */
public class MarcaAdapter extends BaseAdapter
{
    private Context _context;
    private ArrayList<Marca> _aMarcas;

    /**
     * Constructor a partir de un array de elementos
     * @param context el contexto donde se va a desplegar el grid
     * @param aMarcas lista de elementos
     * @throws IOException error al cargar las imágenes
     */
    public MarcaAdapter(Context context, ArrayList<Marca> aMarcas) throws IOException
    {
        _context = context;
        _aMarcas = aMarcas;
        for(Marca marca : _aMarcas)
            marca.getImagen().cargarImagenHD();
    }

    /**
     * Devuelve el número de elementos de la lista
     * @return el tamaño de la lista
     */
    @Override
    public int getCount()
    {
        return _aMarcas.size();
    }

    /**
     * Devuelve un elemento de la lista
     * @param index la posicion del elemento
     * @return el elemento en esa posicion
     */
    @Override
    public Marca getItem(int index)
    {
        return _aMarcas.get(index);
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

        ImageView imagenMarca = view.findViewById(R.id.imagen);
        TextView textoNombre = view.findViewById(R.id.texto);
        Marca item = getItem(position);

        if(item.getImagenBitmap() != null)
        {
            imagenMarca.setImageBitmap(item.getImagenBitmap());
        }
        else
        {
            imagenMarca.setImageResource(R.drawable.ic_file);
            imagenMarca.setScaleType(ImageView.ScaleType.CENTER);
        }
        textoNombre.setVisibility(View.INVISIBLE);

        return view;
    }
}
