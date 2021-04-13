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

    public List<WeightType> listAllWeightType() {
        return repo.findAll();
    }

    public WeightType getWeightTypeById(int id) {
        return repo.findById(id).get();
    }

    public List<WeightType> saveWeightType(WeightType wt) {

        repo.save(wt);
        return repo.findAll();
    }

    public List<WeightType> deleteWeightType(int id) {

        repo.deleteById(id);
        return repo.findAll();
    }

}
