package org.divy.common.bo.query;

import java.util.List;

@FunctionalInterface
public interface InComparison<A> extends Comparison {
    List<A> getValues();
}
