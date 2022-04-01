package com.meass.myrjadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class Package_Active12 extends AppCompatActivity {

    String key, key2, key3;
    FirebaseFirestore firebaseFirestore;
    DocumentReference documentReference;
    RecyclerView recyclerView;
    PackageInAdapter getDataAdapter1;
    List<Package> getList;
    String url;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    KProgressHUD progressHUD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package__active12);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Package Request");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(10.0f);
        firebaseFirestore = FirebaseFirestore.getInstance();
        getList = new ArrayList<>();
        getDataAdapter1 = new PackageInAdapter(getList);
        firebaseFirestore = FirebaseFirestore.getInstance();
        documentReference = firebaseFirestore.collection("Subadmin")
                .document("Packages").collection("101").document();
        recyclerView = findViewById(R.id.blog_list_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(getDataAdapter1);

        reciveData();
    }

    private void reciveData() {

        firebaseFirestore.collection("Subadmin")
                .document("Packages").collection("101")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        for (DocumentChange ds : queryDocumentSnapshots.getDocumentChanges()) {
                            if (ds.getType() == DocumentChange.Type.ADDED) {

                 /*String first;
                 first = ds.getDocument().getString("name");
                 Toast.makeText(MainActivity2.this, "" + first, Toast.LENGTH_SHORT).show();*/
                                Package get = ds.getDocument().toObject(Package.class);
                                getList.add(get);
                                getDataAdapter1.notifyDataSetChanged();
                            }

                        }
                    }
                });


    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
    }

    @Override
    public boolean onSupportNavigateUp() {
        startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
             /*   if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(MainActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                }*/
                //fullsearch(query);

                return false;

            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchAllUser(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void searchAllUser(String newText) {
        firebaseFirestore.collection("Subadmin")
                .document("Packages").collection("101")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        getList.clear();
                        for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots) {
                            String dta=documentSnapshot.getString("username");
                            if (dta.toLowerCase().contains(newText.toLowerCase())) {
                                Package adding_model=new Package(documentSnapshot.getString("useremail"),
                                        documentSnapshot.getString("package_name"),
                                        documentSnapshot.getString("package_price"),
                                        documentSnapshot.getString("payment_methode"),
                                        documentSnapshot.getString("usernumber"),
                                        documentSnapshot.getString("transacation"),
                                        documentSnapshot.getString("uuid"),
                                        documentSnapshot.getString("user_uuid"),
                                        documentSnapshot.getString("status"),
                                        documentSnapshot.getString("username"),
                                        documentSnapshot.getString("date"),
                                        documentSnapshot.getString("package_type"),
                                        documentSnapshot.getString("time")


                                );
                                getList.add(adding_model);

                            }

                            getDataAdapter1 = new PackageInAdapter(getList);
                            recyclerView.setAdapter(getDataAdapter1);
                        }
                    }
                });
    }

}