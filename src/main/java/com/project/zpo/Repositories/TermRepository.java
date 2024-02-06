package com.project.zpo.Repositories;

import com.project.zpo.Tables.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;

/**
 * Repository interface for managing Term entities in the database.
 * Extends JpaRepository providing basic CRUD operations for Term entities.
 */
@Repository
public interface TermRepository extends JpaRepository<Term, Long> {

    /**
     * Custom query method to find a term by its date.
     *
     * @param date The date of the term to search for.
     * @return The Term object representing the term with the specified date.
     */
    @Query("SELECT t FROM Term t WHERE t.date = :date")
    Term findByDate(@PathVariable("date")LocalDate date);
}
