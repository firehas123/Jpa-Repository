package com.example.demo;

import com.example.demo.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFirstName(String firstName);

    List<Student> findByLastName(String lastName);

    List<Student> findByAgeGreaterThan(int age);

    // Update the student with the given ID
    Student updateStudent(Long id, Student updatedStudent);

    // Implement remaining methods

    @Override
    List<Student> findAll();

    @Override
    List<Student> findAllById(Iterable<Long> iterable);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Student student);

    @Override
    void deleteAllByIdInBatch(Iterable<Long> iterable);

    @Override
    void deleteAll(Iterable<? extends Student> iterable);

    @Override
    void deleteAll();

    @Override
    <S extends Student> S save(S s);

    @Override
    <S extends Student> List<S> saveAll(Iterable<S> iterable);

    @Override
    Optional<Student> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    void flush();

    @Override
    <S extends Student> S saveAndFlush(S s);

    @Override
    <S extends Student> List<S> saveAllAndFlush(Iterable<S> iterable);

    @Override
    void deleteAllInBatch();

    @Override
    Student getOne(Long aLong);

    @Override
    Student getById(Long aLong);

    @Override
    <S extends Student> Optional<S> findOne(Example<S> example);

    @Override
    <S extends Student> List<S> findAll(Example<S> example);

    @Override
    <S extends Student> List<S> findAll(Example<S> example, Sort sort);

    @Override
    <S extends Student> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends Student> long count(Example<S> example);

    @Override
    <S extends Student> boolean exists(Example<S> example);
}
