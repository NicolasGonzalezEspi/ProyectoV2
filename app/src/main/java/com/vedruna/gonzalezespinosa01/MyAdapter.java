package com.vedruna.gonzalezespinosa01;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Producto> productList;

    public MyAdapter(List<Producto> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Producto producto = productList.get(position);
        holder.bind(producto);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView codigoTextView, nombreTextView, descripcionTextView, precioTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            codigoTextView = itemView.findViewById(R.id.codigoTextView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            descripcionTextView = itemView.findViewById(R.id.descripcionTextView);
            precioTextView = itemView.findViewById(R.id.precioTextView);
        }

        public void bind(Producto producto) {
            if (producto != null) {
                codigoTextView.setText(String.valueOf(producto.getCodigo()));
                nombreTextView.setText(producto.getNombre());
                descripcionTextView.setText(producto.getDescripcion());
                precioTextView.setText(String.valueOf(producto.getPrecio()));
            }
        }
    }
}
