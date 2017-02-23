package com.word.repository.impl;

import com.word.domain.Kelime;
import com.word.repository.IKelimeRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Nahide on 10.02.2017.
 */
@Component
public class KelimeRepository extends CommonDao<Kelime, Long> implements IKelimeRepository {

}
