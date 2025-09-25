package com.edoardoconti.kmz_backend.content.event;

import com.edoardoconti.kmz_backend.content.Content;
import com.edoardoconti.kmz_backend.content.ContentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="events")
@Getter
@Setter
public class EventContent extends Content {
    private String name;
    private String location;
    private Date date;
    @ElementCollection
    private List<Long> guestsIds;


    public EventContent(){
        super(ContentType.EVENT);
    }

}
