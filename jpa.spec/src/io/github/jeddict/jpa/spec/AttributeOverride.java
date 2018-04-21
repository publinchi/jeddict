//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2014.01.21 at 01:52:19 PM IST
//
package io.github.jeddict.jpa.spec;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import static io.github.jeddict.jcode.jpa.JPAConstants.ATTRIBUTE_OVERRIDES_FQN;
import static io.github.jeddict.jcode.jpa.JPAConstants.ATTRIBUTE_OVERRIDE_FQN;
import io.github.jeddict.source.JavaSourceParserUtil;

/**
 *
 *
 * @Target({TYPE, METHOD, FIELD}) @Retention(RUNTIME) public @interface
 * AttributeOverride { String name(); Column column(); }
 *
 *
 *
 * <p>
 * Java class for attribute-override complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="attribute-override">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="column" type="{http://java.sun.com/xml/ns/persistence/orm}column"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "attribute-override", propOrder = {
    "description",
    "column"
})
//@XmlJavaTypeAdapter(value=AttributeValidator.class)
public class AttributeOverride implements Comparable<AttributeOverride> {

    protected String description;
    @XmlElement(required = true)
    protected Column column;
    @XmlAttribute(required = true)
    protected String name;

    private static AttributeOverride loadAttribute(Element element, AnnotationMirror annotationMirror) {
        AttributeOverride attributeOverride = null;
        if (annotationMirror != null) {
            attributeOverride = new AttributeOverride();
            attributeOverride.name = (String) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "name");

            AnnotationMirror columnsAnnot = (AnnotationMirror) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "column");
            if (columnsAnnot != null) {
                attributeOverride.column = new Column().load(element, columnsAnnot);
            }
        }
        return attributeOverride;
    }

    public static Set<AttributeOverride> load(Element element) {
        Set<AttributeOverride> attributeOverrides = new TreeSet<>();

        AnnotationMirror attributeOverridesMirror = JavaSourceParserUtil.findAnnotation(element, ATTRIBUTE_OVERRIDES_FQN);
        if (attributeOverridesMirror != null) {
            List attributeOverridesMirrorList = (List) JavaSourceParserUtil.findAnnotationValue(attributeOverridesMirror, "value");
            if (attributeOverridesMirrorList != null) {
                for (Object attributeOverrideObj : attributeOverridesMirrorList) {
                    attributeOverrides.add(AttributeOverride.loadAttribute(element, (AnnotationMirror) attributeOverrideObj));
                }
            }
        } else {
            attributeOverridesMirror = JavaSourceParserUtil.findAnnotation(element, ATTRIBUTE_OVERRIDE_FQN);
            if (attributeOverridesMirror != null) {
                attributeOverrides.add(AttributeOverride.loadAttribute(element, attributeOverridesMirror));
            }
        }

        return attributeOverrides;
    }

    /**
     * Gets the value of the description property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the column property.
     *
     * @return possible object is {@link Column }
     *
     */
    public Column getColumn() {
        if (column == null) {
            column = new Column();
        }
        return column;
    }

    /**
     * Sets the value of the column property.
     *
     * @param value allowed object is {@link Column }
     *
     */
    public void setColumn(Column value) {
        this.column = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    @Override
    public int compareTo(AttributeOverride attributeOverride) {
        return this.name.compareTo(attributeOverride.getName());
    }

}
