package com.edoardoconti.kmz_backend.content.event;

import com.edoardoconti.kmz_backend.content.Content;
import com.edoardoconti.kmz_backend.content.ContentType;
import com.edoardoconti.kmz_backend.content.process.ProcessContent;
import com.edoardoconti.kmz_backend.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="events")
@Getter
@Setter
public class EventContent extends Content {
    private String name;
    private String description;
    private String location;
    private Date date;
    @ElementCollection

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "events_guests",
            joinColumns = @JoinColumn(name = "events_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> guests = new ArrayList<>();


    public EventContent(){
        super(ContentType.EVENT);
    }

}
