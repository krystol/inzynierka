package ak.inzynierka.core;

import ak.inzynierka.R;
import ak.inzynierka.core.utility.EntityUtil;
import ak.inzynierka.model.AuthenticationResult;
import ak.inzynierka.model.BoardMessage;
import ak.inzynierka.model.RegisterRequest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class RegisterActivity extends Activity {

    private UserRegisterTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mPasswordView2;
    private EditText mName;
    private EditText mSurname;
    private EditText mRoomNr;
    private String token;
    private Button emailRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        LinearLayout rootLayaut = findViewById(R.id.layautRegister);
        rootLayaut.setBackgroundResource(R.drawable.background);
        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView2 = (EditText) findViewById(R.id.passwordToMatch);
        mName = (EditText) findViewById(R.id.imieReg);
        mSurname = (EditText) findViewById(R.id.nazwiskoReg);
        mRoomNr = (EditText) findViewById(R.id.roomNrReg);
        mRoomNr.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptRegister();
                    return true;
                }
                return false;
            }
        });
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        emailRegisterButton = (Button) findViewById(R.id.email_register_button);
        emailRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegister();
            }
        });
    }

    /**
     * Represents an asynchronous registration task used to authenticate
     * the user.
     */
    public class UserRegisterTask extends AsyncTask<Void, Void, Boolean> {

        private final RegisterRequest mRequest;

        UserRegisterTask(RegisterRequest request) {
            mRequest = request;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            ResponseEntity<AuthenticationResult> response = null;
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<RegisterRequest> requestEntity = new HttpEntity<>(mRequest, requestHeaders);
            if (isNetworkAvailable()) {
                response = restTemplate.exchange(
                        MainActivity.URL + "/auth/register",
                        HttpMethod.POST,
                        EntityUtil.getAuthenticationEntityAndRegister(mRequest),
                        new ParameterizedTypeReference<AuthenticationResult>() {
                        });
            }
            if (response != null && response.getBody() != null) {
                token = response.getBody().getToken();
            }
            return response != null;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;

            if (success) {
                finish();
                Intent myIntent = new Intent(RegisterActivity.this, MainActivity.class);
                myIntent.putExtra("Token", token);
                myIntent.putExtra("login", mRequest.getUsername());
                myIntent.putExtra("pass", mRequest.getPassword());
                RegisterActivity.this.startActivity(myIntent);
            } else {
                mPasswordView.setError(getString(R.string.error_email_taken));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void attemptRegister() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String imie = mName.getText().toString();
        String nazwisko = mSurname.getText().toString();
        String nrPokoju = mRoomNr.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        } else if (!passwordsMatch()) {
            mPasswordView.setError(getString(R.string.error_not_matching_passwords));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt register and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            RegisterRequest request = new RegisterRequest();
            request.setUsername(email);
            request.setPassword(password);
            request.setFirstName(imie);
            request.setLastName(nazwisko);
            request.setRoomNumber(nrPokoju);
            mAuthTask = new RegisterActivity.UserRegisterTask(request);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 4;
    }

    private boolean passwordsMatch() {
        return mPasswordView.getText().toString().equals(mPasswordView2.getText().toString());
    }
}
