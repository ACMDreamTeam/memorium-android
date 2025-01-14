package com.acmdreamteam.memorium.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.acmdreamteam.memorium.JournalReadActivity;
import com.acmdreamteam.memorium.JournalViewActivity;
import com.acmdreamteam.memorium.Model.Journal;
import com.acmdreamteam.memorium.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.ViewHolder> {

    private final Context mContext;

    private final FirebaseUser firebaseUser;



    private ArrayList<Journal> mJournal;



    public JournalAdapter(Context mContext, FirebaseUser firebaseUser,ArrayList<Journal> mJournal) {
       this.mContext = mContext;
       this.firebaseUser = firebaseUser;
       this.mJournal = mJournal;
    }


    @NonNull
    @Override
    public JournalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(mContext).inflate(R.layout.journel_item, parent, false);
        return new JournalAdapter.ViewHolder(viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull JournalAdapter.ViewHolder holder, int position) {


        Journal journal = mJournal.get(position);

        holder.title.setText(journal.getTitle());
        holder.date.setText(journal.getDate());
        holder.description.setText(journal.getThings());


        holder.card.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN)  {
                    holder.card.setCardBackgroundColor(mContext.getResources().getColor(R.color.onClick));

                }else {
                    holder.card.setCardBackgroundColor(mContext.getResources().getColor(R.color.white));

                }
                return false;
            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.card.setCardBackgroundColor(mContext.getResources().getColor(R.color.white));
                Intent intent = new Intent(mContext, JournalReadActivity.class);
                intent.putExtra("journalID", journal.getJournalID());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });









    }


    @Override
    public int getItemCount() {
        return mJournal.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        TextView title = itemView.findViewById(R.id.title);
        TextView date = itemView.findViewById(R.id.date);
        TextView description = itemView.findViewById(R.id.description);

        CardView card = itemView.findViewById(R.id.card);



        public ViewHolder(View view) {
            super(view);



        }
    }
}
