package org.divy.common.bo.query.defaults;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.divy.common.bo.query.INotInComparison;

public class NotInComparison<ATTRIBUTE> implements INotInComparison<ATTRIBUTE> {

    private List<ATTRIBUTE> values;

    @Override
    public List<ATTRIBUTE> getValues() {
        if(values==null)
            values = new ArrayList<ATTRIBUTE>();

        return values;
    }

    public NotInComparison(List<ATTRIBUTE> values) {
        super();
        this.values = values;
    }

    public NotInComparison(ATTRIBUTE[] values) {
        super();
        this.values = Arrays.asList(values);
    }

    public NotInComparison() {
        super();
    }


}
