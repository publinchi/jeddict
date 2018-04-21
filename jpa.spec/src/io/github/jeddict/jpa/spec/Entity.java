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
import static java.util.stream.Collectors.toSet;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.TypeElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.apache.commons.lang3.StringUtils;
import static io.github.jeddict.jcode.jpa.JPAConstants.CACHEABLE_FQN;
import static io.github.jeddict.jcode.jpa.JPAConstants.DISCRIMINATOR_VALUE_FQN;
import static io.github.jeddict.jcode.jpa.JPAConstants.ENTITY_FQN;
import io.github.jeddict.jpa.spec.extend.AccessTypeHandler;
import io.github.jeddict.jpa.spec.extend.AssociationOverrideHandler;
import io.github.jeddict.jpa.spec.extend.Attribute;
import io.github.jeddict.jpa.spec.extend.AttributeOverrideHandler;
import io.github.jeddict.jpa.spec.extend.ConvertContainerHandler;
import io.github.jeddict.jpa.spec.extend.InheritanceHandler;
import io.github.jeddict.jpa.spec.extend.PaginationType;
import io.github.jeddict.jpa.spec.validator.override.AssociationValidator;
import io.github.jeddict.jpa.spec.validator.override.AttributeValidator;
import io.github.jeddict.source.JavaSourceParserUtil;

