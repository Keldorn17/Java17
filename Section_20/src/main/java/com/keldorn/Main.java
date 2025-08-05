package main.java.com.keldorn;

import main.java.com.keldorn.util.Separator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        fileExceptions();
        fileAndPath();
        pathListings();
        fileListings();
        fileWalker();
    }

    private static void fileExceptions() {
        Separator.separator();
        System.out.println("Current Working Directory (cdw) = " +
                new File("").getAbsoluteFile());
        String filename = "files/testing.csv";

        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("I can't run unless this file exists");
            return;
        }
        System.out.println("I'm good to go.");

        for (File f : File.listRoots()) {
            System.out.println(f);
        }

        Path path = Paths.get("files/testing.csv");
        if (!Files.exists(path)) {
            System.out.println("2. I can't run unless this file exists");
            return;
        }
        System.out.println("2. I'm good to go.");
    }

    private static void testFile(String filename) {
        Path path = Paths.get(filename);
        FileReader reader = null;
        try {
//            List<String> lines = Files.readAllLines(path);
            reader = new FileReader(filename);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Maybe I'd log something either way...");
        }
        System.out.println("File exists and able to use as a resource");
    }

    private static void testFile2(String filename) {
        try (FileReader reader = new FileReader(filename)) {

        } catch (FileNotFoundException e) {
            System.out.println("File '" + filename + "' foes not exist");
            throw new RuntimeException(e);
        } catch (NullPointerException | IllegalArgumentException badData) {
            System.out.println("User has added bad data " + badData.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Something unrelated and unexpected happened");
        } finally {
            System.out.println("Maybe I'd log something either way...");
        }
        System.out.println("File exists and able to use as a resource");
    }

    private static void fileAndPath() {
        Separator.separator();
        useFile("files/testfile.txt");
        usePath("files/pathfile.txt");
    }

    private static void useFile(String fileName) {
        File file = new File(fileName);
        boolean fileExists = file.exists();
        System.out.printf("File '%s' %s%n", fileName,
                fileExists ? "exists." : "does not exist.");

        if (fileExists) {
            System.out.println("Deleting File: " + fileName);
            fileExists = !file.delete();
        }

        if (!fileExists) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Something went wrong.");
            }
            System.out.println("Created File: " + fileName);
            if (file.canWrite()) {
                System.out.println("Would write to file here");
            }
        }
    }

    private static void usePath(String fileName) {
        Path path = Path.of(fileName);
        boolean fileExists = Files.exists(path);
        System.out.printf("File '%s' %s%n", fileName,
                fileExists ? "exists." : "does not exist.");

        if (fileExists) {
            System.out.println("Deleting File: " + fileName);
            try {
                Files.delete(path);
                fileExists = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!fileExists) {
            try {
                Files.createFile(path);
                System.out.println("Created File: " + fileName);
                if (Files.isWritable(path)) {
                    Files.writeString(path, """
                            Here is some data,
                            For my file,
                            just to prove,
                            Using the Files class and path are better!""");
                }
                System.out.println("And I can read too");
                Separator.separator();
                Files.readAllLines(path).forEach(System.out::println);
            } catch (IOException e) {
                System.out.println("Something went wrong.");
            }
        }
    }

    private static void pathListings() {
        Separator.separator();
        Path path = Path.of("files/testing.txt");
//        printPathInfo(path);
        logStatement(path);
        extraInfo(path);
    }

    private static void printPathInfo(Path path) {
        System.out.println("Path: " + path);
        System.out.println("fileName = " + path.getFileName());
        System.out.println("parent = " + path.getParent());
        Path absolutePath = path.toAbsolutePath();
        System.out.println("Absolute Path = " + absolutePath);
        System.out.println("Absolute Path Root = " + absolutePath.getRoot());
        System.out.println("Root = " + path.getRoot());
        System.out.println("isAbsolute = " + path.isAbsolute());

        System.out.println(absolutePath.getRoot());
//        int i = 1;
//        var it = path.toAbsolutePath().iterator();
//        while (it.hasNext()) {
//            System.out.println(".".repeat(i++) + " " + it.next());
//        }

        int pathParts = absolutePath.getNameCount();
        for (int i = 0; i < pathParts; i++) {
            System.out.println(".".repeat(i + 1) + " " + absolutePath.getName(i));
        }
        Separator.separator();

    }

    private static void logStatement(Path path) {
        try {
            Path parent = path.getParent();
            if (!Files.exists(parent)) {
                Files.createDirectories(parent);
            }
            Files.writeString(path, Instant.now() +
                    ": hello file world\n", StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void extraInfo(Path path) {
        try {
            var atts = Files.readAttributes(path, "*");
            atts.entrySet().forEach(System.out::println);
            System.out.println(Files.probeContentType(path));
        } catch (IOException e) {
            System.out.println("Problem getting attributes");
        }
    }

    private static void fileListings() {
        Separator.separator();
        Path path = Path.of("");
        System.out.println("cwd = " + path.toAbsolutePath());

        try (Stream<Path> paths = Files.list(path)) {
            paths
                    .map(Main::listDir)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Separator.separator();
        try (Stream<Path> paths = Files.walk(path, 2)) {
            paths
//                    .filter(Files::isRegularFile)
                    .map(Main::listDir)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Separator.separator();
        try (Stream<Path> paths = Files.find(path, Integer.MAX_VALUE,
                (p, attr) -> attr.isRegularFile() && attr.size() > 300
        )) {
            paths
                    .map(Main::listDir)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        path = path.resolve(".idea");
        Separator.separatorWithHeader("Directory Stream");
        try (var dirs = Files.newDirectoryStream(path, "*.xml")) {
            dirs.forEach(d -> System.out.println(Main.listDir(d)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Separator.separatorWithHeader("Directory Stream");
        try (var dirs = Files.newDirectoryStream(path,
                p -> p.getFileName().toString().endsWith(".xml")
                && Files.isRegularFile(p) && Files.size(p) > 1000
                )) {
            dirs.forEach(d -> System.out.println(Main.listDir(d)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String listDir(Path path) {
        try {
            boolean isDir = Files.isDirectory(path);
            FileTime dateField = Files.getLastModifiedTime(path);
            LocalDateTime modDT = LocalDateTime.ofInstant(
                    dateField.toInstant(), ZoneId.systemDefault()
            );
            return "%tD %tT %-5s %12s %s"
                    .formatted(modDT, modDT, (isDir ? "<DIR>" : ""),
                            (isDir ? "" : Files.size(path)), path);
        } catch (IOException e) {
            System.out.println("Whoops! Something went wrong with " + path);
            return path.toString();
        }
    }

    private static void fileWalker() {
        Separator.separator();
        Path startingPath = Path.of("..");
        FileVisitor<Path> statsVisitor = new StatsVisitor(1);
        try {
            Files.walkFileTree(startingPath, statsVisitor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class StatsVisitor extends SimpleFileVisitor<Path> {
        private Path initialPath = null;
        private final Map<Path, Long> folderSizes = new LinkedHashMap<>();
        private int initialCount;
        private int printLevel;

        public StatsVisitor(int printLevel) {
            this.printLevel = printLevel;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Objects.requireNonNull(file);
            Objects.requireNonNull(attrs);
            folderSizes.merge(file.getParent(), 0L, (o, m) -> o += attrs.size());
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            Objects.requireNonNull(dir);
            Objects.requireNonNull(attrs);
            if (initialPath == null) {
                initialPath = dir;
                initialCount = dir.getNameCount();
            } else {
                int relativeLevel = dir.getNameCount() - initialCount;
                if (relativeLevel == 1) {
                    folderSizes.clear();
                }
                folderSizes.put(dir, 0L);
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            Objects.requireNonNull(dir);
//            if (exc != null)
//                throw exc;
            if (dir.equals(initialPath)) {
                return FileVisitResult.TERMINATE;
            }

            int relativeLevel = dir.getNameCount() - initialCount;
            if (relativeLevel == 1) {
                folderSizes.forEach((key, value) -> {
                    int level = key.getNameCount() - initialCount - 1;
                    if (level < printLevel) {
                        System.out.printf("%s[%s] - %,d bytes %n",
                                "\t".repeat(level), key.getFileName(), value);
                    }
                });
            } else {
                long folderSize = folderSizes.get(dir);
                folderSizes.merge(dir.getParent(), 0L, (o, n) -> o += folderSize);
            }
            return FileVisitResult.CONTINUE;
        }
    }
}
