package shop.bookbom.front.domain.address.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import shop.bookbom.front.domain.address.dto.request.AddressRequest;
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

    @GetMapping("/users/address/add")
    public String addAddressPage(@ModelAttribute("addressRequest") AddressRequest addressRequest) {
        return "page/user/address-add";
    }

    @PostMapping("/users/address/add")
    public String addAddress(@ModelAttribute @Valid AddressRequest addressRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "page/user/address-add";
        }
        addressService.saveAddress(addressRequest);
        return "redirect:/users/address";
    }
}
