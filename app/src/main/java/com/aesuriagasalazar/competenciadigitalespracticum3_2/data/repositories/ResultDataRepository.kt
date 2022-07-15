package com.aesuriagasalazar.competenciadigitalespracticum3_2.data.repositories

import com.aesuriagasalazar.competenciadigitalespracticum3_2.data.service.RemoteStorageService
import com.aesuriagasalazar.competenciadigitalespracticum3_2.domain.TestResult
import com.google.gson.Gson
import javax.inject.Inject

class ResultDataRepository @Inject constructor(
    private val localDataRepository: LocalDataRepository,
    private val userRepository: UserRepository,
    private val remoteStorageService: RemoteStorageService
) {

    suspend fun saveTestResult(result: TestResult) {
        userRepository.userLoginState().collect { isLogin ->
            if (isLogin) {
                userRepository.getUserUID().collect { uid ->
                    remoteStorageService.saveResultTest(uid, result).collect {
                        // Return true if task is successful or false if not
                        // Put logic if is necessary
                    }
                }
            }
        }
        saveResultToString(result)
    }

    suspend fun getTestResult() = localDataRepository.getUserResult()
        .also { if (it.isEmpty()) emptyList<TestResult>() else jsonToList(it) }

    private suspend fun saveResultToString(result: TestResult) {
        localDataRepository.getUserResult().also {
            if (it.isNotEmpty()) {
                val list = jsonToList(it) + result
                localDataRepository.saveUserResult(listToJson(list))
            } else {
                val listOfResult = listOf(result)
                localDataRepository.saveUserResult(listToJson(listOfResult))
            }
        }
    }

    private fun listToJson(list: List<TestResult>): String {
        Gson().also {
            return it.toJson(list)
        }
    }

    private fun jsonToList(json: String): List<TestResult> {
        Gson().also {
            return it.fromJson(json, Array<TestResult>::class.java).toList()
        }
    }

    suspend fun saveTestCompleted(isCompleted: Boolean) =
        localDataRepository.saveTestCompleted(isCompleted)
}