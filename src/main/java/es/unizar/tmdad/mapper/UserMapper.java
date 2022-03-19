package es.unizar.tmdad.mapper;

import es.unizar.tmdad.dto.*;
import es.unizar.tmdad.dto.UserCreationDto;
import es.unizar.tmdad.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "isSuperUser", expression = "java(false)")
    UserEntity mapUser(UserCreationDto msg);

    UserDto mapUser(UserEntity msg);

}
