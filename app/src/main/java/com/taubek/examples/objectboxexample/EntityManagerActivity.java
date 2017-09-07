package com.taubek.examples.objectboxexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.objectbox.Box;
import io.objectbox.android.AndroidScheduler;
import io.objectbox.reactive.DataObserver;
import io.objectbox.reactive.DataSubscription;

public class EntityManagerActivity extends AppCompatActivity {

    EntityRecyclerAdapter entityRecyclerAdapter;
    Box<Contact> contactBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entity_manager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveContact("Contact " + new Random().nextInt());
            }
        });
        contactBox = ((App) getApplication()).getBoxStore().boxFor(Contact.class);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager recylerViewLayoutManager = new LinearLayoutManager(this);
        recylerViewLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(recylerViewLayoutManager);
        entityRecyclerAdapter = new EntityRecyclerAdapter(this, new ArrayList<Contact>());
        entityRecyclerAdapter.setOnClickDelete(new EntityRecyclerAdapter.OnClickDelete() {
            @Override
            public void onDelete(Contact contact) {
                deleteContact(contact);
            }
        });
        recyclerView.setAdapter(entityRecyclerAdapter);

        fillAdapterWithData();
    }

    private void saveContact(String firstname) {
        Box<Contact> contactBox = ((App) getApplication()).getBoxStore().boxFor(Contact.class);
        contactBox.put(new Contact(firstname));
        fillAdapterWithData();
        showSnackbar("Contact wurde gespeichert");
    }

    private void deleteContact(Contact contact) {
        Box<Contact> contactBox = ((App) getApplication()).getBoxStore().boxFor(Contact.class);
        contactBox.remove(contact);
        fillAdapterWithData();
        showSnackbar("Contact wurde gelöscht");
    }

    private void showSnackbar(String text) {
        Snackbar.make(getCurrentFocus(), text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void fillAdapterWithData() {

        contactBox
                .query()
                .order(Contact_.firstname)
                .build()
                .subscribe()
                .on(AndroidScheduler.mainThread())
                .observer(new DataObserver<List<Contact>>() {
                    @Override
                    public void onData(List<Contact> contacts) {
                        entityRecyclerAdapter.setContactList(contacts);
                        entityRecyclerAdapter.notifyDataSetChanged();
                    }
                });
    }
}
