package com.example.foodline.database.user;

import com.example.foodline.model.User;
import com.example.foodline.utils.EntityMapper;

import java.util.List;

public class UserCacheMapper implements EntityMapper<User, UserCacheEntity> {

    public UserCacheMapper(){
    }

    @Override
    public User mapFromEntity(UserCacheEntity userCacheEntity) {
        return new User(userCacheEntity.getId(), userCacheEntity.getName(), userCacheEntity.getUsername(),
                userCacheEntity.getEmail(), userCacheEntity.getIsAdmin(), userCacheEntity.getToken());
    }

    @Override
    public UserCacheEntity mapToEntity(User user) {
        return new UserCacheEntity(user.getId(), user.getName(), user.getUsername(),
                user.getEmail(), user.getIsAdmin(), user.getToken());
    }

    @Override
    public List<User> mapFromEntityList(List<UserCacheEntity> userCacheEntities) {
        return null;
    }
}
