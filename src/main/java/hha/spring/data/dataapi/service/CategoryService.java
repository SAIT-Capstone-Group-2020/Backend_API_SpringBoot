package hha.spring.data.dataapi.service;

import hha.spring.data.dataapi.domain.Category;
import hha.spring.data.dataapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This class is a business logic to manage category data
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepository repo;

    /**
     * List all categories list.
     *
     * @return the list
     */
    public List<Category> listAllCategories() {
        return repo.findAll();
    }

    /**
     * Gets category by id.
     *
     * @param id the id
     * @return the category by id
     */
    public Category getCategoryById(int id) {
        return repo.findById(id).get();
    }

    /**
     * Save category list.
     *
     * @param category the category
     * @return the list
     */
    public List<Category> saveCategory(Category category) {

        repo.save(category);
        return repo.findAll();
    }

    /**
     * Delete category list.
     *
     * @param id the id
     * @return the list
     */
    public List<Category> deleteCategory(int id) {

        repo.deleteById(id);
        return repo.findAll();
    }
}
