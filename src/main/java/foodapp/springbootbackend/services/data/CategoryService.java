package foodapp.springbootbackend.services.data;

import foodapp.springbootbackend.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService extends CrudRepository<Category, Long> {

    @Query( value = "Select * from Category;",
            nativeQuery = true )
    List<Category> getAllCategories();
}

//@Query(value = "select id,name,roll_no from USER_INFO_TEST where rollNo = ?1", nativeQuery = true)
//ArrayList<IUserProjection> findUserUsingRollNo(String rollNo);