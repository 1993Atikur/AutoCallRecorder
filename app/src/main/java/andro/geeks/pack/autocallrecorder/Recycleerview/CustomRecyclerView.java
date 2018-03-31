package andro.geeks.pack.autocallrecorder.Recycleerview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import andro.geeks.pack.autocallrecorder.R;


public class CustomRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<String> Name=new ArrayList<>();
    List<String>Number=new ArrayList<>();
    List<String>Duration=new ArrayList<>();
    List<String>Date=new ArrayList<>();

    public CustomRecyclerView(Context context,List<String>Name,List<String>Number,List<String>Duration,List<String>Date){
        this.context=context;
        this.Name=Name;
        this.Number=Number;
        this.Duration=Duration;
        this.Date=Date;


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

        ((MyViewHolder)holder).t_FirstLetter.setText(String.valueOf(Name.get(position).charAt(0)));
        ((MyViewHolder)holder).t_Name.setText(Name.get(position));
        ((MyViewHolder)holder).t_Number.setText(Number.get(position));
        ((MyViewHolder)holder).t_Date.setText(Date.get(position));
        ((MyViewHolder)holder).t_Duration.setText(Duration.get(0));
        ((MyViewHolder)holder).more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,Name.get(position),Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return Name.size();
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
