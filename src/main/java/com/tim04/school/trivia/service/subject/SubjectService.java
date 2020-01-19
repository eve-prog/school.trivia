package com.tim04.school.trivia.service.subject;

import com.tim04.school.trivia.persistence.subject.SubjectEntity;
import com.tim04.school.trivia.persistence.subject.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository bookRepository) {
        this.subjectRepository = bookRepository;
    }

    @Transactional
    public void save(String name){
        SubjectEntity entity = new SubjectEntity();
        entity.setName(name);
        subjectRepository.save(entity);
    }
    @Transactional(readOnly = true)
    public List<SubjectEntity> findAll(){
        return subjectRepository.findAll();
    }
}
