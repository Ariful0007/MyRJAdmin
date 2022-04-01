package com.meass.myrjadmin;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.flatdialoglibrary.dialog.FlatDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import es.dmoral.toasty.Toasty;

public class HomeActivity extends  AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
        DrawerLayout drawerLayout;
        ActionBarDrawerToggle toggle;
        NavigationView nav_view;
        CardView card_view2;
        KProgressHUD progressHUD;
        FirebaseFirestore firebaseFirestore;
        FirebaseAuth firebaseAuth;
        FirebaseUser firebaseUser;
        String  email;
        int count = 0,count1=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Admin Panel");
        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(10.0f);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();

        //fragment
        drawerLayout = findViewById(R.id.drawer);
        nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);
        progressHUD = KProgressHUD.create(HomeActivity.this);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();
        card_view2=findViewById(R.id.card_view2);
        card_view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Package_Active.class));

            }
        });
        CardView card_view5=findViewById(R.id.card_view5);
        card_view5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SettingsActivity.class));
            }
        });
        CardView card_view4=findViewById(R.id.card_view4);
        card_view4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Report.class));
                // Toast.makeText(HomeActivity.this, "gfhgfhgf", Toast.LENGTH_SHORT).show();
            }
        });
        CardView card_view1=findViewById(R.id.card_view1);
        card_view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Taking_Information_to_User.class));
            }
        });
        CardView card_view6=findViewById(R.id.card_view6);
        card_view6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), HelpLine.class));
                startActivity(new Intent(getApplicationContext(),HelpLineActivity.class));
            }
        });
        //7
        CardView card_view7=findViewById(R.id.card_view7);
        card_view7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), HelpLine.class));
                startActivity(new Intent(getApplicationContext(),ListActivity.class));
            }
        });

        //
        //7
        CardView card_view9=findViewById(R.id.card_view9);
        card_view9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), HelpLine.class));
                startActivity(new Intent(getApplicationContext(),RatingActivity.class));
            }
        });
