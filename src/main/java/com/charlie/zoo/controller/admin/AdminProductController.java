package com.charlie.zoo.controller.admin;

import com.charlie.zoo.entity.PackageType;
import com.charlie.zoo.entity.Product;
import com.charlie.zoo.entity.dto.PackageTypeDto;
import com.charlie.zoo.entity.dto.ProductDto;
import com.charlie.zoo.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
@AllArgsConstructor
public class AdminProductController {
    private final ProductService productService;
    private final ProducerService producerService;
    private final PackageTypeService packageTypeService;
    private final CategoryService categoryService;
    private final CategoryItemService categoryItemService;
    private final ImageService imageService;

    @GetMapping
    public String getProduct(Model model){
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("items",categoryItemService.findAll());
        model.addAttribute("producers",producerService.findAll());
        model.addAttribute("products",productService.findAll());
        return "admin/products";
    }

    @PostMapping(value = "/add")
    public String addProduct(@RequestParam String packageTypes,String product,
                             MultipartFile img1,MultipartFile img2,MultipartFile img3,MultipartFile img4,
                             String category,String subCategory){
        List<MultipartFile> multipartFiles = new ArrayList<>();
        multipartFiles.add(img1);
        multipartFiles.add(img2);
        multipartFiles.add(img3);
        multipartFiles.add(img4);
        List<PackageType> packageTypeList = PackageTypeDto.getArrayOfPackageTypes(packageTypes);
        Product productEntity = ProductDto.convertToProduct(product);
        productService.save(productEntity, multipartFiles,packageTypeList,category,subCategory);
        return "redirect:/admin/products";
    }

    @GetMapping("/add")
    public String addProducts(Model model){
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("items",categoryItemService.findAll());
        model.addAttribute("producers",producerService.findAll());
        model.addAttribute("products",productService.findAll());
        return "admin/addProduct";
    }

    @PostMapping(value = "/edit")
    public String editProduct(@RequestParam String packageTypes,String product,
                              MultipartFile img1,MultipartFile img2,MultipartFile img3,MultipartFile img4,
                              String category,String subCategory){
        List<MultipartFile> multipartFiles = new ArrayList<>();
        multipartFiles.add(img1);
        multipartFiles.add(img2);
        multipartFiles.add(img3);
        multipartFiles.add(img4);
        List<PackageType> packageTypeList = PackageTypeDto.getArrayOfPackageTypes(packageTypes);
        System.out.println(packageTypeList);
        Product productEntity = ProductDto.convertToProduct(product);
        productService.update(productEntity,multipartFiles,packageTypeList,category,subCategory);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{productId}")
    public String editProducts(@PathVariable int productId, Model model){
        model.addAttribute("product",productService.findById(productId));
        model.addAttribute("productImg",imageService.findByProductIdAndMainFalse(productId));
        model.addAttribute("productMainImg",imageService.findMainByProductId(productId));
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("items",categoryItemService.findAll());
        model.addAttribute("producers",producerService.findAll());
        model.addAttribute("products",productService.findAll());
        return "admin/editProduct";
    }

    @PostMapping("/changeStatus")
    public String changeStatus(int id, boolean status){
        productService.changeStatus(id,status);
        return "redirect:/admin/products";
    }
    @PostMapping("/deletePack")
    public String deletePack(int id){
        packageTypeService.deleteByID(id);
        return "redirect:/admin/products";
    }

    @PostMapping("/delete")
    public String deleteProduct(int id){
        productService.deleteByID(id);
        return "redirect:/admin/products";
    }
}
