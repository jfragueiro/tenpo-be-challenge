package com.tenpo.utils;

import com.tenpo.model.UserResource;
import com.tenpo.persistence.UserEntity;

public class UserParser {
    public static UserEntity parse(final UserResource userResource) {
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(userResource.getId() != null ? userResource.getId() : 0);
        userEntity.setEmail(userResource.getEmail());
        userEntity.setPassword(userResource.getPassword());
        return userEntity;
    }

    public static UserResource parse(final UserEntity entity){
        final UserResource userResource = new UserResource();
        userResource.setId(entity.getId());
        userResource.setEmail(entity.getEmail());
        userResource.setPassword(entity.getPassword());
        return userResource;
    }

}
