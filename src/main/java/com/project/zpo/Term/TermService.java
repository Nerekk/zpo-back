package com.project.zpo.Term;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TermService {
    private static TermRepository termRepository;

    @Autowired
    public TermService(TermRepository termRepository) {
        TermService.termRepository = termRepository;
    }

    public void addTerm(LocalDate localDate) {
        if (localDate != null) {
            Term term = new Term();
            term.setDate(localDate);

            termRepository.save(term);
        }
    }

    public void deleteTerm(Long id) {
        if (id != null) {
            termRepository.deleteById(id);
        }
    }

    public Term getTerm(Long id) {
        return termRepository.findById(id).orElse(null);
    }

    public Term getTerm(LocalDate localDate) {
        return termRepository.findByDate(localDate);
    }
}
