package com.project.zpo.Term;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "terms")
public class TermController {
    public final TermService termService;

    @Autowired
    public TermController(TermService termService) {
        this.termService = termService;
    }
}
