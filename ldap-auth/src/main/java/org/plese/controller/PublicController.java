package org.plese.controller;

import org.apache.log4j.Logger;
import org.plese.model.IMessage;
import org.plese.model.PublicMessage;
import org.plese.service.GenericService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by iplese
 * Controls all the request related to the public messages.
 */
@Controller
@RequestMapping("/public")
public class PublicController {
    protected static Logger logger = Logger.getLogger("controller");

    @Resource(name="publicService")
    private GenericService publicService;

    /**
     * Retrieves the Edit page
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam(value="id", required=true) Long id,
                          Model model) {
        logger.debug("Received request to show edit page");

        // Retrieve existing message and add to model
        // This is the form the backing object
        model.addAttribute("messageAttribute", publicService.getSingle(id));

        // Add source to model to help us determine the source of the JSP page
        model.addAttribute("source", "Public");

        // Add user details and his authorities
        model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());

        // This will resolve to /WEB-INF/pages/crud-public/editpage.jsp
        return "crud-public/editpage";
    }

    /**
     * Saves the edited message from the Edit page and returns a result page.
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String getEditPage(@ModelAttribute("messageAttribute") PublicMessage message,
                              @RequestParam(value="id", required=true) Long id,
                              Model model) {
        //logger.debug("Received request to view edit page");

        // Re-assign id
        message.setId(id);
        // Assign new date
        message.setDate(new Date());

        // Delegate to service
        if (publicService.edit(message)) {
            // Add result to model
            model.addAttribute("result", "Entry has been edited successfully!");
        } else {
            // Add result to model
            model.addAttribute("result", "You're not allowed to perform that action!");
        }

        // Add source to model to help us determine the source of the JSP page
        model.addAttribute("source", "Public");

        // Add user details and his authorities
        model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());

        // This will resolve to /WEB-INF/pages/crud-public/resultpage.jsp
        return "crud-public/resultpage";
    }

    /**
     * Retrieves the Add page
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
        logger.debug("Received request to show add page");

        // Create new message and add to model
        // This is from the backing object
        model.addAttribute("messageAttribute", new PublicMessage());

        // Add source to model to help us determine the source of the JSP page
        model.addAttribute("source", "Public");

        // Add user details and his authorities
        model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());

        // This will resolve to /WEB-INF/pages/crud-public/addpage.jsp
        return "crud-public/addpage";
    }

    /**
     * Saves a new message from the Add page and returns a result page.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String getAddPage(@ModelAttribute("messageAttribute") PublicMessage message, Model model) {
        logger.debug("Received request to view add page");

        // Add date today
        message.setDate(new Date());

        // Delegate to service
        if (publicService.add(message)) {
            // Success. Add result to model
            model.addAttribute("result", "Entry has been added successfully!");
        } else {
            // Failure. Add result to model
            model.addAttribute("result", "You're not allowed to perform that action!");
        }

        // Add source to model to help us determine the source of the JSP page
        model.addAttribute("source", "Public");

        // Add user details and his authorities
        model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());

        // This will resolve to /WEB-INF/jsp/crud-public/resultpage.jsp
        return "crud-public/resultpage";
    }

    /**
     * Deletes an existing message and returns a result page.
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDeletePage(@RequestParam(value="id", required=true) Long id,
                                Model model) {
        logger.debug("Received request to view delete page");

        // Create new message
        IMessage message = new PublicMessage();
        // Assign id
        message.setId(id);

        // Delegate to service
        if (publicService.delete(message)) {
            // Add result to model
            model.addAttribute("result", "Entry has been deleted successfully!");
        } else {
            // Add result to model
            model.addAttribute("result", "You're not allowed to perform that action!");
        }

        // Add source to model to help us determine the source of the JSP page
        model.addAttribute("source", "Public");

        // Add user details and his authorities
        model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());

        // This will resolve to /WEB-INF/page/crud-public/resultpage.jsp
        return "crud-public/resultpage";
    }
}
