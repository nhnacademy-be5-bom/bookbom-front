package shop.bookbom.front.index;

import feign.Headers;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.bookbom.front.domain.book.dto.request.BookAddRequest;
import shop.bookbom.front.domain.book.service.BookService;
import shop.bookbom.front.domain.category.dto.CategoryDTO;
import shop.bookbom.front.domain.category.service.CategoryService;
import shop.bookbom.front.domain.pointrate.dto.PointRate;
import shop.bookbom.front.domain.pointrate.service.PointRateService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final BookService bookService;
    private final CategoryService categoryService;
    private final PointRateService pointRateService;

    @GetMapping("")
    public String showAdminMainPage() {

        return "page/admin/adminMain";
    }

    @GetMapping("/addbook")
    public String addBookPage(HttpServletRequest request, Model model) {
        List<CategoryDTO> list_depth1 = categoryService.getDepthOneCategories();

        model.addAttribute("categories_depth1", list_depth1);

        return "page/book/addbook";
    }

    @PutMapping("/addbook")
    @Headers("Content-Type: multipart/form-data")
    public String putBook(@ModelAttribute("bookAddRequest") BookAddRequest bookAddRequest,
                          Model model) {

        bookService.putBook(bookAddRequest);
        //model.addAttribute("success", true);

        return "page/book/addbook";
    }

    @GetMapping("/point-rate")
    public String showPointPolicy(Model model) {
        List<PointRate> pointPolicies = pointRateService.getPointPolicies();
        model.addAttribute("policies", pointPolicies);
        return "page/admin/point_rate";
    }
}
