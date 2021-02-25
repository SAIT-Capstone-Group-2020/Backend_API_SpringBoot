package hha.spring.data.dataapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import hha.spring.data.dataapi.domain.Banner;
import hha.spring.data.dataapi.domain.Category;

public interface BannerRepository  extends JpaRepository<Banner, Integer> {

    Banner findByUrl(String url);

    Optional<Banner> findById(Integer id);

}
