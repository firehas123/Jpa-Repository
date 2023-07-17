//package com.example.demo;
//
//import org.springframework.data.domain.*;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Component
//public class StudentRepositoryImplemented implements StudentRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public List<Student> findByFirstName(String firstName) {
//        return entityManager.createQuery("SELECT s FROM Student s WHERE s.name = :firstName", Student.class)
//                .setParameter("firstName", firstName)
//                .getResultList();
//    }
//
//    @Override
//    public List<Student> findByLastName(String lastName) {
//        return entityManager.createQuery("SELECT s FROM Student s WHERE s.name = :lastName", Student.class)
//                .setParameter("lastName", lastName)
//                .getResultList();
//    }
//
//    @Override
//    public List<Student> findByAgeGreaterThan(int age) {
//        return entityManager.createQuery("SELECT s FROM Student s WHERE s.age > :age", Student.class)
//                .setParameter("age", age)
//                .getResultList();
//    }
//
//    @Override
//    public Student updateStudent(Long id, Student updatedStudent) {
//        Student student = entityManager.find(Student.class, id);
//        if (student != null) {
//            student.setName(updatedStudent.getName());
//            student.setAge(updatedStudent.getAge());
//            entityManager.merge(student);
//        }
//        return student;
//    }
//
//    @Override
//    public List<Student> findAll() {
//        return entityManager.createQuery("SELECT s FROM Student s", Student.class)
//                .getResultList();
//    }
//
//    @Override
//    public List<Student> findAll(Sort sort) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Student> query = cb.createQuery(Student.class);
//        Root<Student> root = query.from(Student.class);
//        query.orderBy(getSortOrders(sort, cb, root));
//        TypedQuery<Student> typedQuery = entityManager.createQuery(query);
//        return typedQuery.getResultList();
//    }
//
//    @Override
//    public Page<Student> findAll(Pageable pageable) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Student> query = cb.createQuery(Student.class);
//        Root<Student> root = query.from(Student.class);
//        query.orderBy(getSortOrders(pageable.getSort(), cb, root));
//        TypedQuery<Student> typedQuery = entityManager.createQuery(query);
//        typedQuery.setFirstResult((int) pageable.getOffset());
//        typedQuery.setMaxResults(pageable.getPageSize());
//        List<Student> students = typedQuery.getResultList();
//        long total = count();
//        return new PageImpl<>(students, pageable, total);
//    }
//
//    private List<javax.persistence.criteria.Order> getSortOrders(Sort sort, CriteriaBuilder cb, Root<Student> root) {
//        List<javax.persistence.criteria.Order> orders = new ArrayList<>();
//        for (Sort.Order sortOrder : sort) {
//            String property = sortOrder.getProperty();
//            if (sortOrder.isAscending()) {
//                orders.add(cb.asc(root.get(property)));
//            } else {
//                orders.add(cb.desc(root.get(property)));
//            }
//        }
//        return orders;
//    }
//    @Override
//    public List<Student> findAllById(Iterable<Long> iterable) {
//        return entityManager.createQuery("SELECT s FROM Student s WHERE s.id IN :ids", Student.class)
//                .setParameter("ids", iterable)
//                .getResultList();
//    }
//
//    @Override
//    public long count() {
//        return entityManager.createQuery("SELECT COUNT(s) FROM Student s", Long.class)
//                .getSingleResult();
//    }
//
//    @Override
//    public void deleteById(Long aLong) {
//        Student student = entityManager.find(Student.class, aLong);
//        if (student != null) {
//            entityManager.remove(student);
//        }
//    }
//
//    @Override
//    public void delete(Student student) {
//        entityManager.remove(student);
//    }
//
//    @Override
//    public void deleteAllById(Iterable<? extends Long> iterable) {
//        List<Student> students = findAllById((Iterable<Long>) iterable);
//        students.forEach(entityManager::remove);
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends Student> iterable) {
//        iterable.forEach(entityManager::remove);
//    }
//
//    @Override
//    public void deleteAll() {
//        List<Student> students = findAll();
//        students.forEach(entityManager::remove);
//    }
//
//    @Override
//    public <S extends Student> S save(S s) {
//        entityManager.persist(s);
//        return s;
//    }
//
//    @Override
//    public <S extends Student> List<S> saveAll(Iterable<S> iterable) {
//        iterable.forEach(entityManager::persist);
//        return (List<S>) iterable;
//    }
//
//    @Override
//    public Optional<Student> findById(Long aLong) {
//        return Optional.ofNullable(entityManager.find(Student.class, aLong));
//    }
//
//    @Override
//    public boolean existsById(Long aLong) {
//        return findById(aLong).isPresent();
//    }
//
//    @Override
//    public void flush() {
//        entityManager.flush();
//    }
//
//    @Override
//    public <S extends Student> S saveAndFlush(S s) {
//        entityManager.persist(s);
//        entityManager.flush();
//        return s;
//    }
//
//    @Override
//    public <S extends Student> List<S> saveAllAndFlush(Iterable<S> iterable) {
//        iterable.forEach(entityManager::persist);
//        entityManager.flush();
//        return (List<S>) iterable;
//    }
//
//    @Override
//    public void deleteAllInBatch(Iterable<Student> iterable) {
//        iterable.forEach(entityManager::remove);
//    }
//
//    @Override
//    public void deleteAllByIdInBatch(Iterable<Long> iterable) {
//        List<Student> students = findAllById(iterable);
//        students.forEach(entityManager::remove);
//    }
//
//    @Override
//    public void deleteAllInBatch() {
//        List<Student> students = findAll();
//        students.forEach(entityManager::remove);
//    }
//
//    @Override
//    public Student getOne(Long aLong) {
//        return entityManager.getReference(Student.class, aLong);
//    }
//
//    @Override
//    public Student getById(Long aLong) {
//        return findById(aLong).orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + aLong));
//    }
//
//    @Override
//    public <S extends Student> Optional<S> findOne(Example<S> example) {
//        List<S> results = findAll(example);
//        if (results.isEmpty()) {
//            return Optional.empty();
//        }
//        return Optional.of(results.get(0));
//    }
//
//    @Override
//    public <S extends Student> List<S> findAll(Example<S> example) {
//        // Implement the logic to find students matching the example
//        // You can use JPQL or Criteria API to build the query
//        // Example using Criteria API:
//        return (List<S>) entityManager.createQuery("SELECT s FROM Student s WHERE s = :example", Student.class)
//                .setParameter("example", example)
//                .getResultList();
//    }
//
//    @Override
//    public <S extends Student> List<S> findAll(Example<S> example, Sort sort) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<S> query = cb.createQuery(example.getProbeType());
//        Root<S> root = query.from(example.getProbeType());
//        //query.select(root).where(cb.and(example.getCriteria().toArray(new Predicate[0]))).orderBy(getSortOrders(sort, cb, (Root<Student>) root));
//        TypedQuery<S> typedQuery = entityManager.createQuery(query);
//        return typedQuery.getResultList();
//    }
//
//
//    @Override
//    public <S extends Student> Page<S> findAll(Example<S> example, Pageable pageable) {
//        // Implement the logic to find students matching the example with pagination
//        // You can use JPQL or Criteria API to build the query
//        // Example using Criteria API:
//        List<S> results = (List<S>) entityManager.createQuery("SELECT s FROM Student s WHERE s = :example", Student.class)
//                .setParameter("example", example)
//                .setFirstResult((int) pageable.getOffset())
//                .setMaxResults(pageable.getPageSize())
//                .getResultList();
//        long total = count(example);
//        return new PageImpl<>(results, pageable, total);
//    }
//
//    @Override
//    public <S extends Student> long count(Example<S> example) {
//        return findAll(example).size();
//    }
//
//    @Override
//    public <S extends Student> boolean exists(Example<S> example) {
//        return !findAll(example).isEmpty();
//    }
//}
