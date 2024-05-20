package edu.comillas.icai.gitt.pat.spring.p5.repository;

import edu.comillas.icai.gitt.pat.spring.p5.entity.Pez;
import org.springframework.data.repository.CrudRepository;

public interface PezRepository extends CrudRepository<Pez, Long> {
    Pez findByid(Long id);
}
