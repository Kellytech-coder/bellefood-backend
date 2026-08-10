package com.bellefood.menu.controller;

import com.bellefood.menu.model.Menu;
import com.bellefood.menu.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = "http://localhost:3000")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public List<Menu> getMenus() throws ExecutionException, InterruptedException {
        return menuService.getAllMenus().get();
    }
}