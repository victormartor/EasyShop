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
import victor.easyshop.clases.Articulo;

/*
 * Autor: Víctor Martín Torres - 30/8/17
 *
 * Clase AdaptadorDeArticulo: adaptador para personalizar la vista de los articulos en la ActividadArticulos
 */
public class ArticuloAdapter extends BaseAdapter
{
    private Context _context;
    private ArrayList<Articulo> _aArticulos;

    public ArticuloAdapter(Context context, ArrayList<Articulo> aArticulos)
    {
        _context = context;
        _aArticulos = aArticulos;
        for(Articulo articulo : _aArticulos)
            articulo.getImagen().cargarImagen();
    }

    @Override
    public int getCount()
    {
        return _aArticulos.size();
    }

    @Override
    public Articulo getItem(int index)
    {
        return _aArticulos.get(index);
    }

    @Override
    public long getItemId(int index)
    {
        return _aArticulos.get(index).getId();
    }

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
