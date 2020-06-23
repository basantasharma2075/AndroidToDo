package com.example.todomvvm.tasks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todomvvm.R;
import com.example.todomvvm.database.AppDatabase;
import com.example.todomvvm.database.RepositoryUser;
import com.example.todomvvm.database.TaskDao;

//import com.example.todomvvm.database.RepositoryUser;

public class UserActivity extends AppCompatActivity {
    private Button signIn;
    private Button signUp;
    /*private EditText userEmail;
    private EditText userPassword;*/
    private EditText Email;
    private EditText Password;
    private AppDatabase database;
    private TaskDao taskDao;
    private ProgressDialog progressDialog;
    private RepositoryUser repositoryUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Checking user");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);

        database = AppDatabase.getInstance( this );
        repositoryUser=new RepositoryUser( database );

        signIn = findViewById(R.id.SignIn);
        signUp = findViewById(R.id.Register);
        Email = findViewById(R.id.user_email);
        Password = findViewById(R.id.user_Password);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this,SignupActivity.class));
            }
        });

    }



    private class AsyncLogin extends AsyncTask<Void,Void,Void>{



        @Override
        protected Void doInBackground(Void... voids) {
            int userId = repositoryUser.getUser(Email.getText().toString(), Password.getText().toString());
            if (userId != 0) {
                Intent intent = new Intent(UserActivity.this, MainActivity.class);
                intent.putExtra("userId", Integer.toString(userId));
                startActivity(intent);
            } else {
                openDialog();
               // Toast.makeText(getApplicationContext(), "UserName is not valid!!! Please enter a valid Email and Password", Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        public void openDialog(){
            Dialog dialog = new Dialog();
            dialog.show( getSupportFragmentManager(), "dialog" );

        }
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();

    }

    public void signin(View view) {
        new AsyncLogin().execute(  );

    }
}