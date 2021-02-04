package hha.spring.data.dataapi.repository;

import hha.spring.data.dataapi.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    @Query(nativeQuery = true, name = "findItemDataMapping")
    List<Item> findBySearchKeyword(String keyword);

}