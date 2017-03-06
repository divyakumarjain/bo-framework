package org.divy.common.bo.query;

import java.util.List;

@FunctionalInterface
public interface NotInComparison<A> extends Comparison {
    List<A> getValues();
}
