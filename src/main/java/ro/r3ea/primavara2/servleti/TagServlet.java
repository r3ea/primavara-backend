package ro.r3ea.primavara2.servleti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ro.r3ea.primavara2.date.DateTag;
import ro.r3ea.primavara2.date.DateTagPaginare;
import ro.r3ea.primavara2.model.Tag;

@RestController
@RequestMapping("/tags-todo")
@CrossOrigin(value = {"http://localhost:5500/", "http://127.0.0.1:5500/"})
public class TagServlet {
	
	@Autowired
	private DateTag dateTag;
	
	@Autowired
	private DateTagPaginare dateTagPaginare;
	
	@GetMapping("/all")
	public Iterable<Tag> allTags(){
		return dateTag.findAll();
	}
	
	@GetMapping ("/all-paginated/{pageNumber}/{nrElements}")
	public Page<Tag> allTagCuPaginare(@PathVariable("pageNumber") int pageNumber, @PathVariable("nrElements") int nrElem){
		Pageable paginaCurenta = PageRequest.of(pageNumber, nrElem);
		Page<Tag> rezultat = this.dateTagPaginare.findAll(paginaCurenta);
		return rezultat;
	}
	
	@PostMapping("/save")
	public Tag salveazaTag(@RequestBody Tag tag) {
		return dateTag.save(tag);
	}
	
	@PutMapping("/update")
	public Tag updateazaTag(@RequestBody Tag tag) {
		return dateTag.save(tag);
	}
}
