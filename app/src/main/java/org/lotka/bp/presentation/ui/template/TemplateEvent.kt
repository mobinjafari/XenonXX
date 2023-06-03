package org.lotka.bp.presentation.ui.template



sealed class TemplateEvent {

    object NewSearchEvent : TemplateEvent()

    object NextPageEvent : TemplateEvent()

    // restore after process death
    object RestoreStateEvent: TemplateEvent()
}