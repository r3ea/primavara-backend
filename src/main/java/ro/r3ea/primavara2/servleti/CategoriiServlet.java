package ro.r3ea.primavara2.servleti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.r3ea.primavara2.date.DateCategory;
import ro.r3ea.primavara2.date.DateCategoryPaginare;
import ro.r3ea.primavara2.date.DateProdus;
import ro.r3ea.primavara2.model.Category;
import ro.r3ea.primavara2.services.ServiceLog;

@RestController
@RequestMapping("/categorii")
@CrossOrigin(value = {"http://localhost:5500/", "http://127.0.0.1:5500/", "http://localhost:4200/", "http://127.0.0.1:4200/"})
public class CategoriiServlet {

	@Autowired
	private DateCategory dateCategory;
	
	@Autowired
	private DateCategoryPaginare dateCategoryPaginare;
	
	@Autowired
	private DateProdus dateProdus;
	
	@Autowired
	private ServiceLog service;
	
	@GetMapping("/all")
	// public List<Product> gasesteToate(){
	public Iterable<Category> gasesteToate(){
		return dateCategory.findAll(); // SELECT * FROM products;
	}
	
	@GetMapping("/all-paginated/{pageNumber}/{nrElements}")
	public Page<Category> allCategoriesCuPaginare(@PathVariable("pageNumber") int pageNumber, @PathVariable("nrElements") int nrElem){
		
		
		service.logMessageEndpoint("categories - all-paginated");
		
		
		Pageable paginaCurenta = PageRequest.of(pageNumber, nrElem); // 3
		Page<Category> rezultat  = this.dateCategoryPaginare.findAll(paginaCurenta);
		return rezultat;
	}
	
	
	@PostMapping("/save")
	public Category salveazaCategorie(@RequestBody Category categorie) {
		// securityService.check();
		service.logMessageEndpoint("categories - save");
		return dateCategory.save(categorie);
	}
	
	@PutMapping("/update")
	public Category updateazaCategorie(@RequestBody Category categorie) {
		return dateCategory.save(categorie);
	}
	
	@DeleteMapping("/delete/{idCat}")
	public Category deleteCategory(@PathVariable("idCat") int idCat) {
		System.out.println("HELLO CATEGORII DELETE");
		Category cat = dateCategory.findById(idCat).get();
//		List<Product> productsInCategory = dateProdus.findByCategorieAsociata(cat);
//		for(Product p : productsInCategory) {
//			p.setCategorieAsociata(null);
//			dateProdus.save(p);
//		}
		dateCategory.delete(cat);
		return cat;
	}
	
	
}
