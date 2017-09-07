package com.taubek.examples.objectboxexample;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.NameInDb;

/**
 * Created by markus on 07.09.17.
 */

@Entity
public class Contact {

    @Id
    private long id;

    private String firstname;

    public Contact(String firstname) {
        this.firstname = firstname;
    }

    public Contact() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
