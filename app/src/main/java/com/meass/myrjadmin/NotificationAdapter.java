package com.meass.myrjadmin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class NotificationAdapter  extends RecyclerView.Adapter<NotificationAdapter.myView> {
    private List<Notification_Model> data;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    public NotificationAdapter(List<Notification_Model> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public NotificationAdapter.myView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatbox, parent, false);
        return new NotificationAdapter.myView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.myView holder, final int position) {
        holder.add_notes_title.setText(data.get(position).getUsername());
        holder.blog_detail_desc.setText(data.get(position).getFeedback());
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        holder.add_notes_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                builder.setTitle("Delete")
                        .setMessage("Are you wan to delete")
                        .setPositiveButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        firebaseFirestore.collection("Notificatios")
                                .document(data.get(position).getTime())
                                .delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(view.getContext(), "Done", Toast.LENGTH_SHORT).show();
                                            view.getContext().startActivity(new Intent(view.getContext(),HomeActivity.class));

                                        }
                                    }
                                });
                    }
                }).create().show();
            }
        });
holder.cardsddd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
        builder.setTitle("Delete")
                .setMessage("Are you wan to delete")
                .setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                firebaseFirestore.collection("Notificatios")
                        .document(data.get(position).getTime())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(v.getContext(), "Done", Toast.LENGTH_SHORT).show();
                                    v.getContext().startActivity(new Intent(v.getContext(),HomeActivity.class));

                                }
                            }
                        });
            }
        }).create().show();
    }
});
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class myView extends RecyclerView.ViewHolder {
        TextView blog_detail_desc,add_notes_title;
        CardView cardsddd;
        public myView(@NonNull View itemView) {

            super(itemView);
            blog_detail_desc=itemView.findViewById(R.id.blog_detail_desc);
            add_notes_title=itemView.findViewById(R.id.add_notes_title);
            cardsddd=itemView.findViewById(R.id.cardsddd);

        }
    }
}
