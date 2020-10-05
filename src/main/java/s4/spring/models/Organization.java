package s4.spring.models;
 
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
 
@Entity
@Table(name="organization")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Organization {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
     
    private String name;
    private String domain;
    private String aliases;
     
    @OneToMany(mappedBy = "organization",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private List<Groupe> groupes;
    
    @OneToMany(mappedBy = "organization",fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private List<User> users;

	public String getName() {
		return name = ((this.name == null) ? "N/A" : this.name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain = ((this.domain == null) ? "N/A" : this.domain);
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAliases() {
		return aliases = ((this.aliases == null) ? "N/A" : this.aliases);
	}

	public void setAliases(String aliases) {
		this.aliases = aliases;
	}

	public List<Groupe> getGroupes() {
		return groupes = ((this.groupes == null) ? null : this.groupes);
	}

	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}


}