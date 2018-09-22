/**
 * Copyright 2013-2018 the original author or authors from the Jeddict project (https://jeddict.github.io/).
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
package io.github.jeddict.test;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.printer.PrettyPrinter;
import static io.github.jeddict.jcode.util.Constants.JAVA_EXT_SUFFIX;
import static io.github.jeddict.jpa.modeler.initializer.JPAModelerUtil.getEntityMapping;
import io.github.jeddict.jpa.spec.DefaultClass;
import io.github.jeddict.jpa.spec.Embeddable;
import io.github.jeddict.jpa.spec.Entity;
import io.github.jeddict.jpa.spec.EntityMappings;
import io.github.jeddict.jpa.spec.MappedSuperclass;
import io.github.jeddict.jpa.spec.bean.BeanClass;
import io.github.jeddict.jpa.spec.extend.JavaClass;
import io.github.jeddict.orm.generator.compiler.def.ClassDefSnippet;
import io.github.jeddict.orm.generator.service.BeanClassGenerator;
import io.github.jeddict.orm.generator.service.ClassGenerator;
import io.github.jeddict.orm.generator.service.DefaultClassGenerator;
import io.github.jeddict.orm.generator.service.EmbeddableGenerator;
import io.github.jeddict.orm.generator.service.EmbeddableIdClassGenerator;
import io.github.jeddict.orm.generator.service.EntityGenerator;
import io.github.jeddict.orm.generator.service.MappedSuperClassGenerator;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.StringReader;
import static java.nio.charset.StandardCharsets.UTF_8;
import org.apache.commons.io.IOUtils;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.openide.util.Utilities;

/**
 * Test flow :
 * <br>
 * - Parse the model
 * <br>
 * - Generates the source code
 * <br>
 * - Validate using java parser for compilation issue
 * <br>
 * - Compare with the existing source
 *
 * @author jGauravGupta
 */
public class BaseModelTest {

    protected void testModelerFile(String fileName) throws Exception {
        File file = Utilities.toFile(this.getClass().getResource(fileName).toURI());
        EntityMappings entityMappings = getEntityMapping(file);
        assertNotNull(entityMappings);
        generateClasses(entityMappings);
    }

    private void generateClasses(EntityMappings entityMappings) throws Exception {
        String packageName = this.getClass().getPackage().getName();
        for (Entity clazz : entityMappings.getEntity()) {
            testClass(clazz, new EntityGenerator(clazz, packageName), entityMappings);
        }
        for (MappedSuperclass clazz : entityMappings.getMappedSuperclass()) {
            testClass(clazz, new MappedSuperClassGenerator(clazz, packageName), entityMappings);
        }
        for (Embeddable clazz : entityMappings.getEmbeddable()) {
            testClass(clazz, new EmbeddableGenerator(clazz, packageName), entityMappings);
        }
        for (DefaultClass clazz : entityMappings.getDefaultClass()) {
            if (clazz.isEmbeddable()) {
                testClass(clazz, new EmbeddableIdClassGenerator(clazz, packageName), entityMappings);
            } else {
                testClass(clazz, new DefaultClassGenerator(clazz, packageName), entityMappings);
            }
        }
        for (BeanClass clazz : entityMappings.getBeanClass()) {
            testClass(clazz, new BeanClassGenerator(clazz, packageName), entityMappings);
        }
    }

    private void testClass(JavaClass javaClass, ClassGenerator generator, EntityMappings entityMappings) throws Exception {
        ClassDefSnippet classDef = generator.getClassDef();
        classDef.setJaxbSupport(entityMappings.getJaxbSupport());
        String newSource = classDef.getSnippet();
        assertNotNull(newSource);

        try {
            CompilationUnit newUnit = JavaParser.parse(newSource);
            assertNotNull(newUnit);

            PrettyPrinter prettyPrinter = new PrettyPrinter();
            newSource = prettyPrinter.print(newUnit);

            InputStream existingSourceStream = this.getClass().getResourceAsStream(javaClass.getClazz() + JAVA_EXT_SUFFIX);
            String existingSource = IOUtils.toString(existingSourceStream, UTF_8);

            CompilationUnit existingUnit = JavaParser.parse(existingSource);
            assertNotNull(existingUnit);

            existingSource = prettyPrinter.print(existingUnit);

            try (BufferedReader existingSourceReader = new BufferedReader(new StringReader(existingSource));
                    BufferedReader newSourceReader = new BufferedReader(new StringReader(newSource));) {

                String existingSourceLine;
                String newSourceLine;
                int lineNumber = 0;
                while ((existingSourceLine = existingSourceReader.readLine()) != null && (newSourceLine = newSourceReader.readLine()) != null) {
                   ++lineNumber;

                    assertEquals(existingSourceLine, newSourceLine,
                            "Class : " + javaClass.getClazz() + " failed"
                            + '\n'
                            + " Line number : " + lineNumber
                            + '\n'
                            + " existingSourceLine : " + '\n' + existingSourceLine
                            + '\n'
                            + " newSourceLine : " + '\n' + newSourceLine
                    );
                }
            }

            System.out.println("Class : " + javaClass.getClazz() + " passed");
        } catch (ParseProblemException ex) {
            fail(
                    "Class : "
                    + javaClass.getClazz()
                    + '\n'
                    + "---------------------"
                    + '\n'
                    + newSource
                    + '\n'
                    + "---------------------",
                    ex
            );
        }
    }

}
