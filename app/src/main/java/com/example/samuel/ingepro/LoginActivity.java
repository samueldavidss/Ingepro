package com.example.samuel.ingepro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.samuel.ingepro.models.Users;
import com.example.samuel.ingepro.queries.CurrentUser;
import com.example.samuel.ingepro.queries.References;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ResultCodes;
import com.google.firebase.database.DatabaseReference;

import java.util.Arrays;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class LoginActivity extends AppCompatActivity {
    public static final int RC_SIGN_IN=123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        if(new CurrentUser().getCurrentUser() !=null){
            logged();
        }else{
            singUp();
        }


    }

    private void singUp(){
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setProviders(Arrays.asList(
                                new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()/*,
                                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                        new AuthUI.IdpConfig.Builder(AuthUI.TWITTER_PROVIDER).build()*/))
                        .build(),
                RC_SIGN_IN);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (RC_SIGN_IN==requestCode){
            if(ResultCodes.OK==resultCode){
                register();
            }
        }

    }
    private void logged(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void register(){
        DatabaseReference dbRef = new References().userReference().child(new CurrentUser().getCurrentUser().getUid());
        Users user = new Users();
        user.setEmail(new CurrentUser().getCurrentUser().getEmail());
        user.setKey(new CurrentUser().getCurrentUser().getUid());
        String key = (new CurrentUser().getCurrentUser().getUid());
        dbRef.setValue(user);
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("key",key);
        startActivity(intent);
        finish();

    }



}
