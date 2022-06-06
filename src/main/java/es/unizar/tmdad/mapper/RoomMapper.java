package es.unizar.tmdad.mapper;

import es.unizar.tmdad.dto.RoomCreationDto;
import es.unizar.tmdad.dto.RoomDto;
import es.unizar.tmdad.dto.UserDto;
import es.unizar.tmdad.repository.entity.RoomEntity;
import es.unizar.tmdad.repository.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(target = "name", source = "msg.roomName")
    @Mapping(target = "owner", source = "owner")
    RoomEntity mapRoom(RoomCreationDto msg, UserEntity owner);

    RoomDto mapRoom(RoomEntity msg);

    /*default String mapUserToId(UserEntity entity){
        return Objects.nonNull(entity) ? entity.getName() : null;
    }*/
    UserDto mapUserToId(UserEntity entity);

    List<RoomDto> mapRoomEntities(List<RoomEntity> rooms);
}
