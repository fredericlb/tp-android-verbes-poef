package fr.epsi.verbes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class VerbsViewHolder extends RecyclerView.ViewHolder {
  TextView text;

  public VerbsViewHolder(@NonNull TextView itemView) {
    super(itemView);
    this.text = itemView;
  }

}
