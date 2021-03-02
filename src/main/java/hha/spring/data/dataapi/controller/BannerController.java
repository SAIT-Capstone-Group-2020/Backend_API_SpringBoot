package hha.spring.data.dataapi.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import hha.spring.data.dataapi.domain.Banner;
import hha.spring.data.dataapi.domain.BannerItem;
import hha.spring.data.dataapi.domain.Category;
import hha.spring.data.dataapi.domain.Product;
import hha.spring.data.dataapi.service.BannerService;

public class BannerController {

    @Autowired
    private BannerService bannerService;

    @GetMapping("/api/admin/banner")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<BannerItem> get(){
        return bannerService.getAllBanner();
    }

    @PostMapping("/api/admin/addBanner")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProduct(@RequestBody Banner banner,
                             HttpServletRequest request,MultipartFile upload) throws IllegalStateException, IOException {

        String path=request.getSession().getServletContext().getRealPath("/uploads/");

        File file=new File(path);

        if(!file.exists()) {
            file.mkdirs();
        }

        String fileName=upload.getOriginalFilename();

        Banner check = null;
        check = bannerService.findByUrl(new StringBuffer(path).append(fileName).toString());

        if(check != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Banner already exists");
        }

        upload.transferTo(new File(path,fileName));
        banner.setUrl(new StringBuffer(path).append(fileName).toString());

        bannerService.addBanner(banner);

        return path;

    }


    @PostMapping("/api/admin/banner/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> update(@RequestBody Banner banner, @PathVariable int id) {
        try {
            Banner check = bannerService.getBannerById(id);
            banner.setId(id);
            bannerService.saveBanner(banner);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
