package foodapp.springbootbackend.services.data;

import foodapp.springbootbackend.model.MenuItem;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MenuRepositoryService extends CrudRepository<MenuItem, Long> {

    @Query( value =
            "Select * " +
                "from Menu_Item ",
            nativeQuery = true)
    List<MenuItem> getAllCategories();

    @Query( value =
                "SELECT * " +
                "FROM Menu_Item " +
                "WHERE id = :menuItemID",
            nativeQuery = true)
    Optional<MenuItem> getMenuItemById(Long menuItemID);

    @Modifying
    @Query( value =
                "DELETE " +
                "FROM Menu_Item " +
                "WHERE id = :menuItemID",
            nativeQuery = true)
    void deleteMenuItem(Long menuItemID);

    @Query( value =
            "SELECT " +
                "CASE " +
                    "WHEN EXISTS ( " +
                    "SELECT * " +
                    "FROM Menu_Item " +
                    "WHERE id = :menuItemID" +
                    ") THEN true " +
                "ELSE false " +
                "END ",
            nativeQuery = true)
    boolean existsMenuItemWithId(Long menuItemID);
}
