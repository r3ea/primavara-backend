package ro.r3ea.primavara2.date;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ro.r3ea.primavara2.model.Category;
import ro.r3ea.primavara2.model.Product;

@Repository
public interface DateProdus extends CrudRepository<Product, Integer>{

	public List<Product> findByCategorieAsociata(Category c); // SELECT * FROM products where id_cat = c.getId()
	
	
    @Query(value="select * from products limit :paginaCurenta, 5", nativeQuery = true)
	public List<Product> gasesteCuPaginare(int paginaCurenta);
	
    @Query(value="select * from products where name like %:termen% limit :paginaCurenta, :nrElementePePagina", nativeQuery = true)
	public List<Product> cautareSomethingInSomething(String termen, int paginaCurenta, int nrElementePePagina);
	
    
    
    // select * from products where name like '%mouse%' limit 0, 3;
    
//	select * from products limit 0, 5;
//	select * from products limit 5, 5;
//	select * from products limit 10, 5;
//	select * from products limit 15, 5;
//	-- select * from products limit <DE LA CAT>, <CATE PE PAGINA>;
//	-- select * from products limit <CATE PE PAGINA> * paginaCurenta, <CATE PE PAGINA>;
	
	// SELECT * FROM products 
	// SELECT * FROM products WHERE id = ...
	
	
}
