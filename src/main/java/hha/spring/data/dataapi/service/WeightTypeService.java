package hha.spring.data.dataapi.service;

import hha.spring.data.dataapi.domain.Category;
import hha.spring.data.dataapi.domain.WeightType;
import hha.spring.data.dataapi.repository.CategoryRepository;
import hha.spring.data.dataapi.repository.WeightTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    public void saveWeightType(WeightType wt) {
        repo.save(wt);
    }

    public void deleteWeightType(int id) {
        repo.deleteById(id);
    }

}
