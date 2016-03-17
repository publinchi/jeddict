/**
 * Copyright [2016] Gaurav Gupta
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.netbeans.db.modeler.core.widget.column;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JMenuItem;
import org.apache.commons.lang.StringUtils;
import org.netbeans.api.visual.widget.Widget;
import org.netbeans.db.modeler.core.widget.flow.ReferenceFlowWidget;
import org.netbeans.db.modeler.core.widget.table.TableWidget;
import org.netbeans.db.modeler.spec.DBColumn;
import org.netbeans.db.modeler.spec.DBTable;
import org.netbeans.db.modeler.specification.model.scene.DBModelerScene;
import org.netbeans.db.modeler.specification.model.util.DBModelerUtil;
import org.netbeans.jpa.modeler.core.widget.FlowPinWidget;
import org.netbeans.jpa.modeler.rules.attribute.AttributeValidator;
import org.netbeans.jpa.modeler.rules.entity.SQLKeywords;
import org.netbeans.jpa.modeler.spec.Entity;
import org.netbeans.jpa.modeler.specification.model.file.action.JPAFileActionListener;
import org.netbeans.jpa.modeler.specification.model.scene.JPAModelerScene;
import org.netbeans.modeler.core.ModelerFile;
import static org.netbeans.modeler.core.engine.ModelerDiagramEngine.NODE_WIDGET_SELECT_PROVIDER;
import org.netbeans.modeler.widget.context.ContextPaletteModel;
import org.netbeans.modeler.widget.node.IPNodeWidget;
import org.netbeans.modeler.widget.pin.info.PinWidgetInfo;

/**
 *
 * @author Gaurav Gupta
 */
public abstract class ColumnWidget<E extends DBColumn> extends FlowPinWidget<E, DBModelerScene> {

    private final List<ReferenceFlowWidget> referenceFlowWidget = new ArrayList<>();

    public ColumnWidget(DBModelerScene scene, IPNodeWidget nodeWidget, PinWidgetInfo pinWidgetInfo) {
        super(scene, nodeWidget, pinWidgetInfo);
        this.setImage(DBModelerUtil.COLUMN);
    }

    public void setDatatypeTooltip() {
        DBColumn column = this.getBaseElementSpec();
        StringBuilder writer = new StringBuilder();
        writer.append(column.getDataType());
        if (column.getSize() != 0) {
            writer.append('(').append(column.getSize()).append(')');
        }
        this.setToolTipText(writer.toString());
    }

    @Override
    public boolean remove() {
        return remove(false);
    }

    @Override
    public boolean remove(boolean notification) {
        // Issue Fix #5855 Start
//        if (super.remove(notification)) {
//            getClassWidget().deleteAttribute(ColumnWidget.this);
//            return true;
//        }
        // Issue Fix #5855 End
        return false;
    }

    @Override
    public void setLabel(String label) {
        if (label != null && !label.trim().isEmpty()) {
            this.setPinName(label.replaceAll("\\s+", ""));
        }
    }

    @Override
    public void init() {
//        super.init();
    }

    @Override
    public void destroy() {
    }

    public TableWidget getTableWidget() {
        return (TableWidget) this.getPNodeWidget();
    }

    public boolean addReferenceFlowWidget(ReferenceFlowWidget flowWidget) {
        return getReferenceFlowWidget().add(flowWidget);
    }

    public boolean removeReferenceFlowWidget(ReferenceFlowWidget flowWidget) {
        return getReferenceFlowWidget().remove(flowWidget);
    }

    @Override
    public ContextPaletteModel getContextPaletteModel() {
        return null;
    }

    /**
     * @return the referenceFlowWidget
     */
    public List<ReferenceFlowWidget> getReferenceFlowWidget() {
        return referenceFlowWidget;
    }

    @Override
    public void setName(String name) {
        if (StringUtils.isNotBlank(name)) {
            this.name = name.replaceAll("\\s+", "");
            if (this.getModelerScene().getModelerFile().isLoaded()) {
                updateName(this.name);
            }
        } else {
            setDefaultName();
        }
        validateName(this.name);
    }

    /**
     * Called when dev delete value
     */
    protected void setDefaultName() {
        if (!prePersistName()) {
            return;
        }
        this.name = evaluateName();
        if (this.getModelerScene().getModelerFile().isLoaded()) {
            updateName(null);
        }
        setLabel(name);
    }

    abstract protected String evaluateName();

    abstract protected void updateName(String newName);

    /**
     * Listener called before persistence event of the name, useful in case to
     * skip process
     */
    protected boolean prePersistName() {
        return true;
    }

