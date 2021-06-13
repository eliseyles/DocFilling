package by.bntu.kharaneka.enrolleedocfillingmvp.service;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.Contract;

import java.util.List;

public interface ContractService {
    public Contract findById(Integer id);

    public List<Contract> findAll();

    public Contract saveContract(Contract contract);

    public void deleteById(Integer id);

    public Contract findByPersonality(Integer id);

}
