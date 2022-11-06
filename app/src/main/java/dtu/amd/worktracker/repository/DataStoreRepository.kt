package dtu.amd.worktracker.repository

// SOURCE: https://github.com/dhruvRj18/DataStoreYT

interface DataStoreRepository {
    suspend fun setString(key: String, value: String)
    suspend fun setDouble(key: String, value: Double)
    suspend fun setInt(key: String, value: Int)
    suspend fun getString(key: String, default: String): String
    suspend fun getDouble(key: String, default: Double): Double
    suspend fun getInt(key: String, default: Int): Int

}