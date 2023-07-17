package repository;

import model.Element;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementRepository  extends MongoRepository<Element,String> {
}
