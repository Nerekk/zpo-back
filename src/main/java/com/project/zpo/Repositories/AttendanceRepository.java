package com.project.zpo.Repositories;

import com.project.zpo.Tables.Attendance;
import com.project.zpo.Tables.Student;
import com.project.zpo.Tables.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Attendance> {


    @Query("SELECT a FROM Attendance a WHERE a.term = :term and a.student = :student")
    Attendance findByTerm(@PathVariable("term") Term term, @PathVariable("student") Student student);

}
