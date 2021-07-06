package foodapp.springbootbackend.controllers.api.v1;

import foodapp.springbootbackend.model.MenuItem;
import foodapp.springbootbackend.services.data.MenuRepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class MenuController {

    private final MenuRepositoryService menuRepositoryService;

    public MenuController(MenuRepositoryService menuRepositoryService) {
        this.menuRepositoryService = menuRepositoryService;
    }

    @GetMapping("/menuItems")
    public List<MenuItem> getMenuItems(
            @RequestParam(value = "name", required = false, defaultValue = "")
                    String menuItemNameSearchQuery,
            @RequestParam(value = "description", required = false, defaultValue = "")
                    String menuItemDescriptionSearchQuery,
            @RequestParam(value = "category", required = false, defaultValue = "")
                    String categoriesSearchQuery){

        return menuRepositoryService.getMenuItemsLike(
                menuItemNameSearchQuery,
                menuItemDescriptionSearchQuery,
                categoriesSearchQuery
                );
    }

    @PostMapping("/menuItems")
    @ResponseStatus(HttpStatus.CREATED)
    public MenuItem createNewMenuItem(@RequestBody MenuItem newMenuItem){
        return menuRepositoryService.save(newMenuItem);
    }

    @GetMapping("/menuItems/{menuItemID}")
    public MenuItem getMenuItemById(@PathVariable Long menuItemID){
        Optional<MenuItem> optionalMenuItem = menuRepositoryService.getMenuItemById(menuItemID);
        return optionalMenuItem.orElseThrow(RuntimeException::new);
    }

    @Transactional
    @PutMapping("/menuItems/{menuItemID}")
    public MenuItem updateMenuItem(@PathVariable Long menuItemID,
                                   @RequestBody MenuItem menuItem){
        if(!menuRepositoryService.existsMenuItemWithId(menuItemID))
            throw new RuntimeException();

        menuItem.setId(menuItemID);
        return menuRepositoryService.save(menuItem);
    }

    @Transactional
    @DeleteMapping("/menuItems/{menuItemID}")
    public void deleteMenuItem(@PathVariable Long menuItemID){
        if(!menuRepositoryService.existsMenuItemWithId(menuItemID))
            throw new RuntimeException();

        menuRepositoryService.deleteMenuItem(menuItemID);
    }

}
