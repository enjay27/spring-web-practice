package com.ssafy.web.controller;

import com.ssafy.web.component.product.ProductComponent;
import com.ssafy.web.dto.FileDto;
import com.ssafy.web.dto.MemberDto;
import com.ssafy.web.dto.ProductDto;
import com.ssafy.web.dto.ProductFileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ProductController {

    @Autowired
    ServletContext servletContext;

    @Autowired
    ProductComponent productComponent;

    @GetMapping("/products")
    public ModelAndView products(@RequestParam(value = "name", required = false) String name) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<ProductFileDto> productFileDtos = productComponent.searchProduct(name);
        modelAndView.setViewName("product/list");
        modelAndView.addObject("products", productFileDtos);
        return modelAndView;
    }

    @PostMapping("/products")
    public String addProducts(ProductDto productDto, @RequestParam("upfile") MultipartFile[] files,
                              HttpSession session) throws Exception {
        MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
        productDto.setMemberId(memberDto.getId());

        ProductFileDto productFileDto = new ProductFileDto(productDto);
        if (!files[0].isEmpty()) { // 파일을 하나라도 올렸다면
            String realPath = servletContext.getRealPath("/upload"); // 서버의 디렉터리를 얻기 위해 ServletContext 가 필요하다
            File folder = new File(realPath);
            if (!folder.exists()) // 처음엔 폴더가 없으니까
                folder.mkdirs(); // 만든다
            List<FileDto> fileDtos = new ArrayList<>();
            for (MultipartFile mfile : files) {
                FileDto fileInfoDto = new FileDto();
                String originalFileName = mfile.getOriginalFilename(); // MultipartFile 객체에서 파일의 실제 이름을 얻어온다
                if (!originalFileName.isEmpty()) {
                    String saveFileName = UUID.randomUUID() // 유니크값
                            + originalFileName.substring(originalFileName.lastIndexOf('.'));
                    fileInfoDto.setPath(realPath);
                    fileInfoDto.setFileName(originalFileName);
                    fileInfoDto.setSaveName(saveFileName);
                    mfile.transferTo(new File(folder, saveFileName)); // 해당 디렉터리에 전송
                }
                fileDtos.add(fileInfoDto);
            }
            productFileDto.setFiles(fileDtos);
        }
        productComponent.addProduct(productFileDto);

        return "product/list";
    }

    @GetMapping("products/modify")
    public String modify(@RequestParam("isbn") String isbn, RedirectAttributes redirectAttributes, HttpSession session) throws Exception {
        ProductDto productDto = productComponent.findProduct(isbn);
        if (productDto != null) {
            productComponent.modifyProduct(productDto);
            redirectAttributes.addFlashAttribute("msg", "상품 수정 성공");
        } else {
            redirectAttributes.addFlashAttribute("msg", "상품 수정 실패");
        }
        return "redirect:/product/list";
    }

    @GetMapping("products/delete")
    public String delete(@RequestParam("isbn") String isbn, RedirectAttributes redirectAttributes, HttpSession session) throws Exception {
        ProductDto productDto = productComponent.findProduct(isbn);
        if (productDto != null) {
            productComponent.deleteProduct(productDto);
            redirectAttributes.addFlashAttribute("msg", "상품 삭제 성공");
        } else {
            redirectAttributes.addFlashAttribute("msg", "상품 삭제 실패");
        }
        return "redirect:/product/list";
    }

    @GetMapping("products/post")
    public String register() {
        return "product/post";
    }
}
