package com.example.foodline.utils;

import java.util.List;

public interface EntityMapper<DomainModel, Entity> {

    DomainModel mapFromEntity(Entity entity);

    Entity mapToEntity(DomainModel domainModel);

    List<DomainModel> mapFromEntityList(List<Entity> entityList);
}
