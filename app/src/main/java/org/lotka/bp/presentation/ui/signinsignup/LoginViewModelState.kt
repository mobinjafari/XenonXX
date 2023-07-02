package org.lotka.bp.presentation.ui.signinsignup

data class LoginViewModelState(
    val loginStatus: LoginStatus = LoginStatus.Initial
)

enum class LoginStatus {
    Initial,
    Success,
    Loading,
    Failure
}