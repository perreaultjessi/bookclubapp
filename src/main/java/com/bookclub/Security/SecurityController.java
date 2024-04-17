package com.bookclub.Security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class SecurityController {
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }
    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthrntication();

        if (auth != null) {
            new SecurityContextLogouthandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
        }
    }

}
