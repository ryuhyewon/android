package com.mapia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class SignupActivity extends Activity {


    public final static String mytag = "test";
    EditText edtID, edtPW, edtPWCheck;
    ImageView imgIDStatus, imgPWStatus, imgPWCheckStatus;
    TextView txtClause;
    Button btnSignup;
    Boolean flagIDDup = false, flagIDStatus = false, flagPWStatus = false, flagPWCheckStatus = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.mapia.R.layout.activity_signup);
        Log.i("SignupActivity","SignupActivity Load");

        edtID = (EditText)findViewById(com.mapia.R.id.edtID);
        imgIDStatus = (ImageView)findViewById(com.mapia.R.id.imgIDStatus);
        edtPW = (EditText)findViewById(com.mapia.R.id.edtPW);
        imgPWStatus = (ImageView)findViewById(com.mapia.R.id.imgPWStatus);
        edtPWCheck = (EditText)findViewById(com.mapia.R.id.edtPWCheck);
        imgPWCheckStatus = (ImageView)findViewById(com.mapia.R.id.imgPWCheckStatus);
        txtClause = (TextView)findViewById(com.mapia.R.id.txtClause);
        txtClause.setMovementMethod(new ScrollingMovementMethod());
        Log.i("SignupActivity","txtClause's text : "+txtClause.getText().toString());
        btnSignup = (Button)findViewById(com.mapia.R.id.btnSignup);
        Log.i("SignupActivity","btnSignup's text : "+btnSignup.getText().toString());

        edtID.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Nothingㅌ
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                flagIDStatus = false;
                // imgIDStatus image src = "not yet"
            }
            @Override
            public void afterTextChanged(Editable s)  {
                int i=0;
                int len = edtID.getText().length();
                for(i=0;i<len;i++){
                    if(0<=edtID.getText().charAt(i)-'0' || 9>=edtID.getText().charAt(i)-'0') continue;
                    else if(0<=edtID.getText().charAt(i)-'A' || 25>=edtID.getText().charAt(i)-'A') continue;
                    else if(0<=edtID.getText().charAt(i)-'a' || 25>=edtID.getText().charAt(i)-'a') continue;
                    else break;
                }
                if(i!=len){
                    //imgIDStatus image src = "No"
                    flagIDStatus = false;
                    return;
                }

				/* request UserID check */
                AsyncHTTP.get("auth/signup/" + edtID.getText(), null, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        // If the response is JSONObject instead of expected JSONArray
                        try {
                            if (response.get("exist").toString() == "true") {
                                flagIDDup = true;
                            }
                        } catch (JSONException e) {
                        }
                    }
                });

                flagIDStatus = true;

                // entered ID is already exist
                // You should show some alert message
                if(flagIDDup){

                }
                // imgIDStatus image src = "ready"
            }
        });
        edtPW.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                flagPWStatus = false;
				/* imgPWStatus image src = "not yet" */
            }
            @Override
            public void afterTextChanged(Editable s) {
				/* imgPWStatus image src = "ready" */
				/* request PWCheck */
                int i=0;
                int len = edtPW.getText().length();
                for(i=0;i<len;i++){
                    if(0<=edtPW.getText().charAt(i)-'0' && 9>=edtPW.getText().charAt(i)-'0') continue;
                    else if(0<=edtPW.getText().charAt(i)-'A' && 25>=edtPW.getText().charAt(i)-'A') continue;
                    else if(0<=edtPW.getText().charAt(i)-'a' && 25>=edtPW.getText().charAt(i)-'a') continue;
                    break;
                }
                if(i!=len){
					/* imgPWStatus image src = "No"*/
                    flagPWStatus = false;
                }
                else{
                    flagPWStatus = true;
					/*imgPWStatus image src = "OK"*/
                }
            }
        });
        edtPWCheck.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Nothing
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                flagPWCheckStatus = false;
				    /* imgPWCheckStatus image src = "not yet"*/
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(edtPWCheck.getText().toString().compareTo(edtPW.getText().toString())==0){
					/* imgPWCheckStatus image src = "OK" */
                    flagPWCheckStatus = true;
                }
                else{
					/* imgPWCheckStatus image src = "No" */
                }
            }
        });
        btnSignup.setOnClickListener (new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.i("SignupActivity", "btnSignup clicked");
                // request New User
                int result = 1;
                if (flagIDStatus == false) result = 2;
                else if (flagPWStatus == false) result = 3;
                else if (flagPWCheckStatus == false) result = 4;
                else if (flagIDDup == true) result = 5;
                switch (result) {
                    case 1:           // No-Error : OK
//						/* put user data */
                        try {
                            JSONObject jsonParams = new JSONObject();
                            jsonParams.put("userid", edtID.getText().toString());
                            jsonParams.put("password", edtPW.getText().toString());
                            StringEntity entity = new StringEntity(jsonParams.toString());
                            Context context = null;
                            AsyncHTTP.post(context,"auth/signup", entity, "application/json", new JsonHttpResponseHandler() {
                                @Override
                                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                    // If the response is JSONObject instead of expected JSONArray
                                    try {
                                        Log.i("code", response.toString());

                                        if(response.get("code").toString().compareTo("201")==0){
                                            Log.i("hi",response.get("token").toString());
                                            Intent i = new Intent(SignupActivity.this, SNSActivity.class);
                                            startActivity(i);
                                            finish();
                                        }
                                    } catch (Exception e) {
                                        Log.i("JSON", e.getMessage());
                                    }
                                }
                            });

                        } catch (JSONException e) {
                            Log.i("JSON",e.getMessage());
                        } catch (UnsupportedEncodingException e){
                            Log.i("Unsupported", e.getMessage());
                        }
                        break;
                    case 2:
                        Log.i("SignupActivity", "flagIDStatus : false");
                        break;
                    case 3:
                        Log.i("SignupActivity", "flagPWStatus : false");
                        break;
                    case 4:
                        Log.i("SignupActivity", "flagPWCheckStatus : false");
                        break;
                    case 5:
                        Log.i("SignupActivity", "flagIDDup : true");
                        break;
                    default:
                        break;
                }
            }
        });
    }
}

