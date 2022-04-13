package com.example.demo.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.global.GlobalData;
import com.example.demo.model.Contact;
import com.example.demo.model.CustomUserDetail;
import com.example.demo.model.User;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ContactService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

@Controller
public class HomeController {

	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ContactService contactService;
	
	@Autowired
	UserService userService;
	
	
	
	@GetMapping({"/","/home"})
	public String home(Model model)
	{
		model.addAttribute("cartCount",GlobalData.cart.size());

		return "index";
	}
	
	@GetMapping({"/about"})
	public String about(Model model)
	{
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("contact",new Contact());
		return "aboutus";
	}
	
	@PostMapping("/addContactDeatils")
	public String addcontactDeatails(@ModelAttribute("contact") Contact contact, BindingResult result, Model model, RedirectAttributes rdAtt)
	{
		
		
	
		if(result.hasErrors())
		{
            return "contactus";
		}
	
	else
	{
		Contact savedContact=contactService.addContact(contact);
		rdAtt.addFlashAttribute("message","Thanks for contacting us, we will contact you soon .\n Your Reuqest ID:REQ0000");
		return "index";
	
	
	}
	}
	
	
	
	@GetMapping({"/contact"})
	public String contact(Model model)
	{
		model.addAttribute("cartCount",GlobalData.cart.size());

		return "contactus";
	}
	
	
	
	
	
	@GetMapping("/shop")
	public String shop(Model model)
	{
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProducts());
		model.addAttribute("cartCount",GlobalData.cart.size());

		return "shop";
	}
	
	
	@GetMapping("/shop/category/{id}")
	public String shopByCategory(Model model, @PathVariable int id)
	{
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProductsByCategoryId(id));
		model.addAttribute("cartCount",GlobalData.cart.size());

		return "shop";
	}
	
	
	
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model, @PathVariable int id)
	{
		model.addAttribute("product", productService.getProductById(id).get());
		model.addAttribute("cartCount",GlobalData.cart.size());

		return "viewProduct";
	}
	
	
	// your profile handler
	@GetMapping("/profile")
	public String showProfilePage(@AuthenticationPrincipal CustomUserDetail loggedUser,Model model) {
	
		String email= loggedUser.getUsername();
		Optional<User> user= userService.getByEmail(email);
		

		return "profile";
	}
	
	
	
	
	
	
	
}
