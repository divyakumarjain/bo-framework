package org.divy.common.bo.query;

import java.util.List;

public interface INotInComparison<ATTRIBUTE> extends IComparison {
    List<ATTRIBUTE> getValues();
}
