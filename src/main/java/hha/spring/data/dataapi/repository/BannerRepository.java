package hha.spring.data.dataapi.repository;

import java.util.Optional;

import hha.spring.data.dataapi.domain.Banner;
import hha.spring.data.dataapi.domain.Swipper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, Integer> {
}
