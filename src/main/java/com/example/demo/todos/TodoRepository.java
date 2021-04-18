package com.example.demo.todos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends MongoRepository<Todo,String> {

    //******* Il est obligatoire d'utiliser le CamelCase dans le nom des methodes dans le repository pour permet
    // spirng boot de le connaitre.
    //FindByBlaBla RQ: il est obligatoire que la classe Todo contient une attribut nommé blabka sinon la methode
    // va retouner erruer *****
    Todo findByTitle(String title);

    // ***** Vous pouvez faire ca en plus *****
    // Todo findByTitleAndAndDescriptionOrTimestampsOrderBy()
}
