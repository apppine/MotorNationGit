package com.samsson.motornation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.samsson.motornation.R;
import com.samsson.motornation.delegates.CustomDelegates;
import com.samsson.motornation.model.Home;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Suggestedlist_adapter extends RecyclerView.Adapter<Suggestedlist_adapter.ViewHolder> implements CustomDelegates {
    Context context;
    public ArrayList<Home> Autodetailslist;
    View itemLayoutView;

    public Suggestedlist_adapter(Context context, ArrayList<Home> autodetailslist) {
        this.context = context;
        Autodetailslist = autodetailslist;
    }

    public void setItems(ArrayList<Home> persons) {
        this.Autodetailslist = persons;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.suggested_single, null);
        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView, context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      //  holder.bind((Autodetails) Autodetailslist.get(position), listenerc, position);
       /* Picasso.with(context)
                .load(Autodetailslist.get(position).getImage())
                .error(R.mipmap.ic_launcher)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.Image);*/
     /*  holder.call.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });*/
    }

    @Override
    public int getItemCount() {
        return Autodetailslist.size();
    }


    @Override
    public void multiResponseFromApi(String data, String Apiname) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            //wishlist_status = jsonObject.getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse() {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView profile,call;
        public TextView Autonum,mobilenum;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
           /* profile = (ImageView) itemView.findViewById(R.id.profile);
            call = (ImageView) itemView.findViewById(R.id.call);
            Autonum = (TextView) itemView.findViewById(R.id.autonum);
            mobilenum = (TextView) itemView.findViewById(R.id.phonenum);*/

        }
    }
}
