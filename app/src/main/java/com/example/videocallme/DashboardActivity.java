package com.example.videocallme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

public class DashboardActivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText secretCodeBox;
    Button joinBtn,shareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        auth = FirebaseAuth.getInstance();
        secretCodeBox = findViewById(R.id.SecretCode);
        joinBtn = findViewById(R.id.JoinBtn);
        shareBtn = findViewById(R.id.ShareBtn);

        URL serverURL;

        try
            {
                serverURL =  new URL("https://meet.jit.si");
                JitsiMeetConferenceOptions defaultOptions =
                        new JitsiMeetConferenceOptions.Builder()
                                .setServerURL(serverURL)
                                .setWelcomePageEnabled(false)
                        .build();
                JitsiMeet.setDefaultConferenceOptions(defaultOptions);
            } catch (MalformedURLException e) {
            e.printStackTrace();
        }








        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(secretCodeBox.getText().toString())
                        .setWelcomePageEnabled(false)
                        .build();
                JitsiMeetActivity.launch(DashboardActivity.this, options);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.Home:
                Toast.makeText(this, "Dashboard pr hi h tu lawde", Toast.LENGTH_SHORT).show();
                break;

            case R.id.History:
                Toast.makeText(this, "Dikhau history ;)", Toast.LENGTH_SHORT).show();
                break;

            case R.id.Settings:
                Toast.makeText(this, "Setting nhi dikh nhi dikh rhi tujhe lawde", Toast.LENGTH_SHORT).show();
                break;

            case R.id.Logout:
               auth.signOut();
               startActivity(new Intent(DashboardActivity.this,LoginActivity.class));
                Toast.makeText(this, "Logout ho gya be tu", Toast.LENGTH_SHORT).show();
               finish();

        }
        return super.onOptionsItemSelected(item);
    }
}