package com.edoardoconti.kmz_backend.event;

import com.edoardoconti.kmz_backend.content.Content;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="events")
@Getter
@Setter
public class Event extends Content {
    private String name;
    private String location;
    private Date date;
    @ElementCollection
    private List<Long> guestsIds;
}
