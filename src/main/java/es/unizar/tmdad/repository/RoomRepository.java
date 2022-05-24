package es.unizar.tmdad.repository;

import es.unizar.tmdad.repository.entity.RoomEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends CrudRepository<RoomEntity, Long> {
    @Query("FROM rooms r INNER join r.users rs WHERE r.owner.email = ?1 or rs.email = ?1")
    List<RoomEntity> findAllUserRooms(String user);
}