/**
 *
 *
 * Defines the settings and mappings for an entity. Is allowed to be sparsely
 * populated and used in conjunction with the annotations. Alternatively, the
 * metadata-complete attribute can be used to indicate that no annotations on
 * the entity class (and its fields or properties) are to be processed. If this
 * is the case then the defaulting rules for the entity and its subelements will
 * be recursively applied.
 *
 * @Target(TYPE) @Retention(RUNTIME) public @interface Entity { String name()
 * default ""; }
 *
 *
 *
 * <p>
 * Java class for entity complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="entity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="table" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}table" minOccurs="0"/>
 *         &lt;element name="secondary-table" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}secondary-table" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;sequence>
 *           &lt;element name="primary-key-join-column" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}primary-key-join-column" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="primary-key-foreign-key" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}foreign-key" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;element name="id-class" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}id-class" minOccurs="0"/>
 *         &lt;element name="inheritance" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}inheritance" minOccurs="0"/>
 *         &lt;element name="discriminator-value" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}discriminator-value" minOccurs="0"/>
 *         &lt;element name="discriminator-column" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}discriminator-column" minOccurs="0"/>
 *         &lt;element name="sequence-generator" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}sequence-generator" minOccurs="0"/>
 *         &lt;element name="table-generator" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}table-generator" minOccurs="0"/>
 *         &lt;element name="named-query" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}named-query" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="named-native-query" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}named-native-query" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="named-stored-procedure-query" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}named-stored-procedure-query" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sql-result-set-mapping" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}sql-result-set-mapping" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="exclude-default-listeners" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}emptyType" minOccurs="0"/>
 *         &lt;element name="exclude-superclass-listeners" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}emptyType" minOccurs="0"/>
 *         &lt;element name="entity-listeners" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}entity-listeners" minOccurs="0"/>
 *         &lt;element name="pre-persist" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}pre-persist" minOccurs="0"/>
 *         &lt;element name="post-persist" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}post-persist" minOccurs="0"/>
 *         &lt;element name="pre-remove" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}pre-remove" minOccurs="0"/>
 *         &lt;element name="post-remove" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}post-remove" minOccurs="0"/>
 *         &lt;element name="pre-update" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}pre-update" minOccurs="0"/>
 *         &lt;element name="post-update" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}post-update" minOccurs="0"/>
 *         &lt;element name="post-load" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}post-load" minOccurs="0"/>
 *         &lt;element name="attribute-override" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}attribute-override" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="association-override" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}association-override" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="convert" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}convert" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="named-entity-graph" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}named-entity-graph" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="attributes" type="{http://xmlns.jcp.org/xml/ns/persistence/orm}attributes" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="class" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="access" type="{http://java.sun.com/xml/ns/persistence/orm}access-type" />
 *       &lt;attribute name="cacheable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="metadata-complete" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "entity", propOrder = {
    //    "description",
    "table",
    "secondaryTable",
    "primaryKeyJoinColumn",
    "primaryKeyForeignKey",
    //    "idClass",
    "inheritance",
    "discriminatorValue",
    "discriminatorColumn",
    "sequenceGenerator",
    "tableGenerator",
//    "namedStoredProcedureQuery",
    //    "namedQuery",
    //    "namedNativeQuery",
    //    "sqlResultSetMapping",
    //    "excludeDefaultListeners",
    //    "excludeSuperclassListeners",
    //    "entityListeners",
    //    "prePersist",
    //    "postPersist",
    //    "preRemove",
    //    "postRemove",
    //    "preUpdate",
    //    "postUpdate",
    //    "postLoad",
    "attributeOverride",
    "associationOverride",
    "convert",
    "namedEntityGraph"
//    "attributes",
//    "interfaces"
})
@XmlRootElement
public class Entity extends IdentifiableClass implements AccessTypeHandler, InheritanceHandler, AttributeOverrideHandler, AssociationOverrideHandler, ConvertContainerHandler {

    protected Table table;
    @XmlElement(name = "st")
    protected List<SecondaryTable> secondaryTable;//REVENG PENDING
    @XmlElement(name = "pk-jc")
    private List<PrimaryKeyJoinColumn> primaryKeyJoinColumn;
    @XmlElement(name = "pk-fk")
    protected ForeignKey primaryKeyForeignKey;//REVENG PENDING JPA 2.1

    protected Inheritance inheritance;
    @XmlElement(name = "discriminator-value")
    protected String discriminatorValue;
    @XmlElement(name = "discriminator-column")
    protected DiscriminatorColumn discriminatorColumn;
    @XmlElement(name = "sequence-generator")
    protected SequenceGenerator sequenceGenerator;
    @XmlElement(name = "table-generator")
    protected TableGenerator tableGenerator;

    @XmlElement(name = "attribute-override")
    protected Set<AttributeOverride> attributeOverride;
    @XmlElement(name = "association-override")
    protected Set<AssociationOverride> associationOverride;
    @XmlElement(name = "cn")
    protected List<Convert> convert;
    @XmlElement(name = "neg")
    protected List<NamedEntityGraph> namedEntityGraph;
    @XmlAttribute(name="name")
    protected String entityName;
    @XmlAttribute
    protected Boolean cacheable;
    
    //ui properties start
    
    @XmlAttribute(name = "lbl-ref")
    @XmlIDREF
    private Attribute labelAttribute;//to display value in UI

    @XmlAttribute(name = "uil")
    private String label;
    
    @XmlAttribute(name = "pt")
    private PaginationType paginationType;

    //ui properties end
        
    @Override
    public void load(EntityMappings entityMappings, TypeElement element, boolean fieldAccess) {
        super.load(entityMappings, element, fieldAccess);
        AnnotationMirror annotationMirror = JavaSourceParserUtil.getAnnotation(element, ENTITY_FQN);

        this.table = Table.load(element);
        this.getSecondaryTable().addAll(SecondaryTable.loadTables(element));
        this.inheritance = Inheritance.load(element);
        AnnotationMirror annotDiscrValue = JavaSourceParserUtil.findAnnotation(element, DISCRIMINATOR_VALUE_FQN);
        if (annotDiscrValue != null) {
            Object value = JavaSourceParserUtil.findAnnotationValue(annotationMirror, "value");
            if (value != null) {
                discriminatorValue = value.toString();
            }
        }
        this.discriminatorColumn = DiscriminatorColumn.load(element);
        this.tableGenerator = TableGenerator.load(element);
        this.sequenceGenerator = SequenceGenerator.load(element);

        if (annotationMirror != null) {
            this.entityName = (String) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "name");
        }
        
        AnnotationMirror cacheableAnnotation = JavaSourceParserUtil.findAnnotation(element, CACHEABLE_FQN);
        if (cacheableAnnotation != null) {
            Object value =  JavaSourceParserUtil.findAnnotationValue(cacheableAnnotation, "value");
            if(value == null){
                this.cacheable = true;
            } else {
                this.cacheable = (Boolean) value;
            }
        }
//        AnnotationMirror primaryKeyForeignKeyValue = (AnnotationMirror) JavaSourceParserUtil.findAnnotationValue(annotationMirror, "primaryKeyForeignKeyValue");
//            if (primaryKeyForeignKeyValue != null) {
//                this.primaryKeyForeignKey = ForeignKey.load(element, primaryKeyForeignKeyValue);
//            }
        this.convert = Convert.load(element);
        this.primaryKeyJoinColumn = PrimaryKeyJoinColumn.load(element);
        this.attributeOverride = AttributeOverride.load(element);
        this.associationOverride = AssociationOverride.load(element);
        this.namedEntityGraph = NamedEntityGraph.load(element);
        
    }

    @Override
    public void beforeMarshal(Marshaller marshaller) {
        super.beforeMarshal(marshaller);
        AttributeValidator.filter(this);
        AssociationValidator.filter(this);
    }

    /**
     * Gets the value of the table property.
     *
     * @return possible object is {@link Table }
     *
     */
    public Table getTable() {
        if (table == null) {
            table = new Table();
        }
        return table;
    }

    public String getDefaultTableName() {
        return this.getClazz().toUpperCase();
    }

    public String getTableName() {
        if (this.getTable() != null && StringUtils.isNotBlank(this.getTable().getName())) {
            return getTable().getName();
        } else {
            return getDefaultTableName();
        }
    }

    /**
     * Sets the value of the table property.
     *
     * @param value allowed object is {@link Table }
     *
     */
    public void setTable(Table value) {
        if(this.table !=null && value == null){
            this.table.clear();
        } else {
            this.table = value;
        }
    }

    /**
     * Gets the value of the secondaryTable property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the secondaryTable property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSecondaryTable().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SecondaryTable }
     *
     *
     * @return 
     */
    public List<SecondaryTable> getSecondaryTable() {
        if (secondaryTable == null) {
            secondaryTable = new ArrayList<>();
        }
        return this.secondaryTable;
    }

    public boolean addSecondaryTable(SecondaryTable secondaryTable_In) {
        return secondaryTable.add(secondaryTable_In);
    }

    public boolean removeSecondaryTable(SecondaryTable secondaryTable_In) {
        return secondaryTable.remove(secondaryTable_In);
    }

    /**
     * Gets the value of the primaryKeyJoinColumn property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the primaryKeyJoinColumn property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrimaryKeyJoinColumn().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrimaryKeyJoinColumn }
     *
     *
     */
    public List<PrimaryKeyJoinColumn> getPrimaryKeyJoinColumn() {
        if (primaryKeyJoinColumn == null) {
            setPrimaryKeyJoinColumn(new ArrayList<>());
        }
        return this.primaryKeyJoinColumn;
    }

    /**
     * Gets the value of the primaryKeyForeignKey property.
     *
     * @return possible object is {@link ForeignKey }
     *
     */
    public ForeignKey getPrimaryKeyForeignKey() {
        if(primaryKeyForeignKey==null){
            primaryKeyForeignKey = new ForeignKey();
        }
        return primaryKeyForeignKey;
    }

    /**
     * Sets the value of the primaryKeyForeignKey property.
     *
     * @param value allowed object is {@link ForeignKey }
     *
     */
    public void setPrimaryKeyForeignKey(ForeignKey value) {
        this.primaryKeyForeignKey = value;
    }

    /**
     * Gets the value of the inheritance property.
     *
     * @return possible object is {@link Inheritance }
     *
     */
    @Override
    public Inheritance getInheritance() {
        return inheritance;
    }

    /**
     * Sets the value of the inheritance property.
     *
     * @param value allowed object is {@link Inheritance }
     *
     */
    @Override
    public void setInheritance(Inheritance value) {
        this.inheritance = value;
    }

    /**
     * Gets the value of the discriminatorValue property.
     *
     * @return possible object is {@link String }
     *
     */
    @Override
    public String getDiscriminatorValue() {
        return discriminatorValue;
    }

    /**
     * Sets the value of the discriminatorValue property.
     *
     * @param value allowed object is {@link String }
     *
     */
    @Override
    public void setDiscriminatorValue(String value) {
        this.discriminatorValue = value;
    }

    /**
     * Gets the value of the discriminatorColumn property.
     *
     * @return possible object is {@link DiscriminatorColumn }
     *
     */
    @Override
    public DiscriminatorColumn getDiscriminatorColumn() {
        return discriminatorColumn;
    }

    /**
     * Sets the value of the discriminatorColumn property.
     *
     * @param value allowed object is {@link DiscriminatorColumn }
     *
     */
    @Override
    public void setDiscriminatorColumn(DiscriminatorColumn value) {
        this.discriminatorColumn = value;
    }

    /**
     * Gets the value of the sequenceGenerator property.
     *
     * @return possible object is {@link SequenceGenerator }
     *
     */
    public SequenceGenerator getSequenceGenerator() {
        return sequenceGenerator;
    }

    /**
     * Sets the value of the sequenceGenerator property.
     *
     * @param value allowed object is {@link SequenceGenerator }
     *
     */
    public void setSequenceGenerator(SequenceGenerator value) {
        this.sequenceGenerator = value;
    }

    /**
     * Gets the value of the tableGenerator property.
     *
     * @return possible object is {@link TableGenerator }
     *
     */
    public TableGenerator getTableGenerator() {
        return tableGenerator;
    }

    /**
     * Sets the value of the tableGenerator property.
     *
     * @param value allowed object is {@link TableGenerator }
     *
     */
    public void setTableGenerator(TableGenerator value) {
        this.tableGenerator = value;
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
   
    public String getEntityName() {
        return entityName;
    }
    
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    /**
     * Gets the value of the cacheable property.
     *
     * @return possible object is {@link Boolean }
     *
     */
    public Boolean getCacheable() {//isCacheable()
        return cacheable;
    }

    /**
     * Sets the value of the cacheable property.
     *
     * @param value allowed object is {@link Boolean }
     *
     */
    public void setCacheable(Boolean value) {
        this.cacheable = value;
    }

    @Override
    public String toString() {
        return "Entity{" + "description=" + description + ", table=" + table + ", secondaryTable=" + secondaryTable + ", primaryKeyJoinColumn=" + primaryKeyJoinColumn + ", idClass=" + idClass + ", inheritance=" + inheritance + ", discriminatorValue=" + discriminatorValue + ", discriminatorColumn=" + discriminatorColumn + ", sequenceGenerator=" + sequenceGenerator + ", tableGenerator=" + tableGenerator + ", namedQuery=" + namedQuery + ", namedNativeQuery=" + namedNativeQuery + ", sqlResultSetMapping=" + sqlResultSetMapping + ", excludeDefaultListeners=" + excludeDefaultListeners + ", excludeSuperclassListeners=" + excludeSuperclassListeners + ", entityListeners=" + entityListeners + ", prePersist=" + prePersist + ", postPersist=" + postPersist + ", preRemove=" + preRemove + ", postRemove=" + postRemove + ", preUpdate=" + preUpdate + ", postUpdate=" + postUpdate + ", postLoad=" + postLoad + ", attributeOverride=" + attributeOverride + ", associationOverride=" + associationOverride + ", attributes=" + attributes + ", entityName=" + entityName + ", clazz=" + clazz + ", access=" + access + ", cacheable=" + cacheable + ", metadataComplete=" + metadataComplete + '}';
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
     * Sets the value of the clazz property.
     *
     * @param value allowed object is {@link String }
     *
     */
    @Override
    public void setClazz(String value) {
        notifyListeners("clazz", this.clazz, value);
        super.setClazz(value);
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
     * Gets the value of the namedEntityGraph property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the namedEntityGraph property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNamedEntityGraph().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NamedEntityGraph }
     *
     *
     * @return
     */
    public List<NamedEntityGraph> getNamedEntityGraph() {
        if (namedEntityGraph == null) {
            namedEntityGraph = new ArrayList<>();
        }
        return this.namedEntityGraph;
    }

    public Inheritance getRootInheritance() {
        if (this.getInheritance() == null) {
            if (this.getSuperclass() != null && this.getSuperclass() instanceof Entity) {
                return ((Entity) this.getSuperclass()).getRootInheritance();
            } else {
                return null;
            }
        } else {
            return this.getInheritance();
        }
    }

    public DiscriminatorColumn getRootDiscriminatorColumn() {
        if (this.getInheritance() == null) {
            if (this.getDiscriminatorColumn() == null) {
                if (this.getSuperclass() != null && this.getSuperclass() instanceof Entity) {
                    return ((Entity) this.getSuperclass()).getRootDiscriminatorColumn();
                } else {
                    return null;
                }
            } else {
                return this.getDiscriminatorColumn();
            }
        } else {
            if (this.getDiscriminatorColumn() == null) {
                this.setDiscriminatorColumn(new DiscriminatorColumn());
            }
            return this.getDiscriminatorColumn();
        }
    }

    public String getRootDiscriminatorColumnName() {
        DiscriminatorColumn rootDiscriminatorColumn = getRootDiscriminatorColumn();
        if (rootDiscriminatorColumn == null || StringUtils.isBlank(rootDiscriminatorColumn.getName())) {
            return "DTYPE";
        } else {
            return rootDiscriminatorColumn.getName();
        }
    }

    public String getDiscriminatorColumnName() {
        DiscriminatorColumn localDiscriminatorColumn = getDiscriminatorColumn();
        if (localDiscriminatorColumn == null || StringUtils.isBlank(localDiscriminatorColumn.getName())) {
            return "DTYPE";
        } else {
            return localDiscriminatorColumn.getName();
        }
    }

    public Table getTable(String tableName){
        if(this.getTable()!=null && StringUtils.equalsIgnoreCase(this.getTable().getName(), tableName)){
            return this.getTable();
        }
        if(!this.getSecondaryTable().isEmpty()){
            Optional<SecondaryTable> secondaryTableOptional = this.getSecondaryTable()
                    .stream()
                    .filter(secondaryTable -> StringUtils.equalsIgnoreCase(secondaryTable.getName(), tableName))
                    .findFirst();
            if(secondaryTableOptional.isPresent()){
                return secondaryTableOptional.get();
            }
        }
        return null;
    }

    /**
     * @return the labelAttribute
     */
    public Attribute getLabelAttribute() {
        return labelAttribute;
    }

    /**
     * @param labelAttribute the labelAttribute to set
     */
    public void setLabelAttribute(Attribute labelAttribute) {
        this.labelAttribute = labelAttribute;
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the paginationType
     */
    public PaginationType getPaginationType() {
        if(paginationType == null){
            return PaginationType.NO;
        }
        return paginationType;
    }

    /**
     * @param paginationType the paginationType to set
     */
    public void setPaginationType(PaginationType paginationType) {
        this.paginationType = paginationType;
    }
    
    /**
     * @param primaryKeyJoinColumn the primaryKeyJoinColumn to set
     */
    public void setPrimaryKeyJoinColumn(List<PrimaryKeyJoinColumn> primaryKeyJoinColumn) {
        this.primaryKeyJoinColumn = primaryKeyJoinColumn;
    }

    @Override
    public Set<String> getAllConvert(){
        Set<String> converts = getAttributes().getAllConvert();
        converts.addAll(this.getConverts().stream().filter(con -> StringUtils.isNotBlank(con.getConverter())).map(Convert::getConverter).collect(toSet()));
        return converts;
    }
    
}
