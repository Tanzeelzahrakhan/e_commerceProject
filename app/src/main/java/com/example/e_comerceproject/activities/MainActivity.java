package com.example.e_comerceproject.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.e_comerceproject.Adapter.CategoryAdapter;
import com.example.e_comerceproject.Adapter.ProductAdapter;
import com.example.e_comerceproject.Model.Category;
import com.example.e_comerceproject.Model.Product;
import com.example.e_comerceproject.databinding.ActivityMainBinding;
import com.example.e_comerceproject.utils.Constants;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categories;
    ProductAdapter productAdapter;
    ArrayList<Product>products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initCategories();
        initProducts();
        initSlider();

    }
      private void initSlider() {
         /* binding.carousel.addData(new CarouselItem("https://cdn.pixabay.com/photo/2016/06/15/00/08/special-offer-1457915__340.png"));
          binding.carousel.addData(new CarouselItem("https://st.depositphotos.com/1029663/1384/i/600/depositphotos_13840733-stock-photo-special-offer.jpg"));
          binding.carousel.addData(new CarouselItem("https://www.shutterstock.com/image-vector/special-offer-banner-vector-format-260nw-586903514.jpg"));*/
          getRecentOffers();
      }





      //Categories
    void initCategories(){
        categories=new ArrayList<>();
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,4);
        binding.categoriesList.setLayoutManager(gridLayoutManager);
       /* categories.add(new Category("Tanzeel","https://www.svgrepo.com/show/490925/basketball.svg","#e28743","some Discription",1));
        categories.add(new Category("Tanzeel","https://www.svgrepo.com/show/490925/basketball.svg","#e28743","some Discription",1));
        categories.add(new Category("Tanzeel","https://www.svgrepo.com/show/490925/basketball.svg","#e28743","some Discription",1));
        categories.add(new Category("Tanzeel","https://www.svgrepo.com/show/490925/basketball.svg","#e28743","some Discription",1));
        categories.add(new Category("Tanzeel","https://www.svgrepo.com/show/490925/basketball.svg","#e28743","some Discription",1));
        categories.add(new Category("Tanzeel","https://www.svgrepo.com/show/490925/basketball.svg","#e28743","some Discription",1));
        categories.add(new Category("Tanzeel","https://www.svgrepo.com/show/490925/basketball.svg","#e28743","some Discription",1));
        categories.add(new Category("Tanzeel","https://www.svgrepo.com/show/490925/basketball.svg","#e28743","some Discription",1));*/
        categoryAdapter=new CategoryAdapter(this,categories);
        getCategories();
        binding.categoriesList.setAdapter(categoryAdapter);
    }
    void getCategories(){
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.GET, Constants.GET_CATEGORIES_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject mainObj=new JSONObject(response);
                    if (mainObj.getString("status").equals("success")){
                        JSONArray categoriesArray=mainObj.getJSONArray("categories");
                        for (int i=0;i<categoriesArray.length();i++){
                            JSONObject object=categoriesArray.getJSONObject(i);
                            Category category=new Category(
                                    object.getString("name"),
                                    Constants.CATEGORIES_IMAGE_URL+object.getString("icon"),
                                    object.getString("color"),
                                    object.getString("brief"),
                                    object.getInt("id")

                            );
                            categories.add(category);
                        }
                        categoryAdapter.notifyDataSetChanged();
                    }else {

                    }
                } catch (JSONException e) {

                    throw new RuntimeException(e);
                }
                // Log.e("error",response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
       queue.add(request);
    }







    //products
    void initProducts(){

        products=new ArrayList<>();
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        binding.productList.setLayoutManager(layoutManager);
       /* products.add(new Product("girlsShirt","https://babynestboutique.com/wp-content/uploads/2021/09/SBT-229.jpeg","two shrt avalible",12,12,2,1));
        products.add(new Product("girlsShirt","https://babynestboutique.com/wp-content/uploads/2021/09/SBT-229.jpeg","two shrt avalible",12,12,2,1));
        products.add(new Product("girlsShirt","https://babynestboutique.com/wp-content/uploads/2021/09/SBT-229.jpeg","two shrt avalible",12,12,2,1));
        products.add(new Product("girlsShirt","https://babynestboutique.com/wp-content/uploads/2021/09/SBT-229.jpeg","two shrt avalible",12,12,2,1));
        products.add(new Product("girlsShirt","https://babynestboutique.com/wp-content/uploads/2021/09/SBT-229.jpeg","two shrt avalible",12,12,2,1));
        products.add(new Product("girlsShirt","https://babynestboutique.com/wp-content/uploads/2021/09/SBT-229.jpeg","two shrt avalible",12,12,2,1));
        products.add(new Product("girlsShirt","https://babynes tboutique.com/wp-content/uploads/2021/09/SBT-229.jpeg","two shrt avalible",12,12,2,1));
        products.add(new Product("girlsShirt","https://babynestboutique.com/wp-content/uploads/2021/09/SBT-229.jpeg","two shrt avalible",12,12,2,1));*/
        productAdapter=new ProductAdapter(this,products);
        getRecentProducts();
        binding.productList.setAdapter(productAdapter);
    }
    void getRecentProducts(){
        RequestQueue queue=Volley.newRequestQueue(this);
        String url=Constants.GET_PRODUCTS_URL+ "?count=32 ";
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object=new JSONObject(response);
                    if (object.getString("status").equals("success")){
                        JSONArray productArray=object.getJSONArray("products");
                        for (int i=0;i<productArray.length();i++){
                            JSONObject childObj=productArray.getJSONObject(i);
                            Product product=new Product(
                                    childObj.getString("name"),
                                    Constants.PRODUCTS_IMAGE_URL + childObj.getString("image"),
                                    childObj.getString("status"),
                                    childObj.getDouble("price"),
                                    childObj.getDouble("price_discount"),
                                    childObj.getInt("stock"),
                                    childObj.getInt("id")
                            );
                            products.add(product);
                        }
                        productAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, error -> {

        });
        queue.add(request);

    }
    void getRecentOffers() {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, Constants.GET_OFFERS_URL, response -> {
            try {
                JSONObject object = new JSONObject(response);
                if(object.getString("status").equals("success")) {
                    JSONArray offerArray = object.getJSONArray("news_infos");
                    for(int i =0; i < offerArray.length(); i++) {
                        JSONObject childObj =  offerArray.getJSONObject(i);
                        binding.carousel.addData(
                                new CarouselItem(
                                        Constants.NEWS_IMAGE_URL + childObj.getString("image"),
                                        childObj.getString("title")
                                )
                        );
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {});
        queue.add(request);
    }
}