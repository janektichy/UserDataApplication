package com.example.demo.Controllers;

import com.example.demo.Models.FilterParams;
import com.example.demo.Models.User;
import com.example.demo.Services.FilterService;
import com.example.demo.Services.PagingService;
import com.example.demo.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/users")
@Controller
public class HomeController {

    private UserService userService;
    private PagingService pagingService;
    private FilterService filterService;
    @Autowired
    public HomeController(UserService userService, PagingService pagingService, FilterService filterService){
        this.userService = userService;
        this.pagingService = pagingService;
        this.filterService = filterService;
    }

    @GetMapping("")
    public String homePage(Model model, HttpServletRequest request, Integer page){
        // Retrieving data from session, if null, filtering was not used
        HttpSession session = request.getSession();
        FilterParams filterParams = (FilterParams) session.getAttribute("filterParams");
        List<User> users;

        if (filterParams == null){
            users = userService.getAllUsers();
            filterParams = new FilterParams();
        }

        else{
            users = userService.filterUsers(filterParams);
        }

        // If no Users are found (in case of empty repo or wrong filtering), error message is sent to template with empty FilterParams object - to prevent null exception in template
        if (users.isEmpty()){
            model.addAttribute("errorMessage", "No Users available");
            model.addAttribute("filterParams", filterParams);
        }
        else{
            //Dividing Users to pages and selecting page to show in template
            List<List<User>> pages = pagingService.providePages(users, 10);
            long pageCount = pages.stream().count();
            if (page == null || page < 1){
                page = 1;
            }else if(page > pageCount){
                page = (int) pageCount;
            }
            model.addAttribute("users", pages.get(page-1));
            model.addAttribute("amountOfPages", pageCount);
            model.addAttribute("page", page);
            model.addAttribute("filterParams", filterParams);
        }
        return "UserViews/home";
    }
    @PostMapping("/filter")
    public String filterUsers(HttpServletRequest request, String from, String to, String status, String search, String preset) {
        // Saving filter parameters to Session - so it survives any number of requests and the filtering result is not deleted by paging/deleting/editing or anything else
        FilterParams filterParams = filterService.validateFilters(status, search, preset, from, to);
        HttpSession session = request.getSession();
        session.setAttribute("filterParams", filterParams);
        return "redirect:/users";
    }
    @PostMapping("/reset")
    public String resetFilters(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/users";
    }
    @PostMapping("/status")
    public String activateOrDeactivateUser(boolean changeStatus, Integer id, Integer page){
        userService.changeUserStatus(changeStatus, id);
        return "redirect:/users?page=" + page.toString();
    }
    @PostMapping("/delete")
    public String deleteUser(Integer page, Integer id){
        userService.deleteUser(id);
        return "redirect:/users?page=" + page.toString();
    }
    @PostMapping("/edit")
    public String chooseUserToEdit(Integer page, Integer id, RedirectAttributes redirectAttributes){
        User userToEdit = userService.findUserById(id);
        redirectAttributes.addFlashAttribute("userToEdit", userToEdit);
        return "redirect:/users?page=" + page.toString();
    }
    @PostMapping("/edit/save")
    public String editUser(Integer page, Integer id, String name, String surname, String email, String preset, String phoneNumber){
        userService.editUser(id, name, surname, email, preset, phoneNumber);
        return "redirect:/users?page=" + page.toString();
    }
}
