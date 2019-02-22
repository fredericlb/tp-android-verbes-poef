package fr.epsi.verbes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class InscriptionActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_inscription);
  }

  public void onSubmit(View v) {
    String prenom = ((EditText)findViewById(R.id.inscriptionFirstName)).getText().toString();
    String nom = ((EditText)findViewById(R.id.inscriptionLastName)).getText().toString();
    String mail = ((EditText)findViewById(R.id.inscriptionMail)).getText().toString();
    String password = ((EditText)findViewById(R.id.inscriptionPassword)).getText().toString();
    String niveau = ((EditText)findViewById(R.id.inscriptionNiveau)).getText().toString();

    final Intent intent = new Intent();
    intent.putExtra("prenom", prenom);
    intent.putExtra("nom", nom);
    intent.putExtra("mail", mail);
    intent.putExtra("password", password);
    intent.putExtra("niveau", niveau);

    RequestQueue requestQueue = Volley.newRequestQueue(this);

    /*String url = "http://10.0.2.2:8000/partie/1/score";
    StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
          Log.w("verbslog", response);
          setResult(Activity.RESULT_OK, intent);
          finish();
        }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Log.w("verbslog", "error");
      }
    });
    requestQueue.add(req);*/

    final HashMap<String, String> params = new HashMap<>();

    params.put("nom", nom);
    params.put("prenom", prenom);
    params.put("email", mail);
    params.put("motdepasse", password);
    params.put("idville", "1");
    params.put("niveau", niveau);

    String url = "http://10.0.2.2:8000/joueur";
    StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        Log.w("verbsLog", response);
        intent.putExtra("joueurId", response);
        setResult(Activity.RESULT_OK, intent);
        finish();
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Log.w("verbsLog", error.networkResponse.toString());
      }
    }) {
      @Override
      public byte[] getBody() throws AuthFailureError {
        JSONObject obj = new JSONObject(params);
        return obj.toString().getBytes();
      }
    };

    /*String url = "https://raw.githubusercontent.com/fredericlb/tp-angular-poef/master/examples/Verbes.json";
    JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
      @Override
      public void onResponse(JSONArray response) {
        for (int i = 0; i < response.length(); ++i) {
          try {
            JSONObject obj = response.getJSONObject(i);
            Log.w("verbsLog/preterit", obj.getString("preterit"));
            Log.w("verbsLog/baseVerbale", obj.getString("baseVerbale"));
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }
      }
    }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {

      }
    });*/

    requestQueue.add(req);

  }




}
