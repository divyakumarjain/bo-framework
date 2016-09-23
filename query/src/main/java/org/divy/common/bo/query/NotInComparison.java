package org.divy.common.bo.query;

import java.util.List;

public interface NotInComparison<A> extends Comparison {
    List<A> getValues();
}
