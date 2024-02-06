package com.project.zpo.Repositories;

import com.project.zpo.Tables.Attendance;
import com.project.zpo.Tables.Student;
import com.project.zpo.Tables.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Repository interface for managing Attendance entities in the database.
 * Extends JpaRepository providing basic CRUD operations for Attendance entities.
 */
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Attendance> {

    /**
     * Custom query method to find attendance by term and student.
     *
     * @param term    The term for which attendance is being searched.
     * @param student The student for whom attendance is being searched.
     * @return The Attendance object representing the attendance of the specified student in the given term.
     */
    @Query("SELECT a FROM Attendance a WHERE a.term = :term and a.student = :student")
    Attendance findByTerm(@PathVariable("term") Term term, @PathVariable("student") Student student);

}
