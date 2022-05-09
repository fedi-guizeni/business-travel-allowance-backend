package com.pfe22.ava.batch;

import com.pfe22.ava.entities.ava.AvaFile.Ava;
import com.pfe22.ava.repository.AvaRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AvaItemWriter implements ItemWriter<Ava> {

    @Qualifier("avaOtherActRepository")
    @Autowired
    private AvaRepository avaRepository1;

    @Qualifier("avaForeignExporterRepository")
    @Autowired
    private AvaRepository avaRepository2;

    @Override
    public void write(List<? extends Ava> list) throws Exception {
        avaRepository1.saveAll(list);

    }
}
