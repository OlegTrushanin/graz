package oleg.trushanin.graz;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface KoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<Ko> ko);

    @Query("DELETE FROM k_o")
    Completable deleteAll();


    @Query("SELECT price FROM k_o ORDER BY id")
    LiveData<List<Float>> getKo();

}
