package xyz.jhughes.epsteinandroid.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.jhughes.epsteinandroid.R;
import xyz.jhughes.epsteinandroid.networking.EpsteinApiHelper;
import xyz.jhughes.epsteinandroid.utilities.SharedPrefsHelper;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.sign_in_button)
    SignInButton signInButton;

    private GoogleSignInClient mGoogleSignInClient;

    private GoogleApiClient apiClient;

    private int RC_SIGN_IN = 901;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        try {
            getSupportActionBar().hide();
        } catch (NullPointerException e) {
            // Meh...
        }

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = Auth.GoogleSignInApi.getSignInIntent(apiClient);
                startActivityForResult(myIntent, RC_SIGN_IN);
            }
        });
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.google_client_id))
                .requestServerAuthCode(getString(R.string.google_client_id))
                .requestScopes(new Scope("https://www.googleapis.com/auth/calendar"))
                .requestEmail()
                .build();

        apiClient = new GoogleApiClient.Builder(this).addApi(
                Auth.GOOGLE_SIGN_IN_API, signInOptions).build();
    }

    @Override
    public void onStart() {
        super.onStart();
        apiClient.connect();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(apiClient);

        if (opr.isDone()) {
            Log.d("TAG", "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            GoogleSignInResult res = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(res);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();

            EpsteinApiHelper.getInstance().login(account.getIdToken()).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.code() == 202) {
                        SharedPrefsHelper.getSharedPrefs(getApplicationContext()).edit().putString("idToken", response.body()).apply();
                        startActivity(new Intent(getApplicationContext(), CalendarActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Couldn't sign in to Epstein", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        } else {
            Toast.makeText(this, "Google Sign-In Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
