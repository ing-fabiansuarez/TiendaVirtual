package com.fabiansuarez.tiendavirtual;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    private ArrayList<Category> categoriesList = new ArrayList<>();
    private RecyclerView rvListCategories;
    private ExtendedFloatingActionButton fab;
    AdapterCategory myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        rvListCategories = findViewById(R.id.rv_categories_list);
        fab = findViewById(R.id.fab_add);

        myAdapter = new AdapterCategory(categoriesList);
        rvListCategories.setAdapter(myAdapter);
        rvListCategories.setLayoutManager(new LinearLayoutManager(this));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoryActivity.this, FormCategoryActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFakeDataCategorias();
    }

    private void loadFakeDataCategorias() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Categories").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    categoriesList.clear();
                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
                        Category trappedObject = documentSnapshot.toObject(Category.class);
                        categoriesList.add(trappedObject);
                    }
                    myAdapter.setListSet(categoriesList);
                    Log.i("misproductos", categoriesList.toString());
                } else {
                    Toast.makeText(CategoryActivity.this, "ERROR CARGANDO LOS PRODUCTOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}