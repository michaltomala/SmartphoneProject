package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.entity.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {

}

