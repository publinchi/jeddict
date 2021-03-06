/** *****************************************************************************
 * Copyright (c) 1998, 2013 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 ***************************************************************************** */
package org.eclipse.persistence.tools.schemaframework;

import io.github.jeddict.relation.mapper.spec.DBDiscriminatorColumn;
import io.github.jeddict.relation.mapper.spec.DBParentAssociationInverseJoinColumn;
import io.github.jeddict.relation.mapper.spec.DBMapKeyJoinColumn;
import io.github.jeddict.relation.mapper.spec.DBEmbeddedAttributeColumn;
import io.github.jeddict.relation.mapper.spec.DBParentAttributeColumn;
import io.github.jeddict.relation.mapper.spec.DBEmbeddedAssociationInverseJoinColumn;
import io.github.jeddict.relation.mapper.spec.DBPrimaryKeyJoinColumn;
import io.github.jeddict.relation.mapper.spec.DBEmbeddedAssociationJoinColumn;
import io.github.jeddict.relation.mapper.spec.DBMapKeyColumn;
import io.github.jeddict.relation.mapper.spec.DBEmbeddedAttributeJoinColumn;
import io.github.jeddict.relation.mapper.spec.DBColumn;
import io.github.jeddict.relation.mapper.spec.DBTable;
import io.github.jeddict.relation.mapper.spec.DBMapKeyEmbeddedColumn;
import io.github.jeddict.relation.mapper.spec.DBInverseJoinColumn;
import io.github.jeddict.relation.mapper.spec.DBJoinColumn;
import io.github.jeddict.relation.mapper.spec.DBParentAssociationJoinColumn;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.eclipse.persistence.exceptions.ValidationException;
import org.eclipse.persistence.internal.databaseaccess.FieldTypeDefinition;
import org.eclipse.persistence.internal.sessions.AbstractSession;
import io.github.jeddict.jpa.spec.DefaultAttribute;
import io.github.jeddict.jpa.spec.DiscriminatorColumn;
import io.github.jeddict.jpa.spec.ElementCollection;
import io.github.jeddict.jpa.spec.Embedded;
import io.github.jeddict.jpa.spec.EmbeddedId;
import io.github.jeddict.jpa.spec.Entity;
import io.github.jeddict.jpa.spec.Id;
import io.github.jeddict.jpa.spec.SecondaryTable;
import io.github.jeddict.jpa.spec.extend.Attribute;
import io.github.jeddict.jpa.spec.extend.MapKeyHandler;
import io.github.jeddict.jpa.spec.extend.RelationAttribute;
import org.netbeans.modeler.core.NBModelerUtil;

public class JPAMFieldDefinition extends FieldDefinition {

    private Entity intrinsicClass;
    private LinkedList<Attribute> intrinsicAttribute = new LinkedList<>();
    private Attribute managedAttribute;
    private boolean inverse;
    private boolean foriegnKey;
    private boolean relationTable;
    private boolean inherited;
    private boolean mapKey;
    private boolean primaryKeyJoinColumn;
    private SecondaryTable secondaryTable;
    private DiscriminatorColumn discriminatorColumn;

    public JPAMFieldDefinition(LinkedList<Attribute> intrinsicAttribute, Attribute managedAttribute, boolean inverse, boolean foriegnKey, boolean relationTable) {
        this(intrinsicAttribute, managedAttribute);
        this.inverse = inverse;
        this.foriegnKey = foriegnKey;
        this.relationTable = relationTable;
    }   
    
    public JPAMFieldDefinition(LinkedList<Attribute> intrinsicAttribute, Attribute managedAttribute, boolean mapKey) {
        this(intrinsicAttribute, managedAttribute);
        this.mapKey = mapKey;
    }   
    
