package by.fro.presentation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import by.fro.core.R;
import by.fro.presentation.model.ApplicantPresentationModel;

public class ApplicantAdapter extends RecyclerView.Adapter<ApplicantAdapter.ApplicantViewHolder> {


    private List<ApplicantPresentationModel> applicantPresentationModels;
    private final LayoutInflater layoutInflater;


    @Inject
    ApplicantAdapter(Context context) {
        this.layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.applicantPresentationModels = Collections.emptyList();
    }

    @Override public int getItemCount() {
        return (this.applicantPresentationModels != null) ? this.applicantPresentationModels.size() : 0;
    }

    @Override public ApplicantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.user_list_item, parent, false);
        return new ApplicantViewHolder(view);
    }

    @Override public void onBindViewHolder(ApplicantViewHolder holder, final int position) {
        final ApplicantPresentationModel applicantPresentationModel = this.applicantPresentationModels.get(position);
        holder.textViewName.setText(applicantPresentationModel.getName());
        holder.textViewSurname.setText(applicantPresentationModel.getSurname());
        holder.textViewAge.setText("Age: " + applicantPresentationModel.getAge());
        holder.textViewExperience.setText("Experience: " + applicantPresentationModel.getExperienceYears() + " years");
        holder.textViewSkill.setText("Skill: " + applicantPresentationModel.getSkill());
    }

    @Override public long getItemId(int position) {
        return position;
    }

    public void setApplicantsCollection(Collection<ApplicantPresentationModel> usersCollection) {
        this.validateUsersCollection(usersCollection);
        this.applicantPresentationModels = (List<ApplicantPresentationModel>) usersCollection;
        this.notifyDataSetChanged();
    }

    private void validateUsersCollection(Collection<ApplicantPresentationModel> usersCollection) {
        if (usersCollection == null) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }

    static class ApplicantViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewSurname, textViewAge, textViewExperience, textViewSkill;

        ApplicantViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.name);
            textViewSurname = itemView.findViewById(R.id.surname);
            textViewAge = itemView.findViewById(R.id.age);
            textViewExperience = itemView.findViewById(R.id.experience);
            textViewSkill = itemView.findViewById(R.id.skill);
        }
    }
}
