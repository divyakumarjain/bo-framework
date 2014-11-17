package org.divy.common.bo.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InComparison<ATTRIBUTE> implements IInComparison<ATTRIBUTE> {

    private List<ATTRIBUTE> values;

    @Override
    public List<ATTRIBUTE> getValues() {
        if(values==null)
            values = new ArrayList<ATTRIBUTE>();

        return values;
    }

    public InComparison(List<ATTRIBUTE> values) {
        super();
        this.values = values;
    }

    public InComparison(ATTRIBUTE[] values) {
        super();
        this.values = Arrays.asList(values);
    }

    public InComparison() {
        super();
    }

}
