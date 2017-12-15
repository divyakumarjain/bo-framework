package org.divy.common.bo.mapper;

public interface StateTrackable<S> {
    void setState(S state);
    S getState();
}
