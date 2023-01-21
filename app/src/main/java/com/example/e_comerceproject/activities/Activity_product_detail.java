package com.example.e_comerceproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.e_comerceproject.R;
import com.example.e_comerceproject.databinding.ActivityProductDetailBinding;
import com.example.e_comerceproject.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class Activity_product_detail extends AppCompatActivity {
    @NonNull ActivityProductDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        binding= ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String name=getIntent().getStringExtra("name");
        //binding.productDescription.setText(name);
        String image=getIntent().getStringExtra("image");
        int id =getIntent().getIntExtra("id",0);
       double price=getIntent().getDoubleExtra("price",0);
        Glide.with(this)
                .load(image)
                .into(binding.productImage);
        getProductDetails(id);
        getSupportActionBar().setTitle(name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.cart){
            Intent intent=new Intent(this,CartActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    void getProductDetails(int id){
        RequestQueue queue= Volley.newRequestQueue(this);
        String url=Constants.GET_PRODUCT_DETAILS_URL+id;
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object=new JSONObject(response);
                    if (object.getString("status").equals("success")){
                        JSONObject product=object.getJSONObject("product");
                      String description=product.getString("description");
                      binding.productDescription.setText(
                              Html.fromHtml(description)
                      );
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, error -> {

        });
        queue.add(request);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}