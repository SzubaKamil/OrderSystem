package com.company.ordersystem.controller.user;

import com.company.ordersystem.entity.user.OutlookSetting;
import com.company.ordersystem.entity.user.User;
import com.company.ordersystem.entity.user.UserChangePassword;
import com.company.ordersystem.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping("/change-password")
    public String changePassword (Model model){

        UserChangePassword user = new UserChangePassword();

        model.addAttribute("user", user);
        model.addAttribute("actionDescription", "ZMIANA HASŁA");
        model.addAttribute("action", "/save/");

        return "/password-change-form";
    }

    @RequestMapping("/save/")
    public String save(@ModelAttribute("user") UserChangePassword user, Model model) {
        String description = "Zmiana hasła ";

        return saveOrUpdate(user, description, model);
    }

    @RequestMapping("/save-setting/")
    public String saveSetting(@ModelAttribute("outlookSetting") OutlookSetting outlookSetting, Model model) {
        String description = "Zmiana ustawień poczty ";

        return saveOrUpdate(outlookSetting, description, model);
    }

    @RequestMapping("/settings")
    public String settings (Model model){
        OutlookSetting outlookSetting = getCurrentUser().getOutlookSetting();

        model.addAttribute("outlookSetting", outlookSetting);
        model.addAttribute("actionDescription", "USTAWIENIA");
        model.addAttribute("action", "/save-setting/");

        return "/user/setting-form";
    }

    private String saveOrUpdate(UserChangePassword user, String description, Model model) {
        boolean result;
        User userDb = userService.get(getCurrentUser().getUsername());

        if (user.isEqualNewPassword(userDb.getPassword())){
            userDb.setPassword(user.getNewPassword());
            result = userService.saveOrUpdate(userDb);
        }
        else {
            result = false;
        }

        model.addAttribute("result", result);
        model.addAttribute("description", description);

        return "result";
    }

    private String saveOrUpdate(OutlookSetting outlookSetting, String description, Model model) {
        User userDb = userService.get(getCurrentUser().getUsername());
        userDb.getOutlookSetting().setSettings(outlookSetting);

        boolean result = userService.saveOrUpdate(userDb);

        model.addAttribute("result", result);
        model.addAttribute("description", description);

        return "result";
    }

    private User getCurrentUser (){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.get(username);
    }
}
