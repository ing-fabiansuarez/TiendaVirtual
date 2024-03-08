package com.fabiansuarez.tiendavirtual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Product> productsList = new ArrayList<>();
    private RecyclerView rvProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFakeData();
        rvProducts = findViewById(R.id.rv_products);
        AdapterProduct adapterProduct = new AdapterProduct(productsList);
        rvProducts.setAdapter(adapterProduct);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        rvProducts.setLayoutManager(staggeredGridLayoutManager);
    }


    private void loadFakeData() {
        Product product1 = new Product("Laptop", "Potente laptop para trabajo y entretenimiento", 1200.0, "https://example.com/laptop.jpg");
        Product product2 = new Product("Teléfono", "Teléfono inteligente de última generación", 800.0, "https://example.com/phone.jpg");
        Product product3 = new Product("Televisor", "Televisor LED de 55 pulgadas con resolución 4K", 1500.0, "https://example.com/tv.jpg");
        Product product4 = new Product("Auriculares", "Auriculares inalámbricos con cancelación de ruido", 200.0, "https://example.com/headphones.jpg");
        Product product5 = new Product("Tablet", "Tablet ligera y compacta para leer y navegar por Internet", 300.0, "https://example.com/tablet.jpg");

        productsList.add(product1);
        productsList.add(product2);
        productsList.add(product3);
        productsList.add(product4);
        productsList.add(product5);
        productsList.add(product1);
        productsList.add(product2);
        productsList.add(product3);
        productsList.add(product4);
        productsList.add(product5);
        productsList.add(product1);
        productsList.add(product2);
        productsList.add(product3);
        productsList.add(product4);
        productsList.add(product5);
        productsList.add(product1);
        productsList.add(product2);
        productsList.add(product3);
        productsList.add(product4);
        productsList.add(product5);
        productsList.add(product1);
        productsList.add(product2);
        productsList.add(product3);
        productsList.add(product4);
        productsList.add(product5);
        productsList.add(product1);
        productsList.add(product2);
        productsList.add(product3);
        productsList.add(product4);
        productsList.add(product5);


    }
}