package com.samsson.motornation.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.samsson.motornation.R;
import com.samsson.motornation.activity.CommentActivity;
import com.samsson.motornation.activity.FriendsProfileActivity;
import com.samsson.motornation.activity.MainActivity;
import com.samsson.motornation.delegates.CustomDelegates;
import com.samsson.motornation.model.Home;
import com.samsson.motornation.utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Homelist_adapter extends RecyclerView.Adapter<Homelist_adapter.ViewHolder> implements CustomDelegates {
    Context context;
    public ArrayList<Home> Autodetailslist;
    View itemLayoutView;

    public Homelist_adapter(Context context, ArrayList<Home> autodetailslist) {
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
                .inflate(R.layout.item_feed, null);
        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView, context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
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
     holder.comment.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent =new Intent(context, CommentActivity.class);
             context.startActivity(intent);
         }
     });
     holder.profile.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent =new Intent(context, FriendsProfileActivity.class);
             context.startActivity(intent);
         }
     });
     holder.share.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             boolean result = Utility.checkPermission(MainActivity.mainActivity);
             if (result) {
                 Bitmap b = BitmapFactory.decodeResource(MainActivity.mainActivity.getResources(), R.drawable.motor1);
                 Intent share = new Intent(Intent.ACTION_SEND);
                 share.setType("image/*");
                 ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                 b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                 String path = MediaStore.Images.Media.insertImage(MainActivity.mainActivity.getContentResolver(),
                         b, "Title", null);
                 Uri imageUri = Uri.parse(path);
                 share.putExtra(Intent.EXTRA_STREAM, imageUri);
                 MainActivity.mainActivity.startActivity(Intent.createChooser(share, "Select"));

             }
            /* Bitmap b = BitmapFactory.decodeResource(context.getResources(),R.drawable.motor1);
             Intent share = new Intent(Intent.ACTION_SEND);
             share.setType("image/jpeg");
             ByteArrayOutputStream bytes = new ByteArrayOutputStream();
             b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
             String path = MediaStore.Images.Media.insertImage(context.getContentResolver(),
                     b, "Title", null);
             Uri imageUri =  Uri.parse(path);
             share.putExtra(Intent.EXTRA_STREAM, imageUri);
             MainActivity.mainActivity.startActivity(Intent.createChooser(share, "Select"));*/
         }
     });

     holder.like.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if(holder.like.getDrawable().getConstantState() == context.getResources().getDrawable( R.drawable.like1).getConstantState())
             {
                 //  Toast.makeText(getApplicationContext(),"eye",Toast.LENGTH_SHORT).show();
                 holder.like.setImageResource(R.drawable.like);
                 holder.tvLike.setText("3 Likes");
             }
             else if (holder.like.getDrawable().getConstantState() == context.getResources().getDrawable( R.drawable.like).getConstantState())
             {
                 //Toast.makeText(getApplicationContext(),"eye22",Toast.LENGTH_SHORT).show();
                 holder.like.setImageResource(R.drawable.like1);
                 holder.tvLike.setText("2 Likes");
             }
         }
     });
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
        public ImageView like,share,comment,profile;
        TextView proName,tvLike,tvComment;


        public ViewHolder(View itemView, Context context) {
            super(itemView);
          like = (ImageView) itemView.findViewById(R.id.like);
            share = (ImageView) itemView.findViewById(R.id.share);
            comment = (ImageView) itemView.findViewById(R.id.comment);
            profile = (ImageView) itemView.findViewById(R.id.image_profile);
            proName = (TextView) itemView.findViewById(R.id.user_name_text_view);
            tvLike = (TextView) itemView.findViewById(R.id.tv_like);
            tvComment = (TextView) itemView.findViewById(R.id.tv_comment);


        }
    }
}
