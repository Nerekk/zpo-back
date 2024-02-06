package com.project.zpo.Controllers;

import com.project.zpo.Services.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class responsible for handling term-related HTTP requests.
 * This controller is mapped to the "/terms" endpoint.
 * <p>
 * The controller provides endpoints for managing terms.
 * It utilizes the {@link TermService} layer to handle business logic related to term management.
 */
@RestController
@RequestMapping(path = "terms")
@CrossOrigin
public class TermController {
    /**
     * Service responsible for handling term-related business logic.
     */
    public final TermService termService;

    /**
     * Automatic constructor for the TermController class.
     *
     * @param termService The TermService instance to be used by the controller.
     */
    @Autowired
    public TermController(TermService termService) {
        this.termService = termService;
    }
}
