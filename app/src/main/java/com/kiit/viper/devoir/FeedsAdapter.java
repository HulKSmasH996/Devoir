package com.kiit.viper.devoir;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kiit.viper.devoir.model.Issues;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mrsp7 on 03-04-2018.
 */
class FeedsAdapter extends BaseAdapter

{
    interface ClickListener{
        public  void  onUpvoteClickListener(int postion, View v ,LinearLayout upVote);
        public  void  downUpvoteClickListener(int postion,View v ,LinearLayout downVote);

    }

    ClickListener customListner;
    Context context;
    List<Issues> data;
    private static LayoutInflater inflater = null;

    public FeedsAdapter(Context context, List<Issues> data) {

        this.context = context;
        this.data = data;
        inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if(vi == null){
            vi = inflater.inflate(R.layout.feeds_row,null);
            final TextView category = (TextView) vi.findViewById(R.id.category);
            final TextView submittedby = (TextView) vi.findViewById(R.id.submitted_by);
            final TextView issuelocation = (TextView) vi.findViewById(R.id.location);
            final ImageView issueImage= (ImageView) vi.findViewById(R.id.imageView);
            final LinearLayout upVote= (LinearLayout) vi.findViewById(R.id.upVoteLinearLay);
            final LinearLayout downVote= (LinearLayout) vi.findViewById(R.id.downVoteLinearLay);
           // String s=data.get(position).getDescription();
            category.setText(data.get(position).getIssueType());
            submittedby.setText(data.get(position).getUserName());
            issuelocation.setText(data.get(position).getLatlongi());
            Picasso.get().load(data.get(position).getImageUrl())
                    .placeholder(R.drawable.loading)
                    .fit().centerCrop()
                    .into(issueImage);

            upVote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                    // set title
                    alertDialogBuilder.setTitle("Confirmation");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("By clicking Yes you give your confirmation that this complaint/issue in" +
                                    " genuine to the best of your knowledge")
                            .setCancelable(true)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                        /*Intent intent=new Intent(getContext(), SomeActivit.class);
                        intent.putExtra("task",(Parcelable)task);
                        startActivity(intent);
                        dialog.cancel();
                        getActivity().finish();*/
                                    Toast.makeText(context,"yes",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Not Sure",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();

                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
                }
            });

            downVote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //customListner.downUpvoteClickListener(position,v,downVote);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                    // set title
                    alertDialogBuilder.setTitle("Confirmation");

                    // set dialog message
                    alertDialogBuilder
                            .setMessage("By clicking Yes you give your confirmation that this complaint/issue is" +
                                    " Fake & does not exist in your locality")
                            .setCancelable(true)
                            .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {

                                    Toast.makeText(context,"yes",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Not Sure",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();

                                }
                            });

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // show it
                    alertDialog.show();
                }
            });

        }
        return vi;
    }
    /*Context context;
     List
       FeedsAdapter(Context context, String[] titles,String[] submitterArray)
       {
           super(context,R.layout.feeds_row, R.id.category, titles );
           this.context=context;
           //this.images=imgs;
           this.titlesArray=titles;
           this.submitterArray=submitterArray;
       }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row= inflater.inflate(R.layout.feeds_row,parent,false);
        ImageView issueImage = (ImageView) row.findViewById(R.id.imageView);
        TextView issueCategory= (TextView) row.findViewById(R.id.category);
        TextView issueSubmittedBy= (TextView) row.findViewById(R.id.submitted_by);
        TextView issueLocation = (TextView) row.findViewById(R.id.location);

        issueCategory.setText(titlesArray[position]);
        issueSubmittedBy.setText(submitterArray[position]);

        return row;
    }*/
}
