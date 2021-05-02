package com.example.kelasasqllite.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kelasasqllite.MainActivity;
import com.example.kelasasqllite.R;
import com.example.kelasasqllite.database.DBController;
import com.example.kelasasqllite.database.EditData;
import com.example.kelasasqllite.database.LihatData;
import com.example.kelasasqllite.database.Teman;

import java.util.ArrayList;
import java.util.HashMap;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    private ArrayList<Teman> listData;
    private Context context;

    public TemanAdapter(ArrayList<Teman> listData) {
        this.listData = listData;
    }

    @Override
    public TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutinf = LayoutInflater.from(parent.getContext());
        View view = layoutinf.inflate(R.layout.row_data_teman,parent,false);
        return new TemanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TemanViewHolder holder, int position) {
        String nm,tlp,id;

        id = listData.get(position).getId();
        nm = listData.get(position).getNama();
        tlp = listData.get(position).getTelpon();
        DBController db = new DBController(context);

        holder.namaTxt.setTextColor(Color.BLUE);
        holder.namaTxt.setTextSize(20);
        holder.namaTxt.setText(nm);
        holder.telponTxt.setText(tlp);
        holder.cardku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, LihatData.class);
                i.putExtra("id",id);
                i.putExtra("nama",nm);
                i.putExtra("telp",tlp);
                context.startActivity(i);
            }
        });

        holder.cardku.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu pop = new PopupMenu(context,holder.cardku);
                pop.inflate(R.menu.popupmenu);
                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.edit:
                                Intent i = new Intent(context, EditData.class);
                                i.putExtra("id",id);
                                i.putExtra("nama",nm);
                                i.putExtra("telp",tlp);
                                context.startActivity(i);
                                break;

                            case R.id.hapus:
                                HashMap<String,String> val = new HashMap<>();
                                val.put("id",id);
                                db.hapusData(val);
                                Intent in = new Intent(context, MainActivity.class);
                                context.startActivity(in);
                                break;
                        }
                        return true;
                    }
                });
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (listData != null)?listData.size():0;
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {
        private CardView cardku;
        private TextView namaTxt,telponTxt;
        public TemanViewHolder(View view) {
            super(view);
            cardku = (CardView) view.findViewById(R.id.kartuku);
            namaTxt = (TextView) view.findViewById(R.id.textNama);
            telponTxt = (TextView) view.findViewById(R.id.textTelpon);

        }
    }
}