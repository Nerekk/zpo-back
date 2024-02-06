package com.project.zpo.Services;

import com.project.zpo.Tables.Term;
import com.project.zpo.Repositories.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Service class responsible for handling term-related business logic.
 * It interacts with the database via the {@link TermRepository} and provides various operations related to terms.
 */
@Service
public class TermService {

    /**
     * Repository for managing Term entities in the database.
     */
    private static TermRepository termRepository;

    /**
     * Automatic constructor for the TermService class.
     *
     * @param termRepository The TermRepository instance to be used by the service.
     */
    @Autowired
    public TermService(TermRepository termRepository) {
        TermService.termRepository = termRepository;
    }

    /**
     * Method for adding a new term with the specified date.
     *
     * @param localDate The date of the term to be added.
     * @return True if the term was successfully added, false otherwise.
     */
    public static boolean addTerm(LocalDate localDate) {

        if (localDate == null)
            return false;

        Term term = new Term();
        term.setDate(localDate);

        termRepository.save(term);

        return true;
    }

    /**
     * Method for deleting an existing term with the specified ID.
     *
     * @param id The ID of the term to be deleted.
     * @return True if the term was successfully deleted, false otherwise.
     */
    public static boolean deleteTerm(Long id) {

        if (id == null)
            return false;

        Optional<Term> term = termRepository.findById(id);
        if (term.isEmpty())
            return false;

        termRepository.deleteById(id);

        return true;
    }

    /**
     * Method for retrieving a term by its ID.
     *
     * @param id The ID of the term to be retrieved.
     * @return The Term object representing the term with the given ID, or null if not found.
     */
    public static Term getTerm(Long id) {
        return termRepository.findById(id).orElse(null);
    }

    /**
     * Method for retrieving a term by its date.
     *
     * @param localDate The date of the term to be retrieved.
     * @return The Term object representing the term with the given date, or null if not found.
     */
    public static Term getTerm(LocalDate localDate) {
        return termRepository.findByDate(localDate);
    }
}
