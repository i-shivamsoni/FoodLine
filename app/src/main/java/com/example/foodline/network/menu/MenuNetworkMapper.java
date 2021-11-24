package com.example.foodline.network.menu;

import com.example.foodline.model.MenuItem;
import com.example.foodline.utils.EntityMapper;

import java.util.ArrayList;
import java.util.List;


public class MenuNetworkMapper implements EntityMapper<MenuItem, MenuNetworkEntity> {

    public MenuNetworkMapper(){
    }

    @Override
    public MenuItem mapFromEntity(MenuNetworkEntity menuNetworkEntity) {
        return new MenuItem(menuNetworkEntity.getDishId(), menuNetworkEntity.getDishName(),
                menuNetworkEntity.getPrice(), menuNetworkEntity.getRating(), menuNetworkEntity.getCategory(),
                menuNetworkEntity.getImage(), menuNetworkEntity.getDescription(), menuNetworkEntity.getCounterInStock(), 0);
    }

    @Override
    public MenuNetworkEntity mapToEntity(MenuItem menuItem) {
        return new MenuNetworkEntity(menuItem.getId(), menuItem.getName(), menuItem.getPrice(),
                menuItem.getRating(), menuItem.getCategory(), menuItem.getImageUrl(), menuItem.getDescription(),
                menuItem.getCounterInStock());
    }

    @Override
    public List<MenuItem> mapFromEntityList(List<MenuNetworkEntity> dishNetworkEntities) {
        List<MenuItem> menuItems = new ArrayList<>();

        for(MenuNetworkEntity menuNetworkEntity : dishNetworkEntities){
            menuItems.add(mapFromEntity(menuNetworkEntity));
        }

        return menuItems;
    }
}
