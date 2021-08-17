package com.maciek.studentsystem.repo;

import com.maciek.studentsystem.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends CrudRepository<Student,Long> {

    @Override
    <S extends Student> S save(S s);

    @Override
    Optional<Student> findById(Long aLong);

    @Override
    List<Student> findAll();

    @Override
    void deleteById(Long aLong);
}
