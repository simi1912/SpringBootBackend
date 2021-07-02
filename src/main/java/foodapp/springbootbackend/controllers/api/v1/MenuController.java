package foodapp.springbootbackend.controllers.api.v1;

import foodapp.springbootbackend.model.Category;
import foodapp.springbootbackend.model.MenuItem;
import foodapp.springbootbackend.services.data.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MenuController {

    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/menuItems")
    public List<MenuItem> getMenuItems(){
        return menuService.getAllCategories();
    }

}
