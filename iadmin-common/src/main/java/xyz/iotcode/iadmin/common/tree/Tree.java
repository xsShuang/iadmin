package xyz.iotcode.iadmin.common.tree;

import lombok.Data;

import java.util.Collection;

/**
 * @author xieshuang
 */
@Data
public class Tree{
    private Number id;
    private Number pid;
    private Collection childs;
}