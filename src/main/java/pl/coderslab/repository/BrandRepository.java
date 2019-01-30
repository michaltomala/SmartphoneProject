package pl.coderslab.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import pl.coderslab.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
