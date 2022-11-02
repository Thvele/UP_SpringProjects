package com.example.P50519.Controllers;

import com.example.P50519.Models.Role;
import com.example.P50519.Models.User;
import com.example.P50519.Models.UserData;
import com.example.P50519.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.Collections;

@Controller
public class RegController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/registration")
    public String reg(User user_) {

        return ("registration");
    }

    @PostMapping("/registration")
    public String reg1(@Valid User user, UserData userData, BindingResult result, Model model) {

        if(result.hasErrors()){
            try{
                String fio1 = userData.getFIO().split(" ")[0];
                String fio2 = userData.getFIO().split(" ")[1];
                String fio3 = "";
                try {
                    fio3 = userData.getFIO().split(" ")[2];
                } catch (Exception e) {}
                String email = userData.getEmail();
                String phone = userData.getPhone();

                model.addAttribute("fam", fio1);
                model.addAttribute("im", fio2);
                model.addAttribute("ot", fio3);
                model.addAttribute("em", email);
                model.addAttribute("ph", phone);
            }
            catch (Exception exception){
                System.out.println(userData.getIdgender());
            }

            return ("registration");
        }

        if(userRepository.findByLogin(user.getLogin()) != null) {

            try{
                String fio1 = userData.getFIO().split(" ")[0];
                String fio2 = userData.getFIO().split(" ")[1];
                String fio3 = "";
                try {
                    fio3 = userData.getFIO().split(" ")[2];
                } catch (Exception e) {}
                String email = userData.getEmail();
                String phone = userData.getPhone();

                model.addAttribute("fam", fio1);
                model.addAttribute("im", fio2);
                model.addAttribute("ot", fio3);
                model.addAttribute("em", email);
                model.addAttribute("ph", phone);
            }
            catch (Exception exception){
                System.out.println(userData.getIdgender());
            }

            model.addAttribute("error", "Пользователь с таким логином уже существует!");
            return ("registration");
        }

        user.setProfilePic("https://i.pinimg.com/564x/ef/71/93/ef7193b1e340912369f2d584f2ca76f9.jpg");
        user.setActive(true);
        user.setUserData(userData);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        model.addAttribute("error", "Успешная регистрация!");
        return ("redirect:/login");
    }
}
