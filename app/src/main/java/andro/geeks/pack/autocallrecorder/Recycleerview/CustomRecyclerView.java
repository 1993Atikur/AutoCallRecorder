package andro.geeks.pack.autocallrecorder.Recycleerview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import andro.geeks.pack.autocallrecorder.R;
import andro.geeks.pack.autocallrecorder.RecordMedia.RecordPlayer;


public class CustomRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder>implements Filterable {

    Context context;

    ArrayList<Callers> callers,filterList;

    CustomFilter filter;

    public CustomRecyclerView(Context context,ArrayList<Callers> callers){
        this.context=context;
        this.callers=callers;
        this.filterList=callers;


    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(context);
        View row=inflater.inflate(R.layout.list_single,parent,false);

        MyViewHolder myViewHolder=new MyViewHolder(row);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ((MyViewHolder)holder).t_FirstLetter.setText(String.valueOf(callers.get(position).getName().charAt(0)));
        ((MyViewHolder)holder).t_Name.setText(callers.get(position).getName());
        ((MyViewHolder)holder).t_Number.setText(callers.get(position).getNumber());
        ((MyViewHolder)holder).t_Date.setText(callers.get(position).getDate());
        ((MyViewHolder)holder).t_Duration.setText(callers.get(position).getDuration());
        ((MyViewHolder)holder).more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(context, RecordPlayer.class);
                intent.putExtra("Name",callers.get(position).getName());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return callers.size();
    }

    @Override
    public Filter getFilter() {
        if(filter==null)
        {
            filter=new CustomFilter(filterList,this);
        }

        return filter;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
            TextView t_FirstLetter,t_Name,t_Number,t_Date,t_Duration;
            Button more;

        public MyViewHolder(View convertView) {
            super(convertView);
            t_FirstLetter=(TextView)convertView.findViewById(R.id.firstletterid);

            t_Name=(TextView)convertView.findViewById(R.id.name);

            t_Number=(TextView)convertView.findViewById(R.id.number);

            more=(Button)convertView.findViewById(R.id.more);

            t_Date=(TextView)convertView.findViewById(R.id.date);

            t_Duration=(TextView)convertView.findViewById(R.id.duration);
        }
    }


}
