package com.project.zpo.Term;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

@Repository
public interface TermRepository extends JpaRepository<Term, Long> {
    @Query("SELECT t FROM Term t WHERE t.date = :date")
    Term findByDate(@PathVariable("date")LocalDate date);
}
