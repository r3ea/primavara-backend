package ro.r3ea.primavara2.servleti;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import ro.r3ea.primavara2.date.DateProdus;
import ro.r3ea.primavara2.date.DateTag;
import ro.r3ea.primavara2.model.Category;
import ro.r3ea.primavara2.model.Product;
import ro.r3ea.primavara2.model.Tag;
import ro.r3ea.primavara2.services.ServiceLog;

@RestController
@RequestMapping("/produse")
@CrossOrigin(value = {"http://localhost:5500/", "http://127.0.0.1:5500/", "http://127.0.0.1:4200/", "http://localhost:4200/"})
public class ProduseServlet {

	
	@Autowired
	private DateProdus dateProdus;
	
	@Autowired
	private DateCategory dateCategory;
	
	@Autowired
	private DateTag dateTag;
	
	@Autowired
	private ServiceLog service;
	
	@GetMapping("/all")
	// public List<Product> gasesteToate(){
	public Iterable<Product> gasesteToate(){
//		service.logMessageEndpoint("products - all");
		return dateProdus.findAll(); // SELECT * FROM products;
	}
	
	@GetMapping("/pagina/{nrPagina}")
	public List<Product> functieCareIntoarcePagina(@PathVariable("nrPagina") int nrPagina){
		return dateProdus.gasesteCuPaginare(nrPagina*5);
	}
	
	// TODO:
	
	
	
	@GetMapping("/test-array")
	public String[] getArrayTest() {
		String[] names = {"Alice", "Bob", "Jack"};
		names[2] = "Jackson";
		// names[3] = "Bob";
		return names;
	}
	
	@GetMapping("/test-list")
	public List<String> getListTest(){
		List<String> lista = new ArrayList<>();
		lista.add("Bobby");
		lista.add("Jackie");		
		return lista;
		
	}
	
	
	@GetMapping("/get-day/{numberOfDay}")
	public String getTheDay(@PathVariable("numberOfDay") int nrDay) {
		
		String zile[] = {"LUNI", "MARTI", "MIERCURI", "JOI", "VINERI", "SAMBATA", "DUMINICA"};
		
		return zile[nrDay];
	}
	
	
	@GetMapping("/pagina-cautare-new/{searchTerm}/{nrPagina}/{nrElementePePagina}")
	public List<Product> functieCareIntoarceCautareSiPagina(
			@PathVariable("searchTerm") String searchTerm, 
			@PathVariable("nrPagina") int nrPagina,
			@PathVariable("nrElementePePagina") int nrElementePePagina
		){
		
		Integer numbers[] = {10, 30, 20};
		
		
		
		List<Product> productsWhichMatchTheCriteria = dateProdus.cautareSomethingInSomething(
				searchTerm, 
				nrPagina*nrElementePePagina, 
				nrElementePePagina);
		return productsWhichMatchTheCriteria;
	}
	
	
	@GetMapping("/pagina-cautare-old/{searchTerm}/{nrPagina}/{nrElementePePagina}")
	public List<Product> functieCareIntoarceCautareSiPaginaVariantaCuMultCodSiSELECTALL(
			@PathVariable("searchTerm") String searchTerm, 
			@PathVariable("nrPagina") int nrPagina,
			@PathVariable("nrElementePePagina") int nrElementePePagina
		){
		List<Product> productsWhichMatchTheCriteria = new ArrayList<>();
		
		
		// 0 - 123
		// 1 - 456
		// ...
		
		int nrProduseSkip = nrElementePePagina*nrPagina;
		int nrProduse = 0;
		int nrProduseGasite = 0;
		
		Iterable<Product> allProducts = dateProdus.findAll(); // SELECT * FROM products;
		for(Product p : allProducts) {
			if(p.getName().toLowerCase().contains(searchTerm.toLowerCase())) { // "ShaOrmA" --- "ShaoRMA"
				
				
				nrProduseGasite++;
				
				if(nrProduseGasite > nrProduseSkip) {
					productsWhichMatchTheCriteria.add(p);
					nrProduse++;
					if(nrProduse == nrElementePePagina) {
						break;
					}
				}
			}
		}
		// TODO: call the DateProdus new method
		return productsWhichMatchTheCriteria;
	}
	
	@GetMapping("/by-id/{id}")
	public Product gasesteProdus(@PathVariable("id") int id) {
		return this.dateProdus.findById(id).get();
	}
	
