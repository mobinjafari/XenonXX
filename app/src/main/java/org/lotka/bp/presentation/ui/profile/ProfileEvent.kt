package org.lotka.bp.presentation.ui.profile



sealed class ProfileEvent {

    object NewSearchEvent : ProfileEvent()

    object NextPageEvent : ProfileEvent()

    // restore after process death
    object RestoreStateEvent: ProfileEvent()
}