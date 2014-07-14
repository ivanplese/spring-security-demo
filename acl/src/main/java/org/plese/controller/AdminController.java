package org.plese.controller;

import org.apache.log4j.Logger;
import org.plese.model.AdminMessage;
import org.plese.model.IMessage;
import org.plese.service.GenericService;
import org.springframework.security.access.prepost.PreAuthorize;
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
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    protected static Logger logger = Logger.getLogger(AdminController.class);

    @Resource(name="adminService")
    private GenericService adminService;

    /**
     * Retrieves the Edit page
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam(value="id", required=true) Long id,
                          Model model) {
        logger.debug("Received request to show edit page");

        // Retrieve existing post and add to model
        // This is the formBackingOBject
        model.addAttribute("messageAttribute", adminService.getSingle(id));

        // Add source to model to help us determine the source of the JSP page
        model.addAttribute("source", "Admin");

        // This will resolve to /WEB-INF/jsp/crud-admin/editpage.jsp
        return "crud-admin/editpage";
    }

    /**
     * Saves the edited post from the Edit page and returns a result page.
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String getEditPage(@ModelAttribute("messageAttribute") AdminMessage post,
                              @RequestParam(value="id", required=true) Long id,
                              Model model) {
        logger.debug("Received request to view edit page");

        // Re-assign id
        post.setId(id);
        // Assign new date
        post.setDate(new Date());

        // Delegate to service
        if (adminService.edit(post) == true) {
            // Add result to model
            model.addAttribute("result", "Entry has been edited successfully!");
        } else {
            // Add result to model
            model.addAttribute("result", "You're not allowed to perform that action!");
        }

        // Add source to model to help us determine the source of the JSP page
        model.addAttribute("source", "Admin");

        // Add our current role and username
        model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());

        // This will resolve to /WEB-INF/jsp/crud-admin/resultpage.jsp
        return "crud-admin/resultpage";
    }

    /**
     * Retrieves the Add page
     * <p>
     * Access-control is placed here (instead in the service) because we don't want
     * to show this page if the client is unauthorized and because the new
     * object doesn't have an id. The hasPermission requires an existing id!
     */
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
        logger.debug("Received request to show add page");

        // Create new post and add to model
        // This is the formBackingOBject
        model.addAttribute("messageAttribute", new AdminMessage());

        // Add source to model to help us determine the source of the JSP page
        model.addAttribute("source", "Admin");

        // This will resolve to /WEB-INF/pages/crud-admin/addpage.jsp
        return "crud-admin/addpage";
    }

    /**
     * Saves a new post from the Add page and returns a result page.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String getAddPage(@ModelAttribute("messageAttribute") AdminMessage post, Model model) {
        logger.debug("Received request to view add page");

        // Add date today
        post.setDate(new Date());

        // Delegate to service
        if (adminService.add(post)) {
            // Success. Add result to model
            model.addAttribute("result", "Entry has been added successfully!");
        } else {
            // Failure. Add result to model
            model.addAttribute("result", "You're not allowed to perform that action!");
        }

        // Add source to model to help us determine the source of the JSP page
        model.addAttribute("source", "Admin");

        // Add our current role and username
        model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());

        // This will resolve to /WEB-INF/pages/crud-admin/resultpage.jsp
        return "crud-admin/resultpage";
    }

    /**
     * Deletes an existing post and returns a result page.
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String getDeletePage(@RequestParam(value="id", required=true) Long id,
                                Model model) {
        logger.debug("Received request to view delete page");

        // Create new post
        IMessage post = new AdminMessage();
        // Assign id
        post.setId(id);

        // Delegate to service
        if (adminService.delete(post)) {
            // Add result to model
            model.addAttribute("result", "Entry has been deleted successfully!");
        } else {
            // Add result to model
            model.addAttribute("result", "You're not allowed to perform that action!");
        }

        // Add source to model to help us determine the source of the JSP page
        model.addAttribute("source", "Admin");

        // Add our current role and username
        model.addAttribute("role", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());

        // This will resolve to /WEB-INF/pages/crud-admin/resultpage.jsp
        return "crud-admin/resultpage";
    }

    public GenericService getAdminService() {
        return adminService;
    }

    public void setAdminService(GenericService adminService) {
        this.adminService = adminService;
    }
}
