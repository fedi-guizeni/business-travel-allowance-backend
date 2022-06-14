package com.pfe22.ava.entities.ava.AvaFile;



import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OutputListStat {
    private String name;
    private List<OutputStat> series ;

    public OutputListStat(String name, List<OutputStat> series) {
        this.name = name;
        this.series = series;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeries(List<OutputStat> series) {
        this.series = series;
    }

    public String getName() {
        return name;
    }

    public List<OutputStat> getSeries() {
        return series;
    }
}
