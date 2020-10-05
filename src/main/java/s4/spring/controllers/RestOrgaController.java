package s4.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import s4.spring.models.Groupe;
import s4.spring.models.Organization;
import s4.spring.repositories.GroupeRepository;
import s4.spring.repositories.OrgaRepository;

@CrossOrigin
@RestController
@RequestMapping("rest/orgas")
public class RestOrgaController {
	
    @Autowired
    private OrgaRepository orgaRepo;
    
    @RequestMapping("/")
    @ResponseBody
	public List<Organization> read() {
    	return orgaRepo.findAll();
	}
	
    @RequestMapping("/{id}")
    @ResponseBody
	public List<Organization> read(@PathVariable int id) {
		return orgaRepo.findById(id);
	}
	
	@PostMapping("/create")
	@ResponseBody
	public Organization create(Organization orga) {
		orgaRepo.saveAndFlush(orga);
		return orga;
	}
	
	@PutMapping("/update")
	@ResponseBody
	public Organization update(@RequestBody Organization orga) {
		orgaRepo.save(orga);
		return orga;
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public void delete(@PathVariable int id) {
		orgaRepo.deleteById(id);
	}
	
    @RequestMapping("{member}/{id}")
    @ResponseBody
	public String get(@PathVariable int id,@PathVariable String member) {
		Organization orga=orgaRepo.findById(id).get(0);
		String retour="";
		switch(member) {
		  case "name":
			  retour = orga.getName().toString();
		  case "domain":
			  retour = orga.getDomain().toString();
		  case "aliases":
			  retour = orga.getAliases().toString();
		}
		return retour;
	}

}
