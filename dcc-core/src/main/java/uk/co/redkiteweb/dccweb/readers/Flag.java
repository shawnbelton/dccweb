package uk.co.redkiteweb.dccweb.readers;

import org.w3c.dom.Node;

/**
 * Created by shawn on 16/09/16.
 */
public class Flag implements ValueType {

    public Flag(final DefinitionReader definitionReader, final Node valueNode) {

    }

    @Override
    public Integer getValue() {
        return 0;
    }
}
