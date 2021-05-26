package com.example.foodline.database.menu;

import com.example.foodline.model.MenuItem;
import com.example.foodline.utils.EntityMapper;

import java.util.ArrayList;
import java.util.List;

public class MenuCacheMapper implements EntityMapper<MenuItem, MenuCacheEntity> {

    public MenuCacheMapper(){
    }

    @Override
    public MenuItem mapFromEntity(MenuCacheEntity menuCacheEntity) {
        return new MenuItem(menuCacheEntity.getId(), menuCacheEntity.getName(), menuCacheEntity.getPrice(),
                menuCacheEntity.getRating(), menuCacheEntity.getCategory(), menuCacheEntity.getImageUrl(),
                menuCacheEntity.getDescription(), menuCacheEntity.getCounterInStock(), menuCacheEntity.getCounterInCart());
    }

    @Override
    public MenuCacheEntity mapToEntity(MenuItem menuItem) {
        return new MenuCacheEntity(menuItem.getId(), menuItem.getName(), menuItem.getPrice(),
                menuItem.getRating(), menuItem.getCategory(), menuItem.getImageUrl(),
                menuItem.getDescription(), menuItem.getCounterInStock(), menuItem.getCounterInCart());
    }

    @Override
    public List<MenuItem> mapFromEntityList(List<MenuCacheEntity> menuCacheEntities) {
        List<MenuItem> menuItems = new ArrayList<>();

        for(MenuCacheEntity menuCacheEntity: menuCacheEntities){
            menuItems.add(mapFromEntity(menuCacheEntity));
        }

        return menuItems;
    }
}
