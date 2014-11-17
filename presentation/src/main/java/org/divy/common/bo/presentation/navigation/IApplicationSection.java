package org.divy.common.bo.presentation.navigation;

import java.util.List;

public interface IApplicationSection {
    public String getSectionLabel();
    public List<IApplicationSection> getSubSection();
    public List<IOperation> getOperations();
}