    /**
     * Called when value changed by property panel Override it if multiple name
     * property available
     */
    protected void setPropertyName(String name) {
        if (!prePersistName()) {
            return;
        }
        this.name = name;
        validateName(name);
        setLabel(name);
    }

    protected void setMultiPropertyName(String name) {
        if (!prePersistName()) {
            return;
        }
        this.name = evaluateName();
        validateName(name);
        setLabel(name);
    }

    protected void validateName(String name) {
        if (SQLKeywords.isSQL99ReservedKeyword(name)) {
            this.getErrorHandler().throwError(AttributeValidator.ATTRIBUTE_COLUMN_NAME_WITH_RESERVED_SQL_KEYWORD);
        } else {
            this.getErrorHandler().clearError(AttributeValidator.ATTRIBUTE_COLUMN_NAME_WITH_RESERVED_SQL_KEYWORD);
        }

        DBTable tableSpec = (DBTable) this.getTableWidget().getBaseElementSpec();
        if (tableSpec.findColumns(name).size() > 1) {
            errorHandler.throwError(AttributeValidator.NON_UNIQUE_ATTRIBUTE_NAME);
        } else {
            errorHandler.clearError(AttributeValidator.NON_UNIQUE_ATTRIBUTE_NAME);
        }
    }

    protected void validateTableName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            if (SQLKeywords.isSQL99ReservedKeyword(name)) {
                errorHandler.throwError(AttributeValidator.ATTRIBUTE_TABLE_NAME_WITH_RESERVED_SQL_KEYWORD);
            } else {
                errorHandler.clearError(AttributeValidator.ATTRIBUTE_TABLE_NAME_WITH_RESERVED_SQL_KEYWORD);
            }
        } else {
            errorHandler.clearError(AttributeValidator.ATTRIBUTE_TABLE_NAME_WITH_RESERVED_SQL_KEYWORD);
        }
    }

//    @Override
//    protected List<JMenuItem> getPopupMenuItemList() {
//        List<JMenuItem> menuItemList = new LinkedList<>();
//
//        JMenuItem drive = new JMenuItem("Drive to Entity");
//        drive.addActionListener((ActionEvent e) -> {
//            DBColumn column = ColumnWidget.this.getBaseElementSpec();
//            Entity entity = table.getEntity();
//            ModelerFile modelerFile = ColumnWidget.this.getModelerScene().getModelerFile();
//            modelerFile = modelerFile.getParentFile();
//
//            Widget widget = (Widget) ((JPAModelerScene) modelerFile.getModelerScene()).getBaseElements().stream().filter(w -> w.getBaseElementSpec() == entity).findAny().get();
//            modelerFile.getModelerScene().setFocusedWidget(widget);
//
//            Rectangle visibleRect = modelerFile.getModelerScene().getView().getVisibleRect();
//            Rectangle widetRec = new Rectangle(widget.getLocation());
//            Rectangle sceneRec = widget.getScene().getBounds();
//
//            int x = 0, y = 0;
//            if (widetRec.y + visibleRect.height / 2 > sceneRec.height && widetRec.y + visibleRect.height / 2 < sceneRec.height) {
//                System.out.println("Center Vertcal");
//                y = widetRec.y - visibleRect.height / 2;
//            } else if (widetRec.y + visibleRect.height / 2 > sceneRec.height) {
//                System.out.println("Bottom");
//                y = sceneRec.height;
//            } else if (widetRec.y + visibleRect.height / 2 < sceneRec.height) {
//                System.out.println("Top");
//                y = 0;
//            }
//
//            if (widetRec.x + visibleRect.width / 2 > sceneRec.width && widetRec.x + visibleRect.width / 2 < sceneRec.width) {
//                System.out.println("Center Horizontal");
//                x = widetRec.x - visibleRect.width / 2;
//            } else if (widetRec.x + visibleRect.width / 2 > sceneRec.width) {
//                System.out.println("Right");
//                x = sceneRec.width;
//            } else if (widetRec.x + visibleRect.width / 2 < sceneRec.width) {
//                System.out.println("Left");
//                x = 0;
//            }
//
//            NODE_WIDGET_SELECT_PROVIDER.select(widget, null, false);
//            modelerFile.getModelerScene().getView().scrollRectToVisible(new Rectangle(x, y, widget.getBounds().width, widget.getBounds().height));
//            JPAFileActionListener.open(modelerFile);
//
//        });
//
//        menuItemList.add(0, drive);
//
//        menuItemList.add(getPropertyMenu());
//
//        return menuItemList;
//    }

}
