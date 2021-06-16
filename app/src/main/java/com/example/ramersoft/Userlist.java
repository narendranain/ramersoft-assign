package com.example.ramersoft;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class Userlist extends RecyclerView.Adapter<Userlist.ViewHolder> {

    private static Context context1;
    private List<User> eventUserList;


    public String userid;
    // Provide a suitable constructor (depends on the kind of dataset)
    public Userlist(Context context, List<User> myDataset) {
        eventUserList = myDataset;
        Userlist.context1 = context;
    }

    //after search update list
    public void updateList(List<User> list){

        eventUserList = list;
      //  notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent,
                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(context1)
                .inflate(R.layout.s_userlist ,parent, false);

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        final User user = eventUserList.get(position);

        // - replace the contents of the view with that element
        holder.setItem(user);

        //new logic
        try {
            if (position == 0) {
                holder.alpha_header.setVisibility(View.GONE);
                holder.alpha_header.setText(user.getFirst_name().substring(0, 1));
            } else if (!user.getFirst_name().substring(0, 1).equalsIgnoreCase(eventUserList.get(position - 1).getFirst_name().substring(0, 1))) {
                holder.alpha_header.setVisibility(View.GONE);
                holder.alpha_header.setText(user.getFirst_name().substring(0, 1));
            } else {
                holder.alpha_header.setVisibility(View.GONE);

            }
        }catch (Exception e){

        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return eventUserList.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        Context context;

        TextView name,qualification,address;


        ViewHolder(View v) {
            super(v);



            name = (TextView)v.findViewById(R.id.name);
            qualification = (TextView) v.findViewById(R.id.qualification);
            address = (TextView) v.findViewById(R.id.address);

            v.setOnClickListener(this);

        }

        void setItem(User user) {
            try {
                tileProvider = new LetterTileProvider(context1);
                this.user = user;
                authorName.setText(user.getFirst_name() + " " + user.getLast_name());
                if (city != null && !city.equals("")) {
                    city.setText(user.getCity());
                }
                if (user.getDesignation() != null) {
                    tvDesignation.setText(user.getDesignation());
                    tvDesignation.setVisibility(user.getDesignation().trim().isEmpty() ? View.GONE : View.VISIBLE);
                }

                DisplayMetrics displayMetrics;
                final float dpWidth;
                displayMetrics = context1.getResources().getDisplayMetrics();
                dpWidth = displayMetrics.widthPixels * 0.15F;
                RelativeLayout.LayoutParams imgParams = (RelativeLayout.LayoutParams) profilepic.getLayoutParams();
                imgParams.width = (int) dpWidth;
                imgParams.height = (int) dpWidth;

                if (null != user.getProfile_pic() && !user.getProfile_pic().isEmpty()) {

                    if (URLUtil.isValidUrl(user.getProfile_pic()))
                        Glide.with(context1.getApplicationContext())
                                .load(user.getProfile_pic())
                                .asBitmap()
                                .placeholder(R.drawable.round_user)
                                .error(R.drawable.round_user)
                                .into(profilepic);

                    profilepic.setCornerRadius(8, 8, 8, 8);
                                   /* @Override
                                    protected void setResource(Bitmap resource) {
                                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context1.getResources(),
                                                Bitmap.createScaledBitmap(resource, (int) dpWidth, (int) dpWidth, false));
                                        drawable.setCircular(true);
                                        profilepic.setImageDrawable(drawable);
                                    }
                                });*/
                } else {
                    Bitmap letterTile = tileProvider.getLetterTile(user.getFirst_name().trim(), "key", (int) dpWidth, (int) dpWidth, user.getColor());
                    profilepic.setImageBitmap((letterTile));
                    profilepic.setCornerRadius(8, 8, 8, 8);
                }
            }catch (Exception e)
            {

            }
        }

        @Override
        public void onClick(View v) {
            System.out.println("Item clicked pos " + getAdapterPosition());

            int position = getAdapterPosition();
           /* if (v.getId()==authorName.getId()){

            }*/
            onCardClickListner.OnItemClick( v, user , position );


        }

        @Override
        public boolean onLongClick(View v) {
            int position = getAdapterPosition();
            onCardClickListner.OnItemLongClicked(v, user, position);
            return true;
        }
    }



}
