package uz.java_ee.servlets.storage;

import uz.java_ee.dao.UploadsDao;
import uz.java_ee.domains.Uploads;
import uz.java_ee.exceptions.BadRequestException;
import uz.java_ee.service.FileStorageService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;


@WebServlet("/download")
public class FileDownloadServlet extends HttpServlet {

    private final static UploadsDao uploadDao = new UploadsDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filename = req.getParameter("filename");
        Uploads uploads = uploadDao.findByGeneratedName(filename).orElseThrow(() -> {
            throw new BadRequestException("File not found");
        });
        Path resolvedPath = FileStorageService.rootPath.resolve(filename);

        resp.setContentType(uploads.getContentType());
        FileInputStream fileInputStream = new FileInputStream(resolvedPath.toString());
        byte[] bytes = fileInputStream.readAllBytes();
        resp.getOutputStream().write(bytes);

    }
}
