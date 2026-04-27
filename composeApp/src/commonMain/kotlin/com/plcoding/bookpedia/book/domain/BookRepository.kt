package com.plcoding.bookpedia.book.domain

import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.EmptyResult
import com.plcoding.bookpedia.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
    suspend fun getBookDescription(bookId: String): Result<String?, DataError.Remote>

    fun getFavBooks(): Flow<List<Book>>
    fun isBookFav(id: String): Flow<Boolean>
    //    suspend fun addBookToFav(book: Book): Result<Unit, DataError.Local>
    suspend fun addBookToFav(book: Book): EmptyResult<DataError.Local>
    suspend fun removeBookFromFav(id: String)
}