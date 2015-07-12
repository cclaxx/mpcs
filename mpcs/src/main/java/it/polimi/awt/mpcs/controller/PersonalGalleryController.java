package it.polimi.awt.mpcs.controller;

import it.polimi.awt.mpcs.service.GalleryService;
import it.polimi.awt.mpcs.service.PersonalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class PersonalGalleryController {
	
	@Autowired
	private PersonalService personalService;
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping("")
	public String mapGallery(){
		return "home";
	}
	@RequestMapping("/map")
	public String myMapGallery(Model model){
		
		model.addAttribute("gallery", "personal");
		model.addAttribute("mountains", personalService.personalGallery());
		
		return "mapGallery";
	}
	@RequestMapping("/save/{id}")
	public String savePhoto(Model model, @PathVariable("id") int id, RedirectAttributes redirectAttributes){
		
		personalService.savePhoto(id);
		
		model.addAttribute("mountain", galleryService.getPhoto(id));
		
		redirectAttributes.addFlashAttribute("esitoPositivo", "Photo saved on your personal gallery!");
		
		return "redirect:/photo/"+id;
	}
	public String photoDetail(){
		return null;
	}
	
	@RequestMapping("/delete/{id}")
	public String deletePhoto(Model model, @PathVariable("id") int id, RedirectAttributes redirectAttributes){

		personalService.deletePhoto(id);
		
		model.addAttribute("mountain", galleryService.getPhoto(id));
		
		redirectAttributes.addFlashAttribute("esitoPositivo", "Photo deleted from your personal gallery!");
		
		return "redirect:/photo/"+id;
	}
}
