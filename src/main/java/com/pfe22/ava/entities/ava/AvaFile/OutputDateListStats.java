package com.pfe22.ava.entities.ava.AvaFile;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OutputDateListStats {
    private String name;
    private List<OutputDateStat> series ;

    public OutputDateListStats(String name, List<OutputDateStat> series) {
        this.name = name;
        this.series = series;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeries(List<OutputDateStat> series) {
        this.series = series;
    }

    public String getName() {
        return name;
    }

    public List<OutputDateStat> getSeries() {
        return series;
    }
}
