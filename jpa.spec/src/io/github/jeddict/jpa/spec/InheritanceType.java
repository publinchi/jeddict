//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2014.01.21 at 01:52:19 PM IST
//
package io.github.jeddict.jpa.spec;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;
import io.github.jeddict.source.JavaSourceParserUtil;

/**
 * <p>
 * Java class for inheritance-type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * <
 * pre>
 * &lt;simpleType name="inheritance-type"> &lt;restriction
 * base="{http://www.w3.org/2001/XMLSchema}token"> &lt;enumeration
 * value="SINGLE_TABLE"/> &lt;enumeration value="JOINED"/> &lt;enumeration
 * value="TABLE_PER_CLASS"/> &lt;/restriction> &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "inheritance-type")
@XmlEnum
public enum InheritanceType {

    SINGLE_TABLE("Single Table"),
    JOINED("Joined Table"),
    TABLE_PER_CLASS("Table per Class");

    public String value() {
        return name();
    }

    public static InheritanceType fromValue(String v) {
        return valueOf(v);
    }

    public static InheritanceType load(Element element, AnnotationMirror annotationMirror) {
        InheritanceType inheritanceType = null;
        if (annotationMirror != null) {
            Object value = JavaSourceParserUtil.findAnnotationValue(annotationMirror, "strategy");
            if (value != null) {
                inheritanceType = InheritanceType.valueOf(value.toString());
            }
        }
        return inheritanceType;
    }

    private final String name;

    private InheritanceType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    @Override
    public String toString() {
        return name;
    }

}
