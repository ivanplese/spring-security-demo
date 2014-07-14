package org.plese.controller;

import org.apache.log4j.Logger;
import org.plese.service.GenericService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by iplese
 * Handles all the request related to Messages
 */
@Controller
@RequestMapping("/messages")
public class MessageController {
   protected static Logger logger = Logger.getLogger("controller");

    @Resource(name="publicService")
    private GenericService publicService;

    /**
     * Retrieves the View page.
     */
    @RequestMapping(value = "/overview", method = RequestMethod.GET)
    public String getViewAllPage(Model model) {
       logger.debug("Received request to view all page");

        // Retrieve items from service and add to model
        model.addAttribute("publicmessages", publicService.getAll());

        model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());

        // This will resolve to /WEB-INF/pages/overview.jsp
        return "overview";
    }
}
