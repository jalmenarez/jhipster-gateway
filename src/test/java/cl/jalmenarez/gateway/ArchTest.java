package cl.jalmenarez.gateway;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("cl.jalmenarez.gateway");

        noClasses()
            .that()
            .resideInAnyPackage("cl.jalmenarez.gateway.service..")
            .or()
            .resideInAnyPackage("cl.jalmenarez.gateway.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..cl.jalmenarez.gateway.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
