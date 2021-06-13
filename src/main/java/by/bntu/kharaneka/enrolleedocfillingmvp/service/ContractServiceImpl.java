package by.bntu.kharaneka.enrolleedocfillingmvp.service;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.Contract;
import by.bntu.kharaneka.enrolleedocfillingmvp.entity.Personality;
import by.bntu.kharaneka.enrolleedocfillingmvp.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService{

    private final ContractRepository contractRepository;

    @Autowired
    public ContractServiceImpl(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }


    @Override
    public Contract findById(Integer id) {
        return contractRepository.getOne(id);
    }

    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public Contract saveContract(Contract contract) {
        return contractRepository.save(contract);
    }

    @Override
    public void deleteById(Integer id) {
        contractRepository.deleteById(id);
    }

    @Override
    public Contract findByPersonality(Integer id) {
        return contractRepository.findByPersonality(Personality.builder().id(id).build());
    }
}
