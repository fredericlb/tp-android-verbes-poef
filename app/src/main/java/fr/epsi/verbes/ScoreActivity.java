package fr.epsi.verbes;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        String partieId = getIntent().getStringExtra("partieId");
        final TextView score = (TextView) findViewById(R.id.scoreDisplay);
        final TextView previousScore = (TextView) findViewById(R.id.scoreDisplayPrevious);
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://10.0.2.2:8000/partie/" + partieId + "/score";

        final SharedPreferences sp = getSharedPreferences("app", MODE_PRIVATE);
        if (sp.contains("previousScore")) {
            previousScore.setText(sp.getString("previousScore", "0"));
        } else {
            previousScore.setVisibility(View.INVISIBLE);
        }


        StringRequest req = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                score.setText(response);
                sp.edit().putString("previousScore", response).apply();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.w("Verbes/FetchScore", error.networkResponse.toString());
            }
        });

        queue.add(req);
    }

    public void onHomeButtonClicked(View v) {
        finish();
    }
}
