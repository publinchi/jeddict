//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.18 at 01:46:14 PM IST 
//
package io.github.jeddict.jpa.spec;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.joining;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.persistence.internal.jpa.metadata.tables.IndexMetadata;
import static io.github.jeddict.jcode.jpa.JPAConstants.INDEX_FQN;
import io.github.jeddict.jpa.spec.extend.OrderbyItem;
import io.github.jeddict.source.JavaSourceParserUtil;

/**
 *
 *
 * @Target({}) @Retention(RUNTIME) public @interface Index { String name()
 * default ""; String columnList(); boolean unique() default false; }
 *
 *
 *
 * <p>
 * Java class for index complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="index">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="column-list" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="unique" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "index", propOrder = {
    "description",
    "columnList"
})
public class Index {

    protected String description;
    @XmlAttribute(name = "n")
    protected String name;
    @XmlElement(name = "c")
    protected Set<OrderbyItem> columnList;
    @XmlAttribute(name = "u")
    protected Boolean unique;

    public Index() {
    }

    public Index(String name) {
        this.name = name;
    }

    public static Index load(Element element, AnnotationMirror annotationMirror) {
        if (annotationMirror == null) {
            annotationMirror = JavaSourceParserUtil.findAnnotation(element, INDEX_FQN);
        }
        Index index = null;
        if (annotationMirror != null) {
            index = new Index();
            String columnList = (String) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "columnList");
            if (StringUtils.isNotBlank(columnList)) {
                for (String coulmnExp : columnList.split(",")) {
                    String[] coulmnExpParam = coulmnExp.trim().split(" ");
                    if (coulmnExpParam.length > 1) {
                        index.getColumnList().add(new OrderbyItem(coulmnExpParam[0], OrderType.valueOf(coulmnExpParam[1])));
                    } else {
                        index.getColumnList().add(new OrderbyItem(coulmnExpParam[0], null));
                    }
                }
            }
            index.name = (String) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "name");
            index.unique = (Boolean) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "unique");
        }
        return index;
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

    /**
     * Gets the value of the columnList property.
     *
     * @return possible object is {@link String }
     *
     */
    public Set<OrderbyItem> getColumnList() {
        if (columnList == null) {
            columnList = new LinkedHashSet<>();
        }
        return columnList;
    }

    /**
     * Sets the value of the columnList property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setColumnList(Set<OrderbyItem> value) {
        this.columnList = value;
    }

    /**
     * Gets the value of the unique property.
     *
     * @return possible object is {@link Boolean }
     *
     */
    public Boolean isUnique() {
        return unique;
    }

    /**
     * Sets the value of the unique property.
     *
     * @param value allowed object is {@link Boolean }
     *
     */
    public void setUnique(Boolean value) {
        this.unique = value;
    }

    @Override
    public String toString() {
        return getColumnList().stream().map(c -> c.getProperty()).collect(Collectors.joining(", "));
    }

    public IndexMetadata getAccessor() {
        IndexMetadata accessor = new IndexMetadata();
        accessor.setName(name);
        accessor.setUnique(unique);
        accessor.setColumnList(getColumnList().stream().map(ot -> ot.toString()).collect(joining(",")));
        return accessor;
    }
}
