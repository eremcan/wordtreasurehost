package com.word.repository;

import com.word.domain.Kelime;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Nahide on 10.02.2017.
 */
public interface IKelimeRepository extends ICommonDao<Kelime,Long> {

}
