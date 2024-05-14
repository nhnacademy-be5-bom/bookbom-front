package shop.bookbom.front.domain.address.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.bookbom.front.domain.address.service.AddressService;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/users/address")
    public String addresses(Model model) {
        model.addAttribute("addresses", addressService.getAddressBook());
        return "page/user/address";
    }
}
