package foodapp.springbootbackend.controllers.api.v1;

import foodapp.springbootbackend.model.Category;
import foodapp.springbootbackend.services.data.CategoryRepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    private final CategoryRepositoryService categoryRepositoryService;

    public CategoryController(CategoryRepositoryService categoryRepositoryService) {
        this.categoryRepositoryService = categoryRepositoryService;
    }

    @GetMapping("/categories")
    public List<Category> getAllCategory(){
        return categoryRepositoryService.getAllCategories();
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public Category createNewCategory(@RequestBody Category newCategory){
        return categoryRepositoryService.save(newCategory);
    }

    @GetMapping("/categories/{categoryId}")
    public Category getCategoryById(@PathVariable Long categoryId){
        Optional<Category> optionalCategory =
                categoryRepositoryService.getCategoryById(categoryId);
        return optionalCategory.orElseThrow(RuntimeException::new);
    }

    @Transactional
    @PutMapping("/categories/{categoryId}")
    public Category updateCategory(@PathVariable Long categoryId,
                                    @RequestBody Category updatedCategory){
        if(!categoryRepositoryService.existsCategoryWithId(categoryId))
            throw new RuntimeException();

        updatedCategory.setId(categoryId);
        return categoryRepositoryService.save(updatedCategory);
    }

    @Transactional
    @DeleteMapping("/categories/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId){
        if(!categoryRepositoryService.existsCategoryWithId(categoryId))
            throw new RuntimeException();

        categoryRepositoryService.deleteCategory(categoryId);
    }

}
