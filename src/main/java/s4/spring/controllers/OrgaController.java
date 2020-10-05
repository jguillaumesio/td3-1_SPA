package s4.spring.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.jeemv.springboot.vuejs.VueJS;
import io.github.jeemv.springboot.vuejs.annotations.VueJSInstance;
import io.github.jeemv.springboot.vuejs.utilities.Http;
import s4.spring.models.Organization;
import s4.spring.repositories.GroupeRepository;
import s4.spring.repositories.OrgaRepository;
import s4.spring.repositories.UserRepository;

@Controller
@RequestMapping("/orgas")
public class OrgaController {
    
	@Autowired
	private VueJS vue;
	@Autowired
	private OrgaRepository orgaRepo;
    
    @RequestMapping("/")
    public String index(ModelMap model) {
		vue.addData("orgas", orgaRepo.findAll());
		vue.addDataRaw("expanded","[]");
		vue.addDataRaw("singleExpand","false");
		vue.addDataRaw("headers","[\n"
		+"                        	 { text: 'Name', align: 'start', value: 'name'},\n"
		+"                           { text: 'Aliases', value: 'aliases' },\n"
		+"                           { text: 'Domain', value: 'domain' },\n"
		+"                           { text: 'Actions', value: 'actions', sortable: false },\n"
		+"							 { text: '', value: 'data-table-expand', sortable: false }\n"
		+"                         ]");
		vue.addDataRaw("dialog","false");
		vue.addDataRaw("editedItem","{\n"
		+"    id: null,\n"
	    +"    name: null,\n"
	    +"    domain: null,\n"
	    +"    aliases: null,\n"
	    +"  }");
		vue.addDataRaw("defaultdItem","{\n"
		+"    id: null,\n"
	    +"    name: null,\n"
	    +"    domain: null,\n"
	    +"    aliases: null,\n"
	    +"  }");
		vue.addDataRaw("confirmDelDialog","false");
		//vue.onBeforeMount("let self=this;"+Http.get("http://127.0.0.1:8080/rest/orgas/","self.orgas=response.data;"));
		vue.addMethod("initialize","let self=this;"+Http.get("http://127.0.0.1:8080/rest/orgas/","self.orgas=response.data;"));
		vue.addMethod("editItem","this.editedIndex = this.orgas.indexOf(item)\n"
		+ "        this.editedItem.id=item.id \n"
		+ "        this.editedItem.name=item.name \n"
		+ "        this.editedItem.aliases=item.aliases \n"
		+ "        this.editedItem.domain=item.domain \n"
		+ "        this.dialog = true","item");
		vue.addMethod("deleteItem", "let self=this;this.$http['delete']('http://127.0.0.1:8080/rest/orgas/delete/'+item.id);let index=self.orgas.indexOf(item);self.orgas.splice(index,1);", "item");
		vue.addMethod("save","if (this.editedIndex > -1) {\n"
		+"		this.$http['put']('http://127.0.0.1:8080/rest/orgas/update',this.editedItem,'')\n"	
		+"		Object.assign(this.orgas[this.editedIndex], this.editedItem)}\n"
		+"		else {\n"
		+"		this.$http['post']('http://127.0.0.1:8080/rest/orgas/create',this.editedItem,'')\n"
		+"      this.orgas.push(this.editedItem)}\n"
		+"      this.close()");
		vue.addMethod("close","this.dialog = false;this.$nextTick(() => {\n"
		+"		this.editedItem = Object.assign({}, this.defaultItem)\n"
		+"		this.editedIndex = -1})");
		vue.addMethod("counter", "var count=0;var count=document.getElementById(divName).getElementsByTagName('*').length;alert(count)","divName");
		vue.addComputed("formTitle","return this.editedIndex === -1 ? 'Nouvelle organisation' : 'Modifier'");
		vue.addWatcher("dialog", "val || this.close()");
		vue.addWatcher("created", "this.initialize()");
		model.put("vue", vue);
		return "index";
	}
}
