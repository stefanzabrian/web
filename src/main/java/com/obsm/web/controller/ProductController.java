package com.obsm.web.controller;

import com.obsm.web.model.Product;
import com.obsm.web.model.constant.ProductCategory;
import com.obsm.web.model.constant.ProductModel;
import com.obsm.web.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/addProduct")
    public String showAddProductPage(Product product){
        return "addProduct";
    }
    @GetMapping("product")
    public Product product(){
        return new Product();
    }
    @ModelAttribute("models")
    public ProductModel[] getProductModel() {
        return ProductModel.values();
    }
    @ModelAttribute("categories")
    public ProductCategory[] getProductCategory() {
        return ProductCategory.values();
    }

    @PostMapping("/addProduct")
    public String addProduct(
            @ModelAttribute("product")
            @Valid
            Product product,
            BindingResult result
                             ){
        if (result.hasErrors()){
            return "/addProduct";

        }

        productService.save(product);

        return "redirect:/addProduct?success";
    }


}
