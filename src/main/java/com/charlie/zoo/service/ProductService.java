package com.charlie.zoo.service;


import com.charlie.zoo.entity.Animal;
import com.charlie.zoo.entity.Category;
import com.charlie.zoo.entity.PackageType;
import com.charlie.zoo.entity.Product;
import com.charlie.zoo.enums.StatusOfEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface ProductService {
    Product save(Product product);
    Product save(Product product, MultipartFile multipartFile, List<PackageType>  packageTypes,String category,String subCategory);
    Product update(Product product, MultipartFile multipartFile, List<PackageType>  packageTypes,String category,String subCategory);
    Product findById(int id);
    Set<Product> findByAnimal(Animal animal);
    Set<Product> findByAnimalByCategory(Category category);
    Product changeStatus(int id, boolean status);
    Set<Product> getFilteredProduct(Integer[] categoryIdArr,Integer[] categoryItemIdArr,Integer[] producerId, Double[] packSizeArr);
    List<Product> findAll();
//    List<Product> getFilteredProducts(Integer type, Integer size,Integer page);
//    int getCountOfElements(Integer type, Integer size,Integer page);
//    List<Product> findByStatus(StatusOfEntity status);
//    List<Product> findSameProducts(Product product);
    void deleteByID(int id);
}
