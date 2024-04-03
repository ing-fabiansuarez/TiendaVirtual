package com.fabiansuarez.tiendavirtual;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class FormCategoryActivity extends AppCompatActivity {

    private Button btnOk;
    private TextInputLayout nameTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_category);

        btnOk = findViewById(R.id.btnOK);
        nameTextField = findViewById(R.id.til_name_new_category);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    Toast.makeText(FormCategoryActivity.this, "SE GUARDO LA INFORMACION", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateForm() {
        nameTextField.setError(null);
        String name = nameTextField.getEditText().getText().toString().trim();
        if (name.isEmpty()) {
            nameTextField.setError(getString(R.string.por_favor_ingrese_el_nombre));
            return false;
        }
        return true;
    }
}