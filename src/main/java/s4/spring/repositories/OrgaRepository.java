package s4.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import s4.spring.models.Organization;

public interface OrgaRepository extends JpaRepository<Organization, Integer> {
    List<Organization> findByDomain(String domain);
    List<Organization> findByName(String name);
    List<Organization> findById(int id);

}