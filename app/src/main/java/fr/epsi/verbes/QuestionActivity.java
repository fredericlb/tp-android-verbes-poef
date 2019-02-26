package fr.epsi.verbes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class QuestionActivity extends AppCompatActivity {

    JSONObject verb = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        this.fetchNewVerb();
        final Intent intent = new Intent(this, ScoreActivity.class);
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intent.putExtra("partieId", getIntent().getStringExtra("partieId"));
                finish();
                startActivity(intent);
            }
        }, 20000);
    }

    public void updateUI() {
        TextView textBaseVerbale = (TextView) findViewById(R.id.textBaseVerbale);
        TextView textTraduction = (TextView) findViewById(R.id.textTraduction);
        EditText inputPreterit = (EditText) findViewById(R.id.inputPreterit);
        EditText inputParticipePasse = (EditText) findViewById(R.id.inputParticipePasse);

        try {
            textBaseVerbale.setText(verb.getString("baseVerbale"));
            textTraduction.setText(verb.getString("traduction"));
            inputPreterit.setText("");
            inputParticipePasse.setText("");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void onValidateBtnClicked(View v) {
        EditText inputPreterit = (EditText) findViewById(R.id.inputPreterit);
        EditText inputParticipePasse = (EditText) findViewById(R.id.inputParticipePasse);
        String preterit = inputPreterit.getText().toString();
        String participePasse = inputParticipePasse.getText().toString();

        String partieId = getIntent().getStringExtra("partieId");
        try {
            String verbId = this.verb.getString("id");
            String url = "http://10.0.2.2:8000/partie/" + partieId +
                    "/update?preterit=" + preterit + "&participePasse=" + participePasse + "&id=" + verbId;
            StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    fetchNewVerb();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.w("verbsLog/questionUpdate", error.networkResponse.toString());
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(req);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void fetchNewVerb() {
        String url = "http://10.0.2.2:8000/verbes/random";
        JsonObjectRequest req = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                verb = response;
                updateUI();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.w("verbsLog/question", error.networkResponse.toString());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(req);
    }
}
