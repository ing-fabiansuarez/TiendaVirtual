package com.fabiansuarez.tiendavirtual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.carousel.CarouselLayoutManager;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
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
    private AdapterProduct adapterProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
        adapterProduct = new AdapterProduct(productsList);
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

    @Override
    protected void onResume() {
        super.onResume();
        loadFakeData();
    }

    private void loadFakeData() {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Products").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    productsList.clear();
                    for(DocumentSnapshot documentSnapshot: task.getResult()){
                        Product trappedObject = documentSnapshot.toObject(Product.class);
                        productsList.add(trappedObject);
                    }
                    adapterProduct.setListadoObjetos(productsList);
                    Log.i("misproductos",productsList.toString());
                }else{
                    Toast.makeText(MainActivity.this, "ERROR CARGANDO LOS PRODUCTOS", Toast.LENGTH_SHORT).show();
                }
            }
        });


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