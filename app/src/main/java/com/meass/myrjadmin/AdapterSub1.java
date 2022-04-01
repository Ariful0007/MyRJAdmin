package com.meass.myrjadmin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class AdapterSub1 extends RecyclerView.Adapter<AdapterSub1.myview> {
    public List<Sublime> data;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    public AdapterSub1(List<Sublime> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public AdapterSub1.myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.subbb,parent,false);
        return new AdapterSub1.myview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSub1.myview holder, final int position) {
        firebaseAuth= FirebaseAuth.getInstance();
        firebaseFirestore= FirebaseFirestore.getInstance();
        holder.customer_name.setText(data.get(position).getName());
        holder.customer_number.setText(data.get(position).getEmail());
        holder.logout.setText("Active Account");
       holder.card_view8.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               firebaseFirestore.collection("All_UserID")
                       .document(data.get(position).getUsername()+"@gmail.com")
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
                                                               String username=task.getResult().getString("username");
                                                               String name=task.getResult().getString("name");
                                                               String pass=task.getResult().getString("pass");
                                                               String number=task.getResult().getString("number");
                                                               String email=task.getResult().getString("email");
                                                               firebaseFirestore.collection("Users")
                                                                       .document(uuid)
                                                                       .collection("Main_Balance")
                                                                       .document(data.get(position).getUsername()+"@gmail.com")
                                                                       .get()
                                                                       .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                           @Override
                                                                           public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                               if (task.isSuccessful()) {
                                                                                   if (task.getResult().exists()) {
                                                                                       String shoping_balance=task.getResult().getString("shoping_balance");
                                                                                       String main_balance=task.getResult().getString("main_balance");
                                                                                       String purches_balance=task.getResult().getString("purches_balance");

                                                                                       firebaseFirestore.collection("Persions1")
                                                                                                .document(data.get(position).getUsername()+"@gmail.com")
                                                                                               .collection("List")
                                                                                               .get()
                                                                                              .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                                                  @Override
                                                                                                  public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                                                      if (task.isSuccessful()) {
                                                                                                          int ncount = 0;
                                                                                                          for (DocumentSnapshot document : task.getResult()) {
                                                                                                              ncount++;
                                                                                                          }
                                                                                                          int finalNcount = ncount;
                                                                                                          firebaseFirestore.collection("Persions")
                                                                                                                  .document(data.get(position).getUsername()+"@gmail.com")
                                                                                                                  .collection("List")
                                                                                                                  .get()
                                                                                                                  .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                                                                      @Override
                                                                                                                      public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                                                                          if (task.isSuccessful()) {
                                                                                                                              int ncount1 = 0;
                                                                                                                              for (DocumentSnapshot document : task.getResult()) {
                                                                                                                                  ncount1++;
                                                                                                                              }
                                                                                                                              int finalNcount1 = ncount1;
                                                                                                                              firebaseFirestore.collection("Persions")
                                                                                                                                      .document(data.get(position).getUsername()+"@gmail.com")
                                                                                                                                      .collection("List")
                                                                                                                                      .get()
                                                                                                                                      .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                                                                                          @Override
                                                                                                                                          public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                                                                                              if (task.isSuccessful()) {
                                                                                                                                                  int ncount12 = 0;
                                                                                                                                                  for (DocumentSnapshot document : task.getResult()) {
                                                                                                                                                      ncount12++;
                                                                                                                                                  }
                                                                                                                                                  String message="Username : "+username+"\n" +
                                                                                                                                                          "Full name : "+name+"\n" +
                                                                                                                                                          "Password : "+pass+"\n" +
                                                                                                                                                          "Number : "+number+"\n"+
                                                                                                                                                          "Email : "+email+"\n" +
                                                                                                                                                          "Main Balance : "+main_balance+"\n" +
                                                                                                                                                          "Registration Balance : "+purches_balance+"\n" +
                                                                                                                                                          "Shopping Balance : "+shoping_balance+"\n"+
                                                                                                                                                          "Free Refer ID : "+""+ finalNcount +"\n"+
                                                                                                                                                          "Basic ID : "+""+ finalNcount1+"\n" +
                                                                                                                                                          "Main Active ID : "+ncount12+"\n"+
                                                                                                                                                          "Thank You";
                                                                                                                                                  AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                                                                                                                                                  builder.setTitle("Information")
                                                                                                                                                          .setMessage(message)
                                                                                                                                                          .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                                                                                                                              @Override
                                                                                                                                                              public void onClick(DialogInterface dialogInterface, int i) {
                                                                                                                                                                  dialogInterface.dismiss();
                                                                                                                                                              }
                                                                                                                                                          }).create().show();
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
                                                       }
                                                   }
                                               });
                                   }
                               }
                           }
                       });
           }
       });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myview extends RecyclerView.ViewHolder{
        TextView customer_name,customer_number,customer_area,logout,customer_area3,customer_area8;
        CardView card_view8;
        public myview(@NonNull View itemView) {
            super(itemView);
            customer_name=itemView.findViewById(R.id.customer_name);
            customer_number=itemView.findViewById(R.id.customer_number);
            logout=itemView.findViewById(R.id.logout);
            card_view8=itemView.findViewById(R.id.card_view8);
        }
    }
}


