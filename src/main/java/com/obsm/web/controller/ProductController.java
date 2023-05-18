package com.obsm.web.controller;

import com.obsm.web.model.Product;
import com.obsm.web.model.constant.ProductCategory;
import com.obsm.web.model.constant.ProductModel;
import com.obsm.web.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/addProduct")
    public String showAddProductPage(Product product) {
        return "addProduct";
    }

    @GetMapping("product")
    public Product product() {
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
    ) {
        if (result.hasErrors()) {
            return "/addProduct";

        }

        productService.save(product);

        return "redirect:/addProduct?success";
    }

    @GetMapping("/portfolio-item/{id}")
    public String showPortfolioItemByIdPage(
            @PathVariable("id")
            int id,
            Model model
    ) {
        Product product = productService.findById(id)
                .orElseThrow();

        List<ProductCategory> categories = Arrays.asList(ProductCategory.values());
        List<ProductModel> models = Arrays.asList(ProductModel.values());
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        model.addAttribute("models", models);

        return "portfolio-item";
    }

    @GetMapping("/portfolio-overview")
    public String showAllItems(Model model) {
        List<Product> product = productService.findAll();
        List<ProductModel> models = Arrays.asList(ProductModel.values());
        List<ProductCategory> categories = Arrays.asList(ProductCategory.values());

        model.addAttribute("products", product);
        model.addAttribute("models", models);
        model.addAttribute("categories", categories);
        return "portfolio-overview";
    }


}
