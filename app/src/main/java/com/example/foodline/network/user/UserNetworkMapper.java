package com.example.foodline.network.user;

import com.example.foodline.model.User;
import com.example.foodline.utils.EntityMapper;

import java.util.List;

public class UserNetworkMapper implements EntityMapper<User, UserNetworkEntity> {

    public UserNetworkMapper(){
    }

    @Override
    public User mapFromEntity(UserNetworkEntity userNetworkEntity) {
        return new User(userNetworkEntity.getId(), userNetworkEntity.getName(), userNetworkEntity.getUsername(),
                userNetworkEntity.getEmail(), userNetworkEntity.getIsAdmin(), userNetworkEntity.getToken());
    }

    @Override
    public UserNetworkEntity mapToEntity(User user) {
        return new UserNetworkEntity(user.getId(), user.getName(), user.getUsername(),
                user.getEmail(), user.getIsAdmin(), user.getToken());
    }

    @Override
    public List<User> mapFromEntityList(List<UserNetworkEntity> userNetworkEntities) {
        return null;
    }
}
