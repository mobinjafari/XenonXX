package org.lotka.bp.presentation.ui.explore



sealed class ExploreEvent {

    object NewSearchEvent : ExploreEvent()

    object NextPageEvent : ExploreEvent()

    // restore after process death
    object RestoreStateEvent: ExploreEvent()
}