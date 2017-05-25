package com.szymczak.controller;

import com.szymczak.model.Person;
import com.szymczak.model.Role;
import com.szymczak.service.BreakfastDateService;
import com.szymczak.service.PersonService;
import com.szymczak.service.ReservationService;
import com.szymczak.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.text.ParseException;

/**
 * Created by mateu on 11.05.2017.
 */
@Controller
@Slf4j
public class SecurityController {

    final private PersonService personService;
    final private RoleService roleService;
    private final ReservationService reservationService;
    private final BreakfastDateService breakfastDateService;


    @Autowired
    public SecurityController(PersonService personService, RoleService roleService, ReservationService reservationService, BreakfastDateService breakfastDateService) {
        this.personService = personService;
        this.roleService = roleService;
        this.reservationService = reservationService;
        this.breakfastDateService = breakfastDateService;
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }


    @RequestMapping(value = {"/index", ""})
    public String index(ModelMap model, Principal principal) throws ParseException {
        model.addAttribute("breakfastDates", breakfastDateService.displayDatesByPersonEmail(principal.getName()));
        model.addAttribute("reservations", reservationService.findReservationsByPersonEmail(principal.getName()));
        return "index";
    }


    @RequestMapping(value = "/register")
    public String register() {
        return "register";
    }


    @RequestMapping(value = "/logoutUser")
    public String logout() {
        return "logoutUser";
    }


    @RequestMapping(value = "/registerNewUser", method = RequestMethod.POST)
    public String registerNewUser(ModelMap model, @ModelAttribute Person person) {

        String password = person.getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        person.setPassword(passwordEncoder.encode(password));

        Role role = roleService.findByName("CLIENT");
        person.getRoles().add(role);
        role.getPeople().add(person);
        personService.insertOrUpdate(person);
        roleService.insertOrUpdate(role);
        log.debug("Added new user: name: " + person.getName() + " email: " + person.getEmail());
        model.addAttribute("message", "user successfully added");
        return "login";

    }
}
