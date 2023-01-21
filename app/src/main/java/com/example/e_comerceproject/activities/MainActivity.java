package com.example.e_comerceproject.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.e_comerceproject.Adapter.CategoryAdapter;
import com.example.e_comerceproject.Adapter.ProductAdapter;
import com.example.e_comerceproject.Model.Category;
import com.example.e_comerceproject.Model.Product;
import com.example.e_comerceproject.databinding.ActivityMainBinding;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

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
        //initSlider();
    }

  /*  private void initSlider() {
        binding.carousel.addData(new CarouselItem("https://cdn.pixabay.com/photo/2016/06/15/00/08/special-offer-1457915__340.png"));
        binding.carousel.addData(new CarouselItem("https://st.depositphotos.com/1029663/1384/i/600/depositphotos_13840733-stock-photo-special-offer.jpg"));
        binding.carousel.addData(new CarouselItem("https://www.shutterstock.com/image-vector/special-offer-banner-vector-format-260nw-586903514.jpg"));
    }
*/
    void initCategories(){
        categories=new ArrayList<>();
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,4);
        binding.categoriesList.setLayoutManager(gridLayoutManager);
        categories.add(new Category("Tanzeel","https://www.svgrepo.com/show/490925/basketball.svg","#e28743","some Discription",1));
        categories.add(new Category("Tanzeel","https://www.svgrepo.com/show/490925/basketball.svg","#e28743","some Discription",1));
        categories.add(new Category("Tanzeel","https://www.svgrepo.com/show/490925/basketball.svg","#e28743","some Discription",1));
        categories.add(new Category("Tanzeel","https://www.svgrepo.com/show/490925/basketball.svg","#e28743","some Discription",1));
        categories.add(new Category("Tanzeel","https://www.svgrepo.com/show/490925/basketball.svg","#e28743","some Discription",1));
        categories.add(new Category("Tanzeel","https://www.svgrepo.com/show/490925/basketball.svg","#e28743","some Discription",1));
        categories.add(new Category("Tanzeel","https://www.svgrepo.com/show/490925/basketball.svg","#e28743","some Discription",1));
        categories.add(new Category("Tanzeel","https://www.svgrepo.com/show/490925/basketball.svg","#e28743","some Discription",1));
        categoryAdapter=new CategoryAdapter(this,categories);
        binding.categoriesList.setAdapter(categoryAdapter);
    }

    void initProducts(){
        products=new ArrayList<>();
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        binding.productList.setLayoutManager(layoutManager);
        products.add(new Product("girlsShirt","https://babynestboutique.com/wp-content/uploads/2021/09/SBT-229.jpeg","two shrt avalible",12,12,2,1));
        products.add(new Product("girlsShirt","https://babynestboutique.com/wp-content/uploads/2021/09/SBT-229.jpeg","two shrt avalible",12,12,2,1));
        products.add(new Product("girlsShirt","https://babynestboutique.com/wp-content/uploads/2021/09/SBT-229.jpeg","two shrt avalible",12,12,2,1));
        products.add(new Product("girlsShirt","https://babynestboutique.com/wp-content/uploads/2021/09/SBT-229.jpeg","two shrt avalible",12,12,2,1));
        products.add(new Product("girlsShirt","https://babynestboutique.com/wp-content/uploads/2021/09/SBT-229.jpeg","two shrt avalible",12,12,2,1));
        productAdapter=new ProductAdapter(this,products);
        binding.productList.setAdapter(productAdapter);

    }
}