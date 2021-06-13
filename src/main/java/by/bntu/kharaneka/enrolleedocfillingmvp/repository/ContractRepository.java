package by.bntu.kharaneka.enrolleedocfillingmvp.repository;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.Contract;
import by.bntu.kharaneka.enrolleedocfillingmvp.entity.Personality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContractRepository extends JpaRepository<Contract, Integer> {

    public Contract findByPersonality(Personality personality);
}
