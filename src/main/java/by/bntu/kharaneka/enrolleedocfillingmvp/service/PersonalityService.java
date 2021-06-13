package by.bntu.kharaneka.enrolleedocfillingmvp.service;

import by.bntu.kharaneka.enrolleedocfillingmvp.entity.Personality;

import java.util.List;

public interface PersonalityService {

    public Personality findById(Integer id);

    public List<Personality> findAll();

    public Personality savePersonality(Personality personality);

    public void deleteById(Integer id);

    public List<Personality> findBySurnameLike(String surname);
}
