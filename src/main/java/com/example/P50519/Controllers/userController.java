package com.example.P50519.Controllers;

import com.example.P50519.Models.Car;
import com.example.P50519.Models.Role;
import com.example.P50519.Models.User;
import com.example.P50519.Models.UserData;
import com.example.P50519.Repositories.GenderRepository;
import com.example.P50519.Repositories.UserDataRepository;
import com.example.P50519.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class userController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserDataRepository userDataRepository;
    @Autowired
    GenderRepository genderRepository;

    @GetMapping("/user")
    private String userView(Model model){

        model.addAttribute("listObject", userRepository.findAll());
        return("userTableEdit/user/tableView");
    }

    @GetMapping("/userTableEdit/user/EDT/{id}")
    private String fuelTypeADD(@PathVariable long id, Model model) {

        model.addAttribute("user", userRepository.findById(id).orElseThrow());
        model.addAttribute("listRoles", Role.values());

        User user = userRepository.findById(id).orElseThrow();
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

        return ("userTableEdit/user/EDT");
    }

    @PostMapping("/userTableEdit/user/EDT/{id}")
    private String fuelTypeADD(@PathVariable long id,
                               @RequestParam String login,
                               @RequestParam String active,
                               @RequestParam String FIO,
                               @RequestParam long idgender,
                               @RequestParam String email,
                               @RequestParam String phone,
                               @RequestParam String[] roles,
                               @RequestParam String imgURL) {


        User user = userRepository.findById(id).orElseThrow();
        UserData userData = userDataRepository.findById(user.getUserData().getIddata()).orElseThrow();

        userData.setIdgender(genderRepository.findById(idgender).orElseThrow());
        userData.setFIO(FIO);
        userData.setEmail(email);
        userData.setPhone(phone);

        user.setUserData(userData);

        if(imgURL.isEmpty())
            imgURL = "https://i.pinimg.com/564x/ef/71/93/ef7193b1e340912369f2d584f2ca76f9.jpg";

        user.setLogin(login);

        if(active.equals("true"))
            user.setActive(true);
        else
            user.setActive(false);

        user.getRoles().clear();

        for(String role: roles){
            user.getRoles().add(Role.valueOf(role));
        }

        user.setProfilePic(imgURL);

        userRepository.save(user);
        return ("redirect:/user");
    }

    @GetMapping("/userTableEdit/user/DEL/{id}")
    private String transmissionDEL(@PathVariable long id){

        userRepository.deleteById(id);
        return ("redirect:/user");
    }
}
