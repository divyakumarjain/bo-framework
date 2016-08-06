package org.divy.common.bo.query;

import java.util.List;

public interface IInComparison<A> extends IComparison {
    List<A> getValues();
}
