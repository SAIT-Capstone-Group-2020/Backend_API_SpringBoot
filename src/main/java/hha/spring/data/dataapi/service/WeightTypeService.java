package hha.spring.data.dataapi.service;

import hha.spring.data.dataapi.domain.WeightType;
import hha.spring.data.dataapi.repository.WeightTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This class is a business logic to manage weight_Type data
 *
 * @author HHA E-Commerce
 * @version 1.0, April 20, 2021
 */
@Service
@Transactional
public class WeightTypeService {

    @Autowired
    private WeightTypeRepository repo;

    /**
     * List all weight type list.
     *
     * @return the list
     */
    public List<WeightType> listAllWeightType() {
        return repo.findAll();
    }

    /**
     * Gets weight type by id.
     *
     * @param id the id
     * @return the weight type by id
     */
    public WeightType getWeightTypeById(int id) {
        return repo.findById(id).get();
    }

    /**
     * Save weight type list.
     *
     * @param wt the wt
     * @return the list
     */
    public List<WeightType> saveWeightType(WeightType wt) {

        repo.save(wt);
        return repo.findAll();
    }

    /**
     * Delete weight type list.
     *
     * @param id the id
     * @return the list
     */
    public List<WeightType> deleteWeightType(int id) {

        repo.deleteById(id);
        return repo.findAll();
    }

}
