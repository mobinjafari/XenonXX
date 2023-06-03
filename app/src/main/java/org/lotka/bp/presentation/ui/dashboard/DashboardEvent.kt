package org.lotka.bp.presentation.ui.dashboard



sealed class DashboardEvent {

    object NewSearchEvent : DashboardEvent()

    object NextPageEvent : DashboardEvent()

    // restore after process death
    object RestoreStateEvent: DashboardEvent()
}