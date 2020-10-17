package se.pscode.shortenme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.pscode.shortenme.entity.UrlData;

@Repository
public interface UrlDataRepository extends JpaRepository<UrlData, Integer> {
}
