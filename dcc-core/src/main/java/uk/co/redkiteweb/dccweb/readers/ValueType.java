package uk.co.redkiteweb.dccweb.readers;

import org.w3c.dom.Node;

/**
 * Created by shawn on 16/09/16.
 */
public interface ValueType {
    Integer getValue(final CVReader cvReader, final Node valueNode);
}
