package com.example.e_comerceproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.e_comerceproject.R;
import com.example.e_comerceproject.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {
   ActivityCartBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}