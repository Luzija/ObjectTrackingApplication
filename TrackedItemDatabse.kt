import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "tracked_items")
data class TrackedItemEntity(
    @PrimaryKey val sku: String,
    @ColumnInfo(name = "name") val name: String
)

@Dao
interface TrackedItemDao {
    @Query("SELECT * FROM tracked_items")
    fun getAllItems(): Flow<List<TrackedItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: TrackedItemEntity)
}

@Database(entities = [TrackedItemEntity::class], version = 1)
abstract class TrackedItemDatabase : RoomDatabase() {
    abstract fun trackedItemDao(): TrackedItemDao

    companion object {
        @Volatile
        private var INSTANCE: TrackedItemDatabase? = null

        fun getDatabase(context: android.content.Context): TrackedItemDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TrackedItemDatabase::class.java,
                    "tracked_item_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}