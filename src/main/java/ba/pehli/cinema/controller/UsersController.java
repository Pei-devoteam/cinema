package ba.pehli.cinema.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ba.pehli.cinema.domain.User;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	private MessageSource messageSource;
	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;
	
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
		try {
			sendEmail(user);
		} catch(MessagingException e) {
			redirectAttributes.addFlashAttribute("message", e.getMessage());
			return "redirect:/users/register";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/users/register";
	}
	
	private void sendEmail(User user) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");
		helper.setFrom("Administrator");
		helper.setTo(user.getUsername());
		helper.setSubject("Registracija");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("username", user.getUsername());
		String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "email/templateRegistration.vm", "UTF-8", params);
		helper.setText(text,true);
		
		mailSender.send(message);
	}

	@Autowired
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	@Autowired
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	
	
}
