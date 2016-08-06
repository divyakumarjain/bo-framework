package org.divy.common.bo.query;

import java.util.List;

public interface INotInComparison<A> extends IComparison {
    List<A> getValues();
}
