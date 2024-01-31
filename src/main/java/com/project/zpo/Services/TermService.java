package com.project.zpo.Services;

import com.project.zpo.Tables.Term;
import com.project.zpo.Repositories.TermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TermService {
    private static TermRepository termRepository;

    @Autowired
    public TermService(TermRepository termRepository) {
        TermService.termRepository = termRepository;
    }

    public static boolean addTerm(LocalDate localDate) {

        if (localDate == null)
            return false;

        Term term = new Term();
        term.setDate(localDate);

        termRepository.save(term);

        return true;
    }

    public static boolean deleteTerm(Long id) {

        if (id == null)
            return false;

        Optional<Term> term = termRepository.findById(id);
        if (term.isEmpty())
            return false;

        termRepository.deleteById(id);

        return true;
    }

    public static Term getTerm(Long id) {
        return termRepository.findById(id).orElse(null);
    }

    public static Term getTerm(LocalDate localDate) {
        return termRepository.findByDate(localDate);
    }
}