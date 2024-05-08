package com.fabiansuarez.tiendavirtual;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<SlideModel> imageList = new ArrayList<>();
    private ImageSlider imageSlider;
    private Toolbar topAppBar;
    private ImageView ivProfile;
    private ArrayList<Product> productsList = new ArrayList<>();
    private User userSession = new User();
    private ImageView userImageProfil;

    private RecyclerView rvProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFakeData();
        userImageProfil = findViewById(R.id.iv_profile_home_user);
        Picasso.get().load(userSession.getUrlImageProfil()).into(userImageProfil);
        rvProducts = findViewById(R.id.rv_products);
        imageSlider = findViewById(R.id.image_slider_home);

        imageList.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4FkmvIrIJoaTQ6AXoIIMV3OLcuii_vahisR2bR8UtKw&s" , ScaleTypes.FIT));
        imageList.add(new SlideModel("https://www.movilexito.com/sites/default/files/2021-08/V3_Paqu_LP_Movil_Recibe50_770x315_030821.jpg", ScaleTypes.FIT));
        imageList.add(new SlideModel("https://vtex-resources.s3.amazonaws.com/landings/2024/marzo/landing-dia-redondo/images/mobile/banner.jpg",  ScaleTypes.FIT));
        imageList.add(new SlideModel("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4FkmvIrIJoaTQ6AXoIIMV3OLcuii_vahisR2bR8UtKw&s", "Aproveche .",null));
        imageList.add(new SlideModel("https://www.movilexito.com/sites/default/files/2021-08/V3_Paqu_LP_Movil_Recibe50_770x315_030821.jpg", "Elephants and tigers may become extinct.",null));
        imageList.add(new SlideModel("https://vtex-resources.s3.amazonaws.com/landings/2024/marzo/landing-dia-redondo/images/mobile/banner.jpg", "And people do that.",null));
        imageSlider.setImageList(imageList,ScaleTypes.FIT);
        AdapterProduct adapterProduct = new AdapterProduct(productsList);
        adapterProduct.setOnItemClickListener(new AdapterProduct.OnItemClickListener() {
            @Override
            public void onItemClick(Product myProduct, int position) {
                Intent myIntent = new Intent(MainActivity.this, DetailProductActivity.class);
                myIntent.putExtra("product", myProduct);
                startActivity(myIntent);
            }
        });
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
                }else if(item.getItemId() == R.id.item_category){
                    startActivity(new Intent(MainActivity.this, CategoryActivity.class));
                    return true;
                }else if (item.getItemId() == R.id.log_out){
                    cerrarSesion();
                }
                return false;
            }
        });


    }


    private void loadFakeData() {

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

        //Cargue de informacion de session
        userSession.setName("Fabian");
        userSession.setEmail("fsuarez120@unab.edu.co");
        userSession.setPassword("qazwsx");
        userSession.setPhone("3229243184");
        userSession.setUrlImageProfil("https://www.dzoom.org.es/wp-content/uploads/2020/02/portada-foto-perfil-redes-sociales-consejos.jpg");

    }


    public void cerrarSesion() {
        SharedPreferences miPreferencias = getSharedPreferences("tienda_app", MODE_PRIVATE);
        SharedPreferences.Editor myEditor = miPreferencias.edit();
        myEditor.clear();
        myEditor.apply();

        // Crear una nueva intención para iniciar la actividad de inicio de sesión
        Intent intent = new Intent(this, LoginActivity.class);

        // Agregar banderas para limpiar la pila de actividades y crear una nueva tarea
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Iniciar la actividad de inicio de sesión y cerrar la actividad actual
        startActivity(intent);
        finish();
    }

}