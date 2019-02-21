package fr.epsi.verbes;

import android.app.Activity;
import android.content.Intent;
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

import java.util.Map;

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

    String url = "https://raw.githubusercontent.com/fredericlb/tp-angular-poef/master/examples/Legumes.json";
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
    requestQueue.add(req);
  }
}
