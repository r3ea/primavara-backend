package ro.r3ea.primavara2.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="products")
public class Product {

	
	
	@Id // primary key
	@GeneratedValue(strategy=GenerationType.IDENTITY)// auto_increment
	private Integer id;
	
	private String name;
	private Double price;
	private String description;
	
//	@Column(name="id_cat")
//	private Integer idCat;
	
	@ManyToOne // foreign key
	@JoinColumn(name="id_cat")  // foreign key
	private Category categorieAsociata;
	
	@ManyToMany// (cascade ALL)
	// join column - id_product
	// inverse join column - id_tag (din clasa "cealalta")
	@JoinTable(
	        name = "product_tags", 
	        joinColumns = { @JoinColumn(name = "id_product") }, 
	        inverseJoinColumns = { @JoinColumn(name = "id_tag") }
	    )
	private Set<Tag> taguriAsociate; //
	
	@Transient
	private List<String> taguriTrimiseDinHtml; // ['Fuchsia', 'high-end', 'dsadssda']
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
//		boolean access = true;
		int access = 1;
		String ceva = "";
		if(!(ceva != null)) {
			System.out.println("YOU HAVE ACCESS");
		}
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
//	public Integer getIdCat() {
//		return idCat;
//	}
//	public void setIdCat(Integer idCat) {
//		this.idCat = idCat;
//	}
	public Category getCategorieAsociata() {
		return categorieAsociata;
	}
	public void setCategorieAsociata(Category categorieAsociata) {
		this.categorieAsociata = categorieAsociata;
	}
	public Set<Tag> getTaguriAsociate() {
		return taguriAsociate;
	}
	public void setTaguriAsociate(Set<Tag> taguriAsociate) {
		this.taguriAsociate = taguriAsociate;
	}
	public List<String> getTaguriTrimiseDinHtml() {
		return taguriTrimiseDinHtml;
	}
	public void setTaguriTrimiseDinHtml(List<String> taguriTrimiseDinHtml) {
		this.taguriTrimiseDinHtml = taguriTrimiseDinHtml;
	}
	
	
	
}
