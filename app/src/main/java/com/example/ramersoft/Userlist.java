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

import com.example.ramersoft.model.GlobalConfig;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class Userlist extends RecyclerView.Adapter<Userlist.ViewHolder> {

    private static Context context1;
    private List<JSONArray> eventUserList;
    JSONObject jsonObject;


    public String userid;
     JSONArray user;
    // Provide a suitable constructor (depends on the kind of dataset)
    public Userlist(Context context, List<JSONArray> myDataset) {
        eventUserList = myDataset;
        Userlist.context1 = context;
        updateList(eventUserList);
    }

    //after search update list
    public void updateList(List<JSONArray> list){

        eventUserList = list;
     //  notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.s_userlist, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder( ViewHolder holder, final int position) {
        // - get element from your dataset at this position

        user = eventUserList.get(position);
        for (int i = 0; i < user.length(); i++) {
            try {
                 jsonObject = user.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        try {
            holder.name.setText(jsonObject.getString("user_name").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        holder.qualification.setText(jsonObject.optString("user_qualification").toString());
        holder.address.setText(jsonObject.optString("user_address").toString());

    }

    @Override
    public int getItemCount() {
        return eventUserList.size();
    }

    // Return the size of your dataset (invoked by the layout manager)


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {

        Context context;

        TextView name,qualification,address;
        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            qualification = (TextView) itemView.findViewById(R.id.qualification);
            address = (TextView) itemView.findViewById(R.id.address);
        }












        void setItem(JSONObject user) throws JSONException {

            name.setText(user.optString("user_name").toString());
            qualification.setText(user.optString("user_qualification").toString());
            address.setText(user.optString("user_address").toString());



        }




    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }


}
