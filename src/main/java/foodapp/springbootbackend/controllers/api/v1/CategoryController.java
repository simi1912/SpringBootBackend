package foodapp.springbootbackend.controllers.api.v1;

import foodapp.springbootbackend.model.Category;
import foodapp.springbootbackend.services.data.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getAllCategory(){
        Category soupCategory = new Category(10l, "soupCategory");
        categoryService.save(soupCategory);

        return categoryService.getAllCategories();
    }

}
