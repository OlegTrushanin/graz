package oleg.trushanin.graz;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
@Dao
public interface BazaModelDao {

   // @Insert(onConflict = OnConflictStrategy.REPLACE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<BazaModel> bazaModels);

    @Query("DELETE FROM baza_model")
    Completable deleteAll();



    @Query("SELECT * FROM baza_model WHERE volumePPC = :volumePPC " +
            "AND typeRatio = :typeRatio AND typeAxel = :typeAxel AND typeAxelCount = :typeAxelCount " +
            "AND typeMaterial = :typeMaterial AND axelWeight = :axelWeight")
    LiveData<List<BazaModel>> getNameCostPPC(double volumePPC, String typeRatio,
                                              String typeAxel, String typeAxelCount,
                                              String typeMaterial, String axelWeight);


}
