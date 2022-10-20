package com.example.quiknews.domain.use_case

import com.example.quiknews.domain.repository.NewsRepo

class DeleteAllArticles(
    private val newsRepo: NewsRepo
) {
    suspend operator fun invoke(){
        newsRepo.deleteAllArticles()
    }
}