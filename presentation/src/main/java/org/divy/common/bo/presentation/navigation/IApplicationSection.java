package org.divy.common.bo.presentation.navigation;

import java.util.List;

public interface IApplicationSection {
    String getSectionLabel();
    List<IApplicationSection> getSubSection();
    List<IOperation> getOperations();
}
