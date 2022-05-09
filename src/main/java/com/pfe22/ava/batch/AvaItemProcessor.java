package com.pfe22.ava.batch;

import com.pfe22.ava.entities.ava.AvaFile.Ava;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class AvaItemProcessor implements ItemProcessor<Ava,Ava> {
    private SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
    private Date date = new Date();
    @Override
    public Ava process(Ava ava) throws Exception {

            ava.setIdClient(ava.getIdClient());
            ava.setStatutDossier("non-renouvele");


        return ava;
    }
}
