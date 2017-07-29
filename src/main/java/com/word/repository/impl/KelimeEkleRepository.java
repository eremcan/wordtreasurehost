package com.word.repository.impl;

import com.word.domain.KelimeEkle;
import com.word.repository.IKelimeEkleRepository;
import org.springframework.stereotype.Component;

/**
 * Created by Aytan on 27.05.2017.
 */
@Component
public class KelimeEkleRepository extends CommonDao<KelimeEkle, Long> implements IKelimeEkleRepository{
}
