package com.fabiansuarez.tiendavirtual;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import yuku.ambilwarna.AmbilWarnaDialog;

public class FormCategoryActivity extends AppCompatActivity {

    private Button btnOk, btnColorPicker;
    private TextInputLayout nameTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_category);

        btnOk = findViewById(R.id.btnOK);
        btnColorPicker = findViewById(R.id.btn_color_new_category);
        nameTextField = findViewById(R.id.til_name_new_category);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm()) {
                    String name = nameTextField.getEditText().getText().toString().trim();
                    btnColorPicker.getBackground();
                    Category myCategory = new Category(name,btnColorPicker.getText().toString(),"no image");
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    db.collection("Categories").add(myCategory).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(FormCategoryActivity.this, "SE GUARDO LA INFORMACION", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(FormCategoryActivity.this, "ERROR DE SERVIDOR", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                       }
            }
        });
        btnColorPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AmbilWarnaDialog dialog = new AmbilWarnaDialog(FormCategoryActivity.this, Color.WHITE, new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        btnColorPicker.setBackgroundColor(color);
                        btnColorPicker.setText(String.format("#%06X", color & 0x00FFFFFF));
                    }

                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {
                        // cancel was selected by the user
                    }
                });
                dialog.show();

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