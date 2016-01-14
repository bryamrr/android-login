package la.devacademy.demologin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText mNewUsername;
    private EditText mNewFullName;
    private EditText mNewPassword;
    private Button mNewAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mNewUsername = (EditText) findViewById(R.id.newUsernameEditText);
        mNewFullName = (EditText) findViewById(R.id.newNameEditText);
        mNewPassword = (EditText) findViewById(R.id.newPasswordEditText);
        mNewAccountButton = (Button) findViewById(R.id.newAccountButton);

        mNewAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    private void createAccount() {
        ParseUser user = new ParseUser();

        String username = mNewUsername.getText().toString();
        String fullName = mNewFullName.getText().toString();
        String password = mNewPassword.getText().toString();

        user.setUsername(username);
        user.setPassword(password);

        // other fields can be set just like with ParseObject
        user.put("name", fullName);

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Intent intent = new Intent(CreateAccountActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                } else {
                    Log.d("CreateAccountActivity", "SignUp failed!");
                }
            }
        });
    }
}
