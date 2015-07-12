package it.polimi.awt.mpcs.controller;

import it.polimi.awt.mpcs.service.GalleryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GalleryController {
	
	@Autowired
	private GalleryService galleryService;
	
	@RequestMapping(value = { "/", "/index" })
	public String homePage(){
		return "home";
	}
	@RequestMapping("/photo/{id}")
	public String photoDetail(Model model, @PathVariable("id") int id){
		
		model.addAttribute("mountain", galleryService.getPhoto(id));
		
		return "photo";
	}
	@RequestMapping("/search")
	public String searchGallery(Model model){
		
		model.addAttribute("mountains", galleryService.search());
		
		return "mapGallery";
	}
	@RequestMapping("/gallery")
	public String openGallery(Model model){
		
		model.addAttribute("gallery", "public");
		model.addAttribute("mountains", galleryService.getPhotos());
		
		return "mapGallery";
	}
	@RequestMapping("/wrongphoto/{id}")
	public String isWrong(Model model, @PathVariable("id") int id, RedirectAttributes redirectAttributes){
		
		galleryService.isWrong(id);

		redirectAttributes.addFlashAttribute("esitoNegativo", "This photo will no longer appear in future research! Click BACK button to go back to the Gallery.");
		
		return "redirect:/photo/"+id;
	}

}
