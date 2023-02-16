package io.github.guilhermeabroncari.domain.repository;

import io.github.guilhermeabroncari.domain.entity.Client;
import io.github.guilhermeabroncari.domain.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByClient(Client client);

    @Query(" SELECT R FROM REQUEST R LEFT JOIN FETCH R.itemRequestList WHERE R.ID = :id ")
    Optional<Request> findByIdFetchItems(@Param("id") Long id);
}
