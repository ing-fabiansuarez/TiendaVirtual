package com.fabiansuarez.tiendavirtual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar topAppBar;
    private ImageView ivProfile;
    private ArrayList<Product> productsList = new ArrayList<>();
    private User userSession = new User();
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rvProducts.setLayoutManager(gridLayoutManager);

        topAppBar = findViewById(R.id.topAppBar);
        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Click en Account", Toast.LENGTH_SHORT).show();
            }
        });

        topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.add_product) {
                    startActivity(new Intent(MainActivity.this, FormProductActivity.class));
                    return true;
                }
                return false;
            }
        });


    }


    private void loadFakeData() {

        userSession.setName("Fabian Suarez");
        userSession.setUrlImageProfile("https://img.freepik.com/fotos-premium/retrato-hombre-negocios-expresion-cara-seria-fondo-estudio-espacio-copia-bengala-persona-corporativa-enfoque-pensamiento-duda-mirada-facial-dilema-o-concentracion_590464-84924.jpg");

        Product product1 = new Product("Laptop", "Potente laptop para trabajo y entretenimiento", 1200.0, "https://http2.mlstatic.com/D_NQ_NP_793921-MLM50274201387_062022-O.webp");
        Product product2 = new Product("Teléfono", "Teléfono inteligente de última generación", 800.0, "https://exitocol.vtexassets.com/arquivos/ids/19479809/Celular-TECNO-MOBILE-Tecno-Spark-10PRO-256-GB-Blanco-3371669_c.jpg?v=638275963829800000");
        Product product3 = new Product("Televisor", "Televisor LED de 55 pulgadas con resolución 4K", 1500.0, "https://www.shutterstock.com/image-illustration/perspective-view-television-tv-computer-260nw-671716426.jpg");
        Product product4 = new Product("Auriculares", "Auriculares inalámbricos con cancelación de ruido", 200.0, "https://static.vecteezy.com/system/resources/previews/009/302/605/non_2x/headphones-clipart-design-illustartion-free-png.png");
        Product product5 = new Product("Tablet", "Tablet ligera y compacta para leer y navegar por Internet", 300.0, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTh3Y3GoUjPCzopgn4KNTArm6VhSGWxqu9JSw&usqp=CAU");

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