package rovi.iq;

/**
 * Created by Rrohi on 12-02-2018.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;



public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailsViewHolder> {

    ArrayList<Details> details = new ArrayList<>();
    Context c;

    public DetailsAdapter(Context c, ArrayList<Details> details) {

        this.details = details;
        this.c = c;
    }

    @Override
    public DetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout, parent, false);
        DetailsViewHolder detailsViewHolder = new DetailsViewHolder(view);
        return detailsViewHolder;
    }

    @Override
    public void onBindViewHolder(DetailsViewHolder holder, int position) {
        Details DET = details.get(position);
        holder.cat_image.setImageResource(DET.getImage_id());
        holder.cat_name.setText(DET.getCat_name());


       /* holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {

                MainActivity m = new MainActivity();
                        if(pos==0)
                        {
                            m.move();
                        }
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return details.size();
    }

    public static class DetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView cat_image;
        TextView cat_name;
        int pos;
        Context c;

        public DetailsViewHolder(View view) {

            super(view);


            c = view.getContext();
            cat_image = (ImageView) view.findViewById(R.id.cat_image1);
            cat_name = (TextView) view.findViewById(R.id.cat_name1);
            view.setOnClickListener(this);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pos = getAdapterPosition();
                    if (pos == 0) {
                        Intent i = new Intent(c, GeneralKnowledgeActivity.class);
                        c.startActivity(i);
                    }
                   else if (pos == 1) {
                        Intent i = new Intent(c, ZooActivity.class);
                        c.startActivity(i);
                    }
                    else if (pos == 2) {
                        Intent i = new Intent(c, GeoActivity.class);
                        c.startActivity(i);
                    }
                    else if (pos == 3) {
                        Intent i = new Intent(c, SportsActivity.class);
                        c.startActivity(i);
                    }
                    else if (pos == 4) {
                        Intent i = new Intent(c, FilmActivity.class);
                        c.startActivity(i);
                    }
                }
            });
        }


           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                  int position = getAdapterPosition();


                    if(position==0) {
                        MainActivity m = new MainActivity();
                        m.move();
                });*/


        @Override
        public void onClick(View v) {

        }

    }
}

