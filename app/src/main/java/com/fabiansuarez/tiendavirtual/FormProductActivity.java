package com.fabiansuarez.tiendavirtual;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.ProxyFileDescriptorCallback;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FormProductActivity extends AppCompatActivity {

    private TextInputLayout nameTextField;
    private TextInputLayout descriptionTextField;
    private TextInputLayout priceTextField;
    private TextInputLayout imageUrlTextField;
    private Button saveButton;
    private Toolbar topAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_product);

        nameTextField = findViewById(R.id.et_name_product);
        descriptionTextField = findViewById(R.id.et_description_product);
        priceTextField = findViewById(R.id.et_price_product);
        imageUrlTextField = findViewById(R.id.et_image_product);
        saveButton = findViewById(R.id.btn_save_information);
        topAppBar = findViewById(R.id.topAppBar_form_add_product);

        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    String name = nameTextField.getEditText().getText().toString().trim();
                    String description = descriptionTextField.getEditText().getText().toString().trim();
                    String price = priceTextField.getEditText().getText().toString().trim();
                    String imageUrl = imageUrlTextField.getEditText().getText().toString().trim();

                    Product myProduct = new Product(name, description, Double.parseDouble(price), imageUrl);

                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("Products").add(myProduct).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(FormProductActivity.this, "SE GUARDO LA INFORMACION", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(FormProductActivity.this, "ERROR DE SERVIDOR", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }
        });


    }

    private boolean validateForm() {

        nameTextField.setError(null);
        descriptionTextField.setError(null);
        priceTextField.setError(null);
        imageUrlTextField.setError(null);

        String name = nameTextField.getEditText().getText().toString().trim();
        String description = descriptionTextField.getEditText().getText().toString().trim();
        String price = priceTextField.getEditText().getText().toString().trim();
        String imageUrl = imageUrlTextField.getEditText().getText().toString().trim();

        if (name.isEmpty()) {
            nameTextField.setError(getString(R.string.por_favor_ingrese_el_nombre));
            return false;
        }

        if (description.isEmpty()) {
            descriptionTextField.setError(getString(R.string.por_favor_ingrese_la_descripcion));
            return false;
        }

        if (price.isEmpty()) {
            priceTextField.setError(getString(R.string.por_favor_ingrese_el_precio));
            return false;
        }

        if (imageUrl.isEmpty()) {
            imageUrlTextField.setError(getString(R.string.por_favor_ingrese_la_url_de_la_imagen));
            return false;
        }
        return true;
    }
}