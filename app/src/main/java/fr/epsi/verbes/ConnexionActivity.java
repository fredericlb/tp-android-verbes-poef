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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class ConnexionActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_connexion);
  }

  public void onSubmit(View v) {
    final Intent intent = new Intent();
    intent.putExtra("nom", "Langlade");
    intent.putExtra("prenom", "Frédéric");

    String mail = ((EditText) findViewById(R.id.connexionMailInput)).getText().toString();
    String password = ((EditText) findViewById(R.id.connexionPasswordInput)).getText().toString();
    String url = "http://10.0.2.2:8000/connexion";


    final HashMap<String, String> params = new HashMap<>();
    params.put("email", mail);
    params.put("motdepasse", password);

    StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
      @Override
      public void onResponse(String response) {
        if (response.equals("null")) {
          Log.w("verbsLog", "bad mail or password");
        } else {
          intent.putExtra("joueurId", response);

          setResult(Activity.RESULT_OK, intent);
          SharedPreferences sp = getSharedPreferences("app", Context.MODE_PRIVATE );
          sp.edit()
            .putString("joueurId", response)
            .putString("nom", "Langlade")
            .putString("prenom", "Frédéric")
            .apply();

          finish();
        }
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

    RequestQueue requestQueue = Volley.newRequestQueue(this);
    requestQueue.add(req);

    /*setResult(Activity.RESULT_OK, intent);
    finish();*/
  }
}
