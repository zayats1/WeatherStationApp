import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.bogdandev.weatherstationapp.data.SavedProviders

@Dao
interface SavedProvidersDao {
    @Query("SELECT * FROM SavedProviders")
    fun getAll(): List<SavedProviders>

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg ipaddr: SavedProviders)

    @Delete
    fun delete(ipaddr: SavedProviders)
}

