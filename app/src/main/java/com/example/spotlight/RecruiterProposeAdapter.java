package com.example.spotlight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class RecruiterProposeAdapter extends RecyclerView.Adapter<RecruiterProposeAdapter.ViewHolder> {
    private Context context;
    private List<RecruiterProposal> proposals;

    public RecruiterProposeAdapter(Context context, List<RecruiterProposal> proposals) {
        this.context = context;
        this.proposals = proposals;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recruiter_propose, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecruiterProposal proposal = proposals.get(position);
        holder.name.setText(proposal.getName());
        holder.projectName.setText(proposal.getProjectName());
        holder.companyName.setText(proposal.getCompanyName());
        holder.proposeDate.setText(proposal.getProposeDate());
        holder.role.setText(proposal.getRole());
        Glide.with(context).load(proposal.getPhotoUrl()).into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return proposals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView name, projectName, companyName, proposeDate, role;

        public ViewHolder(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.item_recruiter_propose_photo);
            name = itemView.findViewById(R.id.item_recruiter_propose_name);
            projectName = itemView.findViewById(R.id.item_recruiter_propose_project_name);
            companyName = itemView.findViewById(R.id.item_recruiter_propose_company);
            proposeDate = itemView.findViewById(R.id.item_recruiter_propose_date);
            role = itemView.findViewById(R.id.item_recruiter_propose_role);
        }
    }
}
