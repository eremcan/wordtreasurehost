package com.word.repository.impl;

import com.word.domain.Kelime;
import com.word.repository.IKelimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Nahide on 10.02.2017.
 */
@Component
public class KelimeRepository extends CommonDao<Kelime,Long> implements IKelimeRepository {


//    List<Book> findByCategories(@Param("categories") Collection<Category> categories);
}
