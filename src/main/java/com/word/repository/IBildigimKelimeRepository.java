package com.word.repository;

import com.word.domain.BilinenKelime;
import com.word.domain.Kelime;
import com.word.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Nahide on 10.02.2017.
 */
public interface IBildigimKelimeRepository extends Repository<BilinenKelime, Long> {

    @Query(value = "SELECT k.id,k.kelimeKey,k.kelimeValue\n" +
            "FROM Kelime as k  where k.id not in \n" +
            "(SELECT bl.kelime.id\n" +
            "FROM BilinenKelime as bl where bl.user.id = ?1) \n" +
            "\n", nativeQuery = true)
    List<Kelime> findByuser(long id);

}



