package ba.pehli.cinema.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ba.pehli.cinema.domain.User;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	private MessageSource messageSource;
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String showRegistring(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		return "users/register";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String register(@Valid User user,BindingResult bindingResult,Model model,HttpServletRequest request,RedirectAttributes redirectAttributes,Locale locale) {
		if (bindingResult.hasErrors()) {
			String message = messageSource.getMessage("registration.error", null, locale);
			model.addAttribute("message", message);
			return "users/register";
		}
		String message = messageSource.getMessage("registration.email.message", null, locale);
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/users/register";
	}

	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	
}
