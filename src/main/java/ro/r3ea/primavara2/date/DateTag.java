package ro.r3ea.primavara2.date;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ro.r3ea.primavara2.model.Tag;

@Repository
public interface DateTag extends CrudRepository<Tag, Integer>{
	
	public Tag findByTagname(String tagname);
	
}