    private JPAMFieldDefinition(LinkedList<Attribute> intrinsicAttribute, Attribute managedAttribute) {
        if (intrinsicAttribute != null) {
            intrinsicAttribute.forEach((attr) -> {
                if (attr != null && attr.getOrignalObject() != null) {
                    this.intrinsicAttribute.add((Attribute) attr.getOrignalObject());
                } else {
                    this.intrinsicAttribute.add(attr);
                }
            });
        }
//       if(managedAttribute!=null){
        this.managedAttribute = managedAttribute.getOrignalObject() != null ? (Attribute) managedAttribute.getOrignalObject() : managedAttribute;
//       } else {
//           this.managedAttribute = null;
//       }
        this.inherited = false;
        this.intrinsicClass = null;
    }

    public JPAMFieldDefinition(Entity intrinsicClass) {
        this.intrinsicClass = intrinsicClass.getOrignalObject() != null ? (Entity) intrinsicClass.getOrignalObject() : intrinsicClass;
        if (intrinsicClass.getDiscriminatorColumn() == null) {
            intrinsicClass.setDiscriminatorColumn(new DiscriminatorColumn());
        }
        this.discriminatorColumn = intrinsicClass.getDiscriminatorColumn();
    }

    public JPAMFieldDefinition(Entity intrinsicClass, boolean primaryKeyJoinColumn) {
        this.intrinsicClass = intrinsicClass.getOrignalObject() != null ? (Entity) intrinsicClass.getOrignalObject() : intrinsicClass;
        this.primaryKeyJoinColumn = primaryKeyJoinColumn;
        this.inverse = true; 
        this.foriegnKey = true;
    }
    
    public JPAMFieldDefinition(Entity intrinsicClass, boolean primaryKeyJoinColumn, SecondaryTable secondaryTable) {
        this(intrinsicClass, primaryKeyJoinColumn);
        this.secondaryTable = secondaryTable;
    }

    public JPAMFieldDefinition(Entity intrinsicClass, Attribute managedAttribute, boolean inverse, boolean foriegnKey, boolean relationTable) {
        this.intrinsicClass = intrinsicClass.getOrignalObject() != null ? (Entity) intrinsicClass.getOrignalObject() : intrinsicClass;
        this.managedAttribute = managedAttribute.getOrignalObject() != null ? (Attribute) managedAttribute.getOrignalObject() : managedAttribute;
        this.inverse = inverse; 
        this.foriegnKey = foriegnKey;
        this.relationTable = relationTable;
        this.inherited = true;
    }

