package com.fabiansuarez.tiendavirtual;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    private ArrayList<Category> categoriesList = new ArrayList<>();
    private RecyclerView rvListCategories;
    private ExtendedFloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        rvListCategories = findViewById(R.id.rv_categories_list);
        fab = findViewById(R.id.fab_add);

        loadFakeDataCategorias();
        AdapterCategory myAdapter = new AdapterCategory(categoriesList);
        rvListCategories.setAdapter(myAdapter);
        rvListCategories.setLayoutManager(new LinearLayoutManager(this));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryActivity.this, FormCategoryActivity.class));
            }
        });

    }

    private void loadFakeDataCategorias() {
        categoriesList.add(new Category("Ropa y Moda", "#FF0000", "https://cdn-icons-png.flaticon.com/512/616/616682.png"));
        categoriesList.add(new Category("Electrónica", "#00FF00", "https://cdn-icons-png.flaticon.com/512/1044/1044920.png"));
        categoriesList.add(new Category("Hogar y Jardín", "#0000FF", "https://cdn-icons-png.flaticon.com/512/2558/2558072.png"));
        categoriesList.add(new Category("Salud y Belleza", "#FFFF00", "https://cdn-icons-png.flaticon.com/512/1087/1087975.png"));
        categoriesList.add(new Category("Deportes y Aire libre", "#FF00FF", "https://cdn-icons-png.flaticon.com/512/2154/2154685.png"));
    }
}