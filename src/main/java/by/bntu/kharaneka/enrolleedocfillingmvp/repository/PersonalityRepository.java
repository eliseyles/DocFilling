package by.bntu.kharaneka.enrolleedocfillingmvp.repository;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.Personality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonalityRepository extends JpaRepository<Personality, Integer> {
    public List<Personality> findBySurnameIsLike(String surname);
}
