package uz.jl.java_ee.container;

import uz.jl.java_ee.dao.AuthUserDao;
import uz.jl.java_ee.dao.BookDao;
import uz.jl.java_ee.dao.FileStorageDao;
import uz.jl.java_ee.service.AuthUserService;
import uz.jl.java_ee.service.BookService;
import uz.jl.java_ee.service.FileStorageService;

public class ApplicationContext {
    private static BookDao bookDao() {
        return new BookDao();
    }

    private static BookService bookService() {
        return new BookService(bookDao());
    }

    private static FileStorageDao fileStorageDao() {
        return new FileStorageDao();
    }

    private static FileStorageService fileStorageService() {
        return new FileStorageService(fileStorageDao());
    }

    private static AuthUserDao authUserDao() {
        return new AuthUserDao();
    }
    private static AuthUserService authUserService() {
        return new AuthUserService(authUserDao());
    }

    @SuppressWarnings("raw_types")
    public static <T> T getBean(Class<T> clazz) {
        return switch (clazz.getSimpleName()) {
            case "BookDao" -> (T) bookDao();
            case "BookService" -> (T) bookService();
            case "FileStorageService" -> (T) fileStorageService();
            case "FileStorageDao" -> (T) fileStorageDao();
            case "AuthUserDao" -> (T) authUserDao();
            case "AuthUserService" -> (T) authUserService();
            default -> throw new RuntimeException("Bean not found");
        };
    }
}
