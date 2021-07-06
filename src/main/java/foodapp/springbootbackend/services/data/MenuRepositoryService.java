package foodapp.springbootbackend.services.data;

import foodapp.springbootbackend.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepositoryService extends JpaRepository<MenuItem, Long>, MenuCustomQueries {

//    @Query( value =
//            "SELECT * " +
//            "FROM Menu_Item " +
//            "WHERE name LIKE :menuItemNameSearchQuery OR " +
//                "description LIKE :menuItemDescriptionSearchQuery ",
//            nativeQuery = true)
//    List<MenuItem> getMenuItemNameLike(String menuItemNameSearchQuery,
//                                       String menuItemDescriptionSearchQuery);

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
