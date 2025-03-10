package com.example.todomvvm.tasks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todomvvm.R;
import com.example.todomvvm.database.AppDatabase;
import com.example.todomvvm.database.RepositoryUser;
import com.example.todomvvm.database.TaskDao;
import com.example.todomvvm.database.User;

public class SignupActivity extends AppCompatActivity {

    private EditText name;
    private EditText lastName;
    private EditText email;
    private EditText password;

    private Button registerButton;
 //   private Button cancelButton;
    private AppDatabase appDatabase;

    private TaskDao taskDaoO;
    private RepositoryUser repositoryUser;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Registering");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setProgress(0);



        name = findViewById(R.id.register_name);

        appDatabase=AppDatabase.getInstance( this );
        repositoryUser=new RepositoryUser( appDatabase );

        lastName = findViewById(R.id.register_lastName);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        registerButton = findViewById(R.id.register_user);
       // cancelButton = findViewById(R.id.cancel_user);




        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEmpty())
                {
                    progressDialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            User user = new User(name.getText().toString(),lastName.getText().toString(),email.getText().toString(),password.getText().toString());
                            repositoryUser.insertUser(user);
                            progressDialog.dismiss();
                            startActivity(new Intent(SignupActivity.this,UserActivity.class));
                        }
                    },1000);
                }
                else{
                    Toast.makeText(SignupActivity.this,"Empty Fields",Toast.LENGTH_SHORT).show();
                }
            }
        });






    }
    private boolean isEmpty()
    {
        if(TextUtils.isEmpty(name.getText().toString())||TextUtils.isEmpty(lastName.getText().toString())||TextUtils.isEmpty(email.getText().toString())||TextUtils.isEmpty(password.getText().toString())){
            return  true;
        }
        else
        {
            return  false;
        }
    }
}