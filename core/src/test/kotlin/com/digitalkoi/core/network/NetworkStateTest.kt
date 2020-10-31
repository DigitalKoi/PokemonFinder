package com.digitalkoi.core.network

import org.junit.Assert
import org.junit.Test

class NetworkStateTest {

    @Test
    fun defaultInitializeSuccessState_ShouldHaveDefaultValues() {
        val networkState = NetworkState.Success()

        Assert.assertTrue(networkState.isSuccess())
        Assert.assertEquals(false, networkState.isAdditional)
        Assert.assertEquals(false, networkState.isEmptyResponse)
    }

    @Test
    fun initializeSuccessState_ShouldHaveCorrectValues() {
        val isAdditional = true
        val isEmptyResponse = true
        val networkState = NetworkState.Success(
            isAdditional = isAdditional,
            isEmptyResponse = isEmptyResponse
        )

        Assert.assertTrue(networkState.isSuccess())
        Assert.assertEquals(isAdditional, networkState.isAdditional)
        Assert.assertEquals(isEmptyResponse, networkState.isEmptyResponse)
    }

    @Test
    fun defaultInitializeLoadingState_ShouldHaveDefaultValues() {
        val networkState = NetworkState.Loading()

        Assert.assertTrue(networkState.isLoading())
        Assert.assertEquals(false, networkState.isAdditional)
    }

    @Test
    fun initializeLoadingState_ShouldHaveCorrectValues() {
        val isAdditional = true
        val networkState = NetworkState.Loading(isAdditional)

        Assert.assertTrue(networkState.isLoading())
        Assert.assertEquals(isAdditional, networkState.isAdditional)
    }

    @Test
    fun defaultInitializeErrorState_ShouldHaveDefaultValues() {
        val networkState = NetworkState.Error()

        Assert.assertTrue(networkState.isError())
        Assert.assertEquals(false, networkState.isAdditional)
    }

    @Test
    fun initializeErrorState_ShouldHaveDefaultValues() {
        val isAdditional = true
        val networkState = NetworkState.Error(isAdditional)

        Assert.assertTrue(networkState.isError())
        Assert.assertEquals(isAdditional, networkState.isAdditional)
    }
}
