package com.example.P50519.Controllers;

import com.example.P50519.Models.User;
import com.example.P50519.Models.UserData;
import com.example.P50519.Repositories.GenderRepository;
import com.example.P50519.Repositories.UserDataRepository;
import com.example.P50519.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class ProfileController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDataRepository userDataRepository;
    @Autowired
    GenderRepository genderRepository;

    @GetMapping("/profile")
    private String profile(Model model, Principal principal){
        User user = userRepository.findByLogin(principal.getName());
        UserData userData = user.getUserData();

        try{
            String fio1 = userData.getFIO().split(" ")[0];
            String fio2 = userData.getFIO().split(" ")[1];
            String fio3 = "";
            try{
                fio3 = userData.getFIO().split(" ")[2];
            } catch (Exception e) {}
            String email = userData.getEmail();
            String phone = userData.getPhone();
            String profIcon = user.getProfilePic();

            if(profIcon.isEmpty())
                profIcon = "https://i.pinimg.com/564x/ef/71/93/ef7193b1e340912369f2d584f2ca76f9.jpg";

            model.addAttribute("fam", fio1);
            model.addAttribute("im", fio2);
            model.addAttribute("ot", fio3);
            model.addAttribute("em", email);
            model.addAttribute("ph", phone);
            model.addAttribute("pp", profIcon);
        }
        catch (Exception exception){}

        model.addAttribute("user", user);

        return ("profile");
    }

    @PostMapping("/profileEDT")
    private String profileEDT(@RequestParam Long id,
                              @RequestParam String FIO,
                              @RequestParam String email,
                              @RequestParam String phone,
                              @RequestParam String imgURL,
                              @RequestParam Long idgender) {

        User user = userRepository.findById(id).orElseThrow();
        UserData userData = user.getUserData();

        userData.setFIO(FIO);
        userData.setEmail(email);
        userData.setPhone(phone);
        userData.setIdgender(genderRepository.findById(idgender).orElseThrow());
        user.setProfilePic(imgURL);

        user.setUserData(userData);
        userRepository.save(user);
        return ("redirect:/profile");
    }
}
