package oleg.trushanin.graz;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface SpecialDarkOptionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<SpecialDarkOptions> specialDarkOptions);

    @Query("DELETE FROM special_dark_options")
    Completable deleteAll();


    @Query("SELECT * FROM special_dark_options")
    LiveData<List<SpecialDarkOptions>> getSpecialDark();
}
