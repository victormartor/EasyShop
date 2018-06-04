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

/*
 * Autor: Víctor Martín Torres - 30/8/17
 *
 * Clase AdaptadorDeMarcas: adaptador para personalizar la vista de las marcas en la ActividadPrincipal
 */
public class MarcaAdapter extends BaseAdapter
{
    private Context _context;
    private ArrayList<Marca> _aMarcas;

    public MarcaAdapter(Context context, ArrayList<Marca> aMarcas) throws IOException
    {
        _context = context;
        _aMarcas = aMarcas;
        for(Marca marca : _aMarcas)
            marca.getImagen().cargarImagenHD();
    }

    @Override
    public int getCount()
    {
        return _aMarcas.size();
    }

    @Override
    public Marca getItem(int index)
    {
        return _aMarcas.get(index);
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
