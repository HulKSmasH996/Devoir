package com.kiit.viper.devoir;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kiit.viper.devoir.model.Issues;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by VIPER on 10-Mar-17.
 */

public class Feeds extends Fragment {

    ListView list;
    //List<String> issuesList=new ArrayList<>();
    List<Issues> issuesListObj=new ArrayList<>();
    //List<String> submitter =new ArrayList<>();
   // ArrayAdapter<String> issuesArrayAdapter;
    FeedsAdapter myAdapter;
    private DatabaseReference myDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main3, container, false);

       // list= (ListView) rootView.findViewById(R.id.mainListView);
        list= (ListView) rootView.findViewById(R.id.feedListView);
        myDatabase= FirebaseDatabase.getInstance().getReference("issues");

        /*myDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                *//*Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for(DataSnapshot child: children){
                    Issues issuesValue = child.getValue(Issues.class);
                    String issuesValue = child.child("issueType").getValue(String.class);
                    String issuesSubmitter = child.child("userName").getValue(String.class);
                    issuesList.add(issuesValue);
                    submitter.add(issuesSubmitter);
                    issuesListObj.add(issuesValue);
                    myAdapter.notifyDataSetChanged();
                }*//*
                if(!issuesListObj.isEmpty()){
                    issuesListObj.clear();
                }
                  Issues issueObj = new Issues();
                  Issues issues = dataSnapshot.getValue(Issues.class);
                  issueObj.setImageUrl(issues.getImageUrl());
                  issueObj.setLatlongi(issues.getLatlongi());
                  issueObj.setDescription(issues.getDescription());
                  issueObj.setUserName(issues.getUserName());
                  issueObj.setIssueID(issues.getIssueID());
                  issueObj.setIssueType(issues.getIssueType());
                  issuesListObj.add(issueObj);

                  myAdapter = new FeedsAdapter(getActivity(), issuesListObj);
                  list.setAdapter(myAdapter);

                  myAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                issuesListObj.clear();
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Issues issueValue=child.getValue(Issues.class);

                    issuesListObj.add(issueValue);

                }

                myAdapter = new FeedsAdapter(getActivity(), issuesListObj);
                list.setAdapter(myAdapter);

                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()) {
                    Issues issueValue = child.getValue(Issues.class);

                    issuesListObj.remove(issueValue);
                }

                myAdapter = new FeedsAdapter(getActivity(), issuesListObj);
                list.setAdapter(myAdapter);

                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/
        myDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!issuesListObj.isEmpty()){
                    issuesListObj.clear();
                }
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Issues issueValue=child.getValue(Issues.class);

                    issuesListObj.add(issueValue);

                }

                myAdapter = new FeedsAdapter(getActivity(), issuesListObj);
                list.setAdapter(myAdapter);

                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return rootView;
    }


   /* @Override
    public void onUpvoteClickListener(int postion, View v, LinearLayout upVote) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        // set title
        alertDialogBuilder.setTitle("Confirmation");

        // set dialog message
        alertDialogBuilder
                .setMessage("By clicking Yes you give your confirmation that this complaint/issue in" +
                        " genuine to the best of your knowledge")
                .setCancelable(true)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        *//*Intent intent=new Intent(getContext(), SomeActivit.class);
                        intent.putExtra("task",(Parcelable)task);
                        startActivity(intent);
                        dialog.cancel();
                        getActivity().finish();*//*
                        Toast.makeText(getActivity(),"yes",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Not Sure",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                        getActivity().finish();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    @Override
    public void downUpvoteClickListener(int postion, View v, LinearLayout downVote) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

        // set title
        alertDialogBuilder.setTitle("Confirmation");

        // set dialog message
        alertDialogBuilder
                .setMessage("By clicking Yes you give your confirmation that this complaint/issue is" +
                        " Fake & does not exist in your locality")
                .setCancelable(true)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        *//*Intent intent=new Intent(getContext(), SomeActivit.class);
                        intent.putExtra("task",(Parcelable)task);
                        startActivity(intent);
                        dialog.cancel();
                        getActivity().finish();*//*
                        Toast.makeText(getActivity(),"yes",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Not Sure",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                        getActivity().finish();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }*/
}
