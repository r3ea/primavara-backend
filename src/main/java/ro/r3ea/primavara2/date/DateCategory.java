package ro.r3ea.primavara2.date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ro.r3ea.primavara2.model.Category;

@Repository
public interface DateCategory extends CrudRepository<Category, Integer>{

}
