package com.redlab.practica5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.orm.SugarRecord;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //http://satyan.github.io/sugar/index.html

    //https://github.com/chennaione/sugar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Save Entity:
        Book book = new Book("isbn123", "Title here", "2nd edition");
        book.save();
        //Load Entity:
        Book bookS = Book.findById(Book.class, 1);
        Log.d("ALDO:",bookS.toString());
        Toast.makeText(getApplicationContext(), "ALDO:" + bookS.isbn.toString(), Toast.LENGTH_SHORT).show();
        //Update Entity:
        Book bookU = Book.findById(Book.class, 1);
        bookU.title = "updated title here"; // modify the values
        bookU.edition = "3rd edition";
        bookU.save(); // updates the previous entry with new values.
        Log.d("ALDO:",bookU.toString());
        //Delete Entity:
        Book bookD = Book.findById(Book.class, 1);
        bookD.delete();
        //Bulk Operations:
        List<Book> books = Book.listAll(Book.class);

        Book.deleteAll(Book.class);
    }
}
