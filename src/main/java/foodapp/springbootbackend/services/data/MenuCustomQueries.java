package foodapp.springbootbackend.services.data;

import foodapp.springbootbackend.model.MenuItem;

import java.util.List;

public interface MenuCustomQueries {

    List<MenuItem> getMenuItemsLike(String itemNameSearchQuery, String menuItemNameSearchQuery,
                                    String menuItemDescriptionSearchQuery);

}
