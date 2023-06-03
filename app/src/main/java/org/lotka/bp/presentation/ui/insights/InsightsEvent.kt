package com.example.owl.insights



sealed class InsightsEvent {

    object NewSearchEvent : InsightsEvent()

    object NextPageEvent : InsightsEvent()

    // restore after process death
    object RestoreStateEvent: InsightsEvent()
}