package com.fabiansuarez.tiendavirtual;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {

    private ArrayList<Product> listadoObjetos;

    public AdapterProduct(ArrayList<Product> listadoObjetos) {
        this.listadoObjetos = listadoObjetos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_produt, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.associate(listadoObjetos.get(position));
    }

    @Override
    public int getItemCount() {
        return listadoObjetos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameProduct, priceProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameProduct = itemView.findViewById(R.id.tv_name_product);
            priceProduct = itemView.findViewById(R.id.tv_price_product);
        }

        public void associate(Product myProduct) {
            nameProduct.setText(myProduct.getName());
            priceProduct.setText(myProduct.getPrice().toString());
        }
    }
}
