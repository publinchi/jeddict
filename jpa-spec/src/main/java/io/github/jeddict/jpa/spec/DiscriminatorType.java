//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2014.01.21 at 01:52:19 PM IST
//
package io.github.jeddict.jpa.spec;

import io.github.jeddict.source.JavaSourceParserUtil;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for discriminator-type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * <p>
 * <
 * pre>
 * &lt;simpleType name="discriminator-type"> &lt;restriction
 * base="{http://www.w3.org/2001/XMLSchema}token"> &lt;enumeration
 * value="STRING"/> &lt;enumeration value="CHAR"/> &lt;enumeration
 * value="INTEGER"/> &lt;/restriction> &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "discriminator-type")
@XmlEnum
public enum DiscriminatorType {

    STRING,
    CHAR,
    INTEGER;

    public String value() {
        return name();
    }

    public static DiscriminatorType fromValue(String v) {
        return valueOf(v);
    }

    @Deprecated
    public static DiscriminatorType load(Element element, AnnotationMirror annotationMirror) {
        DiscriminatorType discriminatorType = null;
        if (annotationMirror != null) {
            Object value = JavaSourceParserUtil.findAnnotationValue(annotationMirror, "discriminatorType");
            if (value != null) {
                discriminatorType = DiscriminatorType.valueOf(value.toString());
            }
        }
        return discriminatorType;

    }

}
