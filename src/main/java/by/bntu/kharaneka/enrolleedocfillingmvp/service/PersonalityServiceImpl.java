package by.bntu.kharaneka.enrolleedocfillingmvp.service;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.Personality;
import by.bntu.kharaneka.enrolleedocfillingmvp.repository.PersonalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalityServiceImpl implements PersonalityService {

    private final PersonalityRepository personalityRepository;

    @Autowired
    public PersonalityServiceImpl(PersonalityRepository personalityRepository) {
        this.personalityRepository = personalityRepository;
    }

    @Override
    public Personality findById(Integer id) {
        return personalityRepository.getOne(id);
    }

    @Override
    public List<Personality> findAll() {
        return personalityRepository.findAll();
    }

    @Override
    public Personality savePersonality(Personality personality) {
        return personalityRepository.save(personality);
    }

    @Override
    public void deleteById(Integer id) {
        personalityRepository.deleteById(id);
    }

    @Override
    public List<Personality> findBySurnameLike(String surname) {
        return personalityRepository.findBySurnameIsLike(surname);
    }
}
