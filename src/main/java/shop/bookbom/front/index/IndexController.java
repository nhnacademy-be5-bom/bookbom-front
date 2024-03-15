package shop.bookbom.front.index;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @GetMapping(value = {"/index.html","/"})
    public String index(HttpServletRequest request, Model model){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null ){
            ip = request.getRemoteAddr();
        }
        model.addAttribute("ip",ip);
        return "index/index";
    }
}
