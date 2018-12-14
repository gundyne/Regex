/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

/**
 *
 * @author Dat
 */
public class SymbolRow {
    private String idName;
    private String dataType;
    private String value;

    public SymbolRow(String id, String type, String val)
    {
        idName = id;
        dataType = type;
        value = val;
    }
    
    public SymbolRow(String id, String type)
    {
        idName = id;
        dataType = type;
    }
    
    /**
     * @return the idName
     */
    public String getIdName() {
        return idName;
    }

    /**
     * @return the dataType
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    
}
