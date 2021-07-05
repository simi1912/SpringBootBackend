package foodapp.springbootbackend.services.data;

import foodapp.springbootbackend.model.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryRepositoryService extends CrudRepository<Category, Long> {

    @Query( value =
            "Select * " +
            "from Category",
            nativeQuery = true )
    List<Category> getAllCategories();

    @Query( value =
            "Select * " +
            "from Category " +
            "where id = :categoryId",
            nativeQuery = true )
    Optional<Category> getCategoryById(Long categoryId);

    @Modifying
    @Query( value =
            "DELETE " +
            "FROM Category " +
            "WHERE id = :categoryId",
            nativeQuery = true)
    void deleteCategory(Long categoryId);

    @Query( value =
            "SELECT " +
                "CASE " +
                    "WHEN EXISTS ( " +
                    "SELECT *" +
                    "FROM Category " +
                    "WHERE id = :menuItemID " +
                    ") THEN true " +
                "ELSE false " +
                "END ",
            nativeQuery = true)
    boolean existsCategoryWithId(Long menuItemID);

}
