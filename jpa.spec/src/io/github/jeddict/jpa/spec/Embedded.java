//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2014.01.21 at 01:52:19 PM IST
//
package io.github.jeddict.jpa.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.StringUtils;
import io.github.jeddict.jpa.spec.extend.AssociationOverrideHandler;
import io.github.jeddict.jpa.spec.extend.CompositionAttribute;
import io.github.jeddict.jpa.spec.extend.ConvertContainerHandler;
import io.github.jeddict.jpa.spec.validator.override.AssociationValidator;
import io.github.jeddict.jpa.spec.validator.override.AttributeValidator;
import io.github.jeddict.source.JavaSourceParserUtil;

/**
 *
 *
 * @Target({METHOD, FIELD}) @Retention(RUNTIME) public @interface Embedded {}
 *
 *
 *
 * <p>
 * Java class for embedded complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="embedded">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attribute-override" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}attribute-override" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="association-override" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}association-override" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="convert" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}convert" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="access" type="{http://java.sun.com/xml/ns/persistence/orm}access-type" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "embedded", propOrder = {
    "attributeOverride",
    "associationOverride",
    "convert"
})
@XmlRootElement
public class Embedded extends CompositionAttribute<Embeddable> implements AssociationOverrideHandler, ConvertContainerHandler {

    @XmlElement(name = "attribute-override")
    protected Set<AttributeOverride> attributeOverride;
    @XmlElement(name = "association-override")
    protected Set<AssociationOverride> associationOverride;
    @XmlElement(name = "cn")
    protected List<Convert> convert;

    @XmlAttribute(name = "access")
    protected AccessType access;

    public Embedded() {
    }

    public Embedded(Set<AttributeOverride> attributeOverride) {
        this.attributeOverride = attributeOverride;
    }
    
    public static Embedded load(EntityMappings entityMappings, Element element, VariableElement variableElement, ExecutableElement getterElement) {
        Embedded embedded = new Embedded();
        embedded.loadAttribute(element, variableElement, getterElement);
        embedded.getAttributeOverride().addAll(AttributeOverride.load(element));
        embedded.getAssociationOverride().addAll(AssociationOverride.load(element));
        embedded.setAccess(AccessType.load(element));
        DeclaredType declaredType = (DeclaredType) variableElement.asType();

        Optional<io.github.jeddict.jpa.spec.Embeddable> embeddableClassSpecOpt = entityMappings.findEmbeddable(declaredType.asElement().getSimpleName().toString());
        io.github.jeddict.jpa.spec.Embeddable embeddableClassSpec;
        if (!embeddableClassSpecOpt.isPresent()) {
            boolean fieldAccess = false;
            if (element == variableElement) {
                fieldAccess = true;
            }
            embeddableClassSpec = new io.github.jeddict.jpa.spec.Embeddable();
            TypeElement embeddableTypeElement = JavaSourceParserUtil.getAttributeTypeElement(variableElement);
            if(embeddableTypeElement==null){
                return null;
            }
            embeddableClassSpec.load(entityMappings, embeddableTypeElement, fieldAccess);
            entityMappings.addEmbeddable(embeddableClassSpec);
        } else {
            embeddableClassSpec = embeddableClassSpecOpt.get();
        }
        embedded.setConnectedClass(embeddableClassSpec);
        embedded.setConverts(Convert.load(element));
        embedded.setAttributeConstraints(JavaSourceParserUtil.getBeanValidation(element));
        return embedded;
    }

    @Override
    public void beforeMarshal(Marshaller marshaller) {
        super.beforeMarshal(marshaller);
        AttributeValidator.filter(this);
        AssociationValidator.filter(this);
    }

    /**
     * Gets the value of the attributeOverride property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the attributeOverride property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributeOverride().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AttributeOverride }
     *
     *
     */
    @Override
    public Set<AttributeOverride> getAttributeOverride() {
        if (attributeOverride == null) {
            attributeOverride = new TreeSet<>();
        }
        return this.attributeOverride;
    }

    @Override
    public AttributeOverride findAttributeOverride(String name) {
        for (AttributeOverride attributeOverride : getAttributeOverride()) {
            if (StringUtils.equals(name, attributeOverride.getName())) {
                return attributeOverride;
            }
        }
        return null;
    }

    @Override
    public boolean addAttributeOverride(AttributeOverride attributeOverride) {
        return getAttributeOverride().add(attributeOverride);
    }

    @Override
    public boolean removeAttributeOverride(AttributeOverride attributeOverride) {
        return getAttributeOverride().remove(attributeOverride);
    }

    /**
     * Gets the value of the associationOverride property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the associationOverride property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssociationOverride().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssociationOverride }
     *
     *
     */
    @Override
    public Set<AssociationOverride> getAssociationOverride() {
        if (associationOverride == null) {
            associationOverride = new TreeSet<>();
        }
        return this.associationOverride;
    }

    @Override
    public AssociationOverride findAssociationOverride(String name) {
        for (AssociationOverride associationOverride : getAssociationOverride()) {
            if (StringUtils.equals(name, associationOverride.getName())) {
                return associationOverride;
            }
        }
        return null;
    }

    @Override
    public boolean addAssociationOverride(AssociationOverride associationOverride) {
        return getAssociationOverride().add(associationOverride);
    }

    @Override
    public boolean removeAssociationOverride(AssociationOverride associationOverride) {
        return getAssociationOverride().remove(associationOverride);
    }

    /**
     * Gets the value of the convert property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the convert property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getConvert().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link Convert }
     *
     *
     */
    @Override
    public List<Convert> getConverts() {
        if (convert == null) {
            convert = new ArrayList<>();
        }
        return this.convert;
    }
    @Override
     public void setConverts(List<Convert> converts){
        this.convert = converts;
    }

    /**
     * Gets the value of the access property.
     *
     * @return possible object is {@link AccessType }
     *
     */
    @Override
    public AccessType getAccess() {
        return access;
    }

    /**
     * Sets the value of the access property.
     *
     * @param value allowed object is {@link AccessType }
     *
     */
    @Override
    public void setAccess(AccessType value) {
        this.access = value;
    }

    @Override
    @Deprecated
    public AttributeOverride getAttributeOverride(String attributePath) {
        Set<AttributeOverride> attributeOverrides = getAttributeOverride();
        for (AttributeOverride attributeOverride_TMP : attributeOverrides) {
            if (attributeOverride_TMP.getName().equals(attributePath)) {
                return attributeOverride_TMP;
            }
        }
        AttributeOverride attributeOverride_TMP = new AttributeOverride();
        attributeOverride_TMP.setName(attributePath);
        attributeOverrides.add(attributeOverride_TMP);
        return attributeOverride_TMP;
    }

    @Override
    @Deprecated
    public AssociationOverride getAssociationOverride(String attributePath) {
        Set<AssociationOverride> associationOverrides = getAssociationOverride();
        for (AssociationOverride associationOverride_TMP : associationOverrides) {
            if (associationOverride_TMP.getName().equals(attributePath)) {
                return associationOverride_TMP;
            }
        }
        AssociationOverride attributeOverride_TMP = new AssociationOverride();
        attributeOverride_TMP.setName(attributePath);
        associationOverrides.add(attributeOverride_TMP);
        return attributeOverride_TMP;
    }

}
