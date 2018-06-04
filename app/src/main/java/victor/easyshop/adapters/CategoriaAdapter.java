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

/*
 * Autor: Víctor Martín Torres - 30/8/17
 *
 * Clase AdaptadorDeMarcas: adaptador para personalizar la vista de las marcas en la ActividadPrincipal
 */
public class CategoriaAdapter extends BaseAdapter
{
    private Context _context;
    private ArrayList<Categoria> _aCategorias;

    public CategoriaAdapter(Context context, ArrayList<Categoria> aCategorias) throws IOException
    {
        _context = context;
        _aCategorias = aCategorias;
        for(Categoria categoria : _aCategorias)
            categoria.getImagen().cargarImagen();
    }

    @Override
    public int getCount()
    {
        return _aCategorias.size();
    }

    @Override
    public Categoria getItem(int index)
    {
        return _aCategorias.get(index);
    }

    @Override
    public long getItemId(int index)
    {
        return getItem(index).getId();
    }

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
