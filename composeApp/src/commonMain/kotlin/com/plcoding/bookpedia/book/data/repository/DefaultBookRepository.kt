package com.plcoding.bookpedia.book.data.repository

import androidx.sqlite.SQLiteException
import com.plcoding.bookpedia.book.data.database.FavBookDao
import com.plcoding.bookpedia.book.data.network.RemoteBookDataSource
import com.plcoding.bookpedia.book.data.mappers.toBook
import com.plcoding.bookpedia.book.data.mappers.toBookEntity
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.domain.BookRepository
import com.plcoding.bookpedia.core.domain.Result
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.EmptyResult
import kotlinx.coroutines.flow.map
import com.plcoding.bookpedia.core.domain.map
import kotlinx.coroutines.flow.Flow

class DefaultBookRepository (
    private val remoteBookDataSource: RemoteBookDataSource,
    private val favBookDao: FavBookDao
) : BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource
            .searchBooks(query)
            .map { dto ->
            dto.results.map { it.toBook() }
            }
    }

    override suspend fun getBookDescription(bookId: String): Result<String?, DataError.Remote> {
        val localResult = favBookDao.getFavoriteBook(bookId)

        return if (localResult != null) {
            remoteBookDataSource
            .getBookDetails(bookId)
            .map { it.description}
        } else {
            Result.Success(localResult?.description)
        }
    }

//
    override fun getFavBooks(): Flow<List<Book>> {
        return favBookDao
            .getFavoriteBooks()
            .map { bookEntities -> bookEntities.map { it.toBook() } }
    }

    override fun isBookFav(id: String): Flow<Boolean> {
        return favBookDao
            .getFavoriteBooks()
            .map { bookEntities ->
            bookEntities.any { it.id == id } }
    }

    override suspend fun addBookToFav(book: Book): EmptyResult<DataError.Local> {
        return try {
            favBookDao.upsert(book.toBookEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun removeBookFromFav(id: String) {
        favBookDao.deleteFavoriteBook(id)
    }
}