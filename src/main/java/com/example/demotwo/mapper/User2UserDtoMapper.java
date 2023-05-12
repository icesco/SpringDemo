package com.example.demotwo.mapper;

import com.example.demotwo.model.User;
import com.example.demotwo.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface User2UserDtoMapper {

    public static User2UserDtoMapper INSTANCE = Mappers.getMapper( User2UserDtoMapper.class );

    public abstract UserDTO entity2dto(User source);

    public abstract User dto2entity(UserDTO source);
}
