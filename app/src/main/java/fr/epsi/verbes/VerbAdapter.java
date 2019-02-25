package fr.epsi.verbes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class VerbAdapter extends RecyclerView.Adapter<VerbsViewHolder> {

  private ArrayList<Verbe> verbes;

  public VerbAdapter(ArrayList<Verbe> verbes) {
    this.verbes = verbes;
  }

  @NonNull
  @Override
  public VerbsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    TextView text = (TextView) LayoutInflater.from(viewGroup.getContext())
      .inflate(R.layout.verb_item, viewGroup, false);
    return new VerbsViewHolder(text);
  }

  @Override
  public void onBindViewHolder(@NonNull VerbsViewHolder verbsViewHolder, int i) {
    Verbe v = verbes.get(i);
    String info = v.baseVerbale + "/" + v.traduction + "/" + v.preterit + "/" + v.participePasse;
    verbsViewHolder.text.setText(info);
  }

  @Override
  public int getItemCount() {
    return verbes.size();
  }
}
