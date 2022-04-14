package ro.r3ea.primavara2.date;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ro.r3ea.primavara2.model.Category;
import ro.r3ea.primavara2.model.Product;

@Repository
public interface DateProdus extends CrudRepository<Product, Integer>{

	public List<Product> findByCategorieAsociata(Category c);
	
}