    /**
     * INTERNAL: Append the database field definition string to the table
     * creation statement.
     *
     * @param writer Target writer where to write field definition string.
     * @param session Current session context.
     * @param table Database table being processed.
     * @throws ValidationException When invalid or inconsistent data were found.
     */
    public void buildDBColumn(final DBTable table, final AbstractSession session,
            final JPAMTableDefinition tableDef) throws ValidationException {
        DBColumn column = null;
        if (discriminatorColumn != null) {
            column = new DBDiscriminatorColumn(name, discriminatorColumn);
        } else if (inherited) {
            if (managedAttribute instanceof RelationAttribute) {
                if (inverse) {
                    column = new DBParentAssociationInverseJoinColumn(name, intrinsicClass, managedAttribute, relationTable);
                } else {
                    column = new DBParentAssociationJoinColumn(name, intrinsicClass, managedAttribute, relationTable);
                }
            } else if (managedAttribute instanceof ElementCollection) {
                if (foriegnKey) {
                    column = new DBParentAssociationJoinColumn(name, intrinsicClass, managedAttribute, relationTable);
                } else if(mapKey){//e.g Map<Basic,Basic>
                    column = buildMapKeyColumn();//todo
                } else {
                    column = new DBColumn(name, managedAttribute);
                }
            } else {
                column = new DBParentAttributeColumn(name, intrinsicClass, managedAttribute);
            }
        } else if (primaryKeyJoinColumn && (intrinsicAttribute == null || intrinsicAttribute.isEmpty())) {//intrinsicAttribute will be null in case of JoinTableStrategy primary key mapping to children
            if (secondaryTable != null) {
                column = new DBPrimaryKeyJoinColumn(name, secondaryTable, (Id) managedAttribute);
            } else {
                column = new DBPrimaryKeyJoinColumn(name, intrinsicClass, (Id) managedAttribute);
            }
        } else if (intrinsicAttribute.size() == 1) {
            if (intrinsicAttribute.peek() instanceof RelationAttribute) {
                if(mapKey){//e.g Map<Basic,Basic>
                    column = buildMapKeyColumn();
                } else {
                if (inverse) {
                    column = new DBInverseJoinColumn(name, (RelationAttribute) managedAttribute, relationTable);
                } else {
                    column = new DBJoinColumn(name, managedAttribute, relationTable);
                }
                }
            } else if (intrinsicAttribute.peek() instanceof ElementCollection) {
                if (foriegnKey) {
                    column = new DBJoinColumn(name, managedAttribute, relationTable);
                } else if(mapKey){//e.g Map<Basic,Basic>
                    column = buildMapKeyColumn();
                } else {
                    column = new DBColumn(name, managedAttribute);
                }
            } else if (primaryKeyJoinColumn && intrinsicAttribute.peek() instanceof Id) {//PrimaryKeyJoinColumn
                if (secondaryTable != null) {
                    column = new DBPrimaryKeyJoinColumn(name, secondaryTable, (Id) managedAttribute);
                } else {
                    column = new DBPrimaryKeyJoinColumn(name, intrinsicClass, (Id) managedAttribute);
                }
            } else {
                column = new DBColumn(name, managedAttribute);
            }
        } else if (intrinsicAttribute.size() > 1) {
            if (intrinsicAttribute.get(0) instanceof RelationAttribute) { // nested derived Identity / shared relationship IdClass
                if (mapKey) {
                    column = buildMapKeyColumn();
                } else {
                    //Ex 3.a
                    if (inverse) {
                        column = new DBInverseJoinColumn(name, (RelationAttribute) managedAttribute, relationTable);
                    } else {
                        column = new DBJoinColumn(name, managedAttribute, relationTable);
                    }
                }
            } else if (intrinsicAttribute.get(0) instanceof Embedded) {
                List<Embedded> embeddedList = new ArrayList<>();
                for (int i = 0; i < intrinsicAttribute.size() - 1; i++) {
                    embeddedList.add((Embedded) intrinsicAttribute.get(i));
                }

                if (managedAttribute instanceof RelationAttribute) {
                    if (inverse) {
                        column = new DBEmbeddedAssociationInverseJoinColumn(name, embeddedList, (RelationAttribute) managedAttribute, relationTable);
                    } else {
                        column = new DBEmbeddedAssociationJoinColumn(name, embeddedList, (RelationAttribute) managedAttribute, relationTable);
                    }
                } else if (foriegnKey) {
                    column = new DBEmbeddedAttributeJoinColumn(name, embeddedList, managedAttribute);
                } else {
                    column = new DBEmbeddedAttributeColumn(name, embeddedList, managedAttribute);
                }
            } else if (intrinsicAttribute.get(0) instanceof EmbeddedId) {
                EmbeddedId embeddedId = (EmbeddedId) intrinsicAttribute.get(0);
                if (intrinsicAttribute.size() > 2 && ((DefaultAttribute) intrinsicAttribute.get(1)).isDerived() && ((DefaultAttribute) intrinsicAttribute.get(1)).getConnectedAttribute() instanceof RelationAttribute) {
                        column = new DBInverseJoinColumn(name, (RelationAttribute) ((DefaultAttribute) intrinsicAttribute.get(1)).getConnectedAttribute(), relationTable);//2.4.1.3 Example 2:b firstName, lastName
//                    column = new DBEmbeddedIdDerivedColumn(name, embeddedId, managedAttribute);
                } else {
                    if (((DefaultAttribute) managedAttribute).getConnectedAttribute() instanceof RelationAttribute) {
                        column = new DBInverseJoinColumn(name, (RelationAttribute) ((DefaultAttribute) managedAttribute).getConnectedAttribute(), relationTable);//2.4.1.3 Example 1:b empPK
                    } else if (((EmbeddedId) intrinsicAttribute.get(0)).getConnectedAttribute() instanceof RelationAttribute) {
                        column = new DBInverseJoinColumn(name, (RelationAttribute) ((EmbeddedId) intrinsicAttribute.get(0)).getConnectedAttribute(), relationTable);//2.4.1.3 Example 5:b id(firstName,lastName)
                    } else {
                        column = new DBColumn(name, ((DefaultAttribute) managedAttribute).getConnectedAttribute());
//                        column = new DBEmbeddedIdAttributeColumn(name, embeddedId, managedAttribute);
                    }
                }
            } else if (intrinsicAttribute.get(0) instanceof ElementCollection) {
                if(mapKey){
                    column = buildMapKeyColumn();
                } else {//e.g : Map<Entity,Embedded>, List<Embedded>
                    ElementCollection elementCollection = (ElementCollection)intrinsicAttribute.peek();
                    List<Embedded> embeddedList = new ArrayList<>();
                    embeddedList.add(new Embedded(elementCollection.getAttributeOverride()));
                    for (int i = 1; i < intrinsicAttribute.size() - 1; i++) {//skip first it ElementCollection
                        embeddedList.add((Embedded) intrinsicAttribute.get(i));
                    }

                    if (managedAttribute instanceof RelationAttribute) {
                        if (inverse) {
                            column = new DBEmbeddedAssociationInverseJoinColumn(name, embeddedList, (RelationAttribute) managedAttribute, relationTable);
                        } else {
                            column = new DBEmbeddedAssociationJoinColumn(name, embeddedList, (RelationAttribute) managedAttribute, relationTable);
                        }
                    } else if (foriegnKey) {
                        column = new DBEmbeddedAttributeJoinColumn(name, embeddedList, managedAttribute);
                    } else {
                        column = new DBEmbeddedAttributeColumn(name, embeddedList, managedAttribute);
                    }
                }
            }
        } 
        
        if(column == null) {
            column = new DBColumn(name, managedAttribute);
        }
        
        column.setId(NBModelerUtil.getAutoGeneratedStringId());

        if (getTypeDefinition() != null) { //apply user-defined complete type definition
            //TODO
        } else {
            final FieldTypeDefinition fieldType = type != null ? session.getPlatform().getFieldTypeDefinition(type) : new FieldTypeDefinition(typeName);
            if (fieldType == null) {
                throw ValidationException.javaTypeIsNotAValidDatabaseType(type);
            }
            column.setDataType(fieldType.getName());

            if ((fieldType.isSizeAllowed()) && ((this.getSize() != 0) || (fieldType.isSizeRequired()))) {
                if (this.getSize() == 0) {
                    column.setSize(fieldType.getDefaultSize());
                } else {
                    column.setSize(this.getSize());
                }
                if (this.getSubSize() != 0) {
                    column.setSubSize(this.getSubSize());
                } else if (fieldType.getDefaultSubSize() != 0) {
                    column.setSubSize(fieldType.getDefaultSubSize());
                }
            }

            if (shouldAllowNull && fieldType.shouldAllowNull()) {
                column.setAllowNull(true);
            } else {
                column.setAllowNull(false);
            }
        }

        column.setUniqueKey(isUnique());
        column.setPrimaryKey(isPrimaryKey() && session.getPlatform().supportsPrimaryKeyConstraint());

        table.addColumn(column);

    }
    
    private DBColumn buildMapKeyColumn() {
        DBColumn column;
        MapKeyHandler mapKeyHandler = (MapKeyHandler) intrinsicAttribute.peek();
        if (mapKeyHandler.getMapKeyEntity() != null) {
            column = new DBMapKeyJoinColumn(name, managedAttribute);
        } else if (mapKeyHandler.getMapKeyEmbeddable() != null) {
            // Wrap AttributeOverride to Embedded to reuse the api
            column = new DBMapKeyEmbeddedColumn(name, Collections.singletonList(new Embedded(mapKeyHandler.getMapKeyAttributeOverride())), managedAttribute);
        } else {
            column = new DBMapKeyColumn(name, managedAttribute);
        }
        return column;
    }
    
    

}
