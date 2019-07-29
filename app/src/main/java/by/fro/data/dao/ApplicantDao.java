package by.fro.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import by.fro.data.model.ApplicantLocalModel;
import io.reactivex.Maybe;

@Dao
public interface ApplicantDao {

    @Query("SELECT * FROM Applicant")
    Maybe<List<ApplicantLocalModel>> getApplicantsList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Maybe<List<Long>> insertApplicantsList(List<ApplicantLocalModel> list);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Maybe<Long> insertApplicant(ApplicantLocalModel applicantLocalModel);

    @Query("SELECT * FROM Applicant WHERE name LIKE :name")
    Maybe<List<ApplicantLocalModel>> getApplicantsListByName(String name);
}
