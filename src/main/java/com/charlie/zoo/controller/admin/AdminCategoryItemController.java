package com.charlie.zoo.controller.admin;

import com.charlie.zoo.entity.Category;
import com.charlie.zoo.entity.CategoryItem;
import com.charlie.zoo.service.AnimalService;
import com.charlie.zoo.service.CategoryItemService;
import com.charlie.zoo.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/categoryItem")
@AllArgsConstructor
public class AdminCategoryItemController {
    private final CategoryService categoryService;
    private final AnimalService animalService;
    private CategoryItemService categoryItemService;

    @GetMapping()
    public String getCategoryItem(Integer animalId,Integer categoryId, Model model){
        if(animalId==null){
            animalId=1;
        }
        List<Category>categories = categoryService.findByAnimalId(animalId);
        if(categoryId==null){
            if(categories.size()>0){
                return "redirect:/admin/categoryItem?animalId="+animalId+"&categoryId="+categories.get(0).getId();
            }else {
                model.addAttribute("categoryItems", categoryItemService.findByCategoryAnimalId(animalId));
            }
        }else{
            model.addAttribute("categoryItems",categoryItemService.findByCategoryId(categoryId));
        }
        model.addAttribute("activeTabId",animalId);
        model.addAttribute("categoryTabId",categoryId);
        model.addAttribute("animals",animalService.findAll());
        model.addAttribute("categories",categories);
        return "admin/categoryItem";
    }

    @PostMapping({"/add","/edit"})
    public String addCategoryItem(CategoryItem categoryItem){
        categoryItemService.save(categoryItem);
        return "redirect:/admin/categoryItem?animalId="+categoryItem.getCategory().getAnimal().getId()+"&categoryId="+categoryItem.getCategory().getId();
    }

    @PostMapping("/delete")
    public String deleteCategoryItem(int id){
        CategoryItem categoryItem = categoryItemService.findById(id);
        categoryItemService.deleteByID(id);
        return "redirect:/admin/categoryItem?animalId="+categoryItem.getCategory().getAnimal().getId()+"&categoryId="+categoryItem.getCategory().getId();
    }
}