//notification count
        CardView card_view11=findViewById(R.id.card_view11);
        card_view11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), HelpLine.class));
                startActivity(new Intent(getApplicationContext(),BlocklIstActivity.class));
            }
        });
        CardView card_view2capp=findViewById(R.id.card_view2capp);
        card_view2capp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getApplicationContext(), HelpLine.class));
                startActivity(new Intent(getApplicationContext(),Package_Active12.class));
            }
        });
        firebaseFirestore.collection("Admin_paymentRequest")

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            count = 0;
                            for (DocumentSnapshot document : task.getResult()) {
                                count++;
                            }
                            firebaseFirestore.collection("Notification_count")
                                    .document("abc@gmail.com")
                                    .get()
                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            if (task.isSuccessful()) {
                                                if (task.getResult().exists()) {
                                                    String tas=task.getResult().getString("counter");
                                                    if (Integer.parseInt(tas)==count) {
                                                    }
                                                    else {
                                                        int find=count-Integer.parseInt(tas);
                                                        AlertDialog.Builder builder=new AlertDialog.Builder(HomeActivity.this);
                                                        builder.setTitle("Notification")
                                                                .setMessage("New  Payment Request : "+find)
                                                                .setPositiveButton("Okey", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int i) {
                                                                        dialogInterface.dismiss();
                                                                    }
                                                                }).create().show();
                                                    }
                                                }
                                            }
                                        }
                                    });
                        }

                    }
                });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.admin_people:
                startActivity(new Intent(getApplicationContext(),AdminListActivity.class));
                break;
            case R.id.add_admin:
                final FlatDialog flatDialog3q112add_admin = new FlatDialog(HomeActivity.this);
                flatDialog3q112add_admin.setTitle("Add Admin")
                        .setSubtitle("Are you want to  add people for admin")
                        .setFirstTextFieldHint("Enter Name")
                        .setSecondTextFieldHint("Enter Password")
                        .setFirstButtonText("Ok")
                        .setSecondButtonText("Cancel")
                        .withFirstButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flatDialog3q112add_admin.dismiss();
                                final KProgressHUD progressDialog=  KProgressHUD.create(HomeActivity.this)
                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                        .setLabel("Checking Data....")
                                        .setCancellable(false)
                                        .setAnimationSpeed(2)
                                        .setDimAmount(0.5f)
                                        .show();
                                String name=flatDialog3q112add_admin.getFirstTextField().toLowerCase().toString();
                                String scrond=flatDialog3q112add_admin.getSecondTextField().toLowerCase().toString();
                                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(scrond)) {
                                    progressDialog.dismiss();

                                    Toasty.error(getApplicationContext(),"Error",Toasty.LENGTH_SHORT,true).show();
                                }
                                else {
                                    Long tsLong = System.currentTimeMillis()/1000;
                                    String ts = tsLong.toString();
                                    String uuid=UUID.randomUUID().toString();
                                    Help_Model help_model=new Help_Model(name,scrond,uuid,ts);
                                    firebaseFirestore.collection("AdminAccess")
                                            .document(name+"@gmail.com")
                                            .set(help_model)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        progressDialog.dismiss();
                                                        Toasty.success(getApplicationContext(),"Done",Toasty.LENGTH_SHORT,true).show();
                                                    }
                                                }
                                            });

                                }

                            }
                        }).withSecondButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flatDialog3q112add_admin.dismiss();

                    }
                }).create();
                flatDialog3q112add_admin.show();
                break;
            case R.id.checkmonth:
                Calendar calendar1 = Calendar.getInstance();
                final int year1 = calendar1.get(Calendar.YEAR);
                final int month1 = calendar1.get(Calendar.MONTH)+1;
                final int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
                firebaseFirestore.collection("Daily_Collection")
                        .document("Shopping_Balance")
                        .collection(""+year1)
                        .document(""+month1)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (task.getResult().exists()) {
                                        String ammountshopping=task.getResult().getString("ammount");
                                        firebaseFirestore.collection("Daily_Collection")
                                                .document("Registration_Balance")
                                                .collection(""+year1)
                                                .document(""+month1)
                                                .get()
                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                        if (task.isSuccessful()) {
                                                            if (task.getResult().exists()) {
                                                                String ammountregistration=task.getResult().getString("ammount");
                                                                firebaseFirestore.collection("Daily_Collection")
                                                                        .document("Main_Balance")
                                                                        .collection(""+year1)
                                                                        .document(""+month1)
                                                                        .get()
                                                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                            @Override
                                                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                if (task.isSuccessful()) {
                                                                                    if (task.getResult().exists()) {
                                                                                        String ammountmain=task.getResult().getString("ammount");
                                                                                        Intent intent=new Intent(getApplicationContext(),Daily_Status.class);
                                                                                        intent.putExtra("main",ammountmain);
                                                                                        intent.putExtra("shopping",ammountshopping);
                                                                                        intent.putExtra("registration",ammountregistration);
                                                                                        startActivity(intent);

                                                                                    }
                                                                                }
                                                                            }
                                                                        });
                                                            }
                                                        }
                                                    }
                                                });

                                    }
                                }
                            }
                        });
                break;

            case R.id.daily_status:
                Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH)+1;
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                firebaseFirestore.collection("Daily_Collection")
                        .document("Shopping_Balance")
                        .collection(""+month)
                        .document(""+day)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    if (task.getResult().exists()) {
                                        String ammountshopping=task.getResult().getString("ammount");
                                        firebaseFirestore.collection("Daily_Collection")
                                                .document("Registration_Balance")
                                                .collection(""+month)
                                                .document(""+day)
                                                .get()
                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                        if (task.isSuccessful()) {
                                                            if (task.getResult().exists()) {
                                                                String ammountregistration=task.getResult().getString("ammount");
                                                                firebaseFirestore.collection("Daily_Collection")
                                                                        .document("Main_Balance")
                                                                        .collection(""+month)
                                                                        .document(""+day)
                                                                        .get()
                                                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                            @Override
                                                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                if (task.isSuccessful()) {
                                                                                    if (task.getResult().exists()) {
                                                                                        String ammountmain=task.getResult().getString("ammount");
                                                                                        Intent intent=new Intent(getApplicationContext(),Daily_Status.class);
                                                                                        intent.putExtra("main",ammountmain);
                                                                                        intent.putExtra("shopping",ammountshopping);
                                                                                        intent.putExtra("registration",ammountregistration);
                                                                                        startActivity(intent);

                                                                                    }
                                                                                }
                                                                            }
                                                                        });
                                                            }
                                                        }
                                                    }
                                                });

                                    }
                                }
                            }
                        });
                break;
            case R.id.setHelpdelete:
                firebaseFirestore.collection("ContactHelpLine")
                        .document("abc@gmail.com")
                        .update("contact",""+"Number Not Availble Now.")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                   // progres11sDialog2211.dismiss();
                                    Toast.makeText(HomeActivity.this, "Done", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;

            case R.id.setHelp:
                final FlatDialog flatDialog311Lottery = new FlatDialog(HomeActivity.this);
                flatDialog311Lottery.setTitle("Set Contact Helpline")
                        .setSubtitle("Enter Details For Contact Helpline")
                        .setFirstTextFieldHint("Enter Number")
                        .setFirstButtonText("Ok")
                        .setSecondButtonText("Cancel")
                        .withFirstButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final KProgressHUD progressDialog=  KProgressHUD.create(HomeActivity.this)
                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                        .setLabel("Please wait")
                                        .setCancellable(false)
                                        .setAnimationSpeed(2)
                                        .setDimAmount(0.5f)
                                        .show();
                                String name=flatDialog311Lottery.getFirstTextField().toLowerCase().toString();
                                if (TextUtils.isEmpty(name)) {
                                    progressDialog.dismiss();

                                    Toasty.error(getApplicationContext(),"Error",Toasty.LENGTH_SHORT,true).show();
                                }
                                else {
                                    final KProgressHUD progres11sDialog2211=  KProgressHUD.create(HomeActivity.this)
                                            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                            .setLabel("Please wait")
                                            .setCancellable(false)
                                            .setAnimationSpeed(2)
                                            .setDimAmount(0.5f)
                                            .show();
                                    firebaseFirestore.collection("ContactHelpLine")
                                            .document("abc@gmail.com")
                                            .update("contact",""+name)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        progres11sDialog2211.dismiss();
                                                        Toast.makeText(HomeActivity.this, "Done", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });

                                }

                            }
                        })
                        .withSecondButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flatDialog311Lottery.dismiss();
                            }
                        })
                        .show();
                break;
            case R.id.withdraw_off:
                final KProgressHUD progres11sDialog2211=  KProgressHUD.create(HomeActivity.this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
                firebaseFirestore.collection("Payment__Admin_Checker")
                        .document("abc@gmail.com")
                        .update("get","1")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    progres11sDialog2211.dismiss();
                                    Toast.makeText(HomeActivity.this, "Done", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
            case R.id.withdraw_on:
                final KProgressHUD progressDialog2211=  KProgressHUD.create(HomeActivity.this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
                firebaseFirestore.collection("Payment__Admin_Checker")
                        .document("abc@gmail.com")
                        .update("get","0")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    progressDialog2211.dismiss();
                                    Toast.makeText(HomeActivity.this, "Done", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
            case R.id.payment_off:
                final KProgressHUD progressDialog22=  KProgressHUD.create(HomeActivity.this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
                firebaseFirestore.collection("Payment_Admin_Option")
                        .document("abc@gmail.com")
                        .update("get","0")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    progressDialog22.dismiss();
                                    Toast.makeText(HomeActivity.this, "Done", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                break;
            case R.id.payment_on:
                final KProgressHUD progressDialog=  KProgressHUD.create(HomeActivity.this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
                firebaseFirestore.collection("Payment_Admin_Option")
                        .document("abc@gmail.com")
                        .update("get","1")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                               if (task.isSuccessful()) {
                                   progressDialog.dismiss();
                                   Toast.makeText(HomeActivity.this, "Done", Toast.LENGTH_SHORT).show();
                               }
                            }
                        });
                break;
            case R.id.block_see30:
                final FlatDialog flatDialog311_finid_liki = new FlatDialog(HomeActivity.this);
                flatDialog311_finid_liki.setTitle("See Details")
                        .setSubtitle("You want to see activity of this user")
                        .setFirstTextFieldHint("Enter Username")
                        .setFirstButtonText("Ok")
                        .setSecondButtonText("Cancel")
                        .withFirstButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final KProgressHUD progressDialog=  KProgressHUD.create(HomeActivity.this)
                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                        .setLabel("Please wait")
                                        .setCancellable(false)
                                        .setAnimationSpeed(2)
                                        .setDimAmount(0.5f)
                                        .show();
                                String name=flatDialog311_finid_liki.getFirstTextField().toLowerCase().toString();

                                if (TextUtils.isEmpty(name)) {
                                    progressDialog.dismiss();

                                    Toasty.error(getApplicationContext(),"Error",Toasty.LENGTH_SHORT,true).show();
                                }
                                else {
                                    firebaseFirestore.collection("All_UserID")
                                            .document(name+"@gmail.com")
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        if (task.getResult().exists()) {
                                                            Calendar calendar = Calendar.getInstance();
                                                            final int year = calendar.get(Calendar.YEAR);
                                                            final int month = calendar.get(Calendar.MONTH)+1;
                                                            final int day = calendar.get(Calendar.DAY_OF_MONTH);

                                                            firebaseFirestore.collection("Free_Daily")
                                                                    .document(name+"@gmail.com")
                                                                    .collection("Months")
                                                                    .document(""+year)
                                                                    .collection(""+month)
                                                                    .get()
                                                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                            if(task.isSuccessful()) {
                                                                                int ncount = 0;
                                                                                for (DocumentSnapshot document : task.getResult()) {
                                                                                    ncount++;
                                                                                }
                                                                                int finalNcount = ncount;
                                                                                firebaseFirestore.collection("Paid_Month")
                                                                                        .document(name+"@gmail.com")
                                                                                        .collection("Months")
                                                                                        .document(""+year)
                                                                                        .collection(""+month)
                                                                                        .get()
                                                                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                                            @Override
                                                                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                                                if(task.isSuccessful()) {
                                                                                                    int ncount1 = 0;
                                                                                                    for (DocumentSnapshot document : task.getResult()) {
                                                                                                        ncount1++;
                                                                                                    }
                                                                                                    progressDialog.dismiss();
                                                                                                    Intent intent=new Intent(getApplicationContext(),DetailsActivity2.class);
                                                                                                    intent.putExtra("daily",""+ finalNcount);
                                                                                                    intent.putExtra("monthly",""+ncount1);
                                                                                                    intent.putExtra("name",name);
                                                                                                    startActivity(intent);


                                                                                                }
                                                                                            }
                                                                                        });


                                                                            }
                                                                        }
                                                                    });


                                                        }
                                                        else {
                                                            progressDialog.dismiss();
                                                            flatDialog311_finid_liki.dismiss();
                                                            Toasty.error(getApplicationContext(),"No user found",Toasty.LENGTH_SHORT,true).show();
                                                        }
                                                    }
                                                }
                                            });

                                }

                            }
                        })
                        .withSecondButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flatDialog311_finid_liki.dismiss();
                            }
                        })
                        .show();
                break;
            case R.id.block_see24:
                final FlatDialog flatDialog311_finid = new FlatDialog(HomeActivity.this);
                flatDialog311_finid.setTitle("See Details")
                        .setSubtitle("You want to see activity of this user")
                        .setFirstTextFieldHint("Enter Username")
                        .setFirstButtonText("Ok")
                        .setSecondButtonText("Cancel")
                        .withFirstButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final KProgressHUD progressDialog=  KProgressHUD.create(HomeActivity.this)
                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                        .setLabel("Please wait")
                                        .setCancellable(false)
                                        .setAnimationSpeed(2)
                                        .setDimAmount(0.5f)
                                        .show();
                                String name=flatDialog311_finid.getFirstTextField().toLowerCase().toString();

                                if (TextUtils.isEmpty(name)) {
                                    progressDialog.dismiss();

                                    Toasty.error(getApplicationContext(),"Error",Toasty.LENGTH_SHORT,true).show();
                                }
                                else {
                                    firebaseFirestore.collection("All_UserID")
                                            .document(name+"@gmail.com")
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        if (task.getResult().exists()) {
                                                            Calendar calendar = Calendar.getInstance();
                                                            final int year = calendar.get(Calendar.YEAR);
                                                            final int month = calendar.get(Calendar.MONTH)+1;
                                                            final int day = calendar.get(Calendar.DAY_OF_MONTH);

                                                            firebaseFirestore.collection("Free_Daily")
                                                                    .document(name+"@gmail.com")
                                                                    .collection("Days")
                                                                    .document(""+year)
                                                                    .collection(""+month)
                                                                    .document(""+day)
                                                                    .collection("List")
                                                                    .get()
                                                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                            if(task.isSuccessful()) {
                                                                                int ncount = 0;
                                                                                for (DocumentSnapshot document : task.getResult()) {
                                                                                    ncount++;
                                                                                }
                                                                                int finalNcount = ncount;
                                                                                firebaseFirestore.collection("Paid_Daily")
                                                                                        .document(name+"@gmail.com")
                                                                                        .collection("Days")
                                                                                        .document(""+year)
                                                                                        .collection(""+month)
                                                                                        .document(""+day)
                                                                                        .collection("List")
                                                                                        .get()
                                                                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                                            @Override
                                                                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                                                if(task.isSuccessful()) {
                                                                                                    int ncount1 = 0;
                                                                                                    for (DocumentSnapshot document : task.getResult()) {
                                                                                                        ncount1++;
                                                                                                    }
                                                                                                    progressDialog.dismiss();
                                                                                                    Intent intent=new Intent(getApplicationContext(),DetailsActivity.class);
                                                                                                    intent.putExtra("daily",""+ finalNcount);
                                                                                                    intent.putExtra("monthly",""+ncount1);
                                                                                                    intent.putExtra("name",name);
                                                                                                    startActivity(intent);


                                                                                                }
                                                                                            }
                                                                                        });


                                                                            }
                                                                        }
                                                                    });


                                                        }
                                                        else {
                                                            progressDialog.dismiss();
                                                            flatDialog311_finid.dismiss();
                                                            Toasty.error(getApplicationContext(),"No user found",Toasty.LENGTH_SHORT,true).show();
                                                        }
                                                    }
                                                }
                                            });

                                }

                            }
                        })
                        .withSecondButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flatDialog311_finid.dismiss();
                            }
                        })
                        .show();
                break;


            case R.id.addNoteee:
                startActivity(new Intent(getApplicationContext(),Bkash_Activity.class));
                break;
            case R.id.Password_c:
                startActivity(new Intent(getApplicationContext(),Password_Change.class));
                break;
            case R.id.password:
                final FlatDialog flatDialog1 = new FlatDialog(HomeActivity.this);
                flatDialog1.setTitle("Change Password")
                        .setSubtitle("User forget his/her password.Now as a admin you change it.")
                        .setFirstTextFieldHint("username")
                        .setSecondTextFieldHint("password")

                        .setFirstButtonText("Change")
                        .setSecondButtonText("Cancel")
                        .withFirstButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flatDialog1.dismiss();
                                final KProgressHUD progressDialog=  KProgressHUD.create(HomeActivity.this)
                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                        .setLabel("Please wait")
                                        .setCancellable(false)
                                        .setAnimationSpeed(2)
                                        .setDimAmount(0.5f)
                                        .show();
                                firebaseFirestore.collection("Password")
                                        .document(flatDialog1.getFirstTextField().toLowerCase().toString()+"@gmail.com")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    if (task.getResult().exists()) {
                                                        firebaseFirestore.collection("Password")
                                                                .document(flatDialog1.getFirstTextField().toLowerCase().toString()+"@gmail.com")
                                                                .update("uuid",flatDialog1.getSecondTextField().toLowerCase().toString())
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if (task.isSuccessful()) {
                                                                            firebaseFirestore.collection("All_UserID")
                                                                                    .document(flatDialog1.getFirstTextField().toLowerCase().toString()+"@gmail.com")
                                                                                    .get()
                                                                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                        @Override
                                                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                            if (task.isSuccessful()) {
                                                                                                if (task.getResult().exists()) {
                                                                                                    String uuid=task.getResult().getString("uuid");
                                                                                                    firebaseFirestore.collection("Users")
                                                                                                            .document(uuid)
                                                                                                            .get()
                                                                                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                                                @Override
                                                                                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                                                    if (task.isSuccessful()) {
                                                                                                                        if (task.getResult().exists()) {
                                                                                                                            firebaseFirestore.collection("Users")
                                                                                                                                    .document(uuid)
                                                                                                                                    .update("pass",flatDialog1
                                                                                                                                            .getSecondTextField().toLowerCase().toString())
                                                                                                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                        @Override
                                                                                                                                        public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                            if (task.isSuccessful()) {
                                                                                                                                                Calendar calendar = Calendar.getInstance();
                                                                                                                                                String current = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                                                                                                                                                String current1 = DateFormat.getDateInstance().format(calendar.getTime());
                                                                                                                                                String uuid=UUID.randomUUID().toString();
                                                                                                                                                Sublime1 sublime=new Sublime1(uuid,flatDialog1.getFirstTextField().toString(),
                                                                                                                                                        flatDialog1.getSecondTextField().toLowerCase(),current1);
                                                                                                                                                firebaseFirestore.collection("PassWord_Change")
                                                                                                                                                        .document(uuid)
                                                                                                                                                        .set(sublime)
                                                                                                                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                            @Override
                                                                                                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                                                if (task.isSuccessful()) {
                                                                                                                                                                    progressDialog.dismiss();
                                                                                                                                                                    flatDialog1.dismiss();
                                                                                                                                                                    Toasty.success(getApplicationContext(),"Done",Toasty.LENGTH_SHORT,true).show();
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        });

                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    });
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            });
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    });


                                                                        }
                                                                    }
                                                                });

                                                    }
                                                    else {
                                                        progressDialog.dismiss();
                                                        Toasty.error(getApplicationContext(),"No user found",Toasty.LENGTH_SHORT,true).show();
                                                    }
                                                }
                                            }
                                        });
                            }
                        })
                        .withSecondButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flatDialog1.dismiss();
                            }
                        })
                        .show();

                break;
            case R.id.uslist:
                startActivity(new Intent(getApplicationContext(),SubLimeAcitvity.class));
                break;
            case R.id.mark_a_member:
                final FlatDialog flatDialog3mark_a_member = new FlatDialog(HomeActivity.this);
                flatDialog3mark_a_member.setTitle("Mark a member")
                        .setSubtitle("Are you want to mark a member?")
                        .setFirstTextFieldHint("username")
                        .setFirstButtonText("Ok")
                        .setSecondButtonText("Cancel")
                        .withFirstButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flatDialog3mark_a_member.dismiss();
                                final KProgressHUD progressDialog=  KProgressHUD.create(HomeActivity.this)
                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                        .setLabel("Please wait")
                                        .setCancellable(false)
                                        .setAnimationSpeed(2)
                                        .setDimAmount(0.5f)
                                        .show();
                                String name=flatDialog3mark_a_member.getFirstTextField().toLowerCase().toString();
                                if (TextUtils.isEmpty(name)) {
                                    progressDialog.dismiss();

                                    Toasty.error(getApplicationContext(),"Error",Toasty.LENGTH_SHORT,true).show();
                                }
                                else {
                                    BloackModel bloackModel = new BloackModel(name, name + "@gmail.com");
                                    firebaseFirestore.collection("MarkList")
                                            .document(name + "@gmail.com")
                                            .set(bloackModel)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        progressDialog.dismiss();
                                                        Toasty.success(getApplicationContext(), "Done", Toasty.LENGTH_SHORT, true).show();
                                                    }
                                                }
                                            });
                                }

                            }
                        })
                        .withSecondButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flatDialog3mark_a_member.dismiss();
                            }
                        })
                        .show();
                break;
            case R.id.booklisttt:
                startActivity(new Intent(getApplicationContext(),MarkListActivity.class));
                break;
            case R.id.block:
                final FlatDialog flatDialog3 = new FlatDialog(HomeActivity.this);
                flatDialog3.setTitle("Block a member")
                        .setSubtitle("Are you want to block a member?")
                        .setFirstTextFieldHint("username")
                        .setFirstButtonText("Ok")
                        .setSecondButtonText("Cancel")
                        .withFirstButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flatDialog3.dismiss();
                                final KProgressHUD progressDialog=  KProgressHUD.create(HomeActivity.this)
                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                        .setLabel("Please wait")
                                        .setCancellable(false)
                                        .setAnimationSpeed(2)
                                        .setDimAmount(0.5f)
                                        .show();
                                String name=flatDialog3.getFirstTextField().toLowerCase().toString();
                                if (TextUtils.isEmpty(name)) {
                                    progressDialog.dismiss();

                                    Toasty.error(getApplicationContext(),"Error",Toasty.LENGTH_SHORT,true).show();
                                }
                                else {
                                    BloackModel bloackModel = new BloackModel(name, name + "@gmail.com");
                                    firebaseFirestore.collection("BlockList")
                                            .document(name + "@gmail.com")
                                            .set(bloackModel)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        progressDialog.dismiss();
                                                        Toasty.success(getApplicationContext(), "Done", Toasty.LENGTH_SHORT, true).show();
                                                    }
                                                }
                                            });
                                }

                            }
                        })
                        .withSecondButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flatDialog3.dismiss();
                            }
                        })
                        .show();
                break;
            case R.id.add_persinon:
                final FlatDialog flatDialog3q112 = new FlatDialog(HomeActivity.this);
                flatDialog3q112.setTitle("Add People")
                        .setSubtitle("Are you want to  add people for Helpline")
                        .setFirstTextFieldHint("Enter Name")
                        .setSecondTextFieldHint("Enter number")
                        .setFirstButtonText("Ok")
                        .setSecondButtonText("Cancel")
                        .withFirstButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flatDialog3q112.dismiss();
                                final KProgressHUD progressDialog=  KProgressHUD.create(HomeActivity.this)
                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                        .setLabel("Checking Data....")
                                        .setCancellable(false)
                                        .setAnimationSpeed(2)
                                        .setDimAmount(0.5f)
                                        .show();
                                String name=flatDialog3q112.getFirstTextField().toLowerCase().toString();
                                String scrond=flatDialog3q112.getSecondTextField().toLowerCase().toString();
                                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(scrond)) {
                                    progressDialog.dismiss();

                                    Toasty.error(getApplicationContext(),"Error",Toasty.LENGTH_SHORT,true).show();
                                }
                                else {
                                    Long tsLong = System.currentTimeMillis()/1000;
                                    String ts = tsLong.toString();
                                    String uuid=UUID.randomUUID().toString();
                                    Help_Model help_model=new Help_Model(name,scrond,uuid,ts);
                                    firebaseFirestore.collection("ALl_Subadmin")
                                            .document(uuid)
                                            .set(help_model)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        progressDialog.dismiss();
                                                        Toasty.success(getApplicationContext(),"Done",Toasty.LENGTH_SHORT,true).show();
                                                    }
                                                }
                                            });

                                }

                            }
                        }).withSecondButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flatDialog3q112.dismiss();

                    }
                }).create();
                flatDialog3q112.show();
                break;
            case R.id.shareapp69:
                final KProgressHUD progressDialog11=  KProgressHUD.create(HomeActivity.this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Uploading  Data....")
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
                firebaseFirestore.collection("Main_Notification_Counter")
                        .document("abc@gmail.com")
                        .update("get","0")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    progressDialog11.dismiss();
                                    Toasty.success(getApplicationContext(),"Done",Toasty.LENGTH_SHORT,true).show();
                                }
                            }
                        });
                break;
            case R.id.shareapp6911:
                final KProgressHUD shareapp6911=  KProgressHUD.create(HomeActivity.this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Uploading  Data....")
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
                firebaseFirestore.collection("Main_Notification_Counter2")
                        .document("abc@gmail.com")
                        .update("get","0")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    shareapp6911.dismiss();
                                    Toasty.success(getApplicationContext(),"Done",Toasty.LENGTH_SHORT,true).show();
                                }
                            }
                        });
                break;
            case R.id.main_notice:
                final FlatDialog flatDialog = new FlatDialog(HomeActivity.this);
                flatDialog.setCancelable(false);
                flatDialog.setTitle("Send a admin notification")

                        .setTitleColor(Color.parseColor("#000000"))
                        .setBackgroundColor(Color.parseColor("#f5f0e3"))
                        .setLargeTextFieldHint("write your notification  here ...")
                        .setLargeTextFieldHintColor(Color.parseColor("#000000"))
                        .setLargeTextFieldBorderColor(Color.parseColor("#000000"))
                        .setLargeTextFieldTextColor(Color.parseColor("#000000"))
                        .setFirstButtonColor(Color.parseColor("#fda77f"))
                        .setFirstButtonTextColor(Color.parseColor("#000000"))
                        .setFirstButtonText("Done")

                        .  setSecondButtonText("Cancel")
                        .withFirstButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String main_text=flatDialog.getLargeTextField().toString();
                                if (TextUtils.isEmpty(main_text)) {
                                    Toasty.error(getApplicationContext(),"Error",Toasty.LENGTH_SHORT,true).show();
                                }
                                else {

                                    String args[]={"Main Notification","Apps Stop Notification"};
                                    AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                                    builder.setItems(args, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            if (which==0) {
                                                final KProgressHUD progressDialog=  KProgressHUD.create(HomeActivity.this)
                                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                                        .setLabel("Uploading  Data....")
                                                        .setCancellable(false)
                                                        .setAnimationSpeed(2)
                                                        .setDimAmount(0.5f)
                                                        .show();
                                                firebaseFirestore.collection("Main_Notification_Counter")
                                                        .document("abc@gmail.com")
                                                        .update("get","1")
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    Map<String, String> userMap1 = new HashMap<>();

                                                                    userMap1.put("title", "অ্যাডমিনের বিজ্ঞপ্তি");

                                                                    userMap1.put("message", main_text);
                                                                    firebaseFirestore.collection("Main_Admin_Notification")
                                                                            .document("abc@gmail.com")
                                                                            .set(userMap1)
                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                    if (task.isSuccessful()) {
                                                                                        flatDialog.dismiss();
                                                                                        progressDialog.dismiss();
                                                                                        Toasty.success(getApplicationContext(),"Done",Toasty.LENGTH_SHORT,true).show();
                                                                                    }
                                                                                }
                                                                            });


                                                                }
                                                            }
                                                        });
                                            }
                                            else {
                                                final KProgressHUD progressDialog=  KProgressHUD.create(HomeActivity.this)
                                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                                        .setLabel("Uploading  Data....")
                                                        .setCancellable(false)
                                                        .setAnimationSpeed(2)
                                                        .setDimAmount(0.5f)
                                                        .show();
                                                firebaseFirestore.collection("Main_Notification_Counter2")
                                                        .document("abc@gmail.com")
                                                        .update("get","1")
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    Map<String, String> userMap1 = new HashMap<>();

                                                                    userMap1.put("title", "অ্যাডমিনের বিজ্ঞপ্তি");

                                                                    userMap1.put("message", main_text);
                                                                    firebaseFirestore.collection("Main_Admin_Notification2")
                                                                            .document("abc@gmail.com")
                                                                            .set(userMap1)
                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                    if (task.isSuccessful()) {
                                                                                        flatDialog.dismiss();
                                                                                        progressDialog.dismiss();
                                                                                        Toasty.success(getApplicationContext(),"Done",Toasty.LENGTH_SHORT,true).show();
                                                                                    }
                                                                                }
                                                                            });


                                                                }
                                                            }
                                                        });
                                            }
                                        }
                                    }).create().show();


                                }

                            }
                        }).withSecondButtonListner(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        flatDialog.dismiss();

                    }
                }).create();
                flatDialog.show();
                break;



            case R.id.shareapp1:
                startActivity(new Intent(getApplicationContext(),SettingsActivity.class));

                break;
            case R.id.addNote:
                Toasty.success(getApplicationContext(),"You  are home now", Toast.LENGTH_SHORT,true).show();
                break;
            case R.id.notification:
                startActivity(new Intent(getApplicationContext(),NotificationActivity.class));

                break;
            case R.id.shareapp:
                final KProgressHUD progressDial2og=  KProgressHUD.create(HomeActivity.this)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setLabel("Please wait")
                        .setCancellable(false)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
                firebaseFirestore.collection("Admin_paymentRequest")

                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    int count = 0;
                                    for (DocumentSnapshot document : task.getResult()) {
                                        count++;
                                    }
                                    Map<String, String> userMap1 = new HashMap<>();

                                    userMap1.put("counter",String.valueOf(count));
                                    firebaseFirestore.collection("Notification_count")
                                            .document("abc@gmail.com")
                                            .set(userMap1)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        progressDial2og.dismiss();;
                                                        startActivity(new Intent(getApplicationContext(),PaymentActivity.class));

                                                    }
                                                }
                                            });
                                }

                            }
                        });
                //startActivity(new Intent(getApplicationContext(),PaymentActivity.class));

                break;
            case R.id.money_shopping:
                final FlatDialog flatDialog311_shopping = new FlatDialog(HomeActivity.this);
                flatDialog311_shopping.setTitle("Send balance")
                        .setSubtitle("Are you want send shopping\n balance to user?")
                        .setFirstTextFieldHint("Enter Username")
                        .setSecondTextFieldHint("Enter Ammount")
                        .setFirstButtonText("Ok")
                        .setSecondButtonText("Cancel")
                        .withFirstButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final KProgressHUD progressDialog=  KProgressHUD.create(HomeActivity.this)
                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                        .setLabel("Please wait")
                                        .setCancellable(false)
                                        .setAnimationSpeed(2)
                                        .setDimAmount(0.5f)
                                        .show();
                                String name=flatDialog311_shopping.getFirstTextField().toLowerCase().toString();
                                String scrond=flatDialog311_shopping.getSecondTextField().toLowerCase().toString();
                                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(scrond)) {
                                    progressDialog.dismiss();

                                    Toasty.error(getApplicationContext(),"Error",Toasty.LENGTH_SHORT,true).show();
                                }
                                else {
                                    firebaseFirestore.collection("All_UserID")
                                            .document(name+"@gmail.com")
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        if (task.getResult().exists()) {
                                                            String uuid=task.getResult().getString("uuid");
                                                            firebaseFirestore.collection("Users")
                                                                    .document(uuid)
                                                                    .collection("Main_Balance")
                                                                    .document(name+"@gmail.com")
                                                                    .get()
                                                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                            if (task.isSuccessful()) {
                                                                                if (task.getResult().exists()) {
                                                                                    String purches_balance=task.getResult().getString("shoping_balance");
                                                                                    String giving_balance=task.getResult().getString("giving_balance");
                                                                                    String balance=scrond;
                                                                                    int total_p=Integer.parseInt(purches_balance)+Integer.parseInt(balance);

                                                                                    int total_g=Integer.parseInt(giving_balance)+Integer.parseInt(balance);
                                                                                    firebaseFirestore.collection("Users")
                                                                                            .document(uuid)
                                                                                            .collection("Main_Balance")
                                                                                            .document(name+"@gmail.com")
                                                                                            .update("shoping_balance",String.valueOf(total_p)
                                                                                            )
                                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                @Override
                                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                                    if (task.isSuccessful()) {
                                                                                                        Calendar calendar = Calendar.getInstance();
                                                                                                        String current = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                                                                                                        String current1 = DateFormat.getDateInstance().format(calendar.getTime());
                                                                                                        String uuid=UUID.randomUUID().toString();
                                                                                                        Sublime1 sublime=new Sublime1(uuid,flatDialog311_shopping.getFirstTextField().toString(),
                                                                                                                flatDialog311_shopping.getSecondTextField().toLowerCase(),current1);
                                                                                                        firebaseFirestore.collection("Userrr_All")
                                                                                                                .document(uuid)
                                                                                                                .set(sublime)
                                                                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                    @Override
                                                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                                                        if (task.isSuccessful()) {
                                                                                                                            Calendar calendar = Calendar.getInstance();
                                                                                                                            final int year = calendar.get(Calendar.YEAR);
                                                                                                                            final int month = calendar.get(Calendar.MONTH)+1;
                                                                                                                            final int day = calendar.get(Calendar.DAY_OF_MONTH);
                                                                                                                            firebaseFirestore.collection("Daily_Collection")
                                                                                                                                    .document("Shopping_Balance")
                                                                                                                                    .collection(""+month)
                                                                                                                                    .document(""+day)
                                                                                                                                    .get()
                                                                                                                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                                                                        @Override
                                                                                                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                                                                            if (task.isSuccessful()) {
                                                                                                                                                if (task.getResult().exists()) {
                                                                                                                                                    String balance=scrond;
                                                                                                                                                    String eruelavent=task.getResult().getString("ammount");
                                                                                                                                                    int amm=Integer.parseInt(eruelavent)+Integer.parseInt(balance);
                                                                                                                                                    firebaseFirestore.collection("Daily_Collection")
                                                                                                                                                            .document("Shopping_Balance")
                                                                                                                                                            .collection(""+month)
                                                                                                                                                            .document(""+day)
                                                                                                                                                            .update("ammount",""+amm)
                                                                                                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                                @Override
                                                                                                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                                                    if (task.isSuccessful()) {
                                                                                                                                                                        firebaseFirestore.collection("Daily_Collection")
                                                                                                                                                                                .document("Shopping_Balance")
                                                                                                                                                                                .collection(""+year)
                                                                                                                                                                                .document(""+month)
                                                                                                                                                                                .get()
                                                                                                                                                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                                                                                                                    @Override
                                                                                                                                                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                                                                                                                        if (task.isSuccessful()) {
                                                                                                                                                                                            if (task.getResult().exists()) {
                                                                                                                                                                                                String balance1=scrond;
                                                                                                                                                                                                String eruelavent1=task.getResult().getString("ammount");
                                                                                                                                                                                                int amm1=Integer.parseInt(eruelavent1)+Integer.parseInt(balance1);
                                                                                                                                                                                                firebaseFirestore.collection("Daily_Collection")
                                                                                                                                                                                                        .document("Shopping_Balance")
                                                                                                                                                                                                        .collection(""+year)
                                                                                                                                                                                                        .document(""+month)
                                                                                                                                                                                                        .update("ammount",""+amm1)
                                                                                                                                                                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                                                                            @Override
                                                                                                                                                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                                                                                                if (task.isSuccessful()) {
                                                                                                                                                                                                                    progressDialog.dismiss();
                                                                                                                                                                                                                    flatDialog311_shopping.dismiss();
                                                                                                                                                                                                                    Toasty.success(getApplicationContext(),"Done",Toasty.LENGTH_SHORT,true).show();
                                                                                                                                                                                                                }
                                                                                                                                                                                                            }
                                                                                                                                                                                                        });

                                                                                                                                                                                            }

                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                });




                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            });

                                                                                                                                                }
                                                                                                                                                else {
                                                                                                                                                    String balance=scrond;
                                                                                                                                                    String eruelavent="0";
                                                                                                                                                    int amm=Integer.parseInt(eruelavent)+Integer.parseInt(balance);
                                                                                                                                                    Map<String, String> userMap4 = new HashMap<>();

                                                                                                                                                    userMap4.put("ammount",""+amm);
                                                                                                                                                    firebaseFirestore.collection("Daily_Collection")
                                                                                                                                                            .document("Shopping_Balance")
                                                                                                                                                            .collection(""+month)
                                                                                                                                                            .document(""+day)
                                                                                                                                                            .set(userMap4)
                                                                                                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                                @Override
                                                                                                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                                                    if (task.isSuccessful()) {
                                                                                                                                                                        String balance1=scrond;
                                                                                                                                                                        String eruelavent1="0";
                                                                                                                                                                        int amm1=Integer.parseInt(eruelavent1)+Integer.parseInt(balance1);
                                                                                                                                                                        Map<String, String> userMap41 = new HashMap<>();

                                                                                                                                                                        userMap41.put("ammount",""+amm1);
                                                                                                                                                                        firebaseFirestore.collection("Daily_Collection")
                                                                                                                                                                                .document("Shopping_Balance")
                                                                                                                                                                                .collection(""+year)
                                                                                                                                                                                .document(""+month)
                                                                                                                                                                                .set(userMap41)
                                                                                                                                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                                                    @Override
                                                                                                                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                                                                        if (task.isSuccessful()) {
                                                                                                                                                                                            progressDialog.dismiss();
                                                                                                                                                                                            flatDialog311_shopping.dismiss();
                                                                                                                                                                                            Toasty.success(getApplicationContext(),"Done",Toasty.LENGTH_SHORT,true).show();
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                });
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            });
                                                                                                                                                }
                                                                                                                                            }

                                                                                                                                        }
                                                                                                                                    });
                                                                                                                        }
                                                                                                                    }
                                                                                                                });

                                                                                                    }
                                                                                                }
                                                                                            });

                                                                                }
                                                                            }
                                                                        }
                                                                    });

                                                        }
                                                        else {
                                                            progressDialog.dismiss();
                                                            flatDialog311_shopping.dismiss();
                                                            Toasty.error(getApplicationContext(),"No user found",Toasty.LENGTH_SHORT,true).show();
                                                        }
                                                    }
                                                }
                                            });

                                }

                            }
                        })
                        .withSecondButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flatDialog311_shopping.dismiss();
                            }
                        })
                        .show();
                break;
                //secondbalancve
            case R.id.money_shoppin22g:
                final FlatDialog flatDialog311_shoppingmoney_shoppin22g = new FlatDialog(HomeActivity.this);
                flatDialog311_shoppingmoney_shoppin22g.setTitle("Send balance")
                        .setSubtitle("Are you want send main\n balance to user?")
                        .setFirstTextFieldHint("Enter Username")
                        .setSecondTextFieldHint("Enter Ammount")
                        .setFirstButtonText("Ok")
                        .setSecondButtonText("Cancel")
                        .withFirstButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final KProgressHUD progressDialog=  KProgressHUD.create(HomeActivity.this)
                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                        .setLabel("Please wait")
                                        .setCancellable(false)
                                        .setAnimationSpeed(2)
                                        .setDimAmount(0.5f)
                                        .show();
                                String name=flatDialog311_shoppingmoney_shoppin22g.getFirstTextField().toLowerCase().toString();
                                String scrond=flatDialog311_shoppingmoney_shoppin22g.getSecondTextField().toLowerCase().toString();
                                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(scrond)) {
                                    progressDialog.dismiss();

                                    Toasty.error(getApplicationContext(),"Error",Toasty.LENGTH_SHORT,true).show();
                                }
                                else {
                                    firebaseFirestore.collection("All_UserID")
                                            .document(name+"@gmail.com")
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        if (task.getResult().exists()) {
                                                            String uuid=task.getResult().getString("uuid");
                                                            firebaseFirestore.collection("Users")
                                                                    .document(uuid)
                                                                    .collection("Main_Balance")
                                                                    .document(name+"@gmail.com")
                                                                    .get()
                                                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                            if (task.isSuccessful()) {
                                                                                if (task.getResult().exists()) {
                                                                                    String purches_balance=task.getResult().getString("main_balance");
                                                                                    String giving_balance=task.getResult().getString("giving_balance");
                                                                                    String balance=scrond;
                                                                                    int total_p=Integer.parseInt(purches_balance)+Integer.parseInt(balance);

                                                                                    int total_g=Integer.parseInt(giving_balance)+Integer.parseInt(balance);
                                                                                    firebaseFirestore.collection("Users")
                                                                                            .document(uuid)
                                                                                            .collection("Main_Balance")
                                                                                            .document(name+"@gmail.com")
                                                                                            .update("main_balance",String.valueOf(total_p)
                                                                                            )
                                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                @Override
                                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                                    if (task.isSuccessful()) {
                                                                                                        Calendar calendar = Calendar.getInstance();
                                                                                                        String current = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                                                                                                        String current1 = DateFormat.getDateInstance().format(calendar.getTime());
                                                                                                        String uuid=UUID.randomUUID().toString();
                                                                                                        Sublime1 sublime=new Sublime1(uuid,flatDialog311_shoppingmoney_shoppin22g.getFirstTextField().toString(),
                                                                                                                flatDialog311_shoppingmoney_shoppin22g.getSecondTextField().toLowerCase(),current1);
                                                                                                        firebaseFirestore.collection("Userrr_All")
                                                                                                                .document(uuid)
                                                                                                                .set(sublime)
                                                                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                    @Override
                                                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                                                        if (task.isSuccessful()) {
                                                                                                                            Calendar calendar = Calendar.getInstance();
                                                                                                                            final int year = calendar.get(Calendar.YEAR);
                                                                                                                            final int month = calendar.get(Calendar.MONTH)+1;
                                                                                                                            final int day = calendar.get(Calendar.DAY_OF_MONTH);
                                                                                                                            firebaseFirestore.collection("Daily_Collection")
                                                                                                                                    .document("Main_Balance")
                                                                                                                                    .collection(""+month)
                                                                                                                                    .document(""+day)
                                                                                                                                    .get()
                                                                                                                                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                                                                        @Override
                                                                                                                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                                                                            if (task.isSuccessful()) {
                                                                                                                                                if (task.getResult().exists()) {
                                                                                                                                                    String balance=scrond;
                                                                                                                                                    String eruelavent=task.getResult().getString("ammount");
                                                                                                                                                    int amm=Integer.parseInt(eruelavent)+Integer.parseInt(balance);
                                                                                                                                                    firebaseFirestore.collection("Daily_Collection")
                                                                                                                                                            .document("Main_Balance")
                                                                                                                                                            .collection(""+month)
                                                                                                                                                            .document(""+day)
                                                                                                                                                            .update("ammount",""+amm)
                                                                                                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                                @Override
                                                                                                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                                                    if (task.isSuccessful()) {
                                                                                                                                                                        firebaseFirestore.collection("Daily_Collection")
                                                                                                                                                                                .document("Main_Balance")
                                                                                                                                                                                .collection(""+year)
                                                                                                                                                                                .document(""+month)
                                                                                                                                                                                .get()
                                                                                                                                                                                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                                                                                                                    @Override
                                                                                                                                                                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                                                                                                                        if (task.isSuccessful()) {
                                                                                                                                                                                            if (task.getResult().exists()) {
                                                                                                                                                                                                String balance1=scrond;
                                                                                                                                                                                                String eruelavent1=task.getResult().getString("ammount");
                                                                                                                                                                                                int amm1=Integer.parseInt(eruelavent1)+Integer.parseInt(balance1);
                                                                                                                                                                                                firebaseFirestore.collection("Daily_Collection")
                                                                                                                                                                                                        .document("Main_Balance")
                                                                                                                                                                                                        .collection(""+year)
                                                                                                                                                                                                        .document(""+month)
                                                                                                                                                                                                        .update("ammount",""+amm1)
                                                                                                                                                                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                                                                            @Override
                                                                                                                                                                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                                                                                                if (task.isSuccessful()) {
                                                                                                                                                                                                                    progressDialog.dismiss();
                                                                                                                                                                                                                    flatDialog311_shoppingmoney_shoppin22g.dismiss();
                                                                                                                                                                                                                    Toasty.success(getApplicationContext(),"Done",Toasty.LENGTH_SHORT,true).show();
                                                                                                                                                                                                                }
                                                                                                                                                                                                            }
                                                                                                                                                                                                        });

                                                                                                                                                                                            }

                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                });


                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            });

                                                                                                                                                }
                                                                                                                                                else {
                                                                                                                                                    String balance=scrond;
                                                                                                                                                    String eruelavent="0";
                                                                                                                                                    int amm=Integer.parseInt(eruelavent)+Integer.parseInt(balance);
                                                                                                                                                    Map<String, String> userMap4 = new HashMap<>();

                                                                                                                                                    userMap4.put("ammount",""+amm);
                                                                                                                                                    firebaseFirestore.collection("Daily_Collection")
                                                                                                                                                            .document("Main_Balance")
                                                                                                                                                            .collection(""+month)
                                                                                                                                                            .document(""+day)
                                                                                                                                                            .set(userMap4)
                                                                                                                                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                                @Override
                                                                                                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                                                    if (task.isSuccessful()) {
                                                                                                                                                                        String balance1=scrond;
                                                                                                                                                                        String eruelavent1="0";
                                                                                                                                                                        int amm1=Integer.parseInt(eruelavent1)+Integer.parseInt(balance1);
                                                                                                                                                                        Map<String, String> userMap41 = new HashMap<>();

                                                                                                                                                                        userMap41.put("ammount",""+amm1);
                                                                                                                                                                        firebaseFirestore.collection("Daily_Collection")
                                                                                                                                                                                .document("Main_Balance")
                                                                                                                                                                                .collection(""+year)
                                                                                                                                                                                .document(""+month)
                                                                                                                                                                                .set(userMap41)
                                                                                                                                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                                                    @Override
                                                                                                                                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                                                                        if (task.isSuccessful()) {
                                                                                                                                                                                            progressDialog.dismiss();
                                                                                                                                                                                            flatDialog311_shoppingmoney_shoppin22g.dismiss();
                                                                                                                                                                                            Toasty.success(getApplicationContext(),"Done",Toasty.LENGTH_SHORT,true).show();
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                });

                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            });
                                                                                                                                                }
                                                                                                                                            }

                                                                                                                                        }
                                                                                                                                    });

                                                                                                                        }
                                                                                                                    }
                                                                                                                });

                                                                                                    }
                                                                                                }
                                                                                            });

                                                                                }
                                                                            }
                                                                        }
                                                                    });

                                                        }
                                                        else {
                                                            progressDialog.dismiss();
                                                            flatDialog311_shoppingmoney_shoppin22g.dismiss();
                                                            Toasty.error(getApplicationContext(),"No user found",Toasty.LENGTH_SHORT,true).show();
                                                        }
                                                    }
                                                }
                                            });

                                }

                            }
                        })
                        .withSecondButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flatDialog311_shoppingmoney_shoppin22g.dismiss();
                            }
                        })
                        .show();
                break;
            case R.id.money:
                final FlatDialog flatDialog311 = new FlatDialog(HomeActivity.this);
                flatDialog311.setTitle("Send balance")
                        .setSubtitle("Are you want send registration\n balance to user?")
                        .setFirstTextFieldHint("Enter Username")
                        .setSecondTextFieldHint("Enter Ammount")
                        .setFirstButtonText("Ok")
                        .setSecondButtonText("Cancel")
                        .withFirstButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final KProgressHUD progressDialog=  KProgressHUD.create(HomeActivity.this)
                                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                                        .setLabel("Please wait")
                                        .setCancellable(false)
                                        .setAnimationSpeed(2)
                                        .setDimAmount(0.5f)
                                        .show();
                                String name=flatDialog311.getFirstTextField().toLowerCase().toString();
                                String scrond=flatDialog311.getSecondTextField().toLowerCase().toString();
                                if (TextUtils.isEmpty(name)||TextUtils.isEmpty(scrond)) {
                                    progressDialog.dismiss();

                                    Toasty.error(getApplicationContext(),"Error",Toasty.LENGTH_SHORT,true).show();
                                }
                                else {
                                    firebaseFirestore.collection("All_UserID")
                                            .document(name+"@gmail.com")
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                   if (task.isSuccessful()) {
                                                       if (task.getResult().exists()) {
                                                           String uuid=task.getResult().getString("uuid");
                                                           firebaseFirestore.collection("Users")
                                                                   .document(uuid)
                                                                   .collection("Main_Balance")
                                                                   .document(name+"@gmail.com")
                                                                   .get()
                                                                   .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                       @Override
                                                                       public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                           if (task.isSuccessful()) {
                                                                               if (task.getResult().exists()) {
                                                                                   String purches_balance=task.getResult().getString("main_balance");
                                                                                   String giving_balance=task.getResult().getString("giving_balance");
                                                                                   String balance=scrond;
                                                                                   int total_p=Integer.parseInt(purches_balance)+Integer.parseInt(balance);

                                                                                   int total_g=Integer.parseInt(giving_balance)+Integer.parseInt(balance);
                                                                                   firebaseFirestore.collection("Users")
                                                                                           .document(uuid)
                                                                                           .collection("Main_Balance")
                                                                                           .document(name+"@gmail.com")
                                                                                           .update("main_balance",String.valueOf(total_p)
                                                                                                   )
                                                                                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                               @Override
                                                                                               public void onComplete(@NonNull Task<Void> task) {
                                                                                                   if (task.isSuccessful()) {
                                                                                                       Calendar calendar = Calendar.getInstance();
                                                                                                       String current = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
                                                                                                       String current1 = DateFormat.getDateInstance().format(calendar.getTime());
                                                                                                       String uuid=UUID.randomUUID().toString();
                                                                                                       Sublime1 sublime=new Sublime1(uuid,flatDialog311.getFirstTextField().toString(),
                                                                                                               flatDialog311.getSecondTextField().toLowerCase(),current1);
                                                                                                       firebaseFirestore.collection("Userrr_All")
                                                                                                               .document(uuid)
                                                                                                               .set(sublime)
                                                                                                               .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                   @Override
                                                                                                                   public void onComplete(@NonNull Task<Void> task) {
                                                                                                                       if (task.isSuccessful()) {
                                                                                                                           Calendar calendar = Calendar.getInstance();
                                                                                                                           final int year = calendar.get(Calendar.YEAR);
                                                                                                                           final int month = calendar.get(Calendar.MONTH)+1;
                                                                                                                           final int day = calendar.get(Calendar.DAY_OF_MONTH);
                                                                                                                           firebaseFirestore.collection("Daily_Collection")
                                                                                                                                   .document("Registration_Balance")
                                                                                                                                   .collection(""+month)
                                                                                                                                   .document(""+day)
                                                                                                                                   .get()
                                                                                                                                   .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                                                                       @Override
                                                                                                                                       public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                                                                           if (task.isSuccessful()) {
                                                                                                                                               if (task.getResult().exists()) {
                                                                                                                                                   String balance=scrond;
                                                                                                                                                   String eruelavent=task.getResult().getString("ammount");
                                                                                                                                                   int amm=Integer.parseInt(eruelavent)+Integer.parseInt(balance);
                                                                                                                                                   firebaseFirestore.collection("Daily_Collection")
                                                                                                                                                           .document("Registration_Balance")
                                                                                                                                                           .collection(""+month)
                                                                                                                                                           .document(""+day)
                                                                                                                                                           .update("ammount",""+amm)
                                                                                                                                                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                               @Override
                                                                                                                                                               public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                                                   if (task.isSuccessful()) {
                                                                                                                                                                       firebaseFirestore.collection("Daily_Collection")
                                                                                                                                                                               .document("Registration_Balance")
                                                                                                                                                                               .collection(""+year)
                                                                                                                                                                               .document(""+month)
                                                                                                                                                                               .get()
                                                                                                                                                                               .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                                                                                                                                   @Override
                                                                                                                                                                                   public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                                                                                                                                       if (task.isSuccessful()) {
                                                                                                                                                                                           if (task.getResult().exists()) {
                                                                                                                                                                                               String balance1=scrond;
                                                                                                                                                                                               String eruelavent1=task.getResult().getString("ammount");
                                                                                                                                                                                               int amm1=Integer.parseInt(eruelavent1)+Integer.parseInt(balance1);
                                                                                                                                                                                               firebaseFirestore.collection("Daily_Collection")
                                                                                                                                                                                                       .document("Registration_Balance")
                                                                                                                                                                                                       .collection(""+year)
                                                                                                                                                                                                       .document(""+month)
                                                                                                                                                                                                       .update("ammount",""+amm1)
                                                                                                                                                                                                       .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                                                                           @Override
                                                                                                                                                                                                           public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                                                                                               if (task.isSuccessful()) {
                                                                                                                                                                                                                   progressDialog.dismiss();
                                                                                                                                                                                                                   flatDialog311.dismiss();
                                                                                                                                                                                                                   Toasty.success(getApplicationContext(),"Done",Toasty.LENGTH_SHORT,true).show();
                                                                                                                                                                                                               }
                                                                                                                                                                                                           }
                                                                                                                                                                                                       });

                                                                                                                                                                                           }

                                                                                                                                                                                       }
                                                                                                                                                                                   }
                                                                                                                                                                               });


                                                                                                                                                                   }
                                                                                                                                                               }
                                                                                                                                                           });

                                                                                                                                               }
                                                                                                                                               else {
                                                                                                                                                   String balance=scrond;
                                                                                                                                                   String eruelavent="0";
                                                                                                                                                   int amm=Integer.parseInt(eruelavent)+Integer.parseInt(balance);
                                                                                                                                                   Map<String, String> userMap4 = new HashMap<>();

                                                                                                                                                   userMap4.put("ammount",""+amm);
                                                                                                                                                   firebaseFirestore.collection("Daily_Collection")
                                                                                                                                                           .document("Registration_Balance")
                                                                                                                                                           .collection(""+month)
                                                                                                                                                           .document(""+day)
                                                                                                                                                           .set(userMap4)
                                                                                                                                                           .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                               @Override
                                                                                                                                                               public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                                                   if (task.isSuccessful()) {
                                                                                                                                                                       String balance1=scrond;
                                                                                                                                                                       String eruelavent1="0";
                                                                                                                                                                       int amm1=Integer.parseInt(eruelavent1)+Integer.parseInt(balance1);
                                                                                                                                                                       Map<String, String> userMap41 = new HashMap<>();

                                                                                                                                                                       userMap41.put("ammount",""+amm1);
                                                                                                                                                                       firebaseFirestore.collection("Daily_Collection")
                                                                                                                                                                               .document("Registration_Balance")
                                                                                                                                                                               .collection(""+year)
                                                                                                                                                                               .document(""+month)
                                                                                                                                                                               .set(userMap41)
                                                                                                                                                                               .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                                                                                                                   @Override
                                                                                                                                                                                   public void onComplete(@NonNull Task<Void> task) {
                                                                                                                                                                                       if (task.isSuccessful()) {
                                                                                                                                                                                           progressDialog.dismiss();
                                                                                                                                                                                           flatDialog311.dismiss();
                                                                                                                                                                                           Toasty.success(getApplicationContext(),"Done",Toasty.LENGTH_SHORT,true).show();
                                                                                                                                                                                       }
                                                                                                                                                                                   }
                                                                                                                                                                               });

                                                                                                                                                                   }
                                                                                                                                                               }
                                                                                                                                                           });
                                                                                                                                               }
                                                                                                                                           }

                                                                                                                                       }
                                                                                                                                   });

                                                                                                                       }
                                                                                                                   }
                                                                                                               });

                                                                                                   }
                                                                                               }
                                                                                           });

                                                                               }
                                                                           }
                                                                       }
                                                                   });

                                                       }
                                                       else {
                                                           progressDialog.dismiss();
                                                           flatDialog311.dismiss();
                                                           Toasty.error(getApplicationContext(),"No user found",Toasty.LENGTH_SHORT,true).show();
                                                       }
                                                   }
                                                }
                                            });

                                }

                            }
                        })
                        .withSecondButtonListner(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                flatDialog311.dismiss();
                            }
                        })
                        .show();
                break;


        }
        return false;
    }
    @Override
    public void onBackPressed()   {
        AlertDialog.Builder warning = new AlertDialog.Builder(this)
                .setTitle("Exit")
                .setMessage("Are you want to exit?")
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // ToDO: delete all the notes created by the Anon user


                        dialog.dismiss();
                        finishAffinity();

                    }
                });

        warning.show();
    }
}