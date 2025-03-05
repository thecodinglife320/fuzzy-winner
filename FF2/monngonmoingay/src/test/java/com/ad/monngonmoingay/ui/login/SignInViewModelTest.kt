package com.ad.monngonmoingay.ui.login
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import com.ad.monngonmoingay.domain.SignInUseCase
//import com.ad.monngonmoingay.model.LoginRequest
//import com.ad.monngonmoingay.utils.TestCoroutineRule
//import io.mockk.impl.annotations.MockK
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.runTest
//import org.junit.Before
//import org.junit.Assert.*
//import org.junit.Rule
//import org.junit.Test
//
//@ExperimentalCoroutinesApi
//class SignInViewModelTest{
//    @get:Rule
//    val instantExecutorRule = InstantTaskExecutorRule()
//
//    @get:Rule
//    val testCoroutineRule = TestCoroutineRule()
//
//    @MockK
//    private lateinit var signInUseCase: SignInUseCase
//    @Before
//    fun setUp() {
//        MockitoAnnotations.openMocks(this)
//    }
//    @Test
//    fun `when sign in return success`() = runTest {
//        Mockito.`when`(signInUseCase.invoke(Mockito.any())).thenReturn(true)
//    }
//}