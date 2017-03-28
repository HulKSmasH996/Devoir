package com.kiit.viper.devoir;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by mrsp7 on 28-03-2017.
 */

public class LogoutAct extends AppCompatActivity {

    private FirebaseAuth mAuth;
  //  private Button mlogout;
    private FirebaseAuth.AuthStateListener mAuthlistner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        mAuth=FirebaseAuth.getInstance();
        /*mlogout=(Button) findViewById(R.id.logout);

        mlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
            }
        });*/
        mAuth.signOut();
        mAuthlistner=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser()==null)
                {
                    startActivity(new Intent(LogoutAct.this, Login.class));
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthlistner);
}
}
