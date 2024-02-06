package com.project.zpo.Repositories;

import com.project.zpo.Tables.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Student entities in the database.
 * Extends CrudRepository providing basic CRUD operations for Student entities.
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
}
