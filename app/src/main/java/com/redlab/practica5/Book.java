package com.redlab.practica5;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by aldo on 20/09/17.
 */

public class Book extends SugarRecord {
    @Unique
    String isbn;
    String title;
    String edition;

    // Default constructor is necessary for SugarRecord
    public Book() {

    }

    public Book(String isbn, String title, String edition) {
        this.isbn = isbn;
        this.title = title;
        this.edition = edition;
    }
}