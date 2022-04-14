package ro.r3ea.primavara2.date;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ro.r3ea.primavara2.model.Tag;

@Repository
public interface DateTagPaginare extends  PagingAndSortingRepository<Tag, Integer>{

}
