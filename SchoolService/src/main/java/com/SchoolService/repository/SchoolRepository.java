package com.SchoolService.repository;

import com.SchoolService.domain.School;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SchoolRepository extends MongoRepository<School, Long> {


}
