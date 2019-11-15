package quiz.quiz_game.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import quiz.quiz_game.model.User;
import quiz.quiz_game.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class EditDataViewController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("editData")
    public ModelAndView editDataView(HttpServletRequest req, Model model) {

        User user = findById(req);
        if (user != null) {
            model.addAttribute("user", user);
            return new ModelAndView("editData");
        }

        model.addAttribute("messageType", "danger");
        model.addAttribute("message", "Not found");
        return new ModelAndView("editData");
    }

    @PostMapping("editData")
    public ModelAndView editData(String firstName, String lastName, String email, String password1, String password2, Model model, HttpServletRequest req) {
        User user = findById(req);
        if (user != null) {
            return editData(user, firstName, lastName, email, password1, password2, model);
        } else {
            return new ModelAndView("login");
        }
    }

    private User findById(HttpServletRequest req) {
        Integer id = (Integer) req.getSession().getAttribute("id");
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        }
        return null;
    }

    private ModelAndView editData(User user, String firstName, String lastName, String email, String password1, String password2, Model model) {
        if (!user.getEmail().equals(email)) {
            if (userRepository.findByEmail(email) != null) {
                model.addAttribute("user", user);
                model.addAttribute("messageType", "danger");
                model.addAttribute("message", "Email already exist");
                return new ModelAndView("editData");
            }
        }

        if (!Arrays.equals(password1.toCharArray(), password2.toCharArray())) {
            model.addAttribute("user", user);
            model.addAttribute("messageType", "danger");
            model.addAttribute("message", "Passwords are different");
            return new ModelAndView("editData");
        }

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password1.toCharArray());
        userRepository.save(user);

        model.addAttribute("user", user);
        model.addAttribute("messageType", "success");
        model.addAttribute("message", "Data changed");
        return new ModelAndView("myAccount");
    }
}
