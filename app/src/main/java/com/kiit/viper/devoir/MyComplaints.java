package com.kiit.viper.devoir;

/**
 * Created by VIPER on 10-Mar-17.
 */
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kiit.viper.devoir.FireBase.FireBaseUtil;
import com.kiit.viper.devoir.model.Issues;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.kiit.viper.devoir.Login.fUser;


public class MyComplaints extends Fragment{

    ListView myComplaintList;
    List<Issues> myIssuesList=new ArrayList<>();
    List<Issues> myTrimmedIssuesList=new ArrayList<>();
    MyComplaintAdapter myAdapter;
    private DatabaseReference myDatabase;
    private FirebaseAuth myFirebaseAuth;
    private DatabaseReference myFireBaseDB;

    String[] currentUserIssuesArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main2, container, false);

        myFirebaseAuth=FirebaseAuth.getInstance();
        myFireBaseDB= FirebaseDatabase.getInstance().getReference();

        myComplaintList= (ListView) rootView.findViewById(R.id.myComplaintListView);
        myDatabase= FirebaseDatabase.getInstance().getReference("issues");

        //splitIssueIDs();
        if(!myIssuesList.isEmpty()){
            myIssuesList.clear();
        }
        myDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Issues issueObj;
                 Issues issues =dataSnapshot.getValue(Issues.class);
                if(fUser.getUid().equals(issues.getUserID())){
                    issueObj = new Issues();
                    issueObj.setImageUrl(issues.getImageUrl());
                    issueObj.setLatlongi(issues.getLatlongi());
                    issueObj.setDescription(issues.getDescription());
                    issueObj.setUserName(issues.getUserName());
                    issueObj.setIssueID(issues.getIssueID());
                    issueObj.setIssueType(issues.getIssueType());
                    myIssuesList.add(issueObj);
                    myAdapter = new MyComplaintAdapter(getActivity(), myIssuesList);
                    myComplaintList.setAdapter(myAdapter);

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                /*for(DataSnapshot child: dataSnapshot.getChildren()){
                    Issues issueValue=child.getValue(Issues.class);

                    myIssuesList.add(issueValue);

                }

                myAdapter = new MyComplaintAdapter(getActivity(), myIssuesList);
                myComplaintList.setAdapter(myAdapter);

                myAdapter.notifyDataSetChanged();*/
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                /*for(DataSnapshot child: dataSnapshot.getChildren()){
                    Issues issueValue=child.getValue(Issues.class);

                    myIssuesList.remove(issueValue);

                }

                myAdapter = new MyComplaintAdapter(getActivity(), myIssuesList);
                myComplaintList.setAdapter(myAdapter);

                myAdapter.notifyDataSetChanged();*/
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       /* myDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Issues issueObj;
                Issues issues =dataSnapshot.getValue(Issues.class);
                if(fUser.getUid().equals(issues.getUserID())){
                    issueObj = new Issues();
                    issueObj.setImageUrl(issues.getImageUrl());
                    issueObj.setLatlongi(issues.getLatlongi());
                    issueObj.setDescription(issues.getDescription());
                    issueObj.setUserName(issues.getUserName());
                    issueObj.setIssueID(issues.getIssueID());
                    issueObj.setIssueType(issues.getIssueType());
                    myIssuesList.add(issueObj);
                    myAdapter = new MyComplaintAdapter(getActivity(), myIssuesList);
                    myComplaintList.setAdapter(myAdapter);

                }
                *//*if(!myIssuesList.isEmpty()){
                    myIssuesList.clear();
                }
                for(DataSnapshot child: dataSnapshot.getChildren()){

                    Issues issueValue=child.getValue(Issues.class);
                    for(int i=0;i<currentUserIssuesArray.length;i++){
                        if(currentUserIssuesArray[i].equals(child.getKey())){
                            myIssuesList.add(issueValue);
                        }
                    }
                    if(fUser.getUid().equals(child))
                    myIssuesList.add(issueValue);
                }

                for(int j=0;j<myIssuesList.size();j++){
                for(int i=0;i<currentUserIssuesArray.length;i++){
                    if(currentUserIssuesArray[i].equals(myIssuesList.get(i).getIssueID())){
                        myTrimmedIssuesList.add(myIssuesList.get(i));
                    }
                }
                }*//*
                *//*myAdapter = new MyComplaintAdapter(getActivity(), myIssuesList);
                myComplaintList.setAdapter(myAdapter);

                myAdapter.notifyDataSetChanged();*//*
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        return rootView;

        /*mainListView = (ListView) rootView.findViewById( R.id.mainListView );

        players.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot player : dataSnapshot.getChildren()) {
                    Log.i("player", player.getKey());
                    friends.add(player.getKey());
                }
                ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1,
                        friends);
                listView.setAdapter(arrayAdapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        ArrayList<String> list = new ArrayList<String>();
        list.addAll( Arrays.asList(Fire) );

        // Create ArrayAdapter using the planet list.
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, planetList);

        // Add more planets. If you passed a String[] instead of a List<String>
        // into the ArrayAdapter constructor, you must not add more items.
        // Otherwise an exception will occur.
        listAdapter.add( "Ceres" );
        listAdapter.add( "Pluto" );
        listAdapter.add( "Haumea" );
        listAdapter.add( "Makemake" );
        listAdapter.add( "Eris" );

        // Set the ArrayAdapter as the ListView's adapter.
        mainListView.setAdapter( listAdapter );*/
    }

    private void splitIssueIDs(){
        final String currentUser= myFirebaseAuth.getCurrentUser().getUid();
        myFireBaseDB.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                    for(DataSnapshot child: dataSnapshot.getChildren()){
                        if(child.getKey().equals(currentUser)){
                            String currentUserIssues = child.child("issueIDs").getValue(String.class);
                            currentUserIssuesArray=currentUserIssues.split(",");
                        }
                    }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    class MyComplaintAdapter extends BaseAdapter

    {
        Context context;
        List<Issues> data;
        private LayoutInflater inflater = null;

        public MyComplaintAdapter(Context context, List<Issues> data) {

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
        public View getView(int position, View convertView, ViewGroup parent) {
            View vi = convertView;
            if(vi == null){
                vi = inflater.inflate(R.layout.my_complaint_row,null);
                final TextView category = (TextView) vi.findViewById(R.id.category);
                final TextView submittedby = (TextView) vi.findViewById(R.id.submitted_by);
                final TextView issuelocation = (TextView) vi.findViewById(R.id.location);
                final ImageView issueImage= (ImageView) vi.findViewById(R.id.imageView);
                // String s=data.get(position).getDescription();
                category.setText(data.get(position).getIssueType());
                submittedby.setText(data.get(position).getUserName());
                issuelocation.setText(data.get(position).getLatlongi());
                Picasso.get().load(data.get(position).getImageUrl())
                        .placeholder(R.drawable.loading)
                        .fit().centerCrop()
                        .into(issueImage);


            }
            return vi;
        }
    }


}
