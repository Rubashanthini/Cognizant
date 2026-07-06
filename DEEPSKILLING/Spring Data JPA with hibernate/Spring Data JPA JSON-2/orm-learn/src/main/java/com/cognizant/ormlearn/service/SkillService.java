package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.exception.ResourceNotFoundException;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public Skill get(Long skillId) {
        return skillRepository.findById(skillId)
                .orElseThrow(() -> new ResourceNotFoundException("Skill not found with id: " + skillId));
    }

    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }

    public List<Skill> getAll() {
        return skillRepository.findAll();
    }
}
