package org.divy.common.bo.query;

import java.util.List;

public interface IInComparison<ATTRIBUTE> extends IComparison{
    List<ATTRIBUTE> getValues();
}