	@GetMapping("/salutare/{x}/{y}")
	public String salutareMetoda(@PathVariable("x") String nume, @PathVariable("y") String prenume) {
		return "SALUTARE " + nume + ", " + prenume;
	}
	
	@GetMapping("/suma/{nr1}/{nr2}")
	public int calculSuma(@PathVariable("nr1") int nr1, @PathVariable("nr2") int nr2) {
		return nr1+nr2;
	}
	
	// INSERT INTO PRODUSE(....)
	// http://localhost:9000/produse/save-simpler/xbox/33.33/consola foarte buna jocuri/...
//	@PostMapping("/save-simpler/{denumire}/{pret}/{descriere}")


//	@PostMapping("/save-simple-but-with-category/{categoryId}")
//	public Product salveazaProdusSimplerWithCategory(@RequestBody Product produs, @PathVariable("categoryId")int categoryId) {
//		// ...
//		Product produsSalvat =  dateProdus.save(produs);
//		return produsSalvat;
//	}
	
	@PostMapping("/save-simpler")
	public Product salveazaProdusSimpler(@RequestBody Product produs) {
		Product produsSalvat =  dateProdus.save(produs);
		return produsSalvat;
	}
	
	
	// insert
	@PostMapping("/save")
	public Product salveazaProdus(@RequestBody Product produs) {
		// TODO: save taguri noi
		// TODOL: get all tags (noi si vechi) si asociere cu produs
		// return dateProdus.save(produs);
		
		List<String> taguriVechiSauNoi = produs.getTaguriTrimiseDinHtml(); // ['Fuchsia', 'high-end', 'blablanou']
		System.out.println("SALVAM UN PRODUS CU TAG-URILE: " + taguriVechiSauNoi);
		
		List<Tag> taguriDejaExistente = new ArrayList<>();
		List<Tag> taguriNoi = new ArrayList<>(); // save
		
		for(String tagName : taguriVechiSauNoi) {
			Tag tagDb = dateTag.findByTagname(tagName);
			if(tagDb != null) {
				// tag deja exista, add to list
				taguriDejaExistente.add(tagDb);
			}else {
				// nu exista niciun Tag cu tagName in baza de date
				Tag tagNou = new Tag(); // nu are ID
				tagNou.setTagname(tagName);
				Tag tagNouSaved = dateTag.save(tagNou); // tagNouSaved o sa aiba si ID
				taguriNoi.add(tagNouSaved);
			}
		}
		
		
		produs.setTaguriAsociate(new HashSet<>()); // [] VS null
		produs.getTaguriAsociate().addAll(taguriDejaExistente);
		produs.getTaguriAsociate().addAll(taguriNoi);
		Product produsSalvat =  dateProdus.save(produs);
		return produsSalvat;
	}
	
	
	// http://localhost:9000/produse/delete/7
	@DeleteMapping("/delete/{id}")
	public Product stergeProdus(@PathVariable("id") int id) {
		
//		int numbers[] = {1,2,3};
//		List<Integer> numbersList = new ArrayList<>();
//		numbersList.add
		/// simulate a nasty server
		// Thread.sleep(5000);
		
		Product produsDeSters = this.dateProdus.findById(id).get();
		produsDeSters.setTaguriAsociate(null);
		dateProdus.save(produsDeSters); 
		dateProdus.delete(produsDeSters);
		return produsDeSters;
	}
	
	// update
	// UPDATE PRODUSE SET denumire = .... WHERE ID = ?
	@PutMapping("/update/{idCategorie}")
	public Product updateazaProdus(@RequestBody Product produs, @PathVariable("idCategorie") Integer idCategorie) {
//		Product produsVechi = this.dateProdus.findById(produs.getId()).get();
//		produs.setCategorieAsociata(produsVechi.getCategorieAsociata());
		Category categoriaSelectata = dateCategory.findById(idCategorie).get();
		produs.setCategorieAsociata(categoriaSelectata);
		return dateProdus.save(produs);
	}
	
	@PutMapping("/update-simple")
	public Product updateazaProdusSimple(@RequestBody Product produs) {
		
		Product produsUpdatat = this.dateProdus.save(produs);
//		String sir = null;
//		System.out.println(sir.toUpperCase());
		return produsUpdatat;
	}
	
	@GetMapping("/test-1")
	public String metoda1() {
		return "HELLO GET";
	}
	
	@DeleteMapping("/test-1")
	public String metoda1Delete() {
		return "HELLO DELETE";
	}
	
	
	
}
