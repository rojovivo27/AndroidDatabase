package com.redlab.practica5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.SugarRecord;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //http://satyan.github.io/sugar/index.html

    //https://github.com/chennaione/sugar

    Button btnCreate;
    Button btnRead;
    Button btnUpdate;
    Button btnDelete;
    Button btnClear;

    TextView lblID;

    EditText txtISBN;
    EditText txtTitle;
    EditText txtEdition;

    Integer id = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtISBN = (EditText) findViewById(R.id.txtISBN);
        txtTitle = (EditText) findViewById(R.id.txtTitle);
        txtEdition = (EditText) findViewById(R.id.txtEdition);

        lblID = (TextView) findViewById(R.id.lblID);

        btnCreate = (Button) findViewById(R.id.btnCreate);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClear = (Button) findViewById(R.id.btnClear);

        //CREATE
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtISBN.getText().toString().equals("") || txtTitle.getText().toString().equals("") || txtEdition.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Todos los campos deben tener información",Toast.LENGTH_LONG).show();
                } else {
                    String i = txtISBN.getText().toString();
                    String t = txtTitle.getText().toString();
                    String e = txtEdition.getText().toString();
                    //Save Entity:
                    Book book = new Book(i, t, e);
                    book.save();

                    txtISBN.setText("");
                    txtTitle.setText("");
                    txtEdition.setText("");
                    lblID.setText("");
                    Toast.makeText(getApplicationContext(), "Guardado correctamente con ID: " + book.getId(),Toast.LENGTH_LONG).show();
                }
                id = -1;
            }
        });

        //READ
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtISBN.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Ingresa el ISBN para realizar la búsqueda",Toast.LENGTH_LONG).show();
                } else {
                    String i = txtISBN.getText().toString();
                    //Load Entity:
                    List<Book> books = Book.find(Book.class,"isbn = ?", i);
                    //List<Book> books = Book.listAll(Book.class);
                    if(books.isEmpty()){
                        Toast.makeText(getApplicationContext(), "No lo pude localizar", Toast.LENGTH_SHORT).show();
                        id = -1;
                    } else {
                        Book book = books.get(0);
                        lblID.setText("ID: " + book.getId().toString());
                        txtISBN.setText(book.isbn);
                        txtTitle.setText(book.title);
                        txtEdition.setText(book.edition);

                        id = book.getId().intValue();
                    }
                }
            }
        });

        //UPDATE
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id != -1){
                    String i = txtISBN.getText().toString();
                    String t = txtTitle.getText().toString();
                    String e = txtEdition.getText().toString();
                    Book book = Book.findById(Book.class, id);
                    book.isbn = i;
                    book.title = t;
                    book.edition = e;
                    book.save();
                    Toast.makeText(getApplicationContext(), "Datos actualizados", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Nothing to do here", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //DELETE
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id != -1){
                    Book book = Book.findById(Book.class, id);
                    book.delete();
                    Toast.makeText(getApplicationContext(), "ELIMINADO!", Toast.LENGTH_SHORT).show();
                    txtISBN.setText("");
                    txtTitle.setText("");
                    txtEdition.setText("");
                    lblID.setText("");
                } else {
                    Toast.makeText(getApplicationContext(), "Nothing to do here", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //CLEAR
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtISBN.setText("");
                txtTitle.setText("");
                txtEdition.setText("");
                lblID.setText("");
            }
        });

        /*
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
        */
    }
}
