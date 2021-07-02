package foodapp.springbootbackend.services.data;

import foodapp.springbootbackend.model.MenuItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService extends CrudRepository<MenuItem, Long> {

    @Query( value = "Select * from MenuItem",
            nativeQuery = true)
    List<MenuItem> getAllCategories();

}
