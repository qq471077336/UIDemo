package com.lwd.uidemo.stickyheader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lwd.uidemo.R;

import java.util.List;
import java.util.Objects;

/**
 * @AUTHOR lianwd
 * @TIME 1/3/21
 * @DESCRIPTION LOL英雄联盟列表适配器
 */
public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.LOLViewHolder> {

    private final List<Hero> heroes;

    public HeroAdapter(List<Hero> heroes) {
        this.heroes = heroes;
    }

    @NonNull
    @Override
    public LOLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lol, null);
        return new LOLViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LOLViewHolder holder, int position) {
        Hero hero = heroes.get(position);
        holder.tvName.setText(hero.getName());
    }

    @Override
    public int getItemCount() {
        return heroes == null ? 0 : heroes.size();
    }

    public static class LOLViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;

        public LOLViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

    public boolean isGroupHeader(int position) {
        if (position == 0) {
            return true;
        }
        return !Objects.equals(getGroupName(position), getGroupName(position - 1));
    }

    public String getGroupName(int position) {
        return heroes.get(position).getCountry();
    }
}
