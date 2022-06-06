package es.unizar.tmdad.repository;

import es.unizar.tmdad.repository.entity.RoomEntity;
import es.unizar.tmdad.repository.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<RoomEntity, Long> {
    List<RoomEntity> findAllByOwner(UserEntity owner);
}
