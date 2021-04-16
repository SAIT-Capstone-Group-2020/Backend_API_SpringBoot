package hha.spring.data.dataapi.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Redirect error controller.
 */
@RestController
public class RedirectErrorController implements ErrorController {
    /**
     * Error.
     *
     * @param request  the request
     * @param response the response
     * @throws IOException the io exception
     */
    @GetMapping("/error")
    public void error(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (response.getStatus() == HttpServletResponse.SC_NOT_FOUND) {
            response.sendRedirect("/#/any");
            System.out.println(request.getRequestURI());
            System.out.println(request.getPathInfo());
        }
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
