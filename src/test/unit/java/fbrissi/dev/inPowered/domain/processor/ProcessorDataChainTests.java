package fbrissi.dev.inPowered.domain.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.File;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class ProcessorDataChainTests {

    @Test
    void processesEveryChainAndProducesTheExpectedAnswers() {
        ProcessorDataChain chain = ProcessorDataFactory.create("Bill McKnight", "Paul Robinson");

        chain.first().process("Bill McKnight, Male, 16/03/77");
        chain.first().process("Paul Robinson, Male, 15/01/85");
        chain.first().process("Gemma Lane, Female, 20/11/91");
        chain.first().process("Sarah Stone, Female, 20/09/80");
        chain.first().process("Wes Jackson, Male, 14/08/74");

        assertEquals("3", chain.countMales().getAnswer());
        assertEquals("Wes Jackson", chain.oldestPerson().getAnswer());
        assertEquals("2862", chain.oldestDays().getAnswer());
    }

    @Test
    void factoryIncludesEveryConcreteProcessorInTheClasspath() throws Exception {
        ProcessorDataChain chain = ProcessorDataFactory.create("Bill McKnight", "Paul Robinson");
        List<Class<?>> chainedProcessors = new ArrayList<>();
        ProcessorData current = chain.first();

        while (current instanceof AbstractProcessorData processor) {
            chainedProcessors.add(current.getClass());
            current = processor.next;
        }

        assertNull(current);
        assertEquals(findConcreteProcessorClasses(), Set.copyOf(chainedProcessors));
    }

    private Set<Class<?>> findConcreteProcessorClasses() throws Exception {
        String packageName = "fbrissi.dev.inPowered.domain.processor";
        String packagePath = packageName.replace('.', '/');
        Path packageDirectory = Path.of(ProcessorData.class.getProtectionDomain()
                .getCodeSource().getLocation().toURI()).resolve(packagePath);

        try (Stream<Path> classFiles = Files.walk(packageDirectory)) {
            return classFiles
                    .filter(path -> path.toString().endsWith(".class"))
                    .map(packageDirectory::relativize)
                    .map(path -> path.toString().substring(0, path.toString().length() - ".class".length()))
                    .map(path -> packageName + "." + path.replace(File.separatorChar, '.'))
                    .filter(name -> !name.contains("$"))
                    .map(this::loadClass)
                    .filter(this::isConcreteProcessor)
                    .collect(java.util.stream.Collectors.toSet());
        }
    }

    private Class<?> loadClass(String name) {
        try {
            return Class.forName(name, false, getClass().getClassLoader());
        } catch (ClassNotFoundException exception) {
            throw new IllegalStateException(exception);
        }
    }

    private boolean isConcreteProcessor(Class<?> type) {
        return ProcessorData.class.isAssignableFrom(type)
                && !type.isInterface()
                && !Modifier.isAbstract(type.getModifiers());
    }
}
